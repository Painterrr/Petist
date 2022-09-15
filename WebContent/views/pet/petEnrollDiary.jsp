<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.kh.pet.model.vo.*" %>
<%
	Animal a = (Animal)session.getAttribute("a");
	String selectDate = (String)session.getAttribute("selectDate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-다이어리 등록</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
    .calendar-wrapper{
    	width : 800px;
    	margin : 0 auto;
    	padding : 24px 0;
    	
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
    .diary-input{
    	width : 80px;
    	height : 34px;
    	font-size : 16px;
    	background-color : #ededed;
    	border : none;
    	padding : 0 10px;
    	box-sizing : border-box;
    	margin : 0;
    	border-radius : 6px;
    }
    .diary-input1{
    	width : 150px;
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
	#dateinputnone{
		text-align : right;
		font-size : 11px;
	}
</style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
<div class="content">
	    <div class="content_1">
			<div id="petMain">다이어리 등록</div>
		</div>
		<div class="content_2" >
		<div class="calendar-wrapper">
			<div class="diary-wrapper">
				<form id="update-form" action="<%= contextPath %>/insertdiary.pe" method="post">
					<table id="diary-table">
						<thead></thead>
						<tbody>
								
							<tr>
								<td>일기 날짜 기입</td>
								<td><input type="date" class="diary-input1" name="writeDate" readonly value="<%= selectDate %>"/></td>
							</tr>
							
							<tr>
								<td>몸무게</td>
								<td><input type="number" min="0" class="diary-input" value="0" name="petWeight" />
								<span>g</span></td>
							</tr>
							<tr>
								<td>산책 거리</td>
								<td><input type="number" min="0" value="0" class="diary-input" name="petWalk" />
								<span>m</span></td>
							</tr>
							<tr>
								<td>사료량</td>
								<td><input type="number" min="0" value="0" class="diary-input" name="petFood" />
								<span>g</span></td>
							</tr>
							<tr>
								<td>대소변 상태</td>
								<td><input type="text" class="diary-input" name="petPee" /></td>
							</tr>
							<tr>
								<td>그 외의 기록</td>
								<td><textarea class="diary-textarea" name="petRecord"></textarea></td>
							</tr>
							<tr><td><input type="hidden" name="petNo" value="<%=a.getPetNo() %>" required></td></tr>
						</tbody>
						
						
					</table>
					<div align="center">
						<button type="submit" class="btn btn-sm btn-info">등록하기</button>
						<button type="reset" class="btn btn-sm btn-danger">다시쓰기</button>
						
					</div>
				</form>
				<br>
			</div>
		</div>
		</div>
		<div class="content_3"></div>
    </div>
    <br clear="both"><br><br>
<%@ include file="../common/footer.jsp" %>
</body>
</html>