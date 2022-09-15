package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.Hospital;
import com.kh.user.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.us")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.인코딩
		request.setCharacterEncoding("UTF-8");
		// 2.값 뽑기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 3. service단으로 토스
		Member loginUser = new UserService().loginUser(userId, userPwd);
		
		HttpSession session = request.getSession();
		
		if(loginUser == null) { // Member로 로그인 실패
			
			Hospital loginHosp = new UserService().loginHosp(userId, userPwd);
			
			
			
			if(loginHosp != null) { // Hospital로 로그인 성공
				
				switch(loginHosp.getStatus()) {
				case "Y" :  
					session.setAttribute("loginHosp", loginHosp);
					session.setAttribute("alertMsg", "로그인 성공!");
					response.sendRedirect(request.getContextPath());
					break;
				case "N" :
					session.setAttribute("alertMsg", "이미 탈퇴한 회원입니다.");
					request.getRequestDispatcher("views/user/login.jsp").forward(request, response);
					break;
				case "B" :
					session.setAttribute("alertMsg", "정지당한 회원입니다.");
					request.getRequestDispatcher("views/user/login.jsp").forward(request, response);
				}
				
			} else { // Member,Hospital 둘 다로 로그인 실패
				
				session.setAttribute("alertMsg", "로그인 실패!!");
				
				request.getRequestDispatcher("views/user/login.jsp").forward(request, response);
			
			}
		} else { // Member로 로그인 성공
			
			switch(loginUser.getStatus()) {
			case "Y" :  
				session.setAttribute("loginUser", loginUser);
				session.setAttribute("alertMsg", "로그인 성공!");
				response.sendRedirect(request.getContextPath());
				break;
			case "N" :
				session.setAttribute("alertMsg", "이미 탈퇴한 회원입니다.");
				request.getRequestDispatcher("views/user/login.jsp").forward(request, response);
				break;
			case "B" :
				session.setAttribute("alertMsg", "정지당한 회원입니다.");
				request.getRequestDispatcher("views/user/login.jsp").forward(request, response);
			}
			
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
