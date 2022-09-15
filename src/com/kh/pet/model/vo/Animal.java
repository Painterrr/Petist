package com.kh.pet.model.vo;

import java.sql.Date;

public class Animal {

	private int petNo; // PET_NO NUMBER
	private String petName; // PET_NAME VARCHAR2(20 BYTE)
	private String petInputNo; //PET_INPUTNO VARCHAR2(50 BYTE)
	private String petKind; // PET_KIND VARCHAR2(9 BYTE)
	private String petBreed; // PET_BREED VARCHAR2(50 BYTE)
	private String petBirth; // PET_BIRTH DATE
	private String petNeutral; // PET_NEUTRAL VARCHAR2(1 BYTE)
	private Date createDate; // CREATE_DATE DATE
	private String status; // STATUS VARCHAR2(1 BYTE)
	private String petOwner; // PET_OWNER VARCHAR2(20 BYTE)
	private int petAge;
	private String titleImg; //추가한것
	private int count; //get,set만들기
	
	
	
	public Animal() {
		super();
	}
	
	public Animal(int petNo, String petName, String petInputNo, String petKind, String petBreed, String petBirth,
			String petNeutral, Date createDate, String status, String petOwner) {
		super();
		this.petNo = petNo;
		this.petName = petName;
		this.petInputNo = petInputNo;
		this.petKind = petKind;
		this.petBreed = petBreed;
		this.petBirth = petBirth;
		this.petNeutral = petNeutral;
		this.createDate = createDate;
		this.status = status;
		this.petOwner = petOwner;
	}
	public Animal(int petNo, String petName, String petInputNo, String petKind, String petBreed, String petBirth,
			String petNeutral, Date createDate, String status, String petOwner, int petAge, int count) {
		super();
		this.petNo = petNo;
		this.petName = petName;
		this.petInputNo = petInputNo;
		this.petKind = petKind;
		this.petBreed = petBreed;
		this.petBirth = petBirth;
		this.petNeutral = petNeutral;
		this.createDate = createDate;
		this.status = status;
		this.petOwner = petOwner;
		this.petAge = petAge;
		this.count = count;
	}
	
	
	public String getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	public int getPetNo() {
		return petNo;
	}
	public void setPetNo(int petNo) {
		this.petNo = petNo;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getPetInputNo() {
		return petInputNo;
	}
	public void setPetInputNo(String petInputNo) {
		this.petInputNo = petInputNo;
	}
	public String getPetKind() {
		return petKind;
	}
	public void setPetKind(String petKind) {
		this.petKind = petKind;
	}
	public String getPetBreed() {
		return petBreed;
	}
	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}
	public String getPetBirth() {
		return petBirth;
	}
	public void setPetBirth(String petBirth) {
		this.petBirth = petBirth;
	}
	public String getPetNeutral() {
		return petNeutral;
	}
	public void setPetNeutral(String petNeutral) {
		this.petNeutral = petNeutral;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPetOwner() {
		return petOwner;
	}
	public void setPetOwner(String petOwner) {
		this.petOwner = petOwner;
	}
	
	public int getPetAge() {
		return petAge;
	}
	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	@Override
	public String toString() {
		return "Animal [petNo=" + petNo + ", petName=" + petName + ", petInputNo=" + petInputNo + ", petKind=" + petKind
				+ ", petBreed=" + petBreed + ", petBirth=" + petBirth + ", petNeutral=" + petNeutral + ", createDate="
				+ createDate + ", status=" + status + ", petOwner=" + petOwner + "]";
	}
	
}
