package com.kh.admin.model.vo;

import java.sql.Date;

public class Inquiry {
    private int inquiryNo;//MNO   NUMBER
    private String category;//CATEGORY_NO   NUMBER
    private String userId;//USER_ID   VARCHAR2(20 BYTE)
    private String inquiryTitle; 
    private String inquiryContent;//INQUIRY_CONTENT   VARCHAR2(4000 BYTE)
    private Date createDate;//CREATE_DATE   DATE
    private String status;// STATUS   VARCHAR2(1 BYTE)
    private String checkStatus;//
	public Inquiry() {
		super();
	}
	
	
	public Inquiry(int inquiryNo, String category, String userId, String inquiryTitle, String inquiryContent,
			Date createDate) {
		super();
		this.inquiryNo = inquiryNo;
		this.category = category;
		this.userId = userId;
		this.inquiryTitle = inquiryTitle;
		this.inquiryContent = inquiryContent;
		this.createDate = createDate;
	}


	public Inquiry(int inquiryNo, String category, String userId, String inquiryTitle, String inquiryContent,
			Date createDate, String checkStatus) {
		super();
		this.inquiryNo = inquiryNo;
		this.category = category;
		this.userId = userId;
		this.inquiryTitle = inquiryTitle;
		this.inquiryContent = inquiryContent;
		this.createDate = createDate;
		this.checkStatus = checkStatus;
	}
	

	public Inquiry(int inquiryNo, String category, String userId, String inquiryTitle, String inquiryContent,
			Date createDate, String status, String checkStatus) {
		super();
		this.inquiryNo = inquiryNo;
		this.category = category;
		this.userId = userId;
		this.inquiryTitle = inquiryTitle;
		this.inquiryContent = inquiryContent;
		this.createDate = createDate;
		this.status = status;
		this.checkStatus = checkStatus;
	}
	public int getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInquiryTitle() {
		return inquiryTitle;
	}
	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}
	public String getInquiryContent() {
		return inquiryContent;
	}
	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
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
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	@Override
	public String toString() {
		return "Inquiry [inquiryNo=" + inquiryNo + ", category=" + category + ", userId=" + userId + ", inquiryTitle="
				+ inquiryTitle + ", inquiryContent=" + inquiryContent + ", createDate=" + createDate + ", status="
				+ status + ", checkStatus=" + checkStatus + "]";
	}
    
    

	
   

}