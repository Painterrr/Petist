package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class InquiryDeleteController
 */
@WebServlet("/delete.in")
public class InquiryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int inquiryNo = Integer.parseInt(request.getParameter("ino"));
		
		String userId = request.getParameter("userId");
		
		int result = new UserService().deleteInquiry(inquiryNo);
		
		if(result>0) {
			request.getSession().setAttribute("alertMsg", "문의글이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/inquiry.in?memberId="+userId+"&cpage=1");
		}else {
			request.getSession().setAttribute("alertMsg", "문의글이 삭제 실패");
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
