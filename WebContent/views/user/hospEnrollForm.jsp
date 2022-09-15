<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-회원가입(병원)</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<script src="https://code.jquery.com/jquery-3.6.0.js"
   integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
   crossorigin="anonymous"></script>
<style>
#login-header{
	background-color: rgb(26, 188, 156);
	height: 17%;
}
#enroll-form{
	text-align: left;
}
#enroll-form tr,td{
	padding-top: 5px;
	padding-bottom: 5px;
	
}
#enroll-form table{
	width: 55%;
	height: 900px;
	border-radius: 5px;
}
.box{
	font-size: 35px;
	font-weight: 700;
	text-align: center;
	color: white;
}
button{
	display:inline-block;
	padding: 5px;
	background-color: rgb(26, 188, 156);
	border: none;
	color : white;
}
#enroll-form input{
	width: 80%;
	height: 50%;
	display:inline-block;
	padding: 5px;
	border-radius: 7px;
}
#title-logo{
	margin-left: 10px;
}
.inputTitle{
	text-align: left;
	margin-left: 4px;
	font-weight: bold;
}
tr>td:first-child{
	width: 17%;
}
tr>td:last-child{
	width: 8%;
}
#checkId{
	width: 90px; 
	height: 36px;
	border-radius: 7px;
	margin-left: 45px;
}
#enrollbutton{
	width: 170px;	
	border-radius: 7px;
}
.check{
	font-size: 10px;
}
.checkFalse{	
	color : rgb(248, 26, 26);
}
.checkTrue{
	color : rgb(26, 188, 156);
	font-weight : 300;	
}
#backEnroll{
	margin-left: 10px;
	margin-right: 20px;
	border-radius: 7px;
}
.certification-btn{
	border-radius: 7px;
}
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

