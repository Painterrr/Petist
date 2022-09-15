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
import com.kh.common.model.vo.Attachment;
import com.kh.hospital.model.service.HospitalService;
import com.kh.hospital.model.vo.HospitalInfo;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class HospitalEnrollFormController
 */
@WebServlet("/hospitalInsert.ho")
public class HospitalInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalInsertController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 1024 * 1024 * 10;
			
			String savePath = request.getServletContext().getRealPath("/resources/hospital_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String hospitalId = multiRequest.getParameter("hospId");
			String hospitalName = multiRequest.getParameter("hospitalName");
			String address = multiRequest.getParameter("address");
			String addressDetail = multiRequest.getParameter("address_detail");
			double yCoordinate = Double.parseDouble(multiRequest.getParameter("y_coordinate"));
			double xCoordinate = Double.parseDouble(multiRequest.getParameter("x_coordinate"));
			String operatingTime = multiRequest.getParameter("operatingTime");
			String holiday = multiRequest.getParameter("holiday");
			String parking = multiRequest.getParameter("parking");
			String tel = multiRequest.getParameter("tel");
			String homepage = multiRequest.getParameter("homepage");
			String introduce = multiRequest.getParameter("introduce");
			
			HospitalInfo hi = new HospitalInfo(hospitalId, hospitalName
					, address, addressDetail, yCoordinate, xCoordinate, operatingTime
					, holiday, parking, tel, homepage, introduce);
						
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("hopitalPhoto") != null) {
				
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("hopitalPhoto"));
				at.setChangeName(multiRequest.getFilesystemName("hopitalPhoto"));
				at.setFilePath("resources/hospital_upfiles/");

			}
			
			
			int result = new HospitalService().insertHospital(hi, at);

			HttpSession session = request.getSession();
			
			if(result > 0) { 
				session.setAttribute("alertMsg", "병원 정보가 등록되었습니다.");
			} else { 
				session.setAttribute("alertMsg", "병원 정보 등록에 실패했습니다. 다시 시도해주세요. ");
			}
			
			response.sendRedirect(request.getContextPath() + "/hospitalPage.ho?hospId=" + hi.getHospId());
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
