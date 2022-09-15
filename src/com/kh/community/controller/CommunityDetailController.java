package com.kh.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.CommunityService;
import com.kh.community.model.vo.CoAttachment;
import com.kh.community.model.vo.Community;

/**
 * Servlet implementation class CommunityDetailController
 */
@WebServlet("/detail.bo")
public class CommunityDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// DB로부터 조회
		
				// 2) 뽑기
				int commNo = Integer.parseInt(request.getParameter("ccno"));
				
				// 3) 가공 - 패스

				// 4) Service단으로 토스 - 조회수 증가
				CommunityService cService = new CommunityService();
				
				int result = cService.increaseCount(commNo);
				
				
				
				// 조회수가 성공적으로 증가되었다면  => board, attachment 조회
				if(result > 0) {
					// board 조회
					Community c = cService.selectCommunity(commNo);
					
					// attachment 조회
					CoAttachment coat = cService.selectCoAttachment(commNo);
					
					// 조회했던 b, at 넘기기
					request.setAttribute("c", c);
					request.setAttribute("coat", coat);
					
					// 화면 => 포워딩
					request.getRequestDispatcher("views/community/CommunityDetailView.jsp").forward(request, response);
				
					
				} else {
					request.setAttribute("alertMsg", "응 그런거 없어~");
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
