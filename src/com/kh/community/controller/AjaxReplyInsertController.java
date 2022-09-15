package com.kh.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.CommunityService;
import com.kh.community.model.vo.Reply;
import com.kh.user.model.vo.Hospital;
import com.kh.user.model.vo.Member;

/**
 * Servlet implementation class AjaxReplyInsertController
 */
@WebServlet("/rinsert.bo")
public class AjaxReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST => 인코딩
		
				request.setCharacterEncoding("UTF-8");
				
				// request로부터 값 뽑기
				String replyContent = request.getParameter("content");
				int commNo = Integer.parseInt(request.getParameter("cono"));
				String userId = request.getParameter("userId");
			
				
				 // vo 가공 =-> Reply
				 
				 Reply r = new Reply();
				 r.setRefBno(commNo);
				 r.setReplyContent(replyContent);
				 r.setReplyWriter(userId);
				 
				 // Service단 호출
				 
				 int result =  new CommunityService().insertReply(r);
				 	response.setContentType("text/html; charset=UTF-8");
				 if(result > 0) {
					 response.getWriter().print(result);
				 }else {
					 request.getSession().setAttribute("alertMsg", "댓글 수정 실패");
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
