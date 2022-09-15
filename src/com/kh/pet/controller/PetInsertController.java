package com.kh.pet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.common.model.vo.Attachment;
import com.kh.pet.model.service.PetService;
import com.kh.pet.model.vo.Animal;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class PetInsertController
 */
@WebServlet("/insert.pe")
public class PetInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST -> 인코딩
		
		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 단계 전체 "첨부파일" => multipart/form-data => 조건제시 =>  곧바로 서버로 파일이 올라옴
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// MultipartRequest객체 생성
			// 객체 생성 전
			// 1_1. 전송 용량 제한(10Mbyte)
			int maxSize = 1024 * 1024 * 10;
			
			// 1_2. 저장할 서버의 물리적 경로 제시
			String savePath = request.getServletContext().getRealPath("/resources/pet/");
			
			// 2. MultipartRequest 객체 생성(*** 파일명 수정해서 올리기)
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 2) multiRequest로 부터 값 뽑기 => getParmeter 메소드이용
			String petKind = multiRequest.getParameter("petKind");
			String petOwner = multiRequest.getParameter("petOwner");
			String petName = multiRequest.getParameter("petName");
			String petInputNo = multiRequest.getParameter("petInputNo");
			String petBreed = multiRequest.getParameter("petBreed");
			String petBirth = multiRequest.getParameter("petBirth");
			String petNeutral = multiRequest.getParameter("petNeutral");
			
			// 3) VO로 가공
			// Animal
			Animal p = new Animal();
			p.setPetName(petName);
			p.setPetInputNo(petInputNo);
			p.setPetKind(petKind);
			p.setPetBreed(petBreed);
			p.setPetBirth(petBirth);
			p.setPetNeutral(petNeutral);
			p.setPetOwner(petOwner);
			
			
			Attachment at = new Attachment();
			if(multiRequest.getOriginalFileName("file") != null) { // 원본 파일이 존재한다
					
					// 첨부파일이 존재한다면 Attachment객체 생성
					// 필드 : 원본명, 수정명, 폴더경로, 파일레벨**(1: 대표)
				at.setOriginName(multiRequest.getOriginalFileName("file")); // 원본명
				at.setChangeName(multiRequest.getFilesystemName("file")); // 수정명
				at.setFilePath("resources/pet/"); // 경로명
				at.setFileLevel(1); // 파일레벨
			}
			
			
			
			// 4) Service단으로 토스
			int result = new PetService().insertPet(p, at);
			
			// 5) 결과에 따른 응답뷰 지정
			if(result > 0) { 
				
				request.getSession().setAttribute("alertMsg", "등록되었습니다!");
				response.sendRedirect(request.getContextPath() + "/list.pe");
				
			} else { // 실패 => 에러페이지
				request.getSession().setAttribute("alertMsg", "등록에 실패하였습니다!");
				response.sendRedirect(request.getContextPath() + "/list.pe");
			}
			
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
