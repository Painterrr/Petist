package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.mail.SendMail;

/**
 * Servlet implementation class AjaxCheckEmailController
 */
@WebServlet("/certificationEmail.us")
public class AjaxCheckEmailController extends HttpServlet {
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
         System.out.println(email);
         SendMail sm = new SendMail();
         int randomNumber = sm.compare(email);
         
         response.getWriter().append(String.valueOf(randomNumber));
	   }
}
