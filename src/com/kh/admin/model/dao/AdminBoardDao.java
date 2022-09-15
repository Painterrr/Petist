package com.kh.admin.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.community.model.dao.CommunityDao;
import com.kh.community.model.vo.Community;

public class AdminBoardDao {

	private Properties prop = new Properties();
	
	public AdminBoardDao() {
		
		String fileName = CommunityDao.class.getResource("/sql/admin/admin-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**관리자 페이지 게시글 선택 조회
	 * @param conn
	 * @param searchWord
	 * @return
	 */
	public ArrayList<Community> SearchBoard(Connection conn, String searchWord) {
		
		ArrayList<Community> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		System.out.println("searchBoardDao작동");
		System.out.println("keyWord: " + searchWord);
		String sql = prop.getProperty("searchBoard");
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Community(rset.getInt("COMM_NO"),
										rset.getString("COMM_TITLE"),
										rset.getString("COMM_WRITER"),
										rset.getInt("COMM_COUNT"),
										rset.getString("CATEGORY_NAME"),
										rset.getDate("CREATE_DATE")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		return list;
	}
	
	/** 관리자 페이지 게시글 선택 삭제
	 * @param conn
	 * @param commNo 게시글 번호
	 * @return
	 */
	public int DeleteBoard(Connection conn, int commNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println("deleteBoardDao작동");
		System.out.println("commNo: " + commNo);
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteCheckedBoard(Connection conn, String commNo) {
		int result = 0;

		PreparedStatement pstmt = null;
		System.out.println("deleteCheckedBoard작동");
		String sql = prop.getProperty("deleteCheckedBoard");
		System.out.println(commNo);
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, commNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
