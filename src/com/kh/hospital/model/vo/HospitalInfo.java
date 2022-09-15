package com.kh.hospital.model.vo;

import java.sql.Date;

public class HospitalInfo {

	private int hospNo; // HOSP_NO NUMBER PRIMARY KEY,
    private String hospId; // HOSP_ID VARCHAR2(20) NOT NULL REFERENCES HOSPITAL(HOSP_ID),
    private String hospName; // HOSP_NAME VARCHAR2(60) NOT NULL,
    private String address; // ADDRESS VARCHAR2(300) NOT NULL,
    private String addressDetail; // ADDRESS_DETIL VARCHAR2(300),
    private double yCoordinate; // Y_COORDINATE NUMBER NOT NULL,
    private double xCoordinate; // X_COORDINATE NUMBER NOT NULL,
    private String opertingTime; // OPERATING_TIME VARCHAR2(1000),
    private String holiday; // HOLIDAY VARCHAR2(1000),
    private String parking; // PARKING VARCHAR2(100),
    private String tel; // TEL VARCHAR2(13) NOT NULL,
    private String homepage; // HOMEPAGE VARCHAR2(300),
    private String intoduce; // INTRODUCE VARCHAR2(4000) NOT NULL,
    private Date createDate; // CREATE_DATE DATE DEFAULT SYSDATE,
    private String status; // STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N'))
	private int reviewCount;
	private String titleImg;
	
	
	
	public HospitalInfo() {
		super();
	}
	
	public HospitalInfo(String hospId, String hospName, String address, String addressDetail, double yCoordinate,
			double xCoordinate, String opertingTime, String holiday, String parking, String tel, String homepage,
			String intoduce) {
		super();
		this.hospId = hospId;
		this.hospName = hospName;
		this.address = address;
		this.addressDetail = addressDetail;
		this.yCoordinate = yCoordinate;
		this.xCoordinate = xCoordinate;
		this.opertingTime = opertingTime;
		this.holiday = holiday;
		this.parking = parking;
		this.tel = tel;
		this.homepage = homepage;
		this.intoduce = intoduce;
	}

	public HospitalInfo(int hospNo, String hospId, String hospName, String address, String addressDetail,
			double yCoordinate, double xCoordinate, String opertingTime, String holiday, String parking, String tel,
			String homepage, String intoduce) {
		super();
		this.hospNo = hospNo;
		this.hospId = hospId;
		this.hospName = hospName;
		this.address = address;
		this.addressDetail = addressDetail;
		this.yCoordinate = yCoordinate;
		this.xCoordinate = xCoordinate;
		this.opertingTime = opertingTime;
		this.holiday = holiday;
		this.parking = parking;
		this.tel = tel;
		this.homepage = homepage;
		this.intoduce = intoduce;
	}

	public HospitalInfo(int hospNo, String hospId, String hospName, String address, String addressDetail,
			double yCoordinate, double xCoordinate, String opertingTime, String holiday, String parking, String tel,
			String homepage, String intoduce, int reviewCount) {
		super();
		this.hospNo = hospNo;
		this.hospId = hospId;
		this.hospName = hospName;
		this.address = address;
		this.addressDetail = addressDetail;
		this.yCoordinate = yCoordinate;
		this.xCoordinate = xCoordinate;
		this.opertingTime = opertingTime;
		this.holiday = holiday;
		this.parking = parking;
		this.tel = tel;
		this.homepage = homepage;
		this.intoduce = intoduce;
		this.reviewCount = reviewCount;
	} 


	public HospitalInfo(int hospNo, String hospId, String hospName, String address, String addressDetail,
			double yCoordinate, double xCoordinate, String opertingTime, String holiday, String parking, String tel,
			String homepage, String intoduce, int reviewCount, String titleImg) {
		super();
		this.hospNo = hospNo;
		this.hospId = hospId;
		this.hospName = hospName;
		this.address = address;
		this.addressDetail = addressDetail;
		this.yCoordinate = yCoordinate;
		this.xCoordinate = xCoordinate;
		this.opertingTime = opertingTime;
		this.holiday = holiday;
		this.parking = parking;
		this.tel = tel;
		this.homepage = homepage;
		this.intoduce = intoduce;
		this.reviewCount = reviewCount;
		this.titleImg = titleImg;
	}


	public HospitalInfo(int hospNo, String hospId, String hospName, String address, String addressDetail,
			double yCoordinate, double xCoordinate, String opertingTime, String holiday, String parking, String tel,
			String homepage, String intoduce, Date createDate, String status, int reviewCount, String titleImg) {
		super();
		this.hospNo = hospNo;
		this.hospId = hospId;
		this.hospName = hospName;
		this.address = address;
		this.addressDetail = addressDetail;
		this.yCoordinate = yCoordinate;
		this.xCoordinate = xCoordinate;
		this.opertingTime = opertingTime;
		this.holiday = holiday;
		this.parking = parking;
		this.tel = tel;
		this.homepage = homepage;
		this.intoduce = intoduce;
		this.createDate = createDate;
		this.status = status;
		this.reviewCount = reviewCount;
		this.titleImg = titleImg;
	}
	
	
	
	public int getHospNo() {
		return hospNo;
	}
	public void setHospNo(int hospNo) {
		this.hospNo = hospNo;
	}
	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public double getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public double getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public String getOpertingTime() {
		return opertingTime;
	}
	public void setOpertingTime(String opertingTime) {
		this.opertingTime = opertingTime;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getIntoduce() {
		return intoduce;
	}
	public void setIntoduce(String intoduce) {
		this.intoduce = intoduce;
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
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public String getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	
	
	@Override
	public String toString() {
		return "HospitalInfo [hospNo=" + hospNo + ", hospId=" + hospId + ", hospName=" + hospName + ", address="
				+ address + ", addressDetail=" + addressDetail + ", yCoordinate=" + yCoordinate + ", xCoordinate="
				+ xCoordinate + ", opertingTime=" + opertingTime + ", holiday=" + holiday + ", parking=" + parking
				+ ", tel=" + tel + ", homepage=" + homepage + ", intoduce=" + intoduce + ", createDate=" + createDate
				+ ", status=" + status + ", reviewCount=" + reviewCount + ", titleImg=" + titleImg + "]";
	}
    
	
}
