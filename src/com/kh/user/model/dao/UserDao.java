package com.kh.user.model.dao;

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
import com.kh.common.model.vo.PageInfo;
import com.kh.user.model.vo.Hospital;
import com.kh.user.model.vo.Member;

public class UserDao {
	
	private Properties prop = new Properties();
	
	public UserDao() {
		
		String file = UserDao.class.getResource("/sql/user/user-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Member loginUser(Connection conn, String userId, String userPwd) {
		
		// SELECT문 > ResultSet
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getString("MEMBER_ID"),
							   rset.getString("MEMBER_PWD"),
							   rset.getString("MEMBER_NAME"),
							   rset.getString("MEMBER_FNO"),
							   rset.getString("MEMBER_BNO"),
							   rset.getString("GENDER"),
							   rset.getString("PHONE"),
							   rset.getString("EMAIL"),
							   rset.getString("STATUS"),
							   rset.getInt("MEMBER_CODE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
			return m;
	}

	public Hospital loginHosp(Connection conn, String userId, String userPwd) {
		Hospital h = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginHosp");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				h = new Hospital(rset.getString("HOSP_ID"),
								 rset.getString("HOSP_PWD"),
								 rset.getString("HOSP_NAME"),
								 rset.getInt("HOSP_CRN"),
								 rset.getString("HOSP_EMAIL"),
								 rset.getString("STATUS"),
								 rset.getInt("HOSP_CODE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
			return  h;
		
	}

	public int memberIdCheck(Connection conn, String checkId) {
		
		int count1 = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("memberIdCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count1 = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count1;
	}
	
	public int hospIdCheck(Connection conn, String checkId) {
		int count2 = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("hospIdCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count2 = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count2;
	}
	
	public String idFind(Connection conn, String userName, String userFno) {
		String userId = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idFind");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userName);
			pstmt.setString(2, userFno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				userId = rset.getString("MEMBER_ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return userId;
		
	}
	
	public String pwdFind(Connection conn, String email, String userId) {
		String userPwd = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("pwdFind");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				userPwd = rset.getString("MEMBER_PWD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return userPwd;
	}

	public int insertMember(Connection conn, Member m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			// memberId, memberPwd, memberName, memberFno, memberBno, gender, phone, email
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberFno());
			pstmt.setString(5, m.getMemberBno());
			pstmt.setString(6, m.getGender());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getEmail());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertHospital(Connection conn, Hospital hosp) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertHospital");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, hosp.getHospId());
			pstmt.setString(2, hosp.getHospPwd());
			pstmt.setString(3, hosp.getHospName());
			pstmt.setInt(4, hosp.getHospCrn());
			pstmt.setString(5, hosp.getHospEmail());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getMemberId());
			pstmt.setString(4, m.getMemberPwd());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMemberPwd(Connection conn, String memberId, String memberPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemberPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberPwd);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateHospPwd(Connection conn, String hospId, String hospPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateHospPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hospPwd);
			pstmt.setString(2, hospId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteHosp(Connection conn, String hospId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteHosp");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hospId);
			
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//---------------------승희부분 마이페이지 문의관련 디에이오-----------------------------
		// 문의글 작성디에이오
		public int insertInquiry(Connection conn, Inquiry i) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("insertInquiry");
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(i.getCategory()));
				pstmt.setString(2, i.getUserId());
				pstmt.setString(3, i.getInquiryTitle());
				pstmt.setString(4, i.getInquiryContent());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		// 자신이 쓴 문의글의 갯수
		public int selectListCount(Connection conn, String userId) {
			
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectListCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				
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
		// 자신이 쓴 문의글 조회
		public ArrayList<Inquiry> selectInquiryList(Connection conn, PageInfo pi, String userId){
			
			ArrayList<Inquiry> list = new ArrayList();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectInquiryList");
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
				int endRow = startRow + pi.getBoardLimit() - 1;
				
				pstmt.setString(1, userId);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Inquiry(rset.getInt("INQUIRY_NO"),
										 rset.getString("CATEGORY_NAME"),
										 rset.getString("USER_ID"),
										 rset.getString("INQUIRY_TITLE"),
										 rset.getString("INQUIRY_CONTENT"),
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
		
		// 문의사항 삭제 디에이오
		   public int deleteInquiry(Connection conn, int inquiryNo) {
			   
			   int result = 0;
			   PreparedStatement pstmt = null;
			   String sql = prop.getProperty("deleteInquiry");
			   
			   try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, inquiryNo);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			   return result;
		   }
		
		   //-----------게시글 작성폼 카테고리--------------
		   public ArrayList<Category> selectCategoryList(Connection conn){
			   
			   ArrayList<Category> list = new ArrayList();
			   
			   PreparedStatement pstmt = null;
			   ResultSet rset = null;
			   
			   String sql = prop.getProperty("selectCategoryList");
			   
			   try {
				pstmt=conn.prepareStatement(sql);
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Category(rset.getInt("CATEGORY_NO"),
										  rset.getString("CATEGORY_NAME")));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			   return list;
		   }
		   
		   // 문의사항 수정 디에이오
		   public int updateInquiry(Connection conn, Inquiry i) {
			   
			   int result = 0;
			   PreparedStatement pstmt = null;
			   String sql = prop.getProperty("updateInquiry");
			   
			   try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(i.getCategory()));
				pstmt.setString(2, i.getInquiryTitle());
				pstmt.setString(3, i.getInquiryContent());
				pstmt.setInt(4, i.getInquiryNo());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			   return result;
		   }
		   
		   // 게시글 상세조회
		   public Inquiry selectInquiry(Connection conn, int InquiryNo) {
			   
			   Inquiry i =null;
			   PreparedStatement pstmt = null;
			   ResultSet rset = null;
			   
			   String sql = prop.getProperty("selectInquiry");
			   
			   try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, InquiryNo);
				
				rset=pstmt.executeQuery();
				
				if(rset.next()) {
					i = new Inquiry(rset.getInt("INQUIRY_NO"),
									rset.getString("CATEGORY_NAME"),
									rset.getString("USER_ID"),
									rset.getString("INQUIRY_TITLE"),
									rset.getString("INQUIRY_CONTENT"),
									rset.getDate("CREATE_DATE"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			   return i;
		   }

	

}
