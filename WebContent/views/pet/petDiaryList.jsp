<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.pet.model.vo.*, com.kh.common.model.vo.PageInfo" %>
<%
	ArrayList<Diary> diarylist = (ArrayList<Diary>)request.getAttribute("diarylist");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	Animal a = (Animal)session.getAttribute("a");
	// 페이징바 만들 때 필요한 변수 미리 셋팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다이어리 리스트</title>
<style>
	.outer{
       
        color: black;
        width: 1000px;
		height: 600px;
        margin: auto;
        margin-top: 30px;
    }

    .list-area{
        border : 1px solid black;
        text-align: center;
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
    thead tr th{
        border : 1px solid black;
        background-color : rgb(26, 188, 156);
    }
    tbody tr{
    	 border : 1px solid black;
    }
    .list-area>tbody>tr:hover{
        cursor: pointer;
        background-color: #c8c8c8;
    }
    #backList{
    	width: 80px;
    	height: 30px;
     	border:none; 
     	color:white; 
     	background-color:rgb(26, 188, 156);
     	border-radius: 7px;
    }

</style>
</head>
<body>
<%@ include file="../common/menubar.jsp" %>
	<div class="content">
	    <div class="content_1">
			<div id="petMain"><%=a.getPetName() %><br> 다이어리</div>
		</div>
		<div class="content_2" >
			<div class="outer">
				<table class="list-area" align="center">
					<thead>
						<tr>
							<th width="70">글번호</th>
							<th width="150">일기날짜</th>
							<th width="80">무게</th>
							<th width="80">거리</th>
							<th width="80">사료량</th>
							<th width="100">배변상태</th>
							<th width="300">기록</th>
						</tr>
					</thead>
					<tbody>
						
						<!-- 게시글 출력 : 게시글이 있는지 없는지 => isEmpty() 이용해서 없는 경우 먼저 조건 부여 -->
						
						<% if(diarylist.isEmpty()) { %> <!-- 조회글 없음 -->
							<tr>
								<td colspan="7">조회된 게시글이 없습니다.</td>					
							</tr>
						<% } else {%>
							<!-- 반복 : list에 있는 값을 순차적으로 접근해서 뽑아오기 -->
							<% for(Diary d : diarylist) { %>
								<tr>
									<td style="border : 1px solid black; "><%= d.getDiaryNo() %></td>						
									<td style="border : 1px solid black; "><%= d.getWriteDate().substring(0, 10) %></td>						
									<td><%= d.getPetWeight() %>g</td>						
									<td><%= d.getPetWalk() %>m</td>						
									<td><%= d.getPetFood() %>g</td>
									<% if(d.getPetPee() != null) { %>						
									<td><%= d.getPetPee() %></td>
									<% } else { %>
										<td>-</td>
									<% } %>			
									<% if(d.getPetRecord() != null) { %>			
									<td style="border : 1px solid black; "><%= d.getPetRecord() %></td>		
									<% } else { %>
									<td style="border : 1px solid black; ">-</td>	
									<% } %>				
								</tr>
							<% } %>
						<% } %>
					</tbody>
				</table>
		
				<br><br>
		
				<div align="center" class="paging-area">
		
					<% if(currentPage != 1) {%> <!-- 페이징바에서 <를 담당 -->
						<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/diarylist.pe?cpage=<%= currentPage - 1 %>&pno=<%=a.getPetNo()%>'"> &lt; </button>
					<% } %>
		
					<% for(int i = startPage; i <= endPage; i++) { %>
						<% if(i != currentPage) {%>
							<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/diarylist.pe?cpage=<%= i %>&pno=<%=a.getPetNo()%>'"><%= i %></button>
						<% } else { %>
							<button class="btn btn-sm btn-primary" disabled><%= i %></button>
						<% } %>
					<% } %>
		
					<% if(currentPage != maxPage) {%>
						<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/diarylist.pe?cpage=<%= currentPage + 1 %>&pno=<%=a.getPetNo()%>'"> &gt; </button>
					<% } %>
					
				</div>
				<br><br><br>
				<div align="right" style="margin-right:70px;">
				<button id="backList" onclick="location.href='<%=contextPath%>/calender.pe?pno=<%=a.getPetNo()%>'">뒤로가기</button>
				</div>
			
		</div>
   	    </div>
		<div class="content_3"></div>
	</div>
    <br clear="both"><br><br>
    <%@ include file="../common/footer.jsp" %>
</body>
</html>