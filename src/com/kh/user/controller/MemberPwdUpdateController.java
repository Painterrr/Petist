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
 * Servlet implementation class MemberPwdUpdateController
 */
@WebServlet("/updatePwdMember.us")
public class MemberPwdUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("newPwd");
			
			Member updateMemPwd = new UserService().updateMemberPwd(memberId, memberPwd);
		
			if(updateMemPwd == null) { // 비밀번호 변경 실패!
				request.getSession().setAttribute("alertMsg", "비밀번호 변경 실패!");
			} else {
				request.getSession().setAttribute("loginUser", updateMemPwd);
				request.getSession().setAttribute("alertMsg", "비밀번호가 변경되었습니다. 다시 로그인 해주세요.");
				response.sendRedirect(request.getContextPath()+"/loginEnroll.us");
				
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
