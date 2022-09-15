package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollFormController
 */
@WebServlet("/memberInsert.us")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST방식 인코딩 하기
		request.setCharacterEncoding("UTF-8");
		
		// 값 뽑기
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		String memberName = request.getParameter("memberName");
		String memberFno = request.getParameter("memberFno");
		String memberBno = request.getParameter("memberBno");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		
		
		// vo객체
		Member m = new Member(memberId, memberPwd, memberName, memberFno, memberBno, gender, phone, email);
		
		// Service단으로 토스
		int result = new UserService().insertMember(m);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "회원가입 성공! 로그인으로 이동합니다."); // 회원 가입 성공시 alertMsg에 추가
			response.sendRedirect(request.getContextPath()+"/loginEnroll.us");
		} else {
			request.getSession().setAttribute("alertMsg", "회원가입 실패! 조건에 맞게 입력해주세요.");
			request.getRequestDispatcher("views/user/login.jsp").forward(request, response);
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
