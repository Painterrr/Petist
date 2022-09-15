package com.kh.community.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.CommunityService;
import com.kh.community.model.vo.CoAttachment;
import com.kh.community.model.vo.Community;
import com.kh.admin.model.vo.Category;

/**
 * Servlet implementation class BoardUpdateFormController
 */
@WebServlet("/updateForm.bo")
public class CommunityUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 우선적으로 해야 할 일 : 카테고리 전체 조회해오기 
		
		ArrayList<Category> list = new CommunityService().selectCategoryList();
		
		
		
		// 해당 글 번호 뽑기(bno)
		int commNo = Integer.parseInt(request.getParameter("ccno"));
		
		// 글 번호에 해당하는 Board 테이블의 행 조회
		Community c = new CommunityService().selectCommunity(commNo);
		
		// 글 번호에 해당하는 Attachment테이블의 행을 조회
		
		CoAttachment coat = new CommunityService().selectCoAttachment(commNo);
		
		// 값 넘기기
		request.setAttribute("list", list);
		request.setAttribute("c", c);
		request.setAttribute("coat", coat);
		
		// 화면 먼저 띄워보기 => 포워딩
		request.getRequestDispatcher("views/community/CommunityUpdateForm.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
