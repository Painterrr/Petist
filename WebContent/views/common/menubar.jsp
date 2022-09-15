<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.user.model.vo.*, com.kh.user.model.vo.Hospital" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	Hospital loginHosp = (Hospital)session.getAttribute("loginHosp");

	String alertMsg = (String)session.getAttribute("alertMsg");
	
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴바</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
    .nav-area { background-color: rgb(26, 188, 156); height: 78px; }

    .menu {
        display: table-cell;
        height: 76px;
        width: auto;
        text-align: center;
        color: white;
    }

    .menu a, .menu button {
        color: white;
        font-size: 16px; 
        font-weight: bold;
        display: block;
        width: 100%;
        height: 100%;
        line-height: 30px;
        padding-top: 30px;
        padding-left: 20px;
        padding-right: 20px;
    }

    .menu a {
        text-decoration: none;
    }

    .menu button {
        background-color: rgb(26, 188, 156);
        border: none;
    }
    
    #logo:hover { cursor: pointer; }

    #petist { 
        color: white;
        font-size: 36px;
        font-weight: bold;
        vertical-align: center;
        padding-top: 10px;
    }
    #photo {
        padding-left: 15px;
        padding-right: 15px;
        vertical-align: middle;
    }
    
    .menu a:hover { text-decoration: underline; text-underline-position: under; text-decoration-thickness: 4px; font-size: 18px; }

    .last { padding-right: 40px; padding-left: 40px; }

    img { width: 55px; height: 55px; }
    
    div {
        box-sizing: border-box;
    }

    .content {
    	width: 1200px;
        height: auto;
        margin: auto;
    }
    
    .content > div, .content_short > div {
        height: 100%;
        float: left;
        margin-top: 30px;
        margin-bottom: 30px;    	
    }
    
    .content_short {
    	width: 1200px;
        height: auto;
        margin: auto;
    }
    
    .content_1 {
        width: 10%;
    }
    .content_2 {
    	width: 80%;
    }
    .content_3 {
    	width: 10%;
    }
    
    div {
    	box-sizing: border-box;
    }
</style>
</head>
<body>

    <script>
		
		var msg = "<%= alertMsg %>";
		
		if(msg != "null" ){
			Swal.fire({
				  title: 'Petist',
				  text: msg,
				  imageUrl: '/Petist/resources/common/logo_mint.png',
				  imageWidth: 130,
				  imageHeight: 110,
				  confirmButtonText: '확인',
				  confirmButtonColor: '#1abc9c'
				});
			
			<% session.removeAttribute("alertMsg"); %>
		}
		
	</script>


	<div class="nav-area" align="right">
        <div class="menu" id="logo" style="float:left;" onclick='location.href="<%= contextPath %>";'>
            <div class="menu" id="photo">
                <img src="/Petist/resources/common/logo.png" alt="로고">
            </div>
            <span id="petist" class="menu">Petist</span>
        </div>
        <div class="menu"><a href="<%= contextPath %>">HOME</a></div>
        <div class="menu"><a href="<%= contextPath %>/list.ho?cpage=1">동물병원</a></div>
        <% if(loginUser == null && loginHosp == null) { %>
        <!-- 로그인전에 보여지는 로그인 form -->
            <div class="menu"><a href="<%= contextPath %>/start.pe">반려동물 관리</a></div>
            <div class="menu"><a href="<%= contextPath %>/list.bo?cpage=1&cno=50">커뮤니티</a></div>
            <div class="menu"><a href="<%= contextPath %>/list.no?cpage=1">공지사항</a></div>
            <div class="menu last"><a href="<%= contextPath %>/loginEnroll.us" class="last">로그인/회원가입</a></div>
        
        <% } else if(loginUser != null) { %>

            <% if(loginUser.getMemberId().equals("admin")) { %>
            <!--  관리자로 로그인시 로그인 form -->
                <div class="menu"><a href="<%= contextPath %>/list.bo?cpage=1&cno=50">커뮤니티</a></div>
                <div class="menu"><a href="<%= contextPath %>/list.no?cpage=1">공지사항</a></div>
                <div class="menu"><a href="<%= contextPath %>/list.mem?cpage=1">관리자페이지</a></div>
		        <div class="menu last" style="vertical-align: top;">
		            <p style="margin-top: 20px; margin-bottom: 0px; color: white;"><%= loginUser.getMemberName() %></p>
		            <a style="padding-bottom: 30px; padding-top: 0; height: 30px; " href="<%= contextPath %>/logout.us">로그아웃</a>
		        </div>

            <% } else {  %>
            <!-- 개인회원 로그인 시 로그인 form -->
                <div class="menu"><a href="<%= contextPath %>/start.pe">반려동물 관리</a></div>
                <div class="menu"><a href="<%= contextPath %>/list.bo?cpage=1&cno=50">커뮤니티</a></div>
                <div class="menu"><a href="<%= contextPath %>/list.no?cpage=1">공지사항</a></div>
                <div class="menu"><a href="<%= contextPath %>/myPage.us">마이페이지</a></div>
                <div class="menu last" style="vertical-align: top;">
		            <p style="margin-top: 20px; margin-bottom: 0px; color: white;"><%= loginUser.getMemberName() %></p>
		            <a style="padding-bottom: 30px; padding-top: 0; height: 30px;" href="<%= contextPath %>/logout.us">로그아웃</a>
		        </div>
            <% } %>

        <% } else if(loginHosp != null) { %>
		<!-- 병원회원 로그인 시 로그인 form -->
            <div class="menu"><a href="<%= contextPath %>/list.bo?cpage=1&cno=50">커뮤니티</a></div>
            <div class="menu"><a href="<%= contextPath %>/list.no?cpage=1">공지사항</a></div>
            <div class="menu"><a href="<%= contextPath %>/hospitalPage.ho?hospId=<%= loginHosp.getHospId() %>">동물병원 관리</a></div>
            <div class="menu"><a href="<%= contextPath %>/myPage.us">마이페이지</a></div>
            <div class="menu last" style="vertical-align: top;">
	            <p style="margin-top: 20px; margin-bottom: 0px; color: white;"><%= loginHosp.getHospName() %></p>
	            <a style="padding-bottom: 30px; padding-top: 0; height: 30px;" href="<%= contextPath %>/logout.us">로그아웃</a>
	        </div>       
        <% } %> 

    </div>

</body>
</html>