package com.kh.notice.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;
import com.kh.notice.model.vo.NoticeComment;

public class NoticeService {

	//공지사항 전체조회
	public int selectListCount() {
		Connection conn = getConnection();
		int listCount = new NoticeDao().selectListCount(conn);
		close(conn);
		return listCount;
	}
	
	//공지사항 페이징처리
	public ArrayList<Notice> selectList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectList(conn, pi);
		close(conn);
		return list;
	}
	
	//공지사항 등록
	public int insertNotice(Notice n) {
		
		Connection conn = getConnection();
		int result = new NoticeDao().insertNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	//공지사항 상세조회 전 조회수 update
	public int increaseCount(int noticeNo) {
		Connection conn = getConnection();
		int result = new NoticeDao().increaseCount(conn, noticeNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	//공지사항 조회
	public Notice selectNotice(int noticeNo) {
		
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, noticeNo);
		
		close(conn);
		
		return n;	
	}
	
	//공지사항 수정
	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDao().updateNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	//공지사항 삭제
	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	//댓글 조회
	public ArrayList<NoticeComment> selectComment(int noticeNo) {
		Connection conn = getConnection();
		ArrayList<NoticeComment> list = new NoticeDao().selectComment(conn, noticeNo);
		close(conn);
		return list;
	}
	
	//댓글 등록
	public int insertComment(NoticeComment nCmt) {
		Connection conn = getConnection();
		int result = new NoticeDao().insertComment(conn, nCmt);

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int DeleteComment(int cmtNo) {
		Connection conn = getConnection();
		int result = new NoticeDao().DeleteComment(conn, cmtNo);

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
}
