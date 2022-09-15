package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.Hospital;

/**
 * Servlet implementation class HospEnrollFormController
 */
@WebServlet("/hospInsert.us")
public class HospInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String hospId = request.getParameter("hospId");
		String hospPwd = request.getParameter("hospPwd");
		String hospName = request.getParameter("hospName");
		int hospCrn = Integer.parseInt(request.getParameter("hospCrn"));
		String hospEmail = request.getParameter("email");
		
		Hospital hosp = new Hospital(hospId, hospPwd, hospName, hospCrn, hospEmail);
		
		int result = new UserService().insertHospital(hosp);
		
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "회원가입 성공! 로그인으로 이동합니다.");
			response.sendRedirect(request.getContextPath()+"/loginEnroll.us");
		} else {
			request.getSession().setAttribute("alertMsg", "회원가입 실패! 조건에 맞게 입력해주세요.");
			request.getRequestDispatcher("views/user/login.jsp").forward(request, response);
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
