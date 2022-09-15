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
 * Servlet implementation class HospitalDeleteController
 */
@WebServlet("/hospitalDelete.ho")
public class HospitalDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalDeleteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String hospId = request.getParameter("hospId");
		int hospNo = Integer.parseInt(request.getParameter("hno"));
		
		int result = new HospitalService().deleteHospital(hospNo);
				
		HttpSession session = request.getSession();
		
		if(result > 0) { 
			session.setAttribute("alertMsg", "병원 정보가 삭제되었습니다.");
		} else {
			session.setAttribute("alertMsg", "병원 정보 삭제에 실패했습니다.");
		}
		response.sendRedirect(request.getContextPath() + "/hospitalPage.ho?hospId=" + hospId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
