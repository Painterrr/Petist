package com.kh.admin.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.admin.model.dao.AdminDao;
import com.kh.admin.model.vo.Inquiry;
import com.kh.admin.model.vo.InquiryReply;
import com.kh.common.model.vo.PageInfo;
import com.kh.user.model.vo.Member;

public class AdminService {
	
	// 승희 부분 시작
	//--------------------------------------문의사항 관련 서비스---------------------------------------
   // 총 문의사항 게시글 수
   public int selectListCount() {
      Connection conn = getConnection();
      int listCount = new AdminDao().selectListCount(conn);
      close(conn);
      return listCount;
   }
   
   // 문의사항 게시글 리스트
   public ArrayList<Inquiry> selecInquiryList(PageInfo pi){
      Connection conn = getConnection();
      ArrayList<Inquiry> list = new AdminDao().selecInquiryList(conn, pi);
      close(conn);
      return list;
   }
   
   // 신고게시글 수
   public int selectReportListCount() {
	   Connection conn = getConnection();
	   int listCount = new AdminDao().selectReportListCount(conn);
	   close(conn);
	   return listCount;
   }
   
   // 신고게시글 리스트
   public ArrayList<Inquiry> selectReportList(PageInfo pi){
	   Connection conn = getConnection();
	   ArrayList<Inquiry> rlist = new AdminDao().selectReportList(conn, pi);
	   close(conn);
	   return rlist;
   }
   
   // 신고기능 서치
   public ArrayList<Inquiry> searchIdReport(String userId){
	   
	   Connection conn = getConnection();
	   ArrayList<Inquiry> list = new AdminDao().searchIdReport(conn, userId);
	   close(conn);
	   return list;
   }
   
   // 문의기능 서치
   public ArrayList<Inquiry> searchIdInquiry(String userId){
	   
	   Connection conn = getConnection();
	   ArrayList<Inquiry> list = new AdminDao().searchIdInquiry(conn, userId);
	   close(conn);
	   return list;
   }
   
   //---------------------------------------회원관련 서비스-------------------------------------------
   // 총 맴버수
   public int selectMemberCount(){
	   Connection conn = getConnection();
	   int listCount = new AdminDao().selectMemberCount(conn);
	   close(conn);
	   return listCount;
   }
   
   // 멤버리스트
   public ArrayList<Member> selectMemberList(PageInfo pi){
	   Connection conn = getConnection();
	   ArrayList<Member> list = new AdminDao().selectMemberList(conn, pi);
	   close(conn);
	   return list;
   }
   
   // 아이디로 회원찾기
   public ArrayList<Member> searchIdMember(String userId){
	   Connection conn = getConnection();
	   ArrayList<Member> list = null;
	   if(userId != "") {
			list = new AdminDao().searchIdMember(conn, userId);
			if(list.isEmpty()) {
				list = null;
			}
	   }
	   close(conn);
	   return list;
   }
   
   // 회원 차단하기
   public int blockMember(String blockMem) {
	   Connection conn = getConnection();
	   int result1 = new AdminDao().blockMember(conn, blockMem);
	   int result2 = new AdminDao().blockHosp(conn, blockMem);
	   int result = result1*result2;
	   if(result>0) {
		   commit(conn);
	   } else {
		   rollback(conn);
	   }
	   close(conn);
	   return result;
   }
   
   // 회원 차단해제
   public int UnBlockMember(String unBlockMem) {
	   Connection conn = getConnection();
	   int result1 = new AdminDao().UnBlockMember(conn, unBlockMem);
	   int result2 = new AdminDao().UnBlockHosp(conn, unBlockMem);
	   int result = result1*result2;
	   if(result>0) {
		   commit(conn);
	   } else {
		   rollback(conn);
	   }
	   close(conn);
	   return result;
   }
   //----------------------------------------문의글 댓글---------------------------------------------
  
   // 댓글 리스트
   public ArrayList<InquiryReply> selectReplyList(int inquiryNo){
	   Connection conn = getConnection();
	   ArrayList<InquiryReply> list = new AdminDao().selectReplyList(conn, inquiryNo);
	   close(conn);
	   return list;
   }
   
   // 댓글 작성
   public int insertReply(InquiryReply ir) {
	   Connection conn = getConnection();
	   int result = new AdminDao().insertReply(conn, ir);
	   if(result>0) {
		   commit(conn);
	   }else {
		   rollback(conn);
	   }
	   close(conn);
	   return result;
   }
 
   // 댓글 삭제
   public int deleteReply(int replyNo) {
	   Connection conn = getConnection();
	   int result = new AdminDao().deleteReply(conn, replyNo);
	   if(result>0) {
		   commit(conn);
	   }else {
		   rollback(conn);
	   }
	   close(conn);
	   return result;
	   
   }
   
   // --------------------------------------------답변확인---------------------------------------
   
   public int inquiryOkCheck(String okCheck) {
	   Connection conn = getConnection();
	   int result = new AdminDao().inquiryOkCheck(conn, okCheck);
	   if(result>0) {
		   commit(conn);
	   }else {
		   rollback(conn);
	   }
	   close(conn);
	   return result;
	   
   }
	// 승희 부분 끝-------------------------------------------------------------------------------------------------------------------------------------

   
   
   
   
   
   
   
   
   
   
   
}