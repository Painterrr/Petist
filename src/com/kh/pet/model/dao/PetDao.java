package com.kh.pet.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.pet.model.vo.Animal;
import com.kh.pet.model.vo.Diary;

public class PetDao {
	private Properties prop = new Properties();
	
	public PetDao() {
		
		String file = PetDao.class.getResource("/sql/pet/pet-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int insert1Pet(Connection conn, Animal p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insert1Pet");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getPetName());
			pstmt.setString(2, p.getPetInputNo());
			pstmt.setString(3, p.getPetKind());
			pstmt.setString(4, p.getPetBreed());
			pstmt.setString(5, p.getPetBirth());
			pstmt.setString(6, p.getPetNeutral());			
			pstmt.setString(7, p.getPetOwner());			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public int insert2Pet(Connection conn, Attachment at) {
		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insert2Pet");
		
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				
				// 실행
				result *= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public ArrayList<Animal> selectPetList(Connection conn, String loginUser){
		ArrayList<Animal> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPetList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginUser);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Animal a = new Animal();
				// 게시글번호, 제목, 조회수, 저장경로, 바뀐이름
				a.setPetNo(rset.getInt("PET_NO"));
				a.setTitleImg(rset.getString("TITLEIMG"));
				a.setPetName(rset.getString("PET_NAME"));
				a.setPetBreed(rset.getString("PET_BREED"));
				a.setPetBirth((rset.getString("PET_BIRTH")).substring(0, 10));
				
				list.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;

	}
	
	public int deletePet(Connection conn, String petName, String memberPwd, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deletePet");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, petName);
			pstmt.setString(2, memberPwd);
			pstmt.setString(3, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public Animal selectAnimal(Connection conn, int pno) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Animal a =null;
		
		String sql = prop.getProperty("selectAnimal");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				a = new Animal();
				a.setPetNo(rset.getInt("PET_NO"));
				a.setPetName(rset.getString("PET_NAME"));
				a.setPetInputNo(rset.getString("PET_INPUTNO"));
				a.setPetKind(rset.getString("PET_KIND"));
				a.setPetBreed(rset.getString("PET_BREED"));
				a.setPetNeutral(rset.getString("PET_NEUTRAL"));
				a.setCreateDate(rset.getDate("CREATE_DATE"));
				a.setStatus(rset.getString("STATUS"));
				a.setPetOwner(rset.getString("PET_OWNER"));
				a.setCount(rset.getInt("COUNT"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return a;
	}
	
	public Diary selectDate(Connection conn, String selectDate, int pno) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Diary d =null;
		
		String sql = prop.getProperty("selectDate");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selectDate);
			pstmt.setInt(2, pno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				d = new Diary();
				d.setDiaryNo(rset.getInt("DIARY_NO"));
				d.setPetWeight(rset.getInt("PET_WEIGHT"));
				d.setPetWalk(rset.getInt("PET_WALK"));
				d.setPetFood(rset.getInt("PET_FOOD"));
				d.setPetPee(rset.getString("PET_PEE"));
				d.setPetRecord(rset.getString("PET_RECORD"));
				d.setWriteDate(rset.getString("WRITE_DATE"));
				d.setStatus(rset.getString("STATUS"));
				d.setPetNo(rset.getInt("PET_NO"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return d;
	}
	public int insertDiary(Connection conn, Diary d) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertDiary");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, d.getPetWeight());
			pstmt.setInt(2, d.getPetWalk());
			pstmt.setInt(3, d.getPetFood());
			pstmt.setString(4, d.getPetPee());
			pstmt.setString(5, d.getPetRecord());
			pstmt.setString(6, d.getWriteDate());
			pstmt.setInt(7, d.getPetNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public Diary selectDiary(Connection conn, int dno) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Diary d =null;
		
		String sql = prop.getProperty("selectDiary");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				d = new Diary(rset.getInt("DIARY_NO"),
							  rset.getInt("PET_WEIGHT"),
							  rset.getInt("PET_WALK"),
							  rset.getInt("PET_FOOD"),
							  rset.getString("PET_PEE"),
							  rset.getString("PET_RECORD"),
							  rset.getString("WRITE_DATE"),
							  rset.getInt("PET_NO"));
								
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return d;
	}
	public int updateDiary(Connection conn, Diary d) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateDiary");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, d.getPetWeight());
			pstmt.setInt(2, d.getPetWalk());
			pstmt.setInt(3, d.getPetFood());
			pstmt.setString(4, d.getPetPee());
			pstmt.setString(5, d.getPetRecord());
			pstmt.setInt(6, d.getDiaryNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	public int deleteDiary(Connection conn, int dno) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteDiary");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dno);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	public int selectDiaryListCount(Connection conn) {
		
		// SELECT => ResultSet => 근데 반환형은 int
		// 변수
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDiaryListCount");
		
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
	public ArrayList<Diary> selectDiaryList(Connection conn, PageInfo pi, int pno) {
		
		// SELECT문 => RESULTSET => 여러행 이므로 ArrayList<Board>
		// 변수
		ArrayList<Diary> list = new ArrayList();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDiaryList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, pno);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Diary(rset.getInt("DIARY_NO"),
								   rset.getInt("PET_WEIGHT"),
								   rset.getInt("PET_WALK"),
								   rset.getInt("PET_FOOD"),
								   rset.getString("PET_PEE"),
								   rset.getString("PET_RECORD"),
								   rset.getString("WRITE_DATE"),
								   rset.getInt("PET_NO")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	

}
