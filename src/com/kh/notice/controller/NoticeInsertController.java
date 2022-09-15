package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");// admin
		String noticeTitle = request.getParameter("title");// "XXX"
		String noticeContent = request.getParameter("content");// "XXX"
		
		Notice n = new Notice();
		n.setNoticeWriter(memberId);
		n.setNoticetitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		
		int result = new NoticeService().insertNotice(n);
		
		if(result > 0) {
			request.getSession().setAttribute("alert", "공지사항 등록");
			response.sendRedirect(request.getContextPath() + "/list.no?cpage=1");
		} else {
			request.setAttribute("errorMsg", "공지사항 등록 실패");
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
