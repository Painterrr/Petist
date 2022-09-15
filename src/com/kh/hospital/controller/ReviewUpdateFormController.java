package com.kh.hospital.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.Attachment;
import com.kh.hospital.model.service.HospitalService;
import com.kh.hospital.model.vo.HospitalReview;
import com.kh.pet.model.vo.Animal;

/**
 * Servlet implementation class ReviewUpdateFormController
 */
@WebServlet("/reviewUpdateForm.ho")
public class ReviewUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateFormController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");	
		ArrayList<Animal> list = new HospitalService().selectPetInfoList(userId);

		int reviewNo = Integer.parseInt(request.getParameter("rno"));
		int cpage = Integer.parseInt(request.getParameter("cpage"));
		int crpage = Integer.parseInt(request.getParameter("crpage"));
		
		HospitalReview hr = new HospitalService().selectReview(reviewNo);
		
		Attachment at = new HospitalService().selectAttachment(reviewNo);
		
		request.setAttribute("rno", reviewNo);
		request.setAttribute("hno", Integer.parseInt(request.getParameter("hno")));
		request.setAttribute("hname", request.getParameter("hname"));
		request.setAttribute("cpage", Integer.parseInt(request.getParameter("cpage")));
		request.setAttribute("crpage", Integer.parseInt(request.getParameter("crpage")));
		request.setAttribute("list", list);
		request.setAttribute("hr", hr);
		request.setAttribute("at", at);
		
		request.getRequestDispatcher("views/hospital/reviewUpdateForm.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
