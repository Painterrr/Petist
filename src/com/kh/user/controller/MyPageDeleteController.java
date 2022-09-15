package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyPageDeleteController
 */
@WebServlet("/myPageDelete.us")
public class MyPageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") != null && session.getAttribute("loginHosp") == null) {
			
			// 개인회원 회원탈퇴로 
			request.getRequestDispatcher("views/user/MyPageDelete.jsp").forward(request, response);
		
		} else if(session.getAttribute("loginUser") == null && session.getAttribute("loginHosp") != null){
			
			// 병원 회원 탈퇴로
			request.getRequestDispatcher("views/user/MyPageHospDelete.jsp").forward(request, response);
			
		}else {
			session.setAttribute("alertMsg", "회원탈퇴 불가능!");
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
