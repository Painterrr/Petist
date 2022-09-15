package com.kh.pet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.pet.model.service.PetService;
import com.kh.pet.model.vo.Diary;

/**
 * Servlet implementation class PetUpdateDiaryFormController
 */
@WebServlet("/updatediaryform.pe")
public class PetUpdateDiaryFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetUpdateDiaryFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int dno = Integer.parseInt(request.getParameter("dno"));
		
		// 3) 가공
		
		// 4) Service단으로 토스
		// 상세조회시 selectNotice메소드를 재활용 => 호출만 하면 끝
		Diary diary = new PetService().selectDiary(dno);
		// n에 글번호, 글제목, 글내용, 작성자아이디, 작성일
		
		// 5) 응답 뷰 포워딩
		request.setAttribute("diary", diary);
		
		request.getRequestDispatcher("views/pet/petUpdateDiaryForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
