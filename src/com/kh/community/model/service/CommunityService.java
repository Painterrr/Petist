package com.kh.community.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.community.model.dao.CommunityDao;
import com.kh.community.model.vo.CoAttachment;
import com.kh.community.model.vo.Community;
import com.kh.community.model.vo.Reply;
import com.kh.admin.model.vo.Category;
import com.kh.common.model.vo.PageInfo;
//import com.kh.board.model.vo.Reply;
//import com.kh.board.model.vo.Category;
//import com.kh.community.model.vo.Attachment;


public class CommunityService {

	
public int selectListCount(int categoryNo) {
		
		Connection conn = getConnection();
		
		int listCount = new CommunityDao().selectListCount(conn, categoryNo);
		// SELECT문의 결과는 ResultSet
		// 상식적으로 생각해보면 게시글의 총 갯수는 정수형
		
		close(conn);
		
		return listCount;
	}
	
public ArrayList<Community> selectList(PageInfo pi, int categoryNo) {
	
	Connection conn = getConnection();
	
	ArrayList<Community> list = new CommunityDao().selectList(conn, pi, categoryNo);
	
	close(conn);
	
	return list;


	}


public int increaseCount(int commNo) {
	Connection conn = getConnection();
	
	int result = new CommunityDao().increaseCount(conn, commNo);
	
	if(result > 0) commit(conn);
	else rollback(conn);
	
	close(conn);
	
	return result;
}


public Community selectCommunity(int commNo) {
	
	Connection conn = getConnection();
	
	Community c = new CommunityDao().selectCommunity(conn, commNo);
	close(conn);
	
	return c;
}

public CoAttachment selectCoAttachment(int commNo) {
	Connection conn = getConnection();
	
	CoAttachment coat = new CommunityDao().selectCoAttachment(conn, commNo);
	close(conn);
	
	return coat;
}

public ArrayList<Category> selectCategoryList() {
	Connection conn = getConnection();
	
	ArrayList<Category> list = new CommunityDao().selectCategoryList(conn);
	
	close(conn);
	
	return list;
}


public int insertCommunity(Community c, CoAttachment coat) {
	Connection conn = getConnection();
	
	// board테이블에 INSERT 는 무조건!
	// 첨부파일은 null이 아닐 경우 Attachment테이블에 INSERT해주면 됨
	
	// 1) board테이블에 INSERT
	int result1 = new CommunityDao().insertCommunity(conn, c);
	
	// 2) attachment테이블에 INSERT
	int result2 = 1; // 첨부파일이 있다면 실행할 메소드 결과값을 담을 변수
	if(coat != null) {
		result2 = new CommunityDao().insertCoAttachment(conn, coat);
	}
	
	// 3) 트랜잭션 처리
	// result1도 성공이고 그리고 result2도 성공일 때 commit
	// 둘중에 하나라도 실패했다?? 무조건 rollback
	// result1 > 0 && result2 > 0
	if(result1 * result2 > 0) {
		commit(conn);
	} else {
		rollback(conn);
	}
	close(conn);
	
	return (result1 * result2);
}


public int updateCommunity(Community c , CoAttachment coat) {
	
	
	Connection conn = getConnection();
	
	int result1 = new CommunityDao().updateCommunity(conn, c);
	// Attachment 테이블과 관련된 결과물
	
	int result2 = 1;
	
	// 새롭게 첨부파일이 있을경우
	
	if(coat != null) {
		
		// 기존에 첨부파일이 있었을경우
		
		if(coat.getFileNo() != 0) {
			
			result2 = new CommunityDao().updateCoAttachment(conn, coat);
			
		} else {
			
			// 없었을 경우
			
			result2 = new CommunityDao().insertNewCoAttachment(conn, coat);
		}
	}
	
	
	if(result1 * result2 > 0) {
		
		commit(conn);
	}else {
		
		rollback(conn);
	}
	
	close(conn);
	
	return (result1 * result2);
}

public int deleteCommunity(int commNo) {
	
	Connection conn = getConnection();
	
	int result = new CommunityDao().deleteCommunity(conn, commNo);
	
	if(result > 0) commit(conn);
	else rollback(conn);
	
	close(conn);
	
	return result;
}


public ArrayList<Reply> selectReplyList(int commNo){
	
	
	Connection conn = getConnection();
	
	ArrayList<Reply> list = new CommunityDao().selectReplyList(conn, commNo);
	
	close(conn);
	
	return list;
}




public int insertReply(Reply r) {
	
	Connection conn = getConnection();
	
	int result = new CommunityDao().insertMemReply(conn,r);	
	if(result > 0) {
		commit(conn);
	} else {
		rollback(conn);
	}
	close(conn);
	return result;
}


public int deleteReply(int replycommNo) {
	
	Connection conn = getConnection();
	
	int result = new CommunityDao().deleteReply(conn, replycommNo);
	
	if(result > 0) {
		
		commit(conn);
		
	}
	else {
		rollback(conn);
		
	}
	
	return result;
	
}


}
