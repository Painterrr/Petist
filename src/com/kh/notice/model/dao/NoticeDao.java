package com.kh.notice.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.vo.Notice;
import com.kh.notice.model.vo.NoticeComment;

public class NoticeDao {

   private Properties prop = new Properties();
   
   //매퍼연결
   public NoticeDao() {
      String fileName = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();
      
      try {
         prop.loadFromXML(new FileInputStream(fileName));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   //공지사항 전체조회
   public int selectListCount(Connection conn) {
      int listCount = 0;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("selectListCount");
      try {
         pstmt = conn.prepareStatement(sql);
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            listCount = rset.getInt("COUNT");
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return listCount;
   }
   
   //공지사항 페이징처리
   public ArrayList<Notice> selectList(Connection conn, PageInfo pi) {
      ArrayList<Notice> list = new ArrayList();
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("selectList");
      try {
         pstmt = conn.prepareStatement(sql);
         
         int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
         int endRow = startRow + pi.getBoardLimit() - 1;

         System.out.print(pi.getCurrentPage());
         System.out.println(pi.getBoardLimit());
         
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            list.add(new Notice(rset.getInt("NOTICE_NO"),
                           rset.getString("NOTICE_TITLE"),
                           rset.getString("MEMBER_ID"),
                           rset.getInt("COUNT"),
                           rset.getDate("CREATE_DATE")));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return list;
   }
   
   //공지사항 등록
   public int insertNotice(Connection conn, Notice n) {
      
      int result = 0;
      PreparedStatement pstmt = null;
      
      String sql = prop.getProperty("insertNotice");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, n.getNoticeTitle());
         pstmt.setString(2, n.getNoticeContent());
         pstmt.setString(3, n.getNoticeWriter());
         
         result = pstmt.executeUpdate();         
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }
   
   //공지사항 상세조회 전 조회수 udpate
   public int increaseCount(Connection conn, int noticeNo) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("increaseCount");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, noticeNo);
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }
   
   public Notice selectNotice(Connection conn, int noticeNo) {
      Notice n = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("selectNotice");

      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, noticeNo);
         rset = pstmt.executeQuery();
         if(rset.next()) {
            n = new Notice(rset.getInt("NOTICE_NO"),
                        rset.getString("NOTICE_TITLE"),
                        rset.getString("NOTICE_CONTENT"),
                        rset.getString("MEMBER_ID"),
                        rset.getDate("CREATE_DATE"));
            
            
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return n;
   }
   
   public int updateNotice(Connection conn, Notice n) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("updateNotice");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, n.getNoticeTitle());
         pstmt.setString(2, n.getNoticeContent());
         pstmt.setInt(3, n.getNoticeNo());
         
         result = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }
   
   public int deleteNotice(Connection conn, int noticeNo) {
      int result = 0;
      
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("deleteNotice");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, noticeNo);
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }
   
   //댓글 조회
   public ArrayList<NoticeComment> selectComment(Connection conn, int noticeNo) {
      ArrayList<NoticeComment> list = new ArrayList();
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectComment");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, noticeNo);
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
               list.add(new NoticeComment(rset.getInt("CMT_NO"),
                                    rset.getString("CMT_WRITER"),
                                    rset.getString("CMT_CONTENT"),
                                    rset.getDate("CREATE_DATE")
                                    ));
               System.out.println(list);
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
   
   public int insertComment(Connection conn, NoticeComment nCmt) {
      int result = 0;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("insertComment");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, nCmt.getCmtWriter());
         pstmt.setString(2, nCmt.getCmtContent());
         pstmt.setInt(3, nCmt.getRefNno());
         
         result = pstmt.executeUpdate();
                  
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      return result;
   }
   
   public int DeleteComment(Connection conn, int cmtNo) {
      int result = 0;
      PreparedStatement pstmt = null;

      String sql = prop.getProperty("deleteComment");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, cmtNo);
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