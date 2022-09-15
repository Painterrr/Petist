package com.kh.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.admin.model.vo.InquiryReply;
import com.kh.user.model.vo.Member;

/**
 * Servlet implementation class AjaxReplyInsertController
 */
@WebServlet("/rinsert.in")
public class AjaxReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		String replyContent = request.getParameter("content");
		int inquiryNo = Integer.parseInt(request.getParameter("ino"));
	
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getMemberId();
		
		InquiryReply ir = new InquiryReply();
		ir.setReplyContent(replyContent);
		ir.setRefIno(inquiryNo);
		ir.setReplyWriter(userId);
		
		int result = new AdminService().insertReply(ir);
		
		response.setContentType("text/html; charset=UTF-8");
		
		response.getWriter().print(result);
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
