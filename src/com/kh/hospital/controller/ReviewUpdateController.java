package com.kh.hospital.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.hospital.model.service.HospitalService;
import com.kh.hospital.model.vo.HospitalReview;

/**
 * Servlet implementation class ReviewInsertController
 */
@WebServlet("/reviewUpdate.ho")
public class ReviewUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		int hospitalNo = Integer.parseInt(request.getParameter("hospitalNo"));
		String userId = request.getParameter("userId");
		int petNo = Integer.parseInt(request.getParameter("petNo"));
		String petKind = request.getParameter("petKind");
		String petBreed = request.getParameter("petBreed");
		int petAge = (request.getParameter("petAge") != null) ? Integer.parseInt(request.getParameter("petAge")) : 0;
		String clinicDate = request.getParameter("clinicDate");
		String clinicInfo = request.getParameter("clinicInfo");
		int price = Integer.parseInt(request.getParameter("price"));
		String content = request.getParameter("content");
		
		HospitalReview hr = new HospitalReview(reviewNo, hospitalNo, userId, petNo, petKind, petBreed, petAge, clinicDate, clinicInfo, price, content);
		
		int result = new HospitalService().updateReview(hr);

		HttpSession session = request.getSession();
		
		if(result > 0) { 
			session.setAttribute("alertMsg", "????????? ????????????????????? ~ !");
		} else { 
			session.setAttribute("alertMsg", "?????? ????????? ?????????????????????. ?????? ??????????????????.");	
		}
		
		response.sendRedirect(request.getContextPath() + "/review.ho?hno=" + hospitalNo + "&cpage=" + request.getParameter("cpage") + "&crpage=1");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
