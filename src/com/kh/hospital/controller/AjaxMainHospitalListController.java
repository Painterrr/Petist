package com.kh.hospital.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.model.vo.PageInfo;
import com.kh.hospital.model.service.HospitalService;
import com.kh.hospital.model.vo.HospitalInfo;

/**
 * Servlet implementation class MainHospitalListController
 */
@WebServlet("/mianHospitalList.ho")
public class AjaxMainHospitalListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxMainHospitalListController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int listCount = 6;
		int currentPage = 1;
		int pageLimit = 1;
		int boardLimit = 6;
		
		int maxPage = 1;
		int startPage = 1;
		int endPage = 1;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
				maxPage, startPage, endPage);
		
		ArrayList<HospitalInfo> list = new HospitalService().selectList(pi, "");
		
		response.setContentType("application/json; charset=UTF-8");
		
		new Gson().toJson(list, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
