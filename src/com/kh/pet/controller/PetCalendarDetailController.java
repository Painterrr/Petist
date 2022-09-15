package com.kh.pet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.pet.model.service.PetService;
import com.kh.pet.model.vo.Diary;

/**
 * Servlet implementation class PetCalendarDetailController
 */
@WebServlet("/detail.pe")
public class PetCalendarDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetCalendarDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectDate = request.getParameter("selectDate");
		int pno = Integer.parseInt(request.getParameter("pno"));
		Diary d = new PetService().selectDate(selectDate, pno);
		
		
		request.getSession().setAttribute("d", d);
		request.getSession().setAttribute("selectDate", selectDate);
		
		request.getRequestDispatcher("views/pet/petCalendarDetail.jsp").forward(request, response);


		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
