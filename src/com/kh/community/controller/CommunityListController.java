package com.kh.community.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.community.model.service.CommunityService;
import com.kh.community.model.vo.Community;

/**
 * Servlet implementation class CommunityListController
 */
@WebServlet("/list.bo")
public class CommunityListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿼리스트링으로 요청 /jsp/list.bo?cpage=1   => GET방식 인코딩 x
		
				// 2) request객체로부터 값 뽑기
			int categoryNo = Integer.parseInt(request.getParameter("cno"));
			
				// ------------ 페이징 처리 -------------
				// 필요한 변수들 셋팅 
				int listCount; // 현재 일반게시판의 게시글 총 갯수 => BOARD로 부터 조회 COUNT(*)활용 (STATUS = 'Y')
				int currentPage; // 현재 페이지(즉, 사용자가 요청한 페이지) => request.getParameter("cpage");
				int pageLimit; // 페이지 하단에 보여질 페이징바의 최대 갯수 => 10개로 고정
				int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수 => 10개로 고정
				
				int maxPage; // 가장 마지막 페이지가 몇번 페이지 인지(총 페이지의 갯수)
				int startPage; // 페이지 하단에 보여질 페이징바의 시작 수
				int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
				
			
				listCount = new CommunityService().selectListCount(categoryNo);		
				currentPage = Integer.parseInt(request.getParameter("cpage"));
				pageLimit = 10;
				boardLimit = 10;
				maxPage = (int)Math.ceil((double)listCount / boardLimit);
				startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
				endPage = startPage + pageLimit -1;
		
				if(endPage > maxPage) {
					endPage = maxPage;
				}
			
				// Service단으로 토스 => 7개나 있다 => vo 클래스 만들어서 가공해서 넘기자 
				
				// 3) VO로 가공
				PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
										maxPage, startPage, endPage);
				
				// 4) Service로 토스
				ArrayList<Community> list = new CommunityService().selectList(pi, categoryNo);
				
				// 5) 응답뷰 지정
				request.setAttribute("list", list); // 우리가 실제로 조회한 페이지에 보일 10개의 게시글
				request.setAttribute("pi", pi); // 페이징바를 만들 때 필요한 변수
				
				// views/board/boardListView.jsp 화면으로 응답 => 포워딩
				request.getRequestDispatcher("views/community/CommunityListView.jsp").forward(request, response);
				
				
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
