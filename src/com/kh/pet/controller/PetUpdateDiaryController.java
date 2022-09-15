package com.kh.pet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.pet.model.service.PetService;
import com.kh.pet.model.vo.Diary;

/**
 * Servlet implementation class PetUpdateDiaryController
 */
@WebServlet("/updatediary.pe")
public class PetUpdateDiaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetUpdateDiaryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int dno = Integer.parseInt(request.getParameter("dno"));
		int pno = Integer.parseInt(request.getParameter("pno"));
		int petWeight = Integer.parseInt(request.getParameter("petWeight"));
		int petWalk = Integer.parseInt(request.getParameter("petWalk"));
		int petFood = Integer.parseInt(request.getParameter("petFood"));
		String petPee = request.getParameter("petPee");
		String petRecord = request.getParameter("petRecord");

		Diary d = new Diary();
		d.setDiaryNo(dno);
		d.setPetWeight(petWeight);
		d.setPetWalk(petWalk);
		d.setPetFood(petFood);
		d.setPetPee(petPee);
		d.setPetRecord(petRecord);
		
		int result = new PetService().updateDiary(d);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "수정되었습니다!");
			response.sendRedirect(request.getContextPath() + "/calender.pe?pno=" + pno);
		}else {
			request.getSession().setAttribute("alertMsg", "수정실패!!");
			response.sendRedirect(request.getContextPath() + "/calender.pe?pno=" + pno);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
