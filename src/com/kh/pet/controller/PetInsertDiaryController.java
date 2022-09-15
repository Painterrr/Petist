package com.kh.pet.controller;


import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.pet.model.service.PetService;
import com.kh.pet.model.vo.Diary;


/**
 * Servlet implementation class PetInsertDiaryController
 */
@WebServlet("/insertdiary.pe")
public class PetInsertDiaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetInsertDiaryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String writeDate = request.getParameter("writeDate");
		int petWeight = Integer.parseInt(request.getParameter("petWeight"));
		int petWalk = Integer.parseInt(request.getParameter("petWalk"));
		int petFood = Integer.parseInt(request.getParameter("petFood"));
		String petPee = request.getParameter("petPee");
		String petRecord = request.getParameter("petRecord");
		int petNo = Integer.parseInt(request.getParameter("petNo"));

		Diary d = new Diary();
		d.setWriteDate(writeDate);
		d.setPetWeight(petWeight);
		d.setPetWalk(petWalk);
		d.setPetFood(petFood);
		d.setPetPee(petPee);
		d.setPetRecord(petRecord);
		d.setPetNo(petNo);
		
		int result = new PetService().insertDiary(d);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "등록되었습니다!");
			response.sendRedirect(request.getContextPath() + "/calender.pe?pno=" + petNo);

		}else {
			request.getSession().setAttribute("alertMsg", "날짜를 입력해주세요!!");
			response.sendRedirect(request.getContextPath() + "/calender.pe?pno=" + petNo);

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
