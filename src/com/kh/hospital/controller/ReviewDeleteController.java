package com.kh.hospital.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.hospital.model.service.HospitalService;

/**
 * Servlet implementation class ReviewDeleteController
 */
@WebServlet("/reviewDelete.ho")
public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reviewNo = Integer.parseInt(request.getParameter("rno"));
		int hno = Integer.parseInt(request.getParameter("hno"));
		int cpage = Integer.parseInt(request.getParameter("cpage"));		
		
		int result = new HospitalService().deleteReview(reviewNo);
		
		HttpSession session = request.getSession();

		if(result > 0) { 
			session.setAttribute("alertMsg", "리뷰가 삭제되었습니다.");
		} else {
			session.setAttribute("alertMsg", "리뷰 삭제에 실패하셨습니다. 다시 시도해주세요.");
		}
		response.sendRedirect(request.getContextPath() + "/review.ho?hno=" + hno + "&cpage=" + cpage + "&crpage=1");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
