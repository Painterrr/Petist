package com.kh.hospital.model.dao;

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
import com.kh.common.model.vo.Attachment;
import com.kh.hospital.model.vo.HospitalInfo;
import com.kh.hospital.model.vo.HospitalReview;
import com.kh.pet.model.vo.Animal;

public class HospitalDao {
	
	private Properties prop = new Properties();
	
	public HospitalDao() {
		
		String fileName = HospitalDao.class.getResource("/sql/hospital/hospital-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	} // 생성자 영역 끝
	
	public int selectListCount(Connection conn, String area) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, area + "%");
			
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
				
	} // selectListCount 메소드 영역 끝
	
	
	public ArrayList<HospitalInfo> selectList(Connection conn, PageInfo pi, String area) {
		
		ArrayList<HospitalInfo> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;

			pstmt.setString(1, area + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new HospitalInfo(rset.getInt("HOSP_NO")
										, rset.getString("HOSP_ID")
										, rset.getString("HOSP_NAME")
										, rset.getString("ADDRESS")
										, rset.getString("ADDRESS_DETAIL")
										, rset.getDouble("Y_COORDINATE")
										, rset.getDouble("X_COORDINATE")
										, rset.getString("OPERATING_TIME")
										, rset.getString("HOLIDAY")
										, rset.getString("PARKING")
										, rset.getString("TEL")
										, rset.getString("HOMEPAGE")
										, rset.getString("INTRODUCE")
										, rset.getInt("REVIEW_COUNT")
										, rset.getString("TITLE_IMG")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	} // selectList 메소드 영역 끝
	
	
	public int selectSearchListCount(Connection conn, String area, String keyword) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, area + "%");
			pstmt.setString(2, "%" + keyword + "%");
			
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
		
	} // selectSearchListCount 메소드 영역 끝
	
	
	public ArrayList<HospitalInfo> selectSearchList(Connection conn, PageInfo pi, String area, String keyword) {
		
		ArrayList<HospitalInfo> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, area + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new HospitalInfo(rset.getInt("HOSP_NO")
						, rset.getString("HOSP_ID")
						, rset.getString("HOSP_NAME")
						, rset.getString("ADDRESS")
						, rset.getString("ADDRESS_DETAIL")
						, rset.getDouble("Y_COORDINATE")
						, rset.getDouble("X_COORDINATE")
						, rset.getString("OPERATING_TIME")
						, rset.getString("HOLIDAY")
						, rset.getString("PARKING")
						, rset.getString("TEL")
						, rset.getString("HOMEPAGE")
						, rset.getString("INTRODUCE")
						, rset.getInt("REVIEW_COUNT")
						, rset.getString("TITLE_IMG")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	} // selectSearchList 메소드 영역 끝
	
	
	public HospitalInfo selectHospital(Connection conn, int hospitalNo) {
		
		HospitalInfo hi = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectHospital");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, hospitalNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				hi = new HospitalInfo(rset.getInt("HOSP_NO")
									, rset.getString("HOSP_ID")
									, rset.getString("HOSP_NAME")
									, rset.getString("ADDRESS")
									, rset.getString("ADDRESS_DETAIL")
									, rset.getDouble("Y_COORDINATE")
									, rset.getDouble("X_COORDINATE")
									, rset.getString("OPERATING_TIME")
									, rset.getString("HOLIDAY")
									, rset.getString("PARKING")
									, rset.getString("TEL")
									, rset.getString("HOMEPAGE")
									, rset.getString("INTRODUCE")
									, rset.getInt("REVIEW_COUNT")
									, rset.getString("TITLE_IMG"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hi;
		
	} // selectHospital 메소드 영역
	
	
	public Attachment selectAttachment(Connection conn, int refCno) {
		
		Attachment at = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectHospitalAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, refCno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				at = new Attachment();
				
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
		
	} // selectAttachment 메소드 영역 끝
	
	
	public int selectReviewListCount(Connection conn, int hospitalNo) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReviewListCount");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, hospitalNo);
			
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
				
	} // selectReviewListCount 메소드 영역 끝
	
	
	public ArrayList<HospitalReview> selectReviewList(Connection conn, PageInfo pi, int hospitalNo) {
		
		ArrayList<HospitalReview> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReviewList");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, hospitalNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new HospitalReview(rset.getInt("REVIEW_NO")
											, rset.getInt("HOSP_NO")
											, rset.getString("USER_ID")
											, rset.getInt("PET_NO")
											, rset.getString("PET_KIND")
											, rset.getString("PET_BREED")
											, rset.getInt("PET_AGE")
											, rset.getString("CLINIC_DATE")
											, rset.getString("CLINIC_INFO")
											, rset.getInt("PRICE")
											, rset.getString("CONTENT")
											, rset.getDate("CREATE_DATE")
											, rset.getString("STATUS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	} // selectReviewList 메소드 영역 끝
	
	
	public ArrayList<Animal> selectPetInfoList(Connection conn, String userId) {
		
		ArrayList<Animal> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPetInfoList");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Animal a = new Animal();
				
				a.setPetNo(rset.getInt("PET_NO"));
				a.setPetName(rset.getString("PET_NAME"));
				a.setPetKind(rset.getString("PET_KIND"));
				a.setPetBreed(rset.getString("PET_BREED"));
				a.setPetAge(rset.getInt("PET_AGE"));
				
				list.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	} // selectPetInfoList 메소드 영역 끝
	
	
	public int insertReview(Connection conn, HospitalReview hr) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertReview");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, hr.getHospNo());
			pstmt.setString(2, hr.getUserId());
			pstmt.setInt(3, hr.getPetNo());			
			pstmt.setString(4, hr.getPetKind());
			pstmt.setString(5, hr.getPetBreed());
			pstmt.setInt(6, hr.getPetAge());
			pstmt.setString(7, hr.getClinicDate());
			pstmt.setString(8, hr.getClinicInfo());
			pstmt.setInt(9, hr.getPrice());
			pstmt.setString(10, hr.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	} // insertReview 메소드 영역 끝
		
	
	public HospitalReview selectReview(Connection conn, int reviewNo) {
		
		HospitalReview hr = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				hr = new HospitalReview(rset.getInt("REVIEW_NO")
									  , rset.getInt("HOSP_NO")
									  , rset.getString("USER_ID")
									  , rset.getInt("PET_NO")
									  , rset.getString("PET_KIND")
									  , rset.getString("PET_BREED")
									  , rset.getInt("PET_AGE")
									  , rset.getString("CLINIC_DATE")
									  , rset.getString("CLINIC_INFO")
									  , rset.getInt("PRICE")
									  , rset.getString("CONTENT")
									  , rset.getDate("CREATE_DATE")
									  , rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hr;
		
	} // selectReview 메소드 영역 끝
	
	
	public int updateReview(Connection conn, HospitalReview hr) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateReview");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, hr.getPetNo());
			pstmt.setString(2, hr.getPetKind());
			pstmt.setString(3, hr.getPetBreed());
			pstmt.setInt(4, hr.getPetAge());
			pstmt.setString(5, hr.getClinicDate());
			pstmt.setString(6, hr.getClinicInfo());
			pstmt.setInt(7, hr.getPrice());
			pstmt.setString(8, hr.getContent());
			pstmt.setInt(9, hr.getReviewNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	} // updateReview 메소드 영역 끝
		
	
	public int deleteReview(Connection conn, int reviewNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteReview");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	} // deleteReview 메소드 영역 끝
	
	
	public HospitalInfo selectHospitalById(Connection conn, String hospId) {
		
		HospitalInfo hi = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectHospitalById");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hospId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				hi = new HospitalInfo(rset.getInt("HOSP_NO")
									, rset.getString("HOSP_ID")
									, rset.getString("HOSP_NAME")
									, rset.getString("ADDRESS")
									, rset.getString("ADDRESS_DETAIL")
									, rset.getDouble("Y_COORDINATE")
									, rset.getDouble("X_COORDINATE")
									, rset.getString("OPERATING_TIME")
									, rset.getString("HOLIDAY")
									, rset.getString("PARKING")
									, rset.getString("TEL")
									, rset.getString("HOMEPAGE")
									, rset.getString("INTRODUCE")
									, rset.getInt("REVIEW_COUNT")
									, rset.getString("TITLE_IMG"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hi;
		
	} // selectHospitalById 메소드 영역 끝
	
	
	public int updateHospital(Connection conn, HospitalInfo hi) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateHospital");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hi.getHospName());
			pstmt.setString(2, hi.getAddress());
			pstmt.setString(3, hi.getAddressDetail());
			pstmt.setDouble(4, hi.getyCoordinate());
			pstmt.setDouble(5, hi.getxCoordinate());
			pstmt.setString(6, hi.getOpertingTime());
			pstmt.setString(7, hi.getHoliday());
			pstmt.setString(8, hi.getParking());
			pstmt.setString(9, hi.getTel());
			pstmt.setString(10, hi.getHomepage());
			pstmt.setString(11, hi.getIntoduce());
			pstmt.setInt(12, hi.getHospNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	} // updateHospital 메소드 영역 끝
	
	
	public int updateHospitalAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateHospitalAttachment");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	} // updateHospitalAttachment 메소드 영역 끝
	
	
	public int insertNewHospitalAttachment(Connection conn, Attachment at) {
		
		// INSERT문 => 처리된 행의 갯수
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNewHospitalAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, at.getRefCno());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	} // insertNewHospitalAttachment 메소드 영역 끝
	
	
	public int deleteHospital(Connection conn, int hospNo) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteHospital");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, hospNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
		
	} // deleteHospital 메소드 영역 끝
	
	public int insertHospital(Connection conn, HospitalInfo hi) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertHospital");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hi.getHospId());
			pstmt.setString(2, hi.getHospName());
			pstmt.setString(3, hi.getAddress());
			pstmt.setString(4, hi.getAddressDetail());
			pstmt.setDouble(5, hi.getyCoordinate());
			pstmt.setDouble(6, hi.getxCoordinate());
			pstmt.setString(7, hi.getOpertingTime());
			pstmt.setString(8, hi.getHoliday());
			pstmt.setString(9, hi.getParking());
			pstmt.setString(10, hi.getTel());
			pstmt.setString(11, hi.getHomepage());
			pstmt.setString(12, hi.getIntoduce());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	} // insertHospital 메소드 영역 끝
	
	
	public int insertAttachment(Connection conn, Attachment at, int refCno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, refCno);
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	} // insertAttachment 메소드 영역 끝
	

} // HospitalDao 클래스 영역 끝
