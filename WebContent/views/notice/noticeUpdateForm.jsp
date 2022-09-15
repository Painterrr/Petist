<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.notice.model.vo.Notice" %>
<%
	Notice n = (Notice)request.getAttribute("n");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Petist-공지사항 수정</title>
	<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
</head>
<style>
	.outer{
		background-color: white;
		color: black;
		width: 1000px;
		height:800px;
		margin: auto;
		margin-top: 50px;
	}

    #update-form>table{border: 1px solid white;}
    #update-form input, #update-form textarea{
       	width: 100%;
        box-sizing: border-box;
    }
    table th{
    	align: center;
    }
    #adminPageMenu {
      font-size: 27px;
      font-weight:bolder;
      border-bottom: 1px solid black;
      width: 170px;
      text-align: center;
      color: rgb(26, 188, 156);
      margin-top: 25px;
   }   
   .nav{
      width: 160px;
      margin: 15px;
      
   }
   #management, #notice, #board {
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
   li>a{
      color: black;
   }
   li{
      list-style-type: none;
      font-size: 18px;
   }
</style>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">공지사항 상세보기</h2>
		<br><br>
		<div class="content">
			<div class="content_1">
				
			 </div>
			<div class="content_2">
				<form action="<%= contextPath %>/update.no" method="post" id="update-form">
				
					<input type="hidden" name="nno" value="<%= n.getNoticeNo() %>">
				
					<table align="center" style= "width:800px">
						<tr>
							<th width="100">제목</th>
							<td width="380"><input type="text" value="<%= n.getNoticeTitle() %>" name="title" minlength="8" required></td>
						</tr>
						<tr>
							<th>내용</th>
							<td></td>
						</tr>
						<tr>
							<td colspan="2">
								<textarea name="content" rows="10" required><%= n.getNoticeContent() %></textarea> 
							</td>
						</tr>
					</table>
					<br><br>
			
					<div align="center">
						<button type="submit" class="btn btn-sm btn-info">수정하기</button>
						<button type="button" class="btn btn-secondary btn-sm" onclick="history.back();">뒤로가기</button>
					</div>
					
				</form>
			</div>
			<div class="content_3">
				
			</div>
		</div>
	</div>
</body>
</html>