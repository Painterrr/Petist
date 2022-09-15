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
 * Servlet implementation class InquiryInsertController
 */
@WebServlet("/insert.in")
public class InquiryInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String category = request.getParameter("category");
		String userId = request.getParameter("userId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Inquiry i = new Inquiry();	
		i.setCategory(category);
		i.setUserId(userId);
		i.setInquiryTitle(title);
		i.setInquiryContent(content);
		
		int result = new UserService().insertInquiry(i);
		
		if(result>0) {
			request.getSession().setAttribute("alertMsg", "문의글이 등록되었습니다.");
			response.sendRedirect(request.getContextPath()+"/inquiry.in?memberId="+ userId +"&cpage=1");
		} else {
			request.getSession().setAttribute("alertMsg", "문의글이 등록 실패");
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
