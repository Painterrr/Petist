<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import  = "java.util.ArrayList, com.kh.community.model.vo.* , com.kh.admin.model.vo.*" %>


<%
	
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
	Community c = (Community)request.getAttribute("c");
	CoAttachment coat = (CoAttachment)request.getAttribute("coat");

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-게시글 수정</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
  .outer{
	color: black;
	width: 1100px;
	height: 1000px;
	margin: auto;
	margin-top: 50px;
}
#update-form>table{border: 1px solid black;}
#update-form input, #update-form textarea{
	width: 100%;
	box-sizing: border-box;
	
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
 
 	height : 500px;
 }
 
 .title{
 
 	height : 10px;
 }  
 
   #detail-area>tr>th{
  		text-align : center;
    
 }

.btitle {

	font-size : 45px;
	color : blue;
}
</style>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>

	<div class="outer">
	
	<br>
	<h2 align="center" class='btitle'><mark>게시글 수정하기</mark></h2>
	<br><br><br><br>
	
	<!-- 파일을 첨부하는 요청을 할 때는 반드시 enctype="multipart/form-data"를 추가해야한다. -->
		<form id="update-form" enctype="multipart/form-data" action="<%= contextPath %>/update.bo" method="post">

			<!-- 제목, 내용, 카테고리, 첨부파일 입력받기 + userNo -->
			<input type="hidden" name="ccno" value="<%= c.getCommNo() %>">

			
			<table align="center" id="detail-area" border="1">
				<tr>
					<th width="150">카테고리</th>
					<td width="600">
						<select name="category">
						
						
						<% for(Category ca : list) { %>
						<% if(ca.getCategoryNo() == 50 || ca.getCategoryNo() == 60){ %>
							<option value="<%= ca.getCategoryNo() %>"> <%= ca.getCategoryName() %></option>
						<% } %>
					<% } %>
						</select>
						<script>
						
							$(function() {
								
								$("#update-form option").each(function(){
									//지금 돌고 있는 옵션태그의 카테고리 명이랑 보드 객체에 있는 카테고리랑 동일하면
								 if($(this).text() == "<%= c.getCategory() %>") {
										$(this).attr("selected", "true")
									}
									
								})	
								
								
								
								
							})
							
						</script>
						
						
						
					</td>
				</tr>
				<tr>
					<th class ='rtitle'>제목</th>
					<td class ='title''><input type="text" name="title" value = "<%= c.getCommTitle() %>" minlength="7" required ></td>
				</tr>
				<tr>
					<th>내용</th>
					<td class = 'con'>
						<textarea class='textarea' name="content" rows="10" required style="resize:none;"><%= c.getCommContent() %> </textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
                    <!-- 기존 파일이 있다면 원본명을 보여주자 aaa.jpg-->
                    <td>
                    			
                    			<% if(coat != null) { %>
                    				<%=coat.getOriginName() %>
                    				<input type ="hidden" name ="originFileNo" value ="<%=coat.getFileNo() %>">
                    				<input type ="hidden" name ="originFileName" value ="<%=coat.getChangeName() %>">
                    				
                    				<% }  %>
                    		
					<input type="file" name="reUpfile">
                    </td>

                    <!-- 기존파일이 없다면-->
				</tr>
			</table>
			<br>
			<div align="center">
					<button type="submit"  class = "btn btn-sm btn-secondary" >수정하기</button>
				<a href = "<%= contextPath %>/list.bo?cpage=1&cno=50" class = "btn btn-sm btn-secondary" >목록가기</a>
	
			</div>
		</form>
	</div>

</body>
</html>