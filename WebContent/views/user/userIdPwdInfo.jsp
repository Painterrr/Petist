<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-아이디/비밀번호 찾기</title>
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
	margin-top: 30px;
}
#enroll-form tr,td{
	padding-top: 5px;
	padding-bottom: 5px;
	
}
#enroll-form table{
	width: 55%;
	height: 700px;
	border-radius: 5px;
}
.box{
	font-size: 25px;
	font-weight: 500;
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
	width: 18%;
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
.subTitle{
	font-size: 13px;
}
#backEnroll{
	margin-right: 80px;
}
#findId, #findPwd{
	margin-right: 80px;
	font-size: 13px;
	border-radius: 7px;
	margin-top: 5px;
	font-weight: 600;
}
#showId{
	font-size: 12px;
}
#table{
	border-radius: 7px;
	border-collapse: collapse;
	border-style: hidden;
	box-shadow: 0 0 0 2px #c6c5c5;
}

</style>
</head>
<body>

<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="content">
		<div class="content_1">
		</div>
		<div class="content_2">
			<div id="enroll-form">
				<table id="table" align="center">
					<thead>
						<tr id="login-header">
							<td width="58px"><img src="/Petist/resources/common/logo.png" alt="로고"></td>
							<td class="box" style="font-weight:bold;">아이디 · 비밀번호찾기&nbsp;&nbsp;&nbsp;</td>
							<td width="58px"> </td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td>
							<input type="radio" name="users" value="개인" style="width:15px; height:15px;" checked onclick="radioSelect();">개인회원
							<input type="radio" id="hospChecked" name="users" value="병원" style="width:15px; height:15px;" onclick="radioSelect();">병원회원
							</td>
							<td></td>
						</tr>
												
						<tr>
							<td></td>
							<td style="height:200px;">
								<div class="inputTitle">아이디 찾기</div>
								<div class="subTitle">이름</div>
								<input type="text" name="userName" required style="width: 314px; height: 34px;">
								<div class="subTitle" id="fnoTitle">주민등록번호(앞 6자리)</div>
								<input type="number" id="userFno" name="userFno" required style="width: 314px; height:34px;" maxlength="6" oninput="numberMaxLength(this);">
								<div align="right">
								<button type="button" id="findId" onclick="idFind();">아이디 찾기</button>
								</div>
							</td>
							<td></td>
						</tr>
						
						<tr>
							<td></td>	
							<td style="height:54px;"><div id="showId"> </div></td>	
							<td></td>	
						</tr>
						
						<tr>
							<td></td>
							<td style="height:200px;">
							<div class="inputTitle">비밀번호 찾기</div>
							<div>아이디</div>
							<input type="text" id="userId" name="userId" required required float="left;" style="width:314px; height:37px;">
							<div>이메일</div>
							<input type="text" id="email" name="email" required float="left;" style="width:314px; height:37px;">
							<div align="right">
							<button type="button" class="certification-btn" id="findPwd" onclick="emailCheck();">비밀번호 찾기</button>
							</div>
							</td>
							<td></td>
						</tr>
							
						
						<tr>
							<td></td>
							<td>
								<div align="center">
								<button type="button" id="backEnroll" class="box" onclick="history.back()">로그인</button> 
								</div>
							</td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="content_3">
		</div>
	</div>
<script>

	// 라디오 버튼에 따른 입력란 설정
	function radioSelect(){
		$("#userFno").val('');
		if( $("#hospChecked").is(':checked') ) {
			$("#fnoTitle").text("병원등록번호(8자리)");
			$("#userFno").attr('maxlength', 8);
		}else {
			$("#fnoTitle").text("주민등록번호(앞 6자리)");
			$("#userFno").attr('maxlength', 6);
		}
		
	}	
	
	// input타입 number에 maxlength설정
	function numberMaxLength(e){
	    if(e.value.length > e.maxLength){
	        e.value = e.value.slice(0, e.maxLength);
	    }
	}
	
	// 아이디 찾기
	function idFind(){
			var $userName = $("#enroll-form input[name=userName]");
			var $userFno = $("#enroll-form input[name=userFno]");
		$.ajax({
			url : "idFind.us",
			data : { userName : $userName.val(), userFno : $userFno.val() },
			success : function(userId){
				if(userId == "NNNNN"){ // 아이디 찾기 실패
					alert("아이디찾기 실패!");
				} else { // 아이디 찾기 성공
					$("#showId").text("회원님의 아이디는  '" + userId + "' 입니다.");
				}	
			},
			error : function(){
				alert("아이디 찾기용 비동기요청 실패");
			}
		});
	}
	
	// 이메일로 비밀번호 발송
	function emailCheck() {
		console.log("emailCheck");
		var $email = $("#enroll-form input[name=email]");
		var regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
			if(!regExpEmail.test($email.val())){ // 실패
				alert("이메일을 올바르게 입력해주세요.");
				$email.focus();
			}else{ // 성공
			
			$.ajax({
			   type : "post",
			   url : "pwdToEmail.us",
			   data: {"email" : $("#email").val(), "userId" : $("#userId").val() },
			   success : function(data) {
				   if(data != "NO"){
			      alert("비밀번호를 메일로 전송했습니다.!");
				   }else{
					   alert("찾을 수 없는 아이디 또는 이메일입니다.");
				   }
			    },
	   			error : function() {
		   		alert("이메일인증용 비동기요청 실패");
		   		}
			});  
		}
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

	<br clear="both"><br><br><br>

    <%@ include file="../common/footer.jsp" %>

</body>
</html>