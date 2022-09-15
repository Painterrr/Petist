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

import com.kh.admin.model.vo.Category;
import com.kh.admin.model.vo.Inquiry;
import com.kh.admin.model.vo.InquiryReply;
import com.kh.common.model.vo.PageInfo;
import com.kh.user.model.vo.Member;

public class AdminDao {
   private Properties prop = new Properties();

   public AdminDao() {
      String fileName = AdminDao.class.getResource("/sql/admin/admin-mapper.xml").getPath();
      
      try {
         prop.loadFromXML(new FileInputStream(fileName));
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }
   
   // 승희부분시작
   //---------------------------------------------문의관련 디에이오-----------------------------------------------
   //총 문의사항 게시글 수
   public int selectListCount(Connection conn) {
      int listCount = 0;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectListCount");
      
      try {
         pstmt=conn.prepareStatement(sql);
         
         rset=pstmt.executeQuery();
         
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
   
   // 문의사항 게시글 리스트 조회
   public ArrayList<Inquiry> selecInquiryList(Connection conn, PageInfo pi){
      // SELECT문 
      ArrayList<Inquiry> list = new ArrayList();
      
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("InquiryList");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
         int endRow = startRow + pi.getBoardLimit() - 1;
         
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
         
         rset=pstmt.executeQuery();
         
         while(rset.next()) {
            list.add(new Inquiry(rset.getInt("INQUIRY_NO"),
                            rset.getString("CATEGORY_NAME"),
                            rset.getString("USER_ID"),
                            rset.getString("INQUIRY_TITLE"),
                            rset.getString("INQUIRY_CONTENT"),
                            rset.getDate("CREATE_DATE"),
                            rset.getString("checkStatus")));
            
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return list;
      
   }
   
   // 신고문의 게시글 수
   public int selectReportListCount(Connection conn) {
	   
	   int listCount = 0;
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   
	   String sql = prop.getProperty("selectReportListCount");
	   
	   try {
			pstmt = conn.prepareStatement(sql);
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
  
   // 신고게시글 리스트
   public ArrayList<Inquiry> selectReportList(Connection conn, PageInfo pi){
	   
	   ArrayList<Inquiry> rlist = new ArrayList();
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   
	   String sql = prop.getProperty("selectReportList");
	   
	   try {
			pstmt=conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset= pstmt.executeQuery();
			
			while(rset.next()) {
	            rlist.add(new Inquiry(rset.getInt("INQUIRY_NO"),
	                            rset.getString("CATEGORY_NAME"),
	                            rset.getString("USER_ID"),
	                            rset.getString("INQUIRY_TITLE"),
	                            rset.getString("INQUIRY_CONTENT"),
	                            rset.getDate("CREATE_DATE"),
	                            rset.getString("checkStatus")));
	            
	         }
	   
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	   return rlist;
   }
 
   //----------------------------------------------문의글 관련 아이디 서치------------------------------------------
   // 신고기능
   public ArrayList<Inquiry> searchIdReport(Connection conn, String userId){
	   
	   ArrayList<Inquiry> list = new ArrayList();
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   String sql = prop.getProperty("searchIdReport");
	   
	   try {
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
            list.add(new Inquiry(rset.getInt("INQUIRY_NO"),
                            rset.getString("CATEGORY_NAME"),
                            rset.getString("USER_ID"),
                            rset.getString("INQUIRY_TITLE"),
                            rset.getString("INQUIRY_CONTENT"),
                            rset.getDate("CREATE_DATE"),
                            rset.getString("checkStatus")));
            
         }
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	   return list;
   }
   // 문의기능 서치 디에이오
   public ArrayList<Inquiry> searchIdInquiry(Connection conn, String userId){
	   
	   ArrayList<Inquiry> list = new ArrayList();
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   String sql = prop.getProperty("searchIdInquiry");
	   
	   try {
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
            list.add(new Inquiry(rset.getInt("INQUIRY_NO"),
                            rset.getString("CATEGORY_NAME"),
                            rset.getString("USER_ID"),
                            rset.getString("INQUIRY_TITLE"),
                            rset.getString("INQUIRY_CONTENT"),
                            rset.getDate("CREATE_DATE"),
                            rset.getString("checkStatus")));
            
         }
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	   return list;
   }
   
   //-------------------------------문의사항댓글 관련 디에이오----------------------------
   // 문의사항 댓글 리스트
   public ArrayList<InquiryReply> selectReplyList(Connection conn, int inquiryNo){
	   
	   ArrayList<InquiryReply> list = new ArrayList();
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   String sql = prop.getProperty("selectReplyList");
	   
	   try {
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, inquiryNo);
		rset=pstmt.executeQuery();
		
		while(rset.next()) {
			list.add(new InquiryReply(rset.getInt("REPLY_NO"),
							   rset.getString("REPLY_CONTENT"),
							   rset.getString("MEMBER_ID"),
							   rset.getString("CREATE_DATE")));
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	   return list;
   }
   
   // 문의사항 댓글 작성 
   public int insertReply(Connection conn, InquiryReply ir) {
	   
	   int result=0;
	   PreparedStatement pstmt = null;
	   String sql = prop.getProperty("insertReply");
	   
	   try {
		   pstmt=conn.prepareStatement(sql);
		   
		   pstmt.setString(1, ir.getReplyContent());
		   pstmt.setInt(2, ir.getRefIno());
		   pstmt.setString(3, ir.getReplyWriter());
		   
		   result = pstmt.executeUpdate();
		   
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	   return result;
   }
   
   // 댓글 삭제
   public int deleteReply(Connection conn, int replyNo) {
	   
	   int result = 0;
	   PreparedStatement pstmt = null;
	   String sql = prop.getProperty("deleteReply");
	   
	   try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, replyNo);
			
			result=pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	   return result;
   }
   
   //---------------------------------------------------회원관리 디에이오------------------------------------------------
   // 총 맴버리스트 수
   public int selectMemberCount(Connection conn) {
	   
	   int listCount = 0;
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   
	   String sql = prop.getProperty("selectMemberCount");
	   
	   try {
		pstmt = conn.prepareStatement(sql);
		
		rset=pstmt.executeQuery();

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
   
   // 맴버리스트조회
   public ArrayList<Member> selectMemberList(Connection conn, PageInfo pi){
	   
	   ArrayList<Member> list = new ArrayList();
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   
	   String sql = prop.getProperty("selectMemberList");
	   
	   try {
		   pstmt = conn.prepareStatement(sql);
		   
		   int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
	       int endRow = startRow + pi.getBoardLimit() - 1;
	         
	       pstmt.setInt(1, startRow);
	       pstmt.setInt(2, endRow);
		   
		   rset = pstmt.executeQuery();
		   
		   while(rset.next()) {
	            list.add(new Member(rset.getString("MEMBER_ID"),
	            				    rset.getString("MEMBER_NAME"),
	            				    rset.getString("PHONE"),
	            				    rset.getString("EMAIL"),
	            				    rset.getString("STATUS")));
			   
		   }
		   
	   } catch (SQLException e) {
		   e.printStackTrace();
	   } finally {
		   close(rset);
		   close(pstmt);
	   }
	   return list;
   }
   
   public ArrayList<Member> searchIdMember(Connection conn, String userId){
	   
	   ArrayList<Member> list = new ArrayList();
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   
	   String sql = prop.getProperty("searchIdMember");
	   
	   try {
		   pstmt = conn.prepareStatement(sql);
		   
		   pstmt.setString(1, "%"+userId+"%");
		
		   rset = pstmt.executeQuery();
		   
		   while(rset.next()) {
	            list.add(new Member(rset.getString("MEMBER_ID"),
	            				    rset.getString("MEMBER_NAME"),
	            				    rset.getString("PHONE"),
	            				    rset.getString("EMAIL"),
	            				    rset.getString("STATUS")));
			   
		   }
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	   return list;
   }
   
   // 회원차단 디에이오
   public int blockMember(Connection conn, String blockMem) {
	   
	   int result = 0;
	   PreparedStatement pstmt = null;
	   
	   String sql = prop.getProperty("blockMember");
	   
	   try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, blockMem);
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	   return result;
   }
   
   // 병원차단 디에이오
   public int blockHosp(Connection conn, String blockMem) {
	   int result=0;
	   PreparedStatement pstmt = null;
	   
	   String sql = prop.getProperty("blockHosp");
	   
	   try {
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setString(1, blockMem);
		
		   result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	   return result;
   }
   
   //일반회원 차단해제
   public int UnBlockMember(Connection conn, String unBlockMem) {
	   
	   int result = 0;
	   PreparedStatement pstmt = null;
	   
	   String sql = prop.getProperty("UnBlockMember");
	   
	   try {
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, unBlockMem);
		
		result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	   return result;
   }
   
   //병원회원 차단해제
   public int UnBlockHosp(Connection conn, String unBlockMem) {
	   
	   int result = 0;
	   PreparedStatement pstmt = null;
	   
	   String sql = prop.getProperty("UnBlockHosp");
	   
	   try {
		   pstmt=conn.prepareStatement(sql);
		   pstmt.setString(1, unBlockMem);
			
		   result=pstmt.executeUpdate();
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	   return result;
   }
   
   
   public int inquiryOkCheck(Connection conn,String okCheck) {
	   
	   int result=0;
	   PreparedStatement pstmt = null;
	   
	   String sql = prop.getProperty("inquiryOkCheck");
	   
	   try {
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, okCheck);
		
		result=pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	   return result;
   }
   
   // 승희 부분 끝-------------------------------------------------------------------------------------------------------------------------------------
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}