package com.kh.pet.model.vo;

import java.sql.Date;

public class Diary {
	private int diaryNo;//	DIARY_NO	NUMBER	No		1	일기 등록번호
	private int petWeight;//	PET_WEIGHT	NUMBER	Yes	0	2	반려동물 몸무게
	private int petWalk;//	PET_WALK	NUMBER	Yes	0	3	반려동물 산책거리
	private int petFood;//	PET_FOOD	NUMBER	Yes	0	4	반려동물 사료량
	private String petPee;//	PET_PEE	VARCHAR2(20 BYTE)	Yes	'말랑말랑'	5	반려동물 대소변상태
	private String petRecord;//	PET_RECORD	VARCHAR2(200 BYTE)	Yes	'그 외의 기록'	6	반려동물 그외의기록
	private String writeDate;//	WRITE_DATE	DATE	No		7	캘린더 날짜
	private String status;//	STATUS	VARCHAR2(1 BYTE)	Yes	'Y' 	8	상태
	private int petNo;//	PET_NO	NUMBER	No		9	반려동물 식별번호
	
	



	public Diary() {
		super();
	}




	public Diary(int petWeight, int petWalk, int petFood, String petPee, String petRecord, String writeDate) {
		super();
		this.petWeight = petWeight;
		this.petWalk = petWalk;
		this.petFood = petFood;
		this.petPee = petPee;
		this.petRecord = petRecord;
		this.writeDate = writeDate;
	}
	
	


	public Diary(int diaryNo, int petWeight, int petWalk, int petFood, String petPee, String petRecord, String writeDate, int petNo) {
		super();
		this.diaryNo = diaryNo;
		this.petWeight = petWeight;
		this.petWalk = petWalk;
		this.petFood = petFood;
		this.petPee = petPee;
		this.petRecord = petRecord;
		this.writeDate = writeDate;
		this.petNo = petNo;
	}




	public Diary(int diaryNo, int petWeight, int petWalk, int petFood, String petPee, String petRecord, String writeDate,
			String status, int petNo) {
		super();
		this.diaryNo = diaryNo;
		this.petWeight = petWeight;
		this.petWalk = petWalk;
		this.petFood = petFood;
		this.petPee = petPee;
		this.petRecord = petRecord;
		this.writeDate = writeDate;
		this.status = status;
		this.petNo = petNo;
	}



	public int getDiaryNo() {
		return diaryNo;
	}



	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}



	public int getPetWeight() {
		return petWeight;
	}



	public void setPetWeight(int petWeight) {
		this.petWeight = petWeight;
	}



	public int getPetWalk() {
		return petWalk;
	}



	public void setPetWalk(int petWalk) {
		this.petWalk = petWalk;
	}



	public int getPetFood() {
		return petFood;
	}



	public void setPetFood(int petFood) {
		this.petFood = petFood;
	}



	public String getPetPee() {
		return petPee;
	}



	public void setPetPee(String petPee) {
		this.petPee = petPee;
	}



	public String getPetRecord() {
		return petRecord;
	}



	public void setPetRecord(String petRecord) {
		this.petRecord = petRecord;
	}



	public String getWriteDate() {
		return writeDate;
	}



	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getPetNo() {
		return petNo;
	}



	public void setPetNo(int petNo) {
		this.petNo = petNo;
	}



	@Override
	public String toString() {
		return "Diary [diaryNo=" + diaryNo + ", petWeight=" + petWeight + ", petWalk=" + petWalk + ", petFood="
				+ petFood + ", petPee=" + petPee + ", petRecord=" + petRecord + ", writeDate=" + writeDate + ", status="
				+ status + ", petNo=" + petNo + "]";
	}
	
	
	
	
}
