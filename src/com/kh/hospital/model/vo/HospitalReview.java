package com.kh.hospital.model.vo;

import java.sql.Date;

public class HospitalReview {

	private int reviewNo; // "REVIEW_NO" NUMBER PRIMARY KEY,
	private int hospNo; // "HOSP_NO" NUMBER NOT NULL REFERENCES HOSPITAL_INFO(HOSP_NO),
	private String userId; // "USER_ID" VARCHAR2(20) NOT NULL,
	private int petNo; // "PET_NO" NUMBER,
	private String petKind; // PET_KIND VARCHAR2(9 BYTE),
	private String petBreed; // "PET_BREED"	VARCHAR2(50) NOT NULL,
	private int petAge; // "PET_AGE"	NUMBER NOT NULL,
	private String clinicDate; // "CLINIC_DATE" DATE NOT NULL,
	private String clinicInfo; // "CLINIC_INFO" VARCHAR2(300),
	private int price; // "PRICE"	NUMBER,
	private String content; // "CONTENT" VARCHAR2(4000),
	private Date createDate; // "CREATE_DATE" DATE DEFAULT SYSDATE	NOT NULL,
	private String status; // "STATUS" VARCHAR2(1) DEFAULT 'R' NOT NULL CHECK(STATUS IN ('R', 'Y', 'N', 'B'))
	
	
	public HospitalReview() {
		super();
	}
	
	public HospitalReview(int hospNo, String userId, int petNo, String petKind, String petBreed, int petAge,
			String clinicDate, String clinicInfo, int price, String content) {
		super();
		this.hospNo = hospNo;
		this.userId = userId;
		this.petNo = petNo;
		this.petKind = petKind;
		this.petBreed = petBreed;
		this.petAge = petAge;
		this.clinicDate = clinicDate;
		this.clinicInfo = clinicInfo;
		this.price = price;
		this.content = content;
	}

	public HospitalReview(int reviewNo, int hospNo, String userId, int petNo, String petKind, String petBreed,
			int petAge, String clinicDate, String clinicInfo, int price, String content) {
		super();
		this.reviewNo = reviewNo;
		this.hospNo = hospNo;
		this.userId = userId;
		this.petNo = petNo;
		this.petKind = petKind;
		this.petBreed = petBreed;
		this.petAge = petAge;
		this.clinicDate = clinicDate;
		this.clinicInfo = clinicInfo;
		this.price = price;
		this.content = content;
	}

	public HospitalReview(int reviewNo, int hospNo, String userId, int petNo, String petKind, String petBreed,
			int petAge, String clinicDate, String clinicInfo, int price, String content, Date createDate,
			String status) {
		super();
		this.reviewNo = reviewNo;
		this.hospNo = hospNo;
		this.userId = userId;
		this.petNo = petNo;
		this.petKind = petKind;
		this.petBreed = petBreed;
		this.petAge = petAge;
		this.clinicDate = clinicDate;
		this.clinicInfo = clinicInfo;
		this.price = price;
		this.content = content;
		this.createDate = createDate;
		this.status = status;
	}
	
	
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getHospNo() {
		return hospNo;
	}
	public void setHospNo(int hospNo) {
		this.hospNo = hospNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPetNo() {
		return petNo;
	}
	public void setPetNo(int petNo) {
		this.petNo = petNo;
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
	public int getPetAge() {
		return petAge;
	}
	public void setPetAge(int petAge) {
		this.petAge = petAge;
	}
	public String getClinicDate() {
		return clinicDate;
	}
	public void setClinicDate(String clinicDate) {
		this.clinicDate = clinicDate;
	}
	public String getClinicInfo() {
		return clinicInfo;
	}
	public void setClinicInfo(String clinicInfo) {
		this.clinicInfo = clinicInfo;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
	
	@Override
	public String toString() {
		return "HospitalReview [reviewNo=" + reviewNo + ", hospNo=" + hospNo + ", userId=" + userId + ", petNo=" + petNo
				+ ", petKind=" + petKind + ", petBreed=" + petBreed + ", petAge=" + petAge + ", clinicDate="
				+ clinicDate + ", clinicInfo=" + clinicInfo + ", price=" + price + ", content=" + content
				+ ", createDate=" + createDate + ", status=" + status + "]";
	}
	
		
}
