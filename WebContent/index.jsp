<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>	
		
	#main_photo {
		background-image: url("/Petist/resources/common/main.jpg");
		background-size: 100%;
		background-repeat: no-repeat;
		background-position: center;
		background-color: rgba(0, 0, 0, 0.5);
		background-blend-mode: multiply;
		height: 550px;
		width: 100%;
		padding-top: 220px;
	}

	#main_photo_text {
		color: white;
		font-weight: bold;
		text-align: center;
		width: 350px;
		border: 1px dashed white;
		border-radius: 3em;
		margin: auto;
	}

	#intro_HowToUse>div {
		margin-left: 20px;
	}

	.mint { 
		color: rgb(26, 188, 156);
		font-weight: bold;
	}
	
	.float-left { float: left; }

</style>
</head>
<body>

	<%@ include file="views/common/menubar.jsp" %>
	<div id="main_photo">
		<div id="main_photo_text">
			<h1>Petist</h1>
			<p style="margin-bottom: 5px;">소중한 반려동물을 케어하는 공간</p>
		</div>
	</div>
	
	<div class="content">
		<div class="content_1"></div>
		<div class="content_2">
			<div id="intro_Petist">
				<br><br>
				<h2 class="mint">" Petist? "</h2>
				<img src="/Petist/resources/common/main-001.png" style="width: 920px; height: 400px;">
				<br><br>
				<p align="center">👉 동물병원 정보 조회, 커뮤니티 게시판을 통한 회원간 소통, 반려동물 관리를 한 번에 할 수 있는 <b class="mint">반려동물 통합 관리 플랫폼</b>입니다.</p>
			</div>
			
			<br><hr>
			
			<div id="intro_HowToUse">
				<br><br>
				<h4 class="mint">" 홈페이지 이용 가이드 "</h4>
				<div id="intro_hospital">
					<br><br>
					<h5 class="mint"> - [ 동물병원 ]</h5>
					<br><br>
					<img src="/Petist/resources/common/main-hospital-001.png" style="width: 940px; height: 250px;">
					<br><br>
					<p align="center">👉 <b class="mint">지역별</b>로 조회할 수 있고, <b class="mint">병원명</b>으로 검색할 수 있어요.</p>
					<img src="/Petist/resources/common/main-hospital-002.png" style="width: 940px; height: 200px;">
					<br><br>
					<p align="center">👉 병원 선택시 <b class="mint">상세정보</b>와 <b class="mint">리뷰</b>를 선택하여 볼 수 있어요.</p>
				</div>
	
				<div id="intro_pet_management">
					<br><hr><br>
					<h5 class="mint"> - [ 반려동물 관리 ]</h5>
					<img src="/Petist/resources/common/main-animal1.png" style="width:900px; height: 250px;">
					<br>
					<p align="center">👉<b class="mint"> 반려동물 </b> 등록하고, <b class="mint">관리</b>하고!</p>
					<br>
					<img src="/Petist/resources/common/main-animal2.png" style="width:600px; height: 250px;">
					<span align="center">👉 <b class="mint">켈린더</b>에 일기를 쓸 수 있어요!</span>
				<br>
				</div>
				<br>
				<hr>
				<br>
				<div id="intro_community">
					<h5 class="mint"> - [ 커뮤니티 ]</h5>
					<img src="/Petist/resources/common/wagleqa.png" style="width:600px; height: 300px;">
					<span style="width:900px">👉<b class="mint"> 와글와글 </b> 수다 떨고!!</span>
					<br><br>
					
					<p align="center">👉<b class="mint"> 반려인 </b>들과 Q&A도 주고받기!
					</p>
					<br>
				</div>
	
				<% if(loginUser == null) { %>
					<div align="center">
						<br><br>
						<button type="button" class="btn btn-outline-info" onclick='location.href="<%= contextPath %>/views/user/login.jsp";'>시작하기</button>
					</div>
				<% } %>
			</div>
		</div>
		<div class="content_3"></div>
	</div>

	<br clear="both"><br><br><br>

    <%@ include file="views/common/footer.jsp" %>
	

</body>
</html>