package com.kh.user.model.vo;

public class Hospital {

	private String hospId; //HOSP_ID	VARCHAR2(20 BYTE)
	private String hospPwd; //HOSP_PWD	VARCHAR2(30 BYTE)
	private String hospName; // HOSP_NAME VARCHAR2(20 BYTE)
	private int hospCrn; //HOSP_CRN	NUMBER
	private String hospEmail; //HOSP_EMAIL	VARCHAR2(30 BYTE)
	private String status; //STATUS	VARCHAR2(1 BYTE)
	private int hospCode; //HOSP_CODE	NUMBER
	
	public Hospital() {
		super();
	}
	// 전체
	public Hospital(String hospId, String hospPwd, String hospName, int hospCrn, String hospEmail, String status,
			int hospCode) {
		super();
		this.hospId = hospId;
		this.hospPwd = hospPwd;
		this.hospName = hospName;
		this.hospCrn = hospCrn;
		this.hospEmail = hospEmail;
		this.status = status;
		this.hospCode = hospCode;
	}
	// 회원가입
	public Hospital(String hospId, String hospPwd, String hospName, int hospCrn, String hospEmail) {
		super();
		this.hospId = hospId;
		this.hospPwd = hospPwd;
		this.hospName = hospName;
		this.hospCrn = hospCrn;
		this.hospEmail = hospEmail;
	}

	public String getHospId() {
		return hospId;
	}
	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	public String getHospPwd() {
		return hospPwd;
	}
	public void setHospPwd(String hospPwd) {
		this.hospPwd = hospPwd;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public int getHospCrn() {
		return hospCrn;
	}
	public void setHospCrn(int hospCrn) {
		this.hospCrn = hospCrn;
	}
	public String getHospEmail() {
		return hospEmail;
	}
	public void setHospEmail(String hospEmail) {
		this.hospEmail = hospEmail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getHospCode() {
		return hospCode;
	}
	public void setHospCode(int hospCode) {
		this.hospCode = hospCode;
	}
	
	@Override
	public String toString() {
		return "Hospital [hospId=" + hospId + ", hospPwd=" + hospPwd + ", hospName=" + hospName + ", hospCrn=" + hospCrn
				+ ", hospEmail=" + hospEmail + ", status=" + status + ", hospCode=" + hospCode + "]";
	}
	
	
}
