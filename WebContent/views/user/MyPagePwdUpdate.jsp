<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-비밀번호 변경</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
</head>
<style>
   #myPageMenu {
      font-size: 27px;
      font-weight:bolder;
      border-bottom: 1px solid black;
      width: 170px;
      text-align: center;
      color: rgb(26, 188, 156);
      margin-top: 30px;
   }   
   .content_1{
   		padding-right:50px;
   		padding-bottom:500px;
   		align:center;
   }
 
   .nav{
      width: 160px;
      margin: 15px;
   }
   .nav a{
   	 cursor: pointer;
   }
  #myInfo {
      font-size: 20px;
      font-weight: bold;
   }
   .navibar a:hover{
      text-decoration: none;
      color:black;
      font-weight: bold;
      text-decoration-line: none;
      text-decoration-color: black;
      
   }
   li>a{
      color: black;
   }
   li{
      list-style-type: none;
      font-size: 18px;
   }
   .content_2{
      text-align: center;
      
   }
	#updateTable input {
      width: 270px;
      margin-right: 40px;
      margin-top: 25px;
      border-radius: 7px;
   }
   .title{
      font-size: 17px;
      font-weight:600;
      
   }
   td:first-child {
      width: 150px;
      padding-left: 80px;
      text-align: right;
   }
   button{
      
      background-color: rgb(26, 188, 156);
      border: none;
      color : white;
	}
	table{
		border-collapse: collapse;
	  	border-radius: 10px;
	 	border-style: hidden;
	  	box-shadow: 0 0 0 1px #000;
	}
	
</style>   
<body>
<%@ include file="../common/menubar.jsp" %>

   <div class="content">
      <div class="content_1">
      	<div id="myPageMenu">마이페이지</div>
         <ul class="nav">
            <li id="myInfo">
              	 회원관리
            </li>
            <li> 
               <ul class="navibar">
                  <li>
                     <a href="<%= contextPath %>/myPage.us">내 정보 수정</a>
                  </li>
                  <li>
                  	<a onclick="window.location.reload()" style="font-weight: bold;">비밀번호 변경</a>
                  </li>
                  <li>
                     <a href="<%= contextPath %>/myPageDelete.us">회원 탈퇴</a>
                  </li>
                  <li>
                     <% if(loginUser != null && loginHosp == null) { %>
                     <a href="<%= contextPath %>/inquiry.in?memberId=<%= loginUser.getMemberId() %>&cpage=1">1대1 문의</a>
                     <% } else { %>
                     <a href="<%= contextPath %>/inquiry.in?memberId=<%= loginHosp.getHospId() %>&cpage=1">1대1 문의</a>
                     <% } %>
                  </li>
               </ul>
            </li>

         </ul>
      </div>
      
      		
      <div class="content_2">
            <form action="<%= contextPath %>/updatePwdMember.us" method="post">
            <div style="margin-right: 450px; margin-top: 50px; font-size: 20px; font-weight: bold;">비밀번호 변경</div>
            <br>
         <table style=" border-radius: 7px; width: 600px; height: 850px; " align="center" id="updateTable">
               
               <tr>
                  <td>
                     <span class="title">아이디</span>
                  </td>   
                  <td>
                     <input type="text" name="memberId" value ="<%= loginUser.getMemberId() %>" readonly>
                  </td>
                  
               </tr>
               <tr>
                  <td>
                     <span class="title">현재비밀번호</span>
                     </td>
                  <td>
                     <input type="password" name="memberPwd" onkeyup="memberPwd();" required>
                     <div id="memberPwdFalse" style="color: red; font-size: 12px; text-align: left; margin-left: 70px;"></div>
                  </td>
               </tr>
               <tr>    
                  <td>
                     <span class="title">새 비밀번호</span>
                     </td>
                     <td>
                        <input type="password" onkeyup="newPwd();" name="newPwd">
                        <div style="font-size: 12px; text-align: left; margin-left: 70px;">영문, 숫자, 특수문자 중 2가지 이상 조합해<br>
									8자이상 16자 이하로 입력해주세요.</div>
						<div id="newPwdFalse" style="color: red; font-size: 12px; text-align: left; margin-left: 70px;"></div>			
                     </td>
               </tr>  
               <tr>   
                  <td>
                     <span class="title">비밀번호 확인</span>
                     </td>
                     <td>
                        <input type="password" onkeyup="checkPwd();" name="checkPwd">
                        <div id="checkPwdFalse" style="color: red; font-size: 12px; text-align: left; margin-left: 70px;"></div>
                     </td>
               </tr>  
               
               <tr>
                  <td colspan="2" style="text-align: center;">
                     <button type="button" id="updateButton" style="margin-left:140px; font-size: 30px; border-radius: 7px;">확인</button>
                     <button type="button" style="margin-left: 10px; font-size: 30px; border-radius: 7px; background-color: rgb(164, 164, 164);" onclick="location.reload();">취소</button>
                  </td>
               </tr>
            
       </table>
            </form>
   
      </div> 

      <div class="content_3"></div>
      
      </div>
	<script>
	
	// 새 비밀번호
		// 사용자가 입력 시에는 빨간 글 안 보이게 하기
		var $newPwd = $('#updateTable input[name=newPwd]');

			function newPwd(){
				$("#newPwdFalse").text('');
			}
		// 사용자가 커서를 벗어나면 정규표현식에 따라 false면 빨간 글, true면 초록 글 보여주기 
			$newPwd.focusout(function() {
			var regExpPwd = /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?=[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{8,16}$/;
			if(!regExpPwd.test($newPwd.val())) {
				$("#newPwdFalse").text("조건에 맞지 않습니다.");
				
				} else {
					$("#newPwdFalse").text("사용가능");
					$("#newPwdFalse").css("color", 'rgb(26, 188, 156)');
				}
			if($newPwd.val() == "") {
				$('#newPwdFalse').text("");
			}
			});
		
	// 현재 비밀번호
		var $memberPwd = $('#updateTable input[name=memberPwd]');
	
			function memberPwd(){
				$("#memberPwdFalse").text('');
			}
		// 사용자가 커서를 벗어나면 현재비밀번호 일치 여부 보여주기
			$memberPwd.focusout(function() {
				if($memberPwd.val() == "<%= loginUser.getMemberPwd() %>") { // 현재 비밀번호를 맞게 썼다면
					$('#memberPwdFalse').text("비밀번호가 일치합니다.");
					$("#memberPwdFalse").css("color", 'rgb(26, 188, 156)');
				} else {
					$('#memberPwdFalse').text("비밀번호가 일치하지 않습니다.");
					$("#memberPwdFalse").css("color", 'red');
				}
				if($memberPwd.val() == "") {
					$('#memberPwdFalse').text("");
				}
			});
		
		// 비밀번호 확인	
			var $checkPwd = $('#updateTable input[name=checkPwd]');
				
				function checkPwd(){
					$("#checkPwdFalse").text('');
				}

				$checkPwd.focusout(function(){
					if($newPwd.val() == $checkPwd.val()){ //비밀번호 일치 시 버튼 타입 submit으로 입력값 전송
						$("#updateButton").attr('type','submit');
					}else{
						$("#checkPwdFalse").text("비밀번호가 일치하지 않습니다.");
						$("#checkPwdFalse").css("color", 'red');
					}
					if($checkPwd.val() == "") {
						$('#checkPwdFalse').text("");
					}
				});
	</script>	
      

   
   <br clear="both"><br><br><br>


    <%@ include file="../common/footer.jsp" %>
   



</body>
</html>