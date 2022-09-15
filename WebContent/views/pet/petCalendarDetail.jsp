<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.kh.pet.model.vo.*" %>
<%
	Diary d = (Diary)session.getAttribute("d");
	String selectDate = (String)session.getAttribute("selectDate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-다이어리 내부</title>
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
<div class="content">
	    <div class="content_1">
			<div id="petMain"></div>
		</div>
		<div class="content_2" >
			<div class="calendar-wrapper">
				<% if(d != null){ %>
				<div class="diary-wrapper">
					<table id="diary-table">
						<thead></thead>
						<tbody>
							<tr>
								<td>몸무게</td>
								<td>
								<input class="diary-input" readonly value="<%= d.getPetWeight() %>" />
								<span>g</span>
								</td>
							</tr>
							<tr>
								<td>산책 거리</td>
								<td><input class="diary-input" readonly value="<%= d.getPetWalk() %>" />
								<span>m</span></td>
							</tr>
							<tr>
								<td>사료량</td>
								<td><input class="diary-input" readonly value="<%= d.getPetFood() %>" />
								<span>g</span></td>
							</tr>
							<tr>
								<td>대소변 상태</td>
								<% if(d.getPetPee() != null) { %>
								<td><input class="diary-input" readonly value="<%= d.getPetPee() %>" /></td>
								<% } else { %>
								<td><input class="diary-input" readonly value="-" /></td>
								<% } %>
							</tr>
							<tr>
								<td>그 외의 기록</td>
								<% if(d.getPetRecord() != null) { %>
								<td><textarea class="diary-textarea" readonly><%= d.getPetRecord() %></textarea></td>
								<% } else { %>
								<td><textarea class="diary-textarea" readonly>-</textarea></td>
								<% } %>
							</tr>
						</tbody>
					</table>
					<br>
					<div>
						<a href="<%= contextPath %>/updatediaryform.pe?dno=<%= d.getDiaryNo() %>" class="btn btn-sm btn-info">수정하기</a>
						<a href="<%= contextPath %>/deletediary.pe?dno=<%= d.getDiaryNo() %>&pno=<%=d.getPetNo() %>" class="btn btn-sm btn-warning">삭제하기</a>
					</div>
				</div>
				<%} else { %>
				<p>데이터 없어요</p>
					<a href="<%= contextPath %>/enrolldiary.pe?selectDate=<%= selectDate %>" class="btn btn-sm btn-info">등록하기</a>
					<button onclick="history.back()" class="btn btn-sm btn-warning">뒤로가기</button>
					
				<%} %>
			</div>
			</div>
			<div class="content_3"></div>
    </div>
    <br clear="both"><br><br>
<%@ include file="../common/footer.jsp" %>
</body>
</html>