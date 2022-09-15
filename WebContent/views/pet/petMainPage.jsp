<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-마이펫 홈</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
	.outer{
		width : 800px;
		margin : 0 auto;
		border : 1px solid;
		height : 500px;
		justify-content : center;
		background-image : url('http://localhost:8002/Petist/resources/pet/background/50fc81ae2c5d837a2040ccfb1a558b9e182343.jpg');
		border-radius : 6px;
		
	}
	#petMain {
      font-size: 27px;
      font-weight:bolder;
      border-bottom: 1px solid black;
      width: 170px;
      text-align: center;
      color: rgb(26, 188, 156);
      margin-top: 25px;
   }   
   
	.main{
		padding : 100px;		
		
	}
	.main a{
		margin : auto;
		display : block;
		width : 100px;
		height : 40px;
		padding : 10px;
		
	}
	.main p{
		text-align : center;
		font-weight : 600;
		font-size : 30px;
	}
	#list{
		margin-top : 10px;
	}
	

</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="content">
	    <div class="content_1">
			<div id="petMain">메인 페이지</div>
		</div>
		<div class="content_2" >
			<div class="outer">
				<div class="main">
				<% if(loginUser != null) { %>
						<a href="<%= contextPath %>/enrollForm.pe" class="btn btn-sm btn-info">등록하기</a>
						<a href="<%= contextPath %>/list.pe" id="list" class="btn btn-sm btn-warning">리스트 보기</a>
				<% } else {%>
					<p> 개인회원으로 로그인 해 주세요! </p>
				<% } %>
				</div>
			</div>
		</div>
		<div class="content_3"></div>
    </div>
    <br clear="both"><br><br>
    <%@ include file="../common/footer.jsp" %>
</body>
</html>