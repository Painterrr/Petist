package com.kh.pet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.pet.model.service.PetService;

/**
 * Servlet implementation class PetDeleteDiaryController
 */
@WebServlet("/deletediary.pe")
public class PetDeleteDiaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetDeleteDiaryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dno = Integer.parseInt(request.getParameter("dno"));
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		int result = new PetService().deleteDiary(dno);
		// 5) 결과에 따른 응답뷰 지정
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "삭제되었습니다!");
			response.sendRedirect(request.getContextPath() + "/calender.pe?pno=" + pno);

		}else {
			request.getSession().setAttribute("alertMsg", "삭제실패!!");
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
