package com.kh.community.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.community.model.service.CommunityService;
import com.kh.community.model.vo.CoAttachment;
import com.kh.community.model.vo.Community;
import com.kh.user.model.vo.Hospital;
import com.kh.user.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class CommunityInsertController
 */
@WebServlet("/insert.bo")
public class CommunityInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩(POST)
		request.setCharacterEncoding("UTF-8");
		// 2) 값 뽑기
		//request.getParameter("userNo");  // null
		// form전송을 일반 방식이 아닌 multipart/form-data방식으로 전송하는 경우
		// request.getParameter로 값뽑기가 불가함
		// => multipart라는 객체에 값을 이관시켜서 다뤄야 한다.
		
		// 스텝 0) enctype이 multupart/form-data로 잘 전송되었을 경우
		// 전반적인 내용들이 수정되도록 조건을 걸어줌
		if(ServletFileUpload.isMultipartContent(request)) {

			int maxSize = 10 * 1024 * 1024; // 10Mbyte
		
			String savePath = request.getSession().getServletContext().getRealPath("/resources/community/");
			// 스텝 2) 서버에 업로드 작업(파일명 수정)
			MultipartRequest multiRequest = new MultipartRequest(request, 
									savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 값 뽑기 
			// 카테고리 번호, 제목, 내용, 게시글을 작성한 멤버의 회원번호 Board객체로 가공 => INSERT
			String category = multiRequest.getParameter("category");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			Member loginUser = (Member)request.getSession().getAttribute("loginUser");
			Hospital loginHosp = (Hospital)request.getSession().getAttribute("loginHosp");
			String memberName = null;
			// 개인 회원이 댓글 작성 시
			if( loginUser != null) {
			 String userName = loginUser.getMemberName();
			    memberName = userName;
			// 병원 회원 댓글 작성 시    
			} else if(loginHosp != null) {
				String hospName = loginHosp.getHospId();
				memberName = hospName;
			}
			
			// 3) vo객체로 가공 => 첫번째 INSERT문에 해당
			Community c = new Community();
			c.setCommTitle(title);
			c.setCommContent(content);
			c.setCommWriter(memberName);
			c.setCategory(category);
			
			// 두번째 INSERT => 조건(첨부파일이 있을 경우에만 INSERT)
			CoAttachment coat = null;
			
			// 4) Service단으로 토스 => 선택적으로 첨부파일 객체를 생성 후 토스(첨부파일 유무)
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				// 첨부파일이 있다 => vo객체로 가공
				coat = new CoAttachment();
				coat.setOriginName(multiRequest.getOriginalFileName("upfile")); //원본명
				
				// 수정파일명
				// multiRequest.getFileSystemName("키값");
				coat.setChangeName(multiRequest.getFilesystemName("upfile"));
				
				// 파일경로
				coat.setFilePath("/resources/community");	
			}	
			// 4. 서비스 요청
			int result = new CommunityService().insertCommunity(c, coat);
			
			if(result > 0) { //성공 => list.bo?cpage=1 요청
				
				request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/list.bo?cpage=1&cno="+category);	
			} else { // 실패
				
				// 첨부파일이 있었는데 실패하면 이미 업로드된 첨부파일을 굳이 서버에 보관할 필요가 없다(용량만 차지)
				if(coat != null) {
					// delete()호출 => 삭제시키고자 하는 파일 객체 생성
					new File(savePath + coat.getChangeName()).delete();
				}
					
				request.setAttribute("alertMsg", "게시글 작성 실패");
				request.getRequestDispatcher("views/community/CommunityListView.jsp").forward(request, response);
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
