<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-회원 탈퇴</title>
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
   .navibar a:hover{
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
	#deleteTable input {
      width: 290px;
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
	table p{
		font-size:13px;
	}
	table p,h5{
		margin-left: 10px;
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
	                  	 <a href="<%= contextPath %>/myPage.us">비밀번호 변경</a>
	                  </li>
	                  <li>
	                     <a onclick="window.location.reload()" style="font-weight: bold;">회원 탈퇴</a>
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
	            <form action="<%= contextPath %>/deletehosp.us" method="post">
	            <div style="margin-right: 450px; margin-top: 50px; font-size: 20px; font-weight: bold;">회원 탈퇴</div>
	            <br>
	         <table style=" border-radius: 7px; width: 650px; height: 750px; " align="center" id="deleteTable">
					<tr>
						<td colspan="2" >
							<h3 align="left" style=" font-weight:bold; margin-top: 20px;">정말로 탈퇴하시나요?</h3>
							<p align="left">회원탈퇴 전에 반드시 유의사항을 확인해주세요.</p>
						</td>
					</tr>
	               <tr>
	                  <td>
	                     <span class="title">아이디</span>
	                  </td>
	                  <td> 		
	                      <input type="hidden" name="hospId" value="<%= loginHosp.getHospId() %>">
	                      <div style="font-size: 17px; text-align: left; margin-left: 70px; margin-top: 5px"><%= loginHosp.getHospId() %></div>
	                  </td>
	               </tr>
	               <tr>
	                  <td>
	                     <span class="title">비밀번호 확인</span>
	                  </td>
	                   <td>
	                   	
	                       <input type="password" name="checkPwd" required><br><br>
	                       <input type="hidden" name="hospPwd" value="<%= loginHosp.getHospPwd() %>">
	                       <div id="checkPwdFalse" style="color: red; font-size: 12px; text-align: left; margin-left: 70px;"></div>
	                    </td>
	               </tr>
	               <tr>
	               		<td colspan="2">
	               			<h5 align="left">개인정보 및 서비스 이용 기록 삭제</h5>
							<p align="left">개인정보 및 개인화 서비스이용 기록이 삭제 되며,<br>
											삭제된 데이터는 복구되지 않습니다.</p>
						</td>
	               </tr>
	               <tr>
						<td colspan="2">
							<h5 align="left">커뮤니티 서비스 등록 게시물 유지</h5>
							<p align="left">회원가입 이후에 등록하신 게시물들은 회원탈퇴 후에도<br>
									 		삭제되지 않고 유지됩니다.<br>
											삭제를 원하시는 경우에는 직접 삭제하신 후 회원탈퇴를<br>
									 		 진행하시기 바랍니다.</p>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input  type="checkbox" id="checkbox" style="width: 17px; height: 17px; margin: 0;">
							<span align="left" style="font-size:14px; margin-right: 150px;">회원탈퇴 시 유의사항을 확인하였으며, 모두 동의합니다.</span>
							
							<div align="left" id="checkbox" style="color: red; font-size: 12px; text-align: left; margin-top: 5px;"></div>
						</td>
					</tr>
	               <tr>
	                  <td colspan="2" style="text-align: center;">
	                     <button type="button" id="deleteButton" style="margin-top: 60px; margin-left:140px; font-size: 30px; border-radius: 7px;" onclick="deletehosp();">확인</button>
	                     <button type="button" style="margin-left: 10px; font-size: 30px; border-radius: 7px; background-color: rgb(164, 164, 164);" onclick="location.reload();">취소</button>
	                  </td>
	               </tr>
	            
	       </table>
	            </form>
	   
	      </div> 
	
	      <div class="content_3"></div>
	      
	</div>
	<script>
	
	// 비밀번호 확인	
		var $checkPwd = $('#deleteTable input[name=checkPwd]');
		var $hospPwd = $('#deleteTable input[name=hospPwd]');
		
		$checkPwd.keyup(function(){

			
			if($checkPwd.val() == $hospPwd.val()) { //비밀번호 일치 하면 체크 여부 ㄱㄱ
				$("#checkPwdFalse").text("비밀번호 일치");
				$("#checkPwdFalse").css("color", 'rgb(26, 188, 156)');
			}else { 
				$("#checkPwdFalse").text("비밀번호가 일치하지 않습니다.");
				$("#checkPwdFalse").css("color", 'red');
			}
			if($checkPwd.val() == "") {
				$('#checkPwdFalse').text("");
			}
		});
	
	// 체크박스 체크 여부
	function deletehosp(){
		if($checkPwd.val() != $hospPwd.val()){
			alert("비밀번호를 확인해주세요.");
		}
		else if($("#checkbox").is(":checked") == false ){ // 체크박스 체크 안하고 삭제할 경우
			alert("유의사항에 동의해주세요!");
		} else{
			$("#deleteButton").attr('type','submit');
			alert("성공적으로 탈퇴되었습니다. 확인을 누르시면 메인페이지로 이동합니다.");
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