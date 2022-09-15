package com.kh.user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.admin.model.vo.Category;
import com.kh.admin.model.vo.Inquiry;
import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class InquiryUpdateFormContoller
 */
@WebServlet("/updateForm.in")
public class InquiryUpdateFormContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryUpdateFormContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Category> list = new UserService().selectCategoryList();
		
		int inquiryNo = Integer.parseInt(request.getParameter("ino"));
	
		Inquiry i = new UserService().selectInquiry(inquiryNo);
		request.setAttribute("list", list);
		request.setAttribute("i", i);
		
		request.getRequestDispatcher("views/user/inquiryUpdateForm.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
