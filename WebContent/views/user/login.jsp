<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-로그인/회원가입</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
#login-header{
	background-color: rgb(26, 188, 156);
	height: 16%;
}
#login-form{
	text-align: center;
}
#login-form tr,td{
	padding-top: 5px;
	padding-bottom: 5px;
	
}
#login-form table{
	width: 55%;
	height: 700px;
}
.box{
	font-size: 35px;
	font-weight: 700;
	text-align: center;
	color: white;
}
.button button{
	width: 45%;
	height: 55%; 
	display:inline-block;
	padding: 5px;
	background-color: rgb(26, 188, 156);
	border: none;
	border-radius: 7px;
}
#login-form input{
	width: 55%;
	height: 70%;
	display:inline-block;
	padding: 5px;
	border-radius: 7px;
}
#idpwdfind{
	width: 55%;
	height: 70%;
	display: inline-block;
	text-align: right;
}
#idpwdfind>a{
	color: black;
	letter-spacing: 0.7px;
	
	text-decoration: none;
}
#idpwdfind>a:hover{
	font-weight: 600;
	}
.logSave{
	width: 55%;
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
			<form id="login-form" method="post" action="<%= contextPath %>/login.us" onsubmit="return frm_check();">
				<table id="table" align="center">
					
					<tr id="login-header">
						<td width="58px"><img src="/Petist/resources/common/logo.png" alt="로고"></td>
						<td class="box" style="font-size:40px;">로그인</td>
						<td width="58px"> </td>	
					</tr>
					<tr><td><br></td></tr>
					<tr>
						<br>
						<td colspan="3">
							
							<input type="text" name="userId" id="logId" required placeholder=" 아이디">
						</td>
					</tr>
					<tr>
						<td colspan="3"><input type="password" name="userPwd" required placeholder=" 비밀번호"></td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="logSave">
							    <input type="checkbox" name="checkId" id="saveId" style="width:30px; height:15px; margin-left:70px;">
							    <label for="saveId" style="font-size:15px;">아이디 저장</label>
							</div>
							<div id="idpwdfind"><a href="<%= contextPath %>/findInfo.us">아이디·비밀번호 찾기</a></div>	
						</td>
					</tr>
					<tr>
						<td id="buttonbox" colspan="3"> 
							<div align="center" class="button"><button type="submit" class="box">로그인</button></div>
							<br>
							<div align="center" class="button"><button type="button" onclick="enrollPage();" class="box">회원가입</button></div>
							<br>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="content_3">
		</div>
	</div>
	<script>
		function enrollPage(){
			location.href = "<%= contextPath %>/enrollForm.us";
		}
		
		$(function() {
	         
	           fnInit();
	         
	     });
	     
	     function frm_check(){
	         saveid();
	     }
	 
	    function fnInit(){
	        var cookieid = getCookie("saveid");
	        if(cookieid !=""){
	            $("input:checkbox[id='saveId']").prop("checked", true);
	            $('#logId').val(cookieid);
	        }
	    }    
	 
	    function setCookie(name, value, expiredays) {
	        var todayDate = new Date();
	        todayDate.setTime(todayDate.getTime() + 0);
	        if(todayDate > expiredays){
	            document.cookie = name + "=" + escape(value) + "; path=/; expires=" + expiredays + ";";
	        }else if(todayDate < expiredays){
	            todayDate.setDate(todayDate.getDate() + expiredays);
	            document.cookie = name + "=" + escape(value) + "; path=/; expires=" + todayDate.toGMTString() + ";";
	        }
	    }
	 
	    function getCookie(Name) {
	        var search = Name + "=";
	        
	        if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면 
	            offset = document.cookie.indexOf(search);
	            if (offset != -1) { // 쿠키가 존재하면 
	                offset += search.length;
	                end = document.cookie.indexOf(";", offset);
	                if (end == -1)
	                    end = document.cookie.length;
	                return unescape(document.cookie.substring(offset, end));
	            }
	        }
	        return "";
	    }
	 
	    function saveid() {
	        var expdate = new Date();
	        if ($("#saveId").is(":checked")){
	            expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30);
	            setCookie("saveid", $("#logId").val(), expdate);
	            }else{
	           expdate.setTime(expdate.getTime() - 1000 * 3600 * 24 * 30);
	            setCookie("saveid", $("#logId").val(), expdate);
	        }
	    }
	</script>
	
</body>
	<br clear="both"><br><br>

<%@ include file="/views/common/footer.jsp" %>


</html>