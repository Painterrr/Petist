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
import com.kh.hospital.model.vo.HospitalInfo;

/**
 * Servlet implementation class HospitalPageController
 */
@WebServlet("/hospitalPage.ho")
public class HospitalPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalPageController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hospId = request.getParameter("hospId");
		HospitalInfo hi = new HospitalService().selectHospitalById(hospId);
		request.setAttribute("hi", hi);
		
		if(hi != null) {
			Attachment at = new HospitalService().selectAttachment(hi.getHospNo());
			request.setAttribute("at", at);
		} 
		request.getRequestDispatcher("views/hospital/hospitalPageView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
