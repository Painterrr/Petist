package com.kh.pet.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.pet.model.service.PetService;
import com.kh.pet.model.vo.Animal;
import com.kh.user.model.vo.Member;

/**
 * Servlet implementation class PetListController
 */
@WebServlet("/list.pe")
public class PetListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String loginUser = ((Member)request.getSession().getAttribute("loginUser")).getMemberId();
		
		ArrayList<Animal> list = new PetService().selectPetList(loginUser);
		
		// request에 담기
		
		
		request.getSession().setAttribute("list", list);
		
		// 응답뷰 지정
		// views/board/thumbnailListView.jsp 파일 요청
		// => 포워딩
		request.getRequestDispatcher("views/pet/petListPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
