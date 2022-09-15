package com.kh.pet.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;
import com.kh.pet.model.dao.PetDao;
import com.kh.pet.model.vo.Animal;
import com.kh.pet.model.vo.Diary;

public class PetService {
	
	public int insertPet(Animal p, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new PetDao().insert1Pet(conn, p);
		int result2 = new PetDao().insert2Pet(conn, at);
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return (result1 * result2);
	}

	public ArrayList<Animal> selectPetList(String loginUser) {
		Connection conn = getConnection();
		
		ArrayList<Animal> list = new PetDao().selectPetList(conn, loginUser);
		
		close(conn);
		
		return list;
	}
	public Diary selectDate(String selectDate, int pno) {
		Connection conn = getConnection();
		
		Diary d = new PetDao().selectDate(conn, selectDate, pno);
		
		close(conn);

		return d;
	}
	public int deletePet(String petName, String memberPwd, String memberId) {
		Connection conn = getConnection();
		
		int result = new PetDao().deletePet(conn, petName, memberPwd, memberId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	public Animal selectAnimal(int pno) {
		Connection conn = getConnection();
		
		Animal a = new PetDao().selectAnimal(conn, pno);
		
		close(conn);
		
		return a;
		
		
		
		
	}
	public int insertDiary(Diary d) {
		Connection conn = getConnection();
		
		int result = new PetDao().insertDiary(conn, d);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;	
	}
	public Diary selectDiary(int dno) {
		Connection conn = getConnection();
		
		Diary diary = new PetDao().selectDiary(conn, dno);
		
		close(conn);
		
		return diary;	
	}
	public int updateDiary(Diary d) {
		Connection conn = getConnection();
		
		int result = new PetDao().updateDiary(conn, d);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;	
	}
	public int deleteDiary(int dno) {
		
		Connection conn = getConnection();
		
		int result = new PetDao().deleteDiary(conn, dno);
		
		if(result > 0) {
			commit(conn);
			
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	public int selectDiaryListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new PetDao().selectDiaryListCount(conn);
		
		
		close(conn);
		
		return listCount;
	}
	public ArrayList<Diary> selectDiaryList(PageInfo pi, int pno) {
	
		Connection conn = getConnection();
		
		ArrayList<Diary> list = new PetDao().selectDiaryList(conn, pi, pno);
		
		close(conn);
	
	return list;
}
	
}