</style>	
</head>
<body>
	
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="content">
		<div class="content_1">
		</div>
		<div class="content_2">
			<form id="enroll-form" method="post" action="<%= contextPath %>/hospInsert.us">
				<table style="border: 1px solid rgb(198, 197, 197)" align="center">
					<thead>
						<tr id="login-header">
							<td width="58px"><img src="/Petist/resources/common/logo.png" alt="로고"></td>
							<td class="box">회원가입(병원)&nbsp;&nbsp;&nbsp;</td>
							<td width="58px"> </td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td>
								<div align="right" style="font-size: 10px; padding-right: 85px;">*는 필수입력사항입니다.</div>
								<div class="inputTitle">*아이디</div>
								<input type="text" name="hospId" required maxlength="12" style="height: 34px;">
								
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div>
									<div style="font-size: 11px; float: left;">
									영소문자와 숫자를 조합해<br> 4자이상 12자이하로 입력해주세요.
									</div>
									<button type="button" id="checkId" onclick="idCheck();">중복체크</button>
								</div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div class="check checkFalse" id="checkIdIf" style="display:none;">조건에맞게입력해주세요.</div>
								<div class="check checkFalse" id="checkIdFalse" style="display:none;">중복된아이디입니다.</div>
								<div class="check checkTrue" id="checkIdTrue" style="display:none;">사용가능한아이디입니다.</div>
							</td>
							<td></td>
						</tr>
						<tr> 
							<td></td>
							<td>
								<div class="inputTitle">*비밀번호</div>
								<input type="password" name="hospPwd" required maxlength="16" onkeyup="pwdCheck();">
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div style="font-size: 10px;">
									영문, 숫자, 특수문자 중 2가지 이상 조합하여 <br>
									8자이상 16자 이하로 입력해주세요.
								</div>
								<div class="check checkFalse" id="checkPwdFalse" style="display:none;">조건에맞게입력해주세요.</div> 
								<div class="check checkTrue" id="checkPwdTrue" style="display:none;">사용가능한비밀번호입니다.</div> 
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div class="inputTitle">*비밀번호 확인</div>
								<input type="password" id="samePwd" required maxlength="16" onkeyup="samePwdCheck();">
								
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div class="check checkFalse" id="samePwdFalse" style="display:none;">비밀번호가 일치하지 않습니다.</div>
								<div class="check checkTrue" id="samePwdTrue" style="display:none;">비밀번호가 일치합니다.</div>
							</td>
							<td></td>
						</tr>	
						<tr>
							<td></td>
							<td>
								<div class="inputTitle">*병원명</div>
								<input type="text" name="hospName" required onkeyup="nameCheck();">
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div class="check checkFalse" id="nameFalse" style="display:none;">다시 입력해주세요.</div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<span class="inputTitle">*병원등록번호 </span><span style="font-size:11px;">(사업자등록번호 8자리)</span>
								<input type="number" name="hospCrn" required onkeyup="crnCheck();" maxlength="8" oninput="numberMaxLength(this);">
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div class="check checkTrue" id="crnTrue" style="display:none;">사용가능</div>
								<div class="check checkFalse" id="crnFalse" style="display:none;">다시입력해주세요.</div>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div class="inputTitle">*이메일</div>
								<input type="text" id="email" name="email" required onkeyup="emailCheck();" float="left;" style="width:250px; height:37px;">
								<button type="button" id="certificationBtn" class="certification-btn" style="width:60px; height:37px;" disabled>인증</button>
							</td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<div class="check checkTrue" id="emailTrue" style="display:none;">사용가능</div>
								<div class="check checkFalse" id="emailFalse" style="display:none;">다시입력해주세요.</div>
							</td>
							<td></td>
						</tr>		
						<tr>
							<td></td>
							<td>		
								<input type="text" id="randomCode" maxlength="6" placeholder="인증번호를 입력하세요." style="width:250px; height:37px;" >
								<button type="button" id="ranNumCheck" class="certification-btn" style="width:60px; height:37px;">확인</button>								
							</td>
							<td></td>
						</tr>
						
						<tr>
							<td colspan="3">
								<div align="center">
								<button type="button" id="enrollbutton" class="box" onclick="enroll();">회원가입</button>	
								<button type="button" id="backEnroll" class="box" onclick="history.back()">취소</button> 
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div class="content_3">
		</div>
	</div>
	<script>
	 // 아이디 체크
		function idCheck(){ 
			// 중복체크(ajax)
			var $hospId = $("#enroll-form input[name=hospId]");
			var ok0;
			$.ajax({
				url : "idCheck.us",
				data : {checkId : $hospId.val()},
				async : false,
				success : function(result){	
					if(result == "NNNNN"){ // 중복된 아이디일 경우
						$("#checkIdFalse").attr('style',"display:block;");
						$("#checkIdTrue").attr('style',"display:none;");
						$("#checkIdIf").attr('style',"display:none;");
						$hospId.focus();
					}
					else { // 중복이 아닌 아이디일 경우
						// 유효성 검사
						var regExpId = /^[a-z0-9]{4,12}$/;
						if(!regExpId.test($hospId.val())){ // 조건에 맞지 않는 아이디일 경우
							$("#checkIdIf").attr('style',"display:block;");
							$("#checkIdTrue").attr('style',"display:none;");
							$("#checkIdFalse").attr('style',"display:none;");
							$hospId.focus();
							
						}else { // 중복x + 조건o인 아이디일 경우
							$("#checkIdTrue").attr('style',"display:block;");
							$("#checkIdFalse").attr('style',"display:none;");
							$("#checkIdIf").attr('style',"display:none;");
							ok0 = result;
						}
					}
				},
				error : function(){
					alert("아이디 중복체크용 비동기요청 실패");
				}
			});
			return ok0;
		}
	// 비밀번호 체크
			var $hospPwd = $("#enroll-form input[name=hospPwd]");
			
			var regExpPwd = /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?=[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{8,16}$/;
			
		function pwdCheck(){
		
			if($hospPwd.val() != ""){
				
				if(!regExpPwd.test($hospPwd.val())){ // 조건에 맞지 않는 비밀번호일 경우
					$("#checkPwdFalse").attr('style',"display:block");
					$("#checkPwdTrue").attr('style', "display:none");
					
				} else { // 사용가능한 비밀번호
					$("#checkPwdFalse").attr('style',"display:none");
					$("#checkPwdTrue").attr('style', "display:block");
					var ok1 = "Y";
					return ok1;
				}
			} else { // 비밀번호 빈 문자열일 경우
				$("#checkPwdFalse").attr('style',"display:none");
				$("#checkPwdTrue").attr('style', "display:none");
				$("#samePwdFalse").attr('style',"display:none");
				$("#samePwdTrue").attr('style',"display:none");
			}	
		}	
	// 비밀번호 확인
		function samePwdCheck(){
			if(regExpPwd.test($hospPwd.val())){ // 일단 비밀번호는 사용가능일 경우
				if($("#samePwd").val() != $hospPwd.val()){ // 비밀번호와 비밀번호 확인 불일치
					$("#samePwdFalse").attr('style',"display:block");
					$("#samePwdTrue").attr('style',"display:none");
				} else{	// 비밀번호와 비밀번호 확인 일치
					$("#samePwdFalse").attr('style',"display:none");
					$("#samePwdTrue").attr('style',"display:block");
					var ok2 = "Y";
					return ok2;
				}
			} else { // 비밀번호가 사용불가능(안보여줘도된다.)
				$("#samePwdFalse").attr('style',"display:none");
				$("#samePwdTrue").attr('style',"display:none");
			}
			
		}
	// 병원명
		function nameCheck(){
			var $hospName = $("#enroll-form input[name=hospName]");
			var regExpName = /^[a-zA-Z가-힣]{2,}$/;
			if(!regExpName.test($hospName.val())){ // 이름을 잘못 입력한 경우
				$("#nameFalse").attr('style',"display:block");
			} else { // 사용 가능 이름
				$("#nameFalse").attr('style',"display:none");
				var ok3 = "Y";
				return ok3;
			}
			if($hospName.val() == ""){
				$("#nameFalse").attr('style',"display:none");
			}
		}
	// 병원등록번호 maxlength설정
		function numberMaxLength(e){
		    if(e.value.length > e.maxLength){
		        e.value = e.value.slice(0, e.maxLength);
	   	 	}
		}
	
	// 병원등록번호
		function crnCheck(){
			var $hospCrn = $("#enroll-form input[name=hospCrn]");
			var regExpCrn = /^[0-9]{8}$/;
			if(!regExpCrn.test($hospCrn.val())){ // 병원등록번호 잘못 입력한 경우
				$("#crnTrue").attr('style',"display:none");
				$("#crnFalse").attr('style',"display:block");
			} else{
				$("#crnTrue").attr('style',"display:block");
				$("#crnFalse").attr('style',"display:none");
				var ok4 = "Y";
				return ok4;
			}
			if($hospCrn.val() == ""){
				$("#crnFalse").attr('style',"display:none");
				$("#crnTrue").attr('style',"display:none");
			}
		
	}
	
	
	// 이메일
	
		function emailCheck(){
		
			var $email = $("#enroll-form input[name=email]");
			
			var regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	
			if(!regExpEmail.test($email.val())){ // 실패
				$("#emailTrue").attr('style',"display:none");
				$("#emailFalse").attr('style',"display:block");
			}else{ // 성공
				$("#emailTrue").attr('style',"display:block");
				$("#emailFalse").attr('style',"display:none");
				$("#certificationBtn").removeAttr("disabled");
				var ok5 = "Y";
				return ok5;
			}
				if($email.val()==""){
					$("#emailTrue").attr('style',"display:none");
					$("#emailFalse").attr('style',"display:none");
				}
		}
	
	// 입력값에 따른 회원가입 요청 버튼 함수
	
		function enroll(){
			var success = "";
			success = idCheck() + pwdCheck() + samePwdCheck() + nameCheck() + crnCheck() + emailCheck();
			console.log(success);
			if(success == "NNNNYYYYYY") { // 조건에 모두 만족할 경우 회원가입이 가능
				$("#enrollbutton").attr('type',"submit");
			} else {
				alert("조건에 맞게 다시 입력해주세요");
				return false;
			}
		}
	
		//이메일인증 > 인증버튼 누르자  
			// function certification(){ }
        document.getElementById("certificationBtn").onclick = function() {
           console.log("certificationEmail");
           alert("인증코드 발송!");
           let email = $("#email").val();
           
            $.ajax({
           type : "post",
           //contentType : "application/json",
           url : "certificationEmail.us",
           data: {"email":email},
           //data: JSON.stringify(data),
           //dataType : 'json',
           //cache : false,
           timeout : 600000,
           success : function(data) {
              
              document.getElementById("ranNumCheck").onclick = function() {

                 
                 let randomCode = /[0~9]{6}w/;

                 console.log("${rs}");   
                 if ($("#randomCode").val()==data) {
                    alert("인증성공");
                    $("#email").attr("readonly", true);
                    $("#randomCode").attr("readonly", true);
                 } else {
                    alert("인증실패");
                 }
              }
           },
           error : function() {
        	   alert("이메일인증용 비동기요청 실패");
           }
        });  
	
           
        }
        
	     // 스위트알러창
	        function alert(a){
	        	Swal.fire({
					  title: 'Petist',
					  text: a,
					  imageUrl: '/Petist/resources/common/logo_mint.png',
					  imageWidth: 130,
					  imageHeight: 110,
					  confirmButtonText: '확인',
					  confirmButtonColor: '#1abc9c'
					});
	        }
	
	
	</script>
</body>

	<br clear="both"><br><br>

<%@ include file="/views/common/footer.jsp" %>

</body>
</html>