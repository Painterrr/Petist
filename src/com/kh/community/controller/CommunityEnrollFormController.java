package com.kh.community.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.vo.Category;
import com.kh.community.model.service.CommunityService;

/**
 * Servlet implementation class CommunityEnrollFormController
 */
@WebServlet("/enrollForm.bo")
public class CommunityEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// 작성 폼 띄우기
				// 작성 폼 안에 DB에서 조회한 CATRGORY목록을 SELECT태그의 OPTION으로 띄워줄꺼임
				ArrayList<Category> list = new CommunityService().selectCategoryList();
				
				request.setAttribute("list", list);
				
				
				request.getRequestDispatcher("views/community/CommunityEnrollForm.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
