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
 * Servlet implementation class HospitalDetailController
 */
@WebServlet("/detail.ho")
public class HospitalDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalDetailController() {
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
		
		if(hi != null) {
			request.setAttribute("hi", hi);
			request.setAttribute("at", at);
			request.setAttribute("cpage", cpage);
			request.getRequestDispatcher("views/hospital/hospitalDetailView.jsp").forward(request, response);
		} else {
			request.setAttribute("cpage", cpage);
			request.setAttribute("alertMsg", "병원 상세조회에 실패했어요 ~ ㅜㅠ 다시 시도해주세요 ~");
			request.getRequestDispatcher("views/hospital/hospitalListView.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
