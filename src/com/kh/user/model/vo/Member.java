package com.kh.user.model.vo;

public class Member {

	private String memberId; //MEMBER_ID	VARCHAR2(20 BYTE)
	private String memberPwd; //MEMBER_PWD	VARCHAR2(30 BYTE)
	private String memberName; //MEMBER_NAME	VARCHAR2(15 BYTE)
	private String memberFno; //MEMBER_FNO	VARCHAR2(15 BYTE)
	private String memberBno; //MEMBER_BNO	VARCHAR2(15 BYTE)
	private String gender; //GENDER	VARCHAR2(3 BYTE)
	private String phone; //PHONE	VARCHAR2(13 BYTE)
	private String email; //EMAIL	VARCHAR2(30 BYTE)
	private String status; //STATUS	VARCHAR2(1 BYTE) DEFAULT 'Y'
	private int memberCode; //MEMBER_CODE	NUMBER DEFAULT 1
	
	
	public Member() {
		super();
	}
	
	public Member(String memberId, String memberPwd, String memberName, String memberFno, String memberBno,
			String gender, String phone, String email, String status, int memberCode) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberFno = memberFno;
		this.memberBno = memberBno;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.status = status;
		this.memberCode = memberCode;
	}
	// 회원가입 
	public Member(String memberId, String memberPwd, String memberName, String memberFno, String memberBno,
			String gender, String phone, String email) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberFno = memberFno;
		this.memberBno = memberBno;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}
	// 내 정보 수정
	public Member(String memberId, String memberPwd, String memberName, String phone) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.phone = phone;
	}
	// 관리자 페이지
	public Member(String memberId, String memberName, String phone, String email, String status) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.phone = phone;
		this.email = email;
		this.status = status;
	}
	

	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberFno() {
		return memberFno;
	}
	public void setMemberFno(String memberFno) {
		this.memberFno = memberFno;
	}
	public String getMemberBno() {
		return memberBno;
	}
	public void setMemberBno(String memberBno) {
		this.memberBno = memberBno;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName
				+ ", memberFno=" + memberFno + ", memberBno=" + memberBno + ", gender=" + gender + ", phone=" + phone
				+ ", email=" + email + ", status=" + status + ", memberCode=" + memberCode + "]";
	}
	
	
}
