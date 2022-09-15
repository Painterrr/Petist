<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.admin.model.vo.Category" %>
<%
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-커뮤니티 게시글 작성</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
.outer{

	color: black;
	width: 1100px;
	height: 1000px;
	margin: auto;
	margin-top: 50px;
}

 #detail-area{
        border: 2px solid black;
        width : 830px;
        height : 500px;
       
    }
    
  #detail-area td{
  		text-align : left;
    
 }
 
 #detail-area th{
 
 		text-align : center;
 }
 
 
 .textarea{
 
 	border:none;
 	height : 500px;
 }
 
 .con{
 	padding:0%;
 	margin:0%;
 	height :100%;
 }
 
 .title{
 
 	height : 10px;
 }
 
   #detail-area>tr>th{
  		text-align : center;
    
 }
#enroll-form>table{border: 1px solid black;}
#enroll-form input, #enroll-form textarea{
	width: 100%;
	box-sizing: border-box;
	
}

.rtitle {

	height : 30px;
}

.btitle {

	font-size : 45px;
	color : blue;
}

mark {

	background-color : black;
}


</style>
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>

	<div class="outer">
	
	<br>
	<h2 class ='btitle' align="center"><mark>게시글 작성하기</mark></h2>
	<br><br><br><br>
	
	<!-- 파일을 첨부하는 요청을 할 때는 반드시 enctype="multipart/form-data"를 추가해야한다. -->
		<form id="enroll-form" enctype="multipart/form-data" action="<%= contextPath %>/insert.bo" method="post">

			<!-- 제목, 내용, 카테고리, 첨부파일 입력받기 + userNo -->
			<!-- 작성자의 회원번호를 hidden으로 같이 넘겨야 board 테이블에 INSERT 가능 -->	
		
			
			

			<table id="detail-area" align="center" border="1">
				<tr>
					<th width="150">카테고리</th>
					<td width="600">
						<select name="category">
						<% for(Category c : list) { %>
							<% if(c.getCategoryNo() == 50 || c.getCategoryNo() == 60){ %>
							<option value="<%= c.getCategoryNo() %>"> <%= c.getCategoryName() %></option>
							<% } %>
						<% } %>
						</select>
					</td>
				</tr>
				<tr>
					<th class ='rtitle'>제목</th>
					<td class='title' height ='50px;'><input type="text" name="title" minlength="7" required></td>
				</tr>
				<tr>
					<th>내용</th>
					<td class ='con'>
						<textarea class='textarea' name="content" rows="10" required style="resize:none;" ></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="upfile"></td>
				</tr>
			</table>
			<br>
			<div align="center">
				<button type="submit"  class = "btn btn-sm btn-secondary" background-color= '#008CBA';>작성하기</button>
				<a href = "<%= contextPath %>/list.bo?cpage=1&cno=50" class = "btn btn-sm btn-secondary" >목록가기</a>
				
			
			
			</div>
		</form>
	
	</div>
	<br><br>
</body>
</html>