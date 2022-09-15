package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		int cpage = 1;
		if(request.getParameter("cpage") == null) cpage = 1;
		else cpage = Integer.parseInt(request.getParameter("cpage"));
		
		//조회수 때문에 DB다녀오기 1차
		int result = new NoticeService().increaseCount(noticeNo);
		
		//update성공 후 상세조회 요청
		if(result > 0) {
			Notice n = new NoticeService().selectNotice(noticeNo);
			request.setAttribute("n", n);
			request.setAttribute("cpage", cpage);
			request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "공지사항 조회 실패");
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
