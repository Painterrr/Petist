<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-마이페이지</title>
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
  #myInfo {
      font-size: 20px;
      font-weight: bold;
   }
   .nav a:hover{
      text-decoration: none;
      color:black;
      font-weight: bold;
      text-decoration-line: none;
      text-decoration-color: black;
   }
   .nav a{
   	 cursor: pointer;
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
               <ul>
                  <li>
                     <a onclick="window.location.reload()" style="font-weight: bold;">내 정보 수정</a>
                  </li>
                  <li>
                  	 <a href="<%= contextPath %>/myPagePwdUpdate.us">비밀번호 변경</a>
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
            <form action="<%= contextPath %>/updateMember.us" method="post">
            <div style="margin-right: 450px; margin-top: 50px; font-size: 20px; font-weight: bold;">내 정보 수정</div>
            <br>
         <table style=" border-radius: 7px; width: 600px; height: 750px; " align="center" id="updateTable">
               
               <tr>
                  <td>
                     <span class="title">아이디</span>
                     </td> 		
                  <td>
                      <input type="hidden" name="memberId" value="<%= loginUser.getMemberId() %>">
                      <div style="font-size: 17px; text-align: left; margin-left: 70px; margin-top: 5px"><%= loginUser.getMemberId() %></div>
                      <div style="font-size: 11px; text-align: left; margin-left: 70px;">아이디는 변경하실 수 없습니다.</div>
                  </td>
               </tr>
               <tr>
                  <td>
                     <span class="title">이름</span>
                  </td>
                  <td>
                     <input type="text" name="memberName" value="<%= loginUser.getMemberName() %>" onkeyup="memberName();"><br><br>
                     <div id="memberNameFalse" style="color: red; font-size: 12px; text-align: left; margin-left: 70px;"></div>
                  </td>
               </tr> 
               <tr>   
                  <td>
                     <span class="title">연락처</span>
                  </td>
                  <td>
                  	<% if(loginUser.getPhone() == null) { %>
                     <input type="text" name="phone" maxlength="13" oninput="autoHyphen2(this)" value=""><br><br>
                     <% } else { %>
                     <input type="text" name="phone" maxlength="13" oninput="autoHyphen2(this)" value="<%= loginUser.getPhone() %>"><br><br>
                     <% } %>
                  </td>
               </tr>   
               <tr>
                  <td>
                     <span class="title">현재비밀번호</span>
                  </td>
                  <td>
                        <input type="password" name="memberPwd" required onkeyup="memberPwd();"><br><br>
                        <div id="memberPwdFalse" style="color: red; font-size: 12px; text-align: left; margin-left: 70px;"></div>
                  </td>
               </tr>
               
               
               <tr>
                  <td colspan="2" style="text-align: center;">
                     <button type="button" id="updateButton" style="margin-left:140px; font-size: 30px; border-radius: 7px;" onclick="submitButton();">확인</button>
                     <button type="button" style="margin-left: 10px; font-size: 30px; border-radius: 7px; background-color: rgb(164, 164, 164);" onclick="location.reload();">취소</button>
                  </td>
               </tr>
            
       </table>
            </form>
   
      </div> 

      <div class="content_3"></div>
      
      </div>
	<script>
	
	// 이름
		var $memberName = $('#updateTable input[name=memberName]');
	
			function memberName(){
				$('#memberNameFalse').text('');
			}
			
			$memberName.focusout(function(){
				var regExpName = /^[a-zA-Z가-힣]{2,}$/;
				if(!regExpName.test($memberName.val())){ //이름이 정규표현식에 맞지 않을 경우
					$('#memberNameFalse').text("올바른 이름을 입력해주세요.");
					$("#memberNameFalse").css("color", 'red');
				} else { // 이름이 정규표현식에 맞을 경우
					$('#memberNameFalse').text("사용가능");
					$("#memberNameFalse").css("color", 'rgb(26, 188, 156)');
				}
				if($memberName.val() == "") {
					$('#memberNameFalse').text("");
				}
			});
	
	// 연락처
		const autoHyphen2 = (target) => {
			 target.value = target.value
			   .replace(/[^0-9]/g, '')
			  .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
			}
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
					
					
				} else if($memberPwd.val() != ""){
					$('#memberPwdFalse').text("비밀번호가 일치하지 않습니다.");
					$("#memberPwdFalse").css("color", 'red');
					$memberPwd.focus();
				}
				else if($memberPwd.val() == "") {
					$('#memberPwdFalse').text("");
				}
			});
		
		function submitButton(){
			if($memberPwd.val() == "<%= loginUser.getMemberPwd() %>") {
				$("#updateButton").attr('type','submit');
			} else {
				alert("정보를 올바르게 입력해주세요.");
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