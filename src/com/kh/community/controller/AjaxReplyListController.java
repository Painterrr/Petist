package com.kh.community.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.community.model.service.CommunityService;
import com.kh.community.model.vo.Reply;

/**
 * Servlet implementation class AjaxReplyListController
 */
@WebServlet("/rlist.bo")
public class AjaxReplyListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GET => 인코딩 x
		
		// 값뽑기
		int commNo = Integer.parseInt(request.getParameter("cono"));
		
		// VO 가공
		
		// Service단으로 토스~! => 게시판관련 boardService로
		
		ArrayList<Reply> list = new CommunityService().selectReplyList(commNo);
		
		// 응답
		// GSON 이용해보자 ! => ArrayList를 자바스크립의 배열 형태로 변환
		// 형식, 인코딩
		
		response.setContentType("application/json; charset=UTF-8");
		
		new Gson().toJson(list, response.getWriter());
		// => Gson에서 키값 지정안하면 필드명 == 키값
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
