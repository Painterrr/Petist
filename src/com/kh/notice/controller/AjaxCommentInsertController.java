package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.NoticeComment;
import com.kh.user.model.vo.Member;

/**
 * Servlet implementation class CommentInsertController
 */
@WebServlet("/commentInsert.no")
public class AjaxCommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxCommentInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int RefNno = Integer.parseInt(request.getParameter("cmtNo"));
		String cmtContent = request.getParameter("content");
		String userId = request.getParameter("userId");
		
		int result = 0;
		
		//일반회원 객체
		NoticeComment nCmt = new NoticeComment();
		nCmt.setRefNno(RefNno);
		nCmt.setCmtContent(cmtContent);
		nCmt.setCmtWriter(userId);

		result = new NoticeService().insertComment(nCmt);

		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
