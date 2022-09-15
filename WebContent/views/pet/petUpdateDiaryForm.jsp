<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.kh.pet.model.vo.*" %>
<%
	Diary d = (Diary)request.getAttribute("diary");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-다이어리 업데이트</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
    .calendar-wrapper{
    	width : 800px;
    	margin : 0 auto;
    	padding : 24px 0;
    	
    }
    .diary-input{
    	width : 100px;
    	height : 34px;
    	font-size : 16px;
    	background-color : #ededed;
    	border : none;
    	padding : 0 10px;
    	box-sizing : border-box;
    	margin : 0;
    	border-radius : 6px;
    }
    .diary-input + span {
    	font-size : 18px;
    }
    .diary-textarea{
    	resize : none;
    	background-color : #ededed;
    	border : none;
    	padding : 10px;
    	border-radius : 6px;
    	
    }
	#diary-table tr{
		height : 46px
	}
	#diary-table tr td:first-child{
		padding : 0 16px ;
		text-align : right;
	}
	.diary-wrapper{
		border : 1px solid;
		height : 500px;
		display : flex;
		justify-content : center;
		align-items : center;
	}
</style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
<div class="calendar-wrapper">
	<div class="diary-wrapper">
	<form action="<%=contextPath %>/updatediary.pe?pno=<%=d.getPetNo() %>" method="post">
		
		<input type="hidden" name="dno" value="<%= d.getDiaryNo() %>">
		
		<table id="diary-table">
			<thead></thead>
			<tbody>
				<tr>
					<td>몸무게</td>
					<td>
					<input type="number" min="0" class="diary-input" name="petWeight" value="<%= d.getPetWeight() %>" />
					<span>g</span>
					</td>
				</tr>
				<tr>
					<td>산책 거리</td>
					<td><input type="number" min="0" class="diary-input" name="petWalk" value="<%= d.getPetWalk() %>" />
					<span>m</span></td>
				</tr>
				<tr>
					<td>사료량</td>
					<td><input type="number" min="0" class="diary-input" name="petFood" value="<%= d.getPetFood() %>" />
					<span>g</span></td>
				</tr>
				<tr>
					<td>대소변 상태</td>
					<% if(d.getPetPee() != null) { %>
					<td><input type="text" class="diary-input" name="petPee" value="<%= d.getPetPee() %>" /></td>
					<% } else { %>
					<td><input type="text" class="diary-input" name="petPee" value="-" /></td>
					<% } %>
				</tr>
				<tr>
					<td>그 외의 기록</td>
					<% if(d.getPetRecord() != null) { %>
					<td><textarea class="diary-textarea" name="petRecord"><%= d.getPetRecord() %></textarea></td>
					<% } else { %>
					<td><textarea class="diary-textarea" name="petRecord">-</textarea></td>
					<% } %>
				</tr>
			</tbody>
		</table>
		<br>
			<div align="center">
				<button type="submit" class="btn btn-sm btn-info">수정하기</button>
				<button type="reset" class="btn btn-sm btn-danger">되돌리기</button>
				
			</div>
	</form>	
	</div>
</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>