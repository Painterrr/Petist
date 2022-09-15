package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/updateMember.us")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
			// memberId  memberName  phone  memberPwd
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String phone = request.getParameter("phone");
		String memberPwd = request.getParameter("memberPwd");
		
		Member m = new Member(memberId, memberPwd, memberName, phone);
		
		   Member updateMem = new UserService().updateMember(m);
		   
		   HttpSession session = request.getSession();
		   if(updateMem != null) {
				session.setAttribute("loginUser", updateMem);
				session.setAttribute("alertMsg", "회원 정보가 성공적으로 수정되었습니다.");
				response.sendRedirect(request.getContextPath()+"/myPage.us");
		   } else {
			   session.setAttribute("alertMsg", "회원 정보 수정 실패!!!");
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
