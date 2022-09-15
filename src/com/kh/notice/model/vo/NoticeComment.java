package com.kh.notice.model.vo;

import java.sql.Date;

public class NoticeComment {

	private int cmtNo; //CMT_NO NUMBER PRIMARY KEY, --댓글 번호 REFERENCES COMMENTS.COMMENT_NO
    private String cmtWriter; //CMT_WRITER VARCHAR2(100) NOT NULL, --댓글 작성자
    private String cmtContent; //CMT_CONTENT VARCHAR2 (4000) NOT NULL, --댓글 내용
    private int refNno; //REF_NNO NUMBER NOT NULL,
    private Date createDate; //CREATE_DATE DATE DEFAULT SYSDATE NOT NULL, --작성일
    private String status; //STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')), --상태값

	public NoticeComment() {
		super();
	}

	public NoticeComment(int cmtNo, String cmtWriter, String cmtContent, Date createDate) {
		super();
		this.cmtNo = cmtNo;
		this.cmtWriter = cmtWriter;
		this.cmtContent = cmtContent;
		this.createDate = createDate;
	}

	public NoticeComment(int cmtNo, String cmtWriter, String cmtContent, int refNno, Date createDate, String status) {
		super();
		this.cmtNo = cmtNo;
		this.cmtWriter = cmtWriter;
		this.cmtContent = cmtContent;
		this.refNno = refNno;
		this.createDate = createDate;
		this.status = status;
	}

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public String getCmtWriter() {
		return cmtWriter;
	}

	public void setCmtWriter(String cmtWriter) {
		this.cmtWriter = cmtWriter;
	}

	public String getCmtContent() {
		return cmtContent;
	}

	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}

	public int getRefNno() {
		return refNno;
	}

	public void setRefNno(int refNno) {
		this.refNno = refNno;
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
		return "NoticeComment [cmtNo=" + cmtNo + ", cmtWriter=" + cmtWriter + ", cmtContent=" + cmtContent
				+ ", refNno=" + refNno + ", createDate=" + createDate + ", status=" + status + "]";
	}

}
