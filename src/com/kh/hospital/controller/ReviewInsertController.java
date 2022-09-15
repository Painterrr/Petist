package com.kh.hospital.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.hospital.model.service.HospitalService;
import com.kh.common.model.vo.Attachment;
import com.kh.hospital.model.vo.HospitalReview;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ReviewInsertController
 */
@WebServlet("/reviewInsert.ho")
public class ReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int hospitalNo = Integer.parseInt(request.getParameter("hospitalNo"));
		String userId = request.getParameter("userId");
		int petNo = Integer.parseInt(request.getParameter("petNo"));
		String petKind = request.getParameter("petKind");
		String petBreed = request.getParameter("petBreed");
		int petAge = Integer.parseInt(request.getParameter("petAge"));
		String clinicDate = request.getParameter("clinicDate");
		String clinicInfo = request.getParameter("clinicInfo");
		int price = Integer.parseInt(request.getParameter("price"));
		String content = request.getParameter("content");
		
		HospitalReview hr = new HospitalReview(hospitalNo, userId, petNo, petKind, petBreed, petAge, clinicDate, clinicInfo, price, content);
		
		int result = new HospitalService().insertReview(hr);

		HttpSession session = request.getSession();
		
		if(result > 0) { 
			session.setAttribute("alertMsg", "리뷰가 등록되었습니다 ~ !");
		} else { 
			session.setAttribute("alertMsg", "리뷰 등록에 실패하셨습니다. 다시 시도해주세요.");	
		}
		
		response.sendRedirect(request.getContextPath() + "/review.ho?hno=" + hospitalNo + "&cpage=" + request.getParameter("cpage") + "&crpage=1");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
