package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.vo.Inquiry;
import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class InquiryUpdateController
 */
@WebServlet("/update.in")
public class InquiryUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int inquiryNo = Integer.parseInt(request.getParameter("inquiryNo"));
		String category = request.getParameter("category");
		String inquiryTitle = request.getParameter("title");
		String inquiryContent = request.getParameter("content");
		
		Inquiry i = new Inquiry();
		i.setInquiryNo(inquiryNo);
		i.setCategory(category);
		i.setInquiryTitle(inquiryTitle);
		i.setInquiryContent(inquiryContent);
		
		int result = new UserService().updateInquiry(i);
	
		if(result>0) {
			request.getSession().setAttribute("alertMsg", "문의글이 수정되었습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.in?ino=" + inquiryNo);
		} else {
			request.getSession().setAttribute("alertMsg", "문의글 수정 실패");
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
