package com.kh.hospital.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.hospital.model.service.HospitalService;
import com.kh.pet.model.vo.Animal;

/**
 * Servlet implementation class ReviewInsertController
 */
@WebServlet("/reviewEnrollFrom.ho")
public class ReviewEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewEnrollFormController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");	
		ArrayList<Animal> list = new HospitalService().selectPetInfoList(userId);

		request.setAttribute("hno", Integer.parseInt(request.getParameter("hno")));
		request.setAttribute("cpage", Integer.parseInt(request.getParameter("cpage")));
		request.setAttribute("hname", request.getParameter("hname"));
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/hospital/reviewEnrollForm.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
