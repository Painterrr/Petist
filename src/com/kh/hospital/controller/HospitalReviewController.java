package com.kh.hospital.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.hospital.model.service.HospitalService;
import com.kh.hospital.model.vo.HospitalInfo;
import com.kh.hospital.model.vo.HospitalReview;

/**
 * Servlet implementation class HospitalDetailController
 */
@WebServlet("/review.ho")
public class HospitalReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalReviewController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int hospitalNo = Integer.parseInt(request.getParameter("hno"));
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		HospitalInfo hi = new HospitalService().selectHospital(hospitalNo);
		Attachment at = new HospitalService().selectAttachment(hospitalNo);
		
		int listCount = new HospitalService().selectReviewListCount(hospitalNo);
		int currentPage = Integer.parseInt(request.getParameter("crpage"));
		int pageLimit = 5;
		int boardLimit = 5;
		
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
				maxPage, startPage, endPage);
		
		ArrayList<HospitalReview> list = new HospitalService().selectReviewList(pi, hospitalNo);
				
		request.setAttribute("list", list);
		request.setAttribute("at", at);
		request.setAttribute("pi", pi);
		request.setAttribute("cpage", cpage);
		request.setAttribute("hi", hi);
		request.getRequestDispatcher("views/hospital/hospitalReviewView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
