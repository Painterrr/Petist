package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.admin.model.vo.Inquiry;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class InquiryListController
 */
@WebServlet("/list.in")
public class InquiryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		      // 쿼리스트링으로 요청/jsp/list.in?cpage=1 => GET방식 인코딩X
		   
		      
		      // -- 페이징처리--
		      int listCount; // 현재 문의 게시글 총 갯수
		      int currentPage; // 현재페이지(즉, 사용자가 요청한 페이지)
		      int pageLimit; // 페이지하단에 보여질 페이징바의 최대 갯수
		      int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수
		      
		      int maxPage; //총 페이지 갯수
		      int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		      int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		      
		      listCount = new AdminService().selectListCount();

		      currentPage = Integer.parseInt(request.getParameter("cpage"));
		      //System.out.println(listCount); //14개 나옴 listCount값 있음
		      //System.out.println(currentPage); //1 나옴 currentPage값 있음
		      
		      pageLimit = 10;
		      
		      boardLimit = 10;
		      
		      maxPage = (int)Math.ceil((double)listCount / boardLimit);
		     // System.out.println(maxPage); //2나옴 maxPage값 있음
		      startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		     // System.out.println(startPage); //1나옴 startPage값있음
		      
		      endPage = startPage + pageLimit - 1;
		    //  System.out.println(endPage); //10나옴 endPage값 있음 
		      if(endPage > maxPage) {
		         endPage=maxPage;
		      }
		   
		      // 3) Vo로 가공 
		      PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		      //System.out.println(pi); //14,1,10,10,2,1,2
		      // 4) Service로 토스
		      ArrayList<Inquiry> list = new AdminService().selecInquiryList(pi);
		      //System.out.println(list); //값나옴
		      
		      // 5) 응답뷰 지정
		      request.setAttribute("list", list);
		      request.setAttribute("pi", pi);
		      //System.out.println(list);
		      
		      // views/admin/inquiryListView.jsp화면으로 응답 => 포워딩
		      request.getRequestDispatcher("/views/admin/inquiryListView.jsp").forward(request, response);
		      
		   }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
