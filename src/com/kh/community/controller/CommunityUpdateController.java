package com.kh.community.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.community.model.service.CommunityService;
import com.kh.community.model.vo.CoAttachment;
import com.kh.community.model.vo.Community;
import com.kh.common.MyFileRenamePolicy;
import com.kh.community.model.vo.Community;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class CommunityUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		// 1) POST => 인코딩
		
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기 전 => 파일이 전송될것인가를 먼저 파악
		// multipart/form-date로 잘 전송되었을 때만 내용이 수행되게끔 조건을 먼저 걸어 주어야 함
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 파일 업로드 => 2가지 설정
			
			//1. 전송파일 용량 제한 int maxSize => 10Mbyte
			int maxSize = 1024 * 1024 * 10;
			
			// 2. 전달된 파일을 저장시킬 서버폴더의 물리적인 경로를 알아내기 String savePath
			String savePath = request.getSession().getServletContext().getRealPath("/resources/community/");
			
			// 전달된 파일명 수정 후 서버에 업로드
			// MultiRequest 객체를 생성함으로써 서버에 파일이 내려받아짐 
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8" , new MyFileRenamePolicy()); 
			
			// 공통적으로 수행 : Update Board
			// 2) 값 뽑기 request => multiRequest 
			int commNo = Integer.parseInt(multiRequest.getParameter("ccno"));
			String category = multiRequest.getParameter("category");
			String commTitle = multiRequest.getParameter("title");
			String commContent = multiRequest.getParameter("content");
			
			// 3) VO 가공 - Board와 관련
			
			Community c = new Community();
			
			c.setCommNo(commNo);
			c.setCategory(category);
			c.setCommTitle(commTitle);
			c.setCommContent(commContent);
			
			// Attachment와 관련된 것도 얼씨구 절씨구
			// Attachment객체 선언만 (null)
			// 실제 첨부파일이 있을경우에만 => 객체 생성
			// 없으면 null
			
			CoAttachment coat = null;
			
			// 새로운 첨부파일명을 리턴해주는 메소드를 이용해서 첨부파일이 있는지 확인
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				
				
				// 새로운 파일명이 존재한다면 객체 생성 후 원본 이름, 수정 된 이름, 파일 경로 지정
				
				coat = new CoAttachment();
				coat.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
				coat.setChangeName(multiRequest.getOriginalFileName("reUpfile"));
				coat.setFilePath("resources/community/");
				// => 여기까지는 새롭게 업로드한 새로운 첨부파일에 대한 내용
				
				// 첨부파일이 있을경우 + 원본파일도 있을 경우
				
				if(multiRequest.getParameter("originFileNo") != null) {
					
					// 기존 파일이 존재했다
					// 기존 파일에 대한 파일번호 at에 담을것 (where)
					coat.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
					// 기존에 서버에 존재하던 첨부파일 삭제
					new File(savePath + multiRequest.getParameter("originFileName")).delete();
					
					
				} else {
					
					// 새로운 첨부파일이 넘어왔지만 기존 파일이 없을경우 => INSERT
					// + 어느 게시글의 첨부파일인지 boardNo가 필요
					
					coat.setRefCno(commNo);
				}
	
			}
			
			
			// 4) Service 단으로 ~
			// 경우에 따라서 모두 한개의 트랜젝션으로 묶어야함 
			// Service 작성 전 경우의 수 처리
			// case 1 : 새로운 첨부파일이 없다 x -> b,  null => Board update 
			// case 2 : 새로운 첨부파일(o , 기존 첨부파일 o => Board update, Attachment update
			// case 3 : 새로운 첨부파일(o , 기존 첨부파일 x => Board update, attachment insert
			
			int result = new CommunityService().updateCommunity(c, coat);
			
			// 5) 결과에 따른 응답 뷰 지정
			
			if(result > 0 ) { // 성공 => 상세보기 페이지 이동
				
			request.getSession().setAttribute("alertMsg", "게시글이 수정되었습니다!");
			response.sendRedirect(request.getContextPath() + "/detail.bo?ccno=" + commNo);
			
			}else { /// 에러페이지 이동
				
				request.getSession().setAttribute("alertMsg", "게시글 수정 실패!");
				response.sendRedirect(request.getContextPath() + "/updateForm.bo?ccno=" + commNo);
			
				
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
