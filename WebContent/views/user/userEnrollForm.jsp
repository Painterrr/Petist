<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-회원가입</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
    #login-header{
        background-color: rgb(26, 188, 156);
        height: 15%;
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
        height: 600px;
    }
    .box{
        font-size: 35px;
        font-weight: 700;
        text-align: center;
        color: white;
    }
    #buttonbox button{
        width: 200px;
        height: 150px; 
        display:inline-block;
        padding: 5px;
        background-color: rgb(26, 188, 156);
        border: none;
        margin-bottom: 15px;
        margin-left: 10px;
        margin-right: 10px;
    }
    
    
    
    
    </style>
</head>
<body>

    <%@ include file="/views/common/menubar.jsp" %>
	<br>

	<div class="content">
		<div class="content_1">
		</div>
		<div class="content_2">
			<div id="login-form" >
				<table style="border: 1px solid rgb(198, 197, 197)" align="center">
					
					<tr id="login-header">
						<td width="58px"><img src="/Petist/resources/common/logo.png" alt="로고"></td>
						<td class="box">회원가입</td>
						<td width="58px"> </td>
					</tr>
					
					<tr>
						<td id="buttonbox" colspan="3"> 
                                <button type="button" onclick="memberEnrollPage();" class="box">개인<br>회원가입</button>
                                <button type="button" onclick="hospEnrollPage();" class="box">병원<br>회원가입</button>
							<br>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="content_3">
		</div>
	</div>
    <script>
		function memberEnrollPage(){
			location.href = "<%= contextPath %>/selectMember.us";
		}
		function hospEnrollPage(){
			location.href = "<%= contextPath %>/selectHosp.us";
		}
	
	</script>
</body>

    <br clear="both"><br><br>

    <%@ include file="/views/common/footer.jsp" %>
</html>