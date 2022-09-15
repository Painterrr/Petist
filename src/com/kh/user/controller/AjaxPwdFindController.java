package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.mail.SendMail;
import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class AjaxCheckEmailController
 */
@WebServlet("/pwdToEmail.us")
public class AjaxPwdFindController extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doAction(request, response);
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doAction(request, response);
	   }
	   
	   protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("UTF-8");
	      response.setCharacterEncoding("UTF-8");
	      
         String email = request.getParameter("email");
         String userId = request.getParameter("userId");
         
         String userPwd = new UserService().pwdFind(email, userId);
         
         if(userPwd != null) { // DB에 해당 아이디, 이메일을 가진 회원이 존재o
        	 
	         SendMail sm = new SendMail();
	         String passWord = sm.pwdFind(email, userPwd);
	         
	         response.getWriter().append(passWord);
         }
         else {
        	
        	 response.getWriter().append("NO");
         }
	   }
}
