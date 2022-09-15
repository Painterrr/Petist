package com.kh.common.model.vo;

import java.sql.Date;

public class Attachment {
	
	private int fileNo; // FILE_NO NUMBER PRIMARY KEY,
	private int refCno; // REF_CNO NUMBER NOT NULL,
	private String originName; // ORIGIN_NAME VARCHAR2(255) NOT NULL,
	private String changeName; // CHANGE_NAME VARCHAR2(255) NOT NULL,
	private String filePath;// FILE_PATH VARCHAR2(1000),
	private Date uploadDate; // UPLOAD_DATE DATE DEFAULT SYSDATE NOT NULL,
	private int fileLevel; // FILE_LEVEL NUMBER,
	private String status; // STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(STATUS IN('Y', 'N'))
	
	
	public Attachment() {
		super();
	}
	
	public Attachment(int fileNo, String originName, String changeName, String filePath) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
	}

	public Attachment(int fileNo, int refCno, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, String status) {
		super();
		this.fileNo = fileNo;
		this.refCno = refCno;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.status = status;
	}
	
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getRefCno() {
		return refCno;
	}
	public void setRefCno(int refCno) {
		this.refCno = refCno;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", refCno=" + refCno + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel
				+ ", status=" + status + "]";
	}
	
}
