<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist- 찾을 수 없는 페이지</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
#errorContent{
	height: 700px;
	text-align: center;
}
#first{
	font-weight: bold;
	font-size: 30px;
}
#second{
	font-size: 20px;
	margin-left: 55px;
}
#back, #home{
	width: 90px;
	height: 45px;
	padding: 5px;
	background-color: rgb(26, 188, 156);
	border: none;
	color: white;
	border-radius: 7px;
	font-weight: 600;
	font-size: 18px;
}	
#back{
	margin-right: 10px;
}
#home{
	margin-left: 20px;
}
</style>
</head>
<body>
	
	<div id="errorContent">
		<br>
		
		<img src="/Petist/resources/common/500cat.png" style="width: 900px; height: 500px;">
		<br><br><br>
		<button type="button" style='cursor:pointer;' id="back" onclick="history.back();">이전</button>
		<button type="button" style='cursor:pointer;' id="home" onclick="location.href='index.jsp'">홈으로</button>
	</div>
	
	<br clear="both"><br><br><br>


   
	
</body>
</html>