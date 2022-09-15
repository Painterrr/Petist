package com.kh.pet.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.pet.model.service.PetService;
import com.kh.pet.model.vo.Diary;

/**
 * Servlet implementation class PetDiaryListController
 */
@WebServlet("/diarylist.pe")
public class PetDiaryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetDiaryListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listCount; // 현재 일반게시판의 게시글 총 갯수 => BOARD로 부터 조회 COUNT(*)활용(STATUS = 'Y')
		int currentPage; // 현재 페이지(즉, 사용자가 요청한 페이지) => request.getParameter("cpage");
		int pageLimit; // 페이지하단에 보여질 페이징바의 최대 갯수 => 10개로 고정
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수 => 10개로 고정
		
		int maxPage; // 가장 마지막 페이지가 몇번 페이지 인지(총 페이지의 갯수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
		
		// * listCount : 총 게시글 갯수
		listCount = new PetService().selectDiaryListCount();
		
		// * currentPage : 현재페이지(== 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		// * pageLimit : 페이징바의 페이지 최대 갯수
		pageLimit = 10;
		
		// * boardLimit : 한 페이지에 보여질 게시글의 최대 갯수
		boardLimit = 10;
		
		// * maxPage : 가장 마지막 페이지가 몇 번 페이지인지(총 페이지 갯수)
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
				
	
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// * endPage : 페이지 하단에 보여질 페이징바의 끝수
		
		endPage = startPage + pageLimit - 1;
				
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		
		// 3) VO로 가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
									maxPage, startPage, endPage);
				
		// 4) Service로 토스
		ArrayList<Diary> diarylist = new PetService().selectDiaryList(pi, pno);
		
		// 5) 응답뷰 지정
		request.setAttribute("diarylist", diarylist); // 우리가 실제로 조회한 페이지에 보일 10개의 게시글
		request.setAttribute("pi", pi); // 페이징바를 만들 때 필요한 변수
	
		// views/board/boardDetailView.jsp 화면으로 응답 => 포워딩
		request.getRequestDispatcher("views/pet/petDiaryList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
