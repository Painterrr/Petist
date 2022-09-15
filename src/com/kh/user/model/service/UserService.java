package com.kh.user.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.admin.model.vo.Category;
import com.kh.admin.model.vo.Inquiry;
import com.kh.common.model.vo.PageInfo;
import com.kh.user.model.dao.UserDao;
import com.kh.user.model.vo.Hospital;
import com.kh.user.model.vo.Member;

public class UserService {

	public Member loginUser(String userId, String userPwd) {
		
		Connection conn = getConnection();
		
		Member m = new UserDao().loginUser(conn, userId, userPwd);
		
		close(conn);
		
		return m;
	}

	public Hospital loginHosp(String userId, String userPwd) {
		Connection conn = getConnection();
		
		Hospital h = new UserDao().loginHosp(conn, userId, userPwd);
		
		close(conn);
		
		return h;
	}

	public int idCheck(String checkId) {
		Connection conn = getConnection();
		int count = 0;
		int count1 = new UserDao().memberIdCheck(conn, checkId);
		int count2 = new UserDao().hospIdCheck(conn, checkId);
			
			count = count1 + count2;
			// count = 0 이면 중복 x 
		close(conn);
		
		return count;
		
	}
	
	public String idFind(String userName, String userFno) {
		Connection conn = getConnection();
		
		String userId = new UserDao().idFind(conn, userName, userFno);
		
		close(conn);
		
		return userId;
	}
	
	public String pwdFind(String email, String userId) {
			Connection conn = getConnection();
			
			String userPwd = new UserDao().pwdFind(conn, email, userId); 
			
			close(conn);
			
			return userPwd;
			
	}
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		
		int result = new UserDao().insertMember(conn, m);
		
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int insertHospital(Hospital hosp) {
		Connection conn = getConnection();
		
		int result = new UserDao().insertHospital(conn, hosp);
		
		if(result > 0) { // 성공
			commit(conn);
		} else { // 실패
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Member updateMember(Member m) {
		Connection conn = getConnection();
		int result = new UserDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) { // 성공
			commit(conn);
			updateMem = new UserDao().loginUser(conn, m.getMemberId(), m.getMemberPwd());
		} else { // 실패
			rollback(conn);
		}
		close(conn);
		
		return updateMem;
	}

	public Member updateMemberPwd(String memberId, String memberPwd) {
		Connection conn = getConnection();
		int result = new UserDao().updateMemberPwd(conn, memberId, memberPwd);
		
		Member updateMemPwd = null;
		
		if(result > 0) {
			commit(conn);
			updateMemPwd = new UserDao().loginUser(conn, memberId, memberPwd);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return updateMemPwd;
	}
	
	public Hospital updateHospPwd(String hospId, String hospPwd) {
		Connection conn = getConnection();
		int result = new UserDao().updateHospPwd(conn, hospId, hospPwd);
		
		Hospital updateHospPwd = null;
		
		if(result > 0) {
			commit(conn);
			updateHospPwd = new UserDao().loginHosp(conn, hospId, hospPwd);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return updateHospPwd;
	}

	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = new UserDao().deleteMember(conn, memberId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public int deleteHosp(String hospId) {
		Connection conn = getConnection();
		int result = new UserDao().deleteHosp(conn, hospId);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	//------------------------------승희부분------------------------------
		public int insertInquiry(Inquiry i) {
			
			Connection conn = getConnection();
			int result = new UserDao().insertInquiry(conn, i);
			if(result>0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			close(conn);
			
			return result;
		}
		
		// 페이징 처리시 리스트카운트
		public int selectListCount(String userId) {
			Connection conn = getConnection();
			int listCount = new UserDao().selectListCount(conn, userId);
			close(conn);
			return listCount;
		}
		
		public ArrayList<Inquiry> selectInquiryList(PageInfo pi, String userId){
		
			Connection conn = getConnection();
			ArrayList<Inquiry> list = new UserDao().selectInquiryList(conn, pi, userId);
			close(conn);
			return list;
			
		}
		// 문의사항 삭제 서비스
		   public int deleteInquiry(int inquiryNo) {
			   Connection conn = getConnection();
			   int result = new UserDao().deleteInquiry(conn, inquiryNo);
			   if(result>0) {
				   commit(conn);
			   }else {
				   rollback(conn);
			   }
			   close(conn);
			   return result;
		   }
		
		// 카테고리
		   public ArrayList<Category> selectCategoryList(){
			   Connection conn = getConnection();
			   ArrayList<Category> list = new UserDao().selectCategoryList(conn);
			   close(conn);
			   return list;
		   }
		
		// 문의사항 수정 서비스
		   public int updateInquiry(Inquiry i) {
			   
			   Connection conn = getConnection();
			   int result = new UserDao().updateInquiry(conn, i);
			   
			   if(result>0) {
				   commit(conn);
			   } else {
				   rollback(conn);
			   }
			   close(conn);
			   return result;
		   }
		// 문의사항보기
		   public Inquiry selectInquiry(int inquiryNo) {
			   
			   Connection conn = getConnection();
			   Inquiry i = new UserDao().selectInquiry(conn, inquiryNo);
			   close(conn);
			   return i;
			   
		   }

	

	

}
