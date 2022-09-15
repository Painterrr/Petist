package com.kh.admin.model.vo;

public class InquiryReply {
	private int replyNo;//REPLY_NO	NUMBER
	private String replyContent;//REPLY_CONTENT	VARCHAR2(400 BYTE)
	private int refIno;//REF_INO	NUMBER
	private String replyWriter;//REPLY_WRITER	VARCHAR2(20 BYTE)
	private String createDate;//CREATE_DATE	DATE
	private String status;//STATUS	VARCHAR2(1 BYTE)
	public InquiryReply() {
		super();
	}
	
	public InquiryReply(int replyNo, String replyContent, String replyWriter, String createDate) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
	}

	public InquiryReply(int replyNo, String replyContent, int refIno, String replyWriter, String createDate,
			String status) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refIno = refIno;
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
	public int getRefIno() {
		return refIno;
	}
	public void setRefIno(int refIno) {
		this.refIno = refIno;
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
		return "InquiryReply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refIno=" + refIno
				+ ", replyWriter=" + replyWriter + ", createDate=" + createDate + ", status=" + status + "]";
	}
	
	
	
	
	
}
