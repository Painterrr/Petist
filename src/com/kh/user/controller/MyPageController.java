package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.vo.Member;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/myPage.us")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null && session.getAttribute("loginHosp")== null) {
			// 악의적 접근 막기
			session.setAttribute("alertMsg", "로그인 후 이용가능한 서비스입니다.");
		
			
		}else if(session.getAttribute("loginUser") != null){ 
			// 개인회원 마이페이지 보여주기
			request.getRequestDispatcher("views/user/MyPage.jsp").forward(request, response);
		}else if(session.getAttribute("loginHosp") != null) {
			// 병원회원 마이페이지 보여주기
			request.getRequestDispatcher("views/user/MyPageHospPwdUpdate.jsp").forward(request, response);
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
