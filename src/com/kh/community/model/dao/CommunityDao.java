package com.kh.community.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.admin.model.vo.Category;
import com.kh.common.model.vo.PageInfo;
import com.kh.community.model.vo.Community;
import com.kh.community.model.vo.*;

//import com.kh.board.model.vo.Attachment;
//import com.kh.board.model.vo.Category;
//import com.kh.board.model.vo.Reply;

public class CommunityDao {

	
	private Properties prop = new Properties();
	
	public CommunityDao() {
		
		String fileName = CommunityDao.class.getResource("/sql/community/community-mapper.xml").getPath();
		
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public int selectListCount(Connection conn, int categoryNo) {
		// SELECT => ResultSet => 근데 반환형은 int
		// 변수
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, categoryNo);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			 close(rset);
			 close(pstmt);
		}
		
		return listCount;
	}
	
	
	
	
public ArrayList<Community> selectList(Connection conn, PageInfo pi, int categoryNo) {
		
		// SELECT문 => RESULTSET => 여러행 이므로 ArrayList<Board>
		// 변수
		ArrayList<Community> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, categoryNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
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
			e.printStackTrace();
		} finally {
			 close(rset);
			 close(pstmt);
		}
		
		System.out.println(list);
		return list;
		
	}

public int increaseCount(Connection conn, int commNo) {
	// UPDATE 문 => 처리된 행의 갯수
	// 변수
	int result = 0;
	PreparedStatement pstmt = null;
	
	String sql = prop.getProperty("increaseCount");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, commNo);
		
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	return result;
}


	
public Community selectCommunity(Connection conn, int commNo) {
	
	// SELECT => ResultSet => PK 한행만 => Board 객체에
	Community c = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	String sql = prop.getProperty("selectCommunity");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1,commNo);
		
		rset = pstmt.executeQuery();
		if(rset.next()) {
			c= new Community(rset.getInt("COMM_NO"),
						 rset.getString("CATEGORY_NAME"),
						 rset.getString("COMM_TITLE"),
						 rset.getString("COMM_CONTENT"),
						 rset.getString("COMM_WRITER"),
						 rset.getDate("CREATE_DATE"));
			
		
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	return c;
}

public CoAttachment selectCoAttachment(Connection conn, int commNo) {
	// SELECT => ResultSet => 첨부파일 한개(일반게시판) => Attachment
	// 변수
	CoAttachment at = null;
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	
	String sql = prop.getProperty("selectCoAttachment");
	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, commNo);
		rset = pstmt.executeQuery();
		if(rset.next()) {
			at = new CoAttachment();
			at.setFileNo(rset.getInt("FILE_NO"));
			at.setOriginName(rset.getString("ORIGIN_NAME"));
			at.setChangeName(rset.getString("CHANGE_NAME"));
			at.setFilePath(rset.getString("FILE_PATH"));
			
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
			
	return at;
}


public ArrayList<Category> selectCategoryList(Connection conn) {
	// SELECT문 => ResultSet => 여러 행 => vo객체 리스트
	// 변수
	ArrayList<Category> list = new ArrayList();
	
	PreparedStatement pstmt = null;

	ResultSet rset = null;
	
	String sql = prop.getProperty("selectCategoryList");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			list.add(new Category(rset.getInt("CATEGORY_NO"),
								  rset.getString("CATEGORY_NAME")));
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	
	return list;
}

public int insertCommunity(Connection conn, Community c) {
	// insert문 => 처리된 행의 갯수
	int result = 0;
	PreparedStatement pstmt = null;
	
	String sql = prop.getProperty("insertCommunity");
	
	try {
		pstmt = conn.prepareStatement(sql);
		// COMM_TITLE, COMM_CONTENT, COMM_WRITER, CATEGORY_NO
		pstmt.setString(1, c.getCommTitle());
		pstmt.setString(2, c.getCommContent());
		pstmt.setString(3, c.getCommWriter());
		pstmt.setInt(4, Integer.parseInt(c.getCategory()));
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	
	return result;
}

public int insertCoAttachment(Connection conn, CoAttachment coat) {
	int result = 0;
	PreparedStatement pstmt = null;
	
	String sql = prop.getProperty("insertCoAttachment"); 
	
	try {
		pstmt = conn.prepareStatement(sql);
		// ORIGIN_NAME, CHANGE_NAME, FILE_PATH
		pstmt.setString(1, coat.getOriginName());
		pstmt.setString(2, coat.getChangeName());
		pstmt.setString(3, coat.getFilePath());
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	
	return result;
}





public int updateCommunity(Connection conn , Community c) {
	
	// UPDATE => 처리 된 행의 갯수 
	int result = 0;
	
	PreparedStatement pstmt = null;
	
	String sql = prop.getProperty("updateCommunity");
	
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  Integer.parseInt(c.getCategory()));
			pstmt.setString(2, c.getCommTitle());
			pstmt.setString(3, c.getCommContent());
			pstmt.setInt(4, c.getCommNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		
		
			return result;
			
		}



public int updateCoAttachment(Connection conn, CoAttachment coat) {
	
	int result = 0;
	
	PreparedStatement pstmt = null;
	
	String sql = prop.getProperty("updateCoAttachment");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,  coat.getOriginName());
		pstmt.setString(2,  coat.getChangeName());
		pstmt.setString(3,  coat.getFilePath());
		pstmt.setInt(4,  coat.getFileNo());
		
		result = pstmt.executeUpdate();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		
		close(pstmt);
	}
		
	return result;
	
}


public int insertNewCoAttachment(Connection conn , CoAttachment coat) {
	
	int result = 0;
	
	PreparedStatement pstmt = null;
	
	String sql = prop.getProperty("insertNewCoAttachment");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, coat.getRefCno());
		pstmt.setString(2, coat.getOriginName());
		pstmt.setString(3, coat.getChangeName());
		pstmt.setString(4, coat.getFilePath());

		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		
		close(pstmt);
	}
	return result;
}



public int deleteCommunity(Connection conn, int commNo) {
	
	int result = 0;
	PreparedStatement pstmt = null;
	String sql = prop.getProperty("deleteCommunity");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, commNo);
		
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	return result;
}

public ArrayList<Reply> selectReplyList(Connection conn, int commNo) {
	
	// select -> resultset -> ArrayList , while
	// 변수
	
	ArrayList<Reply> list = new ArrayList();
	
	PreparedStatement pstmt = null;
	
	ResultSet rset = null;
	
	String sql = prop.getProperty("selectReplyList");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1,  commNo);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			
			list.add(new Reply(rset.getInt("REPLY_NO"),
					           rset.getString("REPLY_CONTENT"),
					           rset.getString("REPLY_WRITER"),
					           rset.getString("CREATE_DATE")));
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

public int insertMemReply(Connection conn, Reply r) {
	
	
	// INSERT 문 => 처리된 행의 갯수
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  r.getReplyContent());
			pstmt.setInt(2,  r.getRefBno());
			pstmt.setString(3,  r.getReplyWriter());
			
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	
}

public int deleteReply(Connection conn, int replycommNo) {
	
	int result = 0;
	PreparedStatement pstmt = null;
	
	String sql = prop.getProperty("deleteReply");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, replycommNo);
		
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
