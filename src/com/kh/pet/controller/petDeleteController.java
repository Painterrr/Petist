package com.kh.pet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.pet.model.service.PetService;
import com.kh.user.model.vo.Member;

/**
 * Servlet implementation class petDeleteController
 */
@WebServlet("/detelepet.pe")
public class petDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public petDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String petName = request.getParameter("petName");
		String memberPwd = request.getParameter("memberPwd");
		
		HttpSession session = request.getSession();
		String memberId = ((Member)session.getAttribute("loginUser")).getMemberId();
		
		int result = new PetService().deletePet(petName, memberPwd, memberId);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "삭제되었습니다!");
			response.sendRedirect(request.getContextPath() + "/list.pe");
			
		} else { 
			request.getSession().setAttribute("alertMsg", "삭제에 실패하였습니다.");
			response.sendRedirect(request.getContextPath() + "/list.pe");
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
