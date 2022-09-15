package com.kh.community.model.vo;

public class Reply {
	
	
	
	private int replyNo;            // REPLY_NO NUMBER
	private String replyContent;	// REPLY_CONTENT
	private int refBno;				// REF_BNO NUMBEWR
	private String replyWriter;     // REPLY_WRITER NUMBER
	private String createDate;      // CREATEA_DATE DATE
	private String status;			// STATUS VARCHAR2(1 BYTE)
	
	
	
	public Reply() {
		super();
	}

	

	public Reply(int replyNo, String replyContent, String replyWriter) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
	}



	public Reply(int replyNo, String replyContent, String replyWriter, String createDate) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
	}



	public Reply(int replyNo, String replyContent, int refBno, String replyWriter, String createDate, String status) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.status = status;
	}



	public int getReplyNo() {
		return replyNo;
	}



	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}



	public String getReplyContent() {
		return replyContent;
	}



	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}



	public int getRefBno() {
		return refBno;
	}



	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}



	public String getReplyWriter() {
		return replyWriter;
	}



	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}



	public String getCreateDate() {
		return createDate;
	}



	public void setCreateDate(String createDate) {
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
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refBno=" + refBno + ", replyWriter="
				+ replyWriter + ", createDate=" + createDate + ", status=" + status + "]";
	}
	
	
	

}
