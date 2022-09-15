package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int listCount;//공지사항 게시글 총 개수(status = 'Y')
		int currentPage;//사용자 요청 페이지
		int pageLimit;//한 페이지에 보이는 페이징바 최대 개수
		int noticeLimit;//한 페이지에 보이는 게시글 최대 개수
		
		int maxPage;//총 페이지 개수
		int startPage;//페이징바 시작 수
		int endPage;//페이징바 끝 수

		//총 게시글 수
		listCount = new NoticeService().selectListCount();
		
		//사용자가 요청한 페이지
		if(request.getParameter("cpage") == null) currentPage = 1;
		else currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		//페이징바 최대 개수
		pageLimit = 10;
		
		//한 페이지에 보이는 게시글 최대 개수
		noticeLimit = 10;
		
		//총 페이지 개수
		maxPage = (int)Math.ceil((double)listCount/noticeLimit);
		
		//시작 페이지 숫자
		startPage = (currentPage - 1)/ pageLimit * pageLimit +1;
		
		//끝 페이지 수자
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, noticeLimit, maxPage, startPage, endPage);
		
		ArrayList<Notice> list = new NoticeService().selectList(pi);
		
		request.setAttribute("list", list);//페이지에 보일 게시글 10개
		request.setAttribute("pi", pi);//페이징바 만들 때 필요한 변수
		
		request.getRequestDispatcher("/views/notice/noticeListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
