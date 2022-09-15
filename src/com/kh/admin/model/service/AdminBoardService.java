package com.kh.admin.model.service;

import static com.kh.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.ArrayList;

import com.kh.admin.model.dao.AdminBoardDao;
import com.kh.community.model.vo.Community;

public class AdminBoardService {

	public ArrayList<Community> SearchBoard(String searchWord) {
		
		Connection conn = getConnection();
		System.out.println("searchBoardService작동");
		ArrayList<Community> list = new AdminBoardDao().SearchBoard(conn, searchWord);
		close(conn);
		
		return list;
	}
	
	public int deleteBoard(int commNo) {
		Connection conn = getConnection();
		System.out.println("deleteBoardService작동");
		int result = new AdminBoardDao().DeleteBoard(conn, commNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteCheckedBoard(String commNo) {
		Connection conn = getConnection();
		System.out.println("deleteCheckedBoard작동");
		int result = new AdminBoardDao().deleteCheckedBoard(conn, commNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
}
