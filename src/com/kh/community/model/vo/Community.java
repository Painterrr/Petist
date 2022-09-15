package com.kh.community.model.vo;

import java.sql.Date;

public class Community {
	
	

	private int commNo;  		//"COMM_NO" NUMBER PRIMARY KEY,
	private String commTitle; 	//"COMM_TITLE" VARCHAR2(100) NOT NULL,
	private String commContent;	//"COMM_CONTENT" VARCHAR2(4000) NOT NULL,
	private String commWriter;	//"COMM_WRITER" VARCHAR2(100) NOT NULL,
	private int count;			//"COMM_COUNT" NUMBER	DEFAULT 0,
	private String category;	// "CATEGORY_NO" NUMBER,
	private Date createDate;	//"CREATE_DATE" DATE DEFAULT SYSDATE NOT NULL,
	private String status;		//"STATUS" VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N'))
	private int commType;		// "COMM_TYPE" NUMBER DEFAULT 1 CHECK (STATUS IN (1, 2)),
	
	
	public Community() {
		super();
	}
	
	
	
	
	public Community(String commTitle, String commContent, String commWriter, String category) {
		super();
		this.commTitle = commTitle;
		this.commContent = commContent;
		this.commWriter = commWriter;
		this.category = category;
	}




	public Community(int commNo, String commTitle, String commContent, String commWriter, int count, String category,
			Date createDate, String status, int commType) {
		super();
		this.commNo = commNo;
		this.commTitle = commTitle;
		this.commContent = commContent;
		this.commWriter = commWriter;
		this.count = count;
		this.category = category;
		this.createDate = createDate;
		this.status = status;
		this.commType = commType;
	}

	



	public Community(int commNo, String category, String commTitle, String commContent, String commWriter,   Date createDate) {
		super();
		this.commNo = commNo;
		this.commTitle = commTitle;
		this.commContent = commContent;
		this.commWriter = commWriter;
		this.category = category;
		this.createDate = createDate;
	}



	public Community(int commNo, String commTitle, String commWriter, int count, String category, Date createDate) {
		super();
		this.commNo = commNo;
		this.commTitle = commTitle;
		this.commWriter = commWriter;
		this.count = count;
		this.category = category;
		this.createDate = createDate;
	}


	public int getCommNo() {
		return commNo;
	}


	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}


	public String getCommTitle() {
		return commTitle;
	}


	public void setCommTitle(String commTitle) {
		this.commTitle = commTitle;
	}


	public String getCommContent() {
		return commContent;
	}


	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}


	public String getCommWriter() {
		return commWriter;
	}


	public void setCommWriter(String commWriter) {
		this.commWriter = commWriter;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
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
		return "Community [commNo=" + commNo + ", commTitle=" + commTitle + ", commContent=" + commContent
				+ ", commWriter=" + commWriter + ", count=" + count + ", category=" + category + ", createDate="
				+ createDate + ", status=" + status + ", commType=" + commType + "]";
	}


	
	
		
	
}
