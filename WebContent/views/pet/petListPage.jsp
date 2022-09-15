<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.pet.model.vo.Animal" %>
<%
	ArrayList<Animal> list = (ArrayList<Animal>)session.getAttribute("list");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-내 겸둥이 펫들</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
	#petMain {
      font-size: 27px;
      font-weight: bolder;
      border-bottom: 1px solid black;
      width: 170px;
      text-align: center;
      color: rgb(26, 188, 156);
      margin-top: 25px;
   }  
   .outer{
		width : 850px;
		margin : 0 auto;
		border : 1px solid;
		height : 550px;
		justify-content : center;
		border-radius : 6px;
		position : relative;
		padding-top : 10px;
		padding-left : 18px;
	}
	.thumbnail {
		display : inline-block;
		width: 200px;
		height: 200px;
		border-radius : 6px;
	}
	.thumbnail:hover {
		background : silver;
		border-radius : 6px;
	}
	.thumbnail img{
		width : 100px;
		height : 100px;
		text-align : ;
		border-radius : 6px;
	}
	.thumbnail p{
		margin : 0 auto;
		text-align : center;
		font-weight : 600;
	}
	
	#delete {
		position : absolute;
		bottom : 20px;
		right : 20px;
		display : inline-block;
	}

</style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
	<div class="content">
	    <div class="content_1">
			<div id="petMain">마이펫 홈</div>
		</div>
		<div class="content_2" >
			<div class="outer">
					<% if(list.isEmpty() || list == null) { %>
						<div>
							<p>
							등록된 펫이 없습니다. <br>
							펫을 등록해주세요.		
							</p>
							<a href="<%= contextPath %>/enrollForm.pe" class="btn btn-sm btn-info">등록하기</a>
						</div>
					<% } else {%>
						<% for(Animal b : list) { %>
							<div class="thumbnail">
									<input type="hidden" name="petNo" value="<%=b.getPetNo() %>">
									<center><img src="<%=b.getTitleImg() %>"></center><br>
									<p>이름: <%=b.getPetName()%></p>
									<p>품종: <%=b.getPetBreed()%></p>
									<p>생일: <%=b.getPetBirth()%></p>
							</div>
						<% } %>
							<div id="delete">
								<a href="<%= contextPath %>/enrollForm.pe" class="btn btn-sm btn-info">등록하기</a>
								<button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#deleteAnimal">삭제하기</button>
							</div>
					<% } %>
				<script>
					$(function(){
		
						$(".thumbnail").click(function(){
		
							// 클릭될때마다 url요청 => location.href
							var pno = $(this).children().eq(0).val();
		
							location.href = "<%= contextPath %>/calender.pe?pno=" + pno;
							
						});
					});
				</script>
			</div>
			
	</div>
		<div class="content_3"></div>
    </div>
    <br clear="both"><br><br>
    <%@ include file="../common/footer.jsp" %>
	
	<!-- 탈퇴만들자리 -->
	<div class="modal" id="deleteAnimal">
		<div class="modal-dialog">
		<div class="modal-content">
	
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">펫을 삭제하시겠습니까?</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
	
			<!-- Modal body -->
			<div class="modal-body">

				<form action="<%= contextPath %>/detelepet.pe" method="post">

					<table>
						<tr>
							<td>펫 이름을 입력해주세요</td>
							<td><input type="text" required name="petName"></td>
						</tr>
						<tr>
							<td>로그인 한 비밀번호를 입력해주세요</td>
							<td><input type="password" required name="memberPwd"></td>
						</tr>
					</table>

					<br>

					<button type="submit" class="btn btn-sm btn-danger">내 펫 삭제</button>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>