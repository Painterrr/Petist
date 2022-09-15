package com.kh.hospital.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.hospital.model.dao.HospitalDao;
import com.kh.common.model.vo.Attachment;
import com.kh.hospital.model.vo.HospitalInfo;
import com.kh.hospital.model.vo.HospitalReview;
import com.kh.pet.model.vo.Animal;

public class HospitalService {

	public int selectListCount(String area) {
		
		Connection conn = getConnection();
		
		int listCount = new HospitalDao().selectListCount(conn, area);
		
		close(conn);
		
		return listCount;
		
	} // selectListCount 메소드 영역 끝
	
	
	public ArrayList<HospitalInfo> selectList(PageInfo pi, String area) {
		
		Connection conn = getConnection();
		
		ArrayList<HospitalInfo> list = new HospitalDao().selectList(conn, pi, area);
		
		close(conn);
		
		return list;
		
	} // selectList 메소드 영역 끝
	
	
	public int selectSearchListCount(String area, String keyword) {
		
		Connection conn = getConnection();
		
		int listCount = new HospitalDao().selectSearchListCount(conn, area, keyword);
		
		close(conn);
		
		return listCount;
		
	} // selectSearchListCount 메소드 영역 끝
	
	
	public ArrayList<HospitalInfo> selectSearchList(PageInfo pi, String area, String keyword) {
		
		Connection conn = getConnection();
		
		ArrayList<HospitalInfo> list = new HospitalDao().selectSearchList(conn, pi, area, keyword);
		
		close(conn);
		
		return list;
		
	} // selectSearchList 메소드 영역 끝
	
	
	public HospitalInfo selectHospital(int hospitalNo) {
		
		Connection conn = getConnection();
		
		HospitalInfo hi = new HospitalDao().selectHospital(conn, hospitalNo);
		
		close(conn);
		
		return hi;
		
	} // selectHospital 메소드 영역 끝
	
	
	public Attachment selectAttachment(int refCno) {
		
		Connection conn = getConnection();
		
		Attachment at = new HospitalDao().selectAttachment(conn, refCno);
		
		close(conn);
		
		return at;
		
	} // selectAttachment 메소드 영역 끝
	
	
	public int selectReviewListCount(int hospitalNo) {
		
		Connection conn = getConnection();
		
		int listCount = new HospitalDao().selectReviewListCount(conn, hospitalNo);
		
		close(conn);
		
		return listCount;
		
	} // selectReviewListCount 메소드 영역 끝
	
	
	public ArrayList<HospitalReview> selectReviewList(PageInfo pi, int hospitalNo) {
		
		Connection conn = getConnection();
		
		ArrayList<HospitalReview> list = new HospitalDao().selectReviewList(conn, pi, hospitalNo);
		
		close(conn);
		
		return list;
		
	} // selectReviewList 메소드 영역 끝

	
	public ArrayList<Animal> selectPetInfoList(String userId) {
		
		Connection conn = getConnection();
		
		ArrayList<Animal> list = new HospitalDao().selectPetInfoList(conn, userId);
		
		close(conn);
		
		return list;
		
	} // selectPetInfoList 메소드 영역 끝
	
	
	public int insertReview(HospitalReview hr) {
		
		Connection conn = getConnection();
		
		int result = new HospitalDao().insertReview(conn, hr);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	} // insertReview 메소드 영역 끝
	
	
	public HospitalReview selectReview(int reviewNo) {
		
		Connection conn = getConnection();
		
		HospitalReview hr = new HospitalDao().selectReview(conn, reviewNo);
		
		close(conn);
		
		return hr;
		
	} // selectReview 메소드 영역 끝
	
	
	public int updateReview(HospitalReview hr) {
		
		Connection conn = getConnection();
		
		int result = new HospitalDao().updateReview(conn, hr);
				
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	} // updateReview 메소드 영역 끝
	
	
	public int deleteReview(int reviewNo) {
		
		Connection conn = getConnection();
		
		int result = new HospitalDao().deleteReview(conn, reviewNo);
				
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	} // deleteReview 메소드 영역 끝
	
	
	public HospitalInfo selectHospitalById(String hospId) {
		
		Connection conn = getConnection();
		
		HospitalInfo hi = new HospitalDao().selectHospitalById(conn, hospId);
		
		close(conn);
		
		return hi;
		
	} // selectHospitalById 메소드 영역 끝
	
	
	public int updateHospital(HospitalInfo hi, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new HospitalDao().updateHospital(conn, hi);
		
		int result2 = 1;
		
		if(at != null) {
			
			if(at.getFileNo() != 0) {
				result2 = new HospitalDao().updateHospitalAttachment(conn, at);	
			} else {
				result2 = new HospitalDao().insertNewHospitalAttachment(conn, at);
			}
			
		} 
				
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return (result1 * result2);
		
	} // updateHospital 메소드 영역 끝
	
	
	public int deleteHospital(int hospNo) {
		
		Connection conn = getConnection();
		
		int result = new HospitalDao().deleteHospital(conn, hospNo);
				
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);

		return result;
		
	} // deleteHospital 메소드 영역 끝
	
	
	public int insertHospital(HospitalInfo hi, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new HospitalDao().insertHospital(conn, hi);
		
		hi = new HospitalDao().selectHospitalById(conn, hi.getHospId());
		
		int result2 = 1; 
		if(at != null) {
			result2 = new HospitalDao().insertAttachment(conn, at, hi.getHospNo());			
		}
		
		if((result1 * result2) > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return (result1 * result2);
		
	} // insertHospital 메소드 영역 끝
	

} // HospitalService 클래스 영역 끝
