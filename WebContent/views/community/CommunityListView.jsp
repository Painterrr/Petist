<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList, com.kh.community.model.vo.*, com.kh.common.model.vo.PageInfo"%>

<%
	ArrayList<Community> list = (ArrayList<Community>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");	
	
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
<title>Petist-게시글 목록</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>   
      
   #communityPageMenu {
      font-size: 27px;
      font-weight:bolder;
      border-bottom: 1px solid black;
      width: 150px;
      text-align: center;
      color: rgb(26, 188, 156);
      margin-top: 25px;
   }   
   .nav{
      width: 160px;
      margin: 15px;
      cursor: pointer;
   }
   #selectMem th{
      background-color: lightgrey;
   }
   #management, #notice, #board {
      font-size: 20px;
      font-weight: bold;
   }
   #chat,#questionboard{
      text-decoration: none;
      color:black;
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
   .content_2{
      padding-left:150px;
      padding-top: 90px;
   }
   #reachMem{
      margin-bottom: 20px;
   }
   table{
      text-align: center;
      border:1px solid lightgray;
      width:810px;
  
   }
   table td{
         text-align:left;
         vertical-align: top;
         
   }
   table th{
         height:30px;
   }
   .listButton{
      padding-top: 40px;
      text-align:center;
   }
   
    .paging-area{
      padding-top: 40px;
      text-align:center;
   }
   
.list-area>tbody>tr:hover{
        cursor: pointer;
        background-color: gray;
 }
 	
 	.wagle {
 		font-weight:bold;
 	}
 	
 	.question{
 		font-weight:bold;
 	}
 	
 	.llist>td {
 	
 	text-align : center;
 	}
 	
 	#writeBtn {
 		margin-left:750px
 	}
 	
  
</style>
</head>
<body>
   <%@ include file="../common/menubar.jsp" %>
   
   <div class="content">
      <div class="content_1">
        <div id="communityPageMenu">커뮤니티</div>
         <ul class="nav">                          
            <li id="chat">									
               	<a href="<%= contextPath %>/list.bo?cpage=1&cno=50" id="wagle" class="wagle"; >와글와글게시판</a>
            </li>
           
            <li id="questionboard">
             			<a href="<%= contextPath %>/list.bo?cpage=1&cno=60" id="question" class="question";>질문게시판</a> 
             			

             			
            </li>
          
         </ul>
      </div>
      
      <div class="content_2">
      
      

         <h2 id='wag' class=wag><%= list.get(0).getCategory()%></h2>

            <hr>
            <div id="writeBtn" style="width:850px;">
			<!--  로그인한 회원만 보여지는 버튼 : loginUser가 null인지 아닌지 판단 -->
			<% if((loginUser != null) || (loginHosp !=null)) { %>
			<a id='write'href="<%= contextPath %>/enrollForm.bo" class="btn btn-info btn-sm">글작성</a>
			<br><br>
			<% } %>
		</div>
	
		<table class="list-area" align="center">
			<thead>
				<tr>
					<th width="70">글번호</th>
					<th width="70">카테고리</th>
					<th width="300">제목</th>
					<th width="100">작성자</th>
					<th width="50">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				
				<!-- 게시글 출력 : 게시글이 있는지 없는지 => inEmpty() -->
				
				<% if(list.isEmpty()) { %>
					<tr>
						<td colspan="6">조회된 게시글이 없습니다.</td>
					</tr>
				<% } else { %>
					<!-- 반복문 : list에 있는 값을 순차적으로 접근해서 뽑아오기 -->
					<% for(Community c : list) { %>
						<tr class='llist'>
							<td><%= c.getCommNo() %></td>
							<td width = "150px"><%= c.getCategory() %></td>
							<td><%= c.getCommTitle().substring(0, 7) %>. . .</td>
							<td><%= c.getCommWriter() %></td>
							<td><%= c.getCount() %></td>
							<td><%= c.getCreateDate() %></td>
						</tr>
					<% } %>
				<% } %>
				
			</tbody>
		</table>
         <script>
		
         $(function(){
        	
        	 
        	 <% if(list.get(0).getCategory().equals("와글와글게시판")) { %>
     	 		$('#question').removeClass('question');
     	 	<% } else {%>
     	 		$('#wagle').removeClass('wagle');
     	 	<% } %>
         $("#question").click(function() {
        	 
        	 $('#wagle').removeClass('wagle');
        	  	 
        	 })
 	 
         })
			$(function(){
	
				$(".list-area>tbody>tr").click(function(){
		
					
					var ccno = $(this).children().eq(0).text();
	
					
					// "쿼리 스트링" : ? 뒤의 내용들, 직접 쿼리스트링을 만들어서 요청
					
							
					location.href="<%=contextPath%>/detail.bo?ccno="+ccno;
					
	
				})
	
			})
			
		</script>

		<br><br>
		
		<%if(list.get(0).getCategory().equals("와글와글게시판")) {%>
		<div align="center" class="paging-area">
			<% if(currentPage != 1) { %>  <!-- 페이징바에서 <를 담당 -->
			<button class="btn btn-info" onclick="location.href='<%= contextPath %>/list.bo?cpage=<%= currentPage-1 %>&cno=50'"> &lt; </button>
			<% } %>
			<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(i != currentPage) { %>
				<button class="btn btn-info" onclick="location.href='<%= contextPath %>/list.bo?cpage=<%=i %>&cno=50'" ><%= i %></button>
				<% } else { %>
					<button class="btn btn-info" disabled><%= i %></button>
				<% } %>
			<% } %>
			<% if(currentPage != maxPage) { %>
			<button class="btn btn-info" onclick="location.href='<%= contextPath %>/list.bo?cpage=<%= currentPage+1 %>&cno=50'"> &gt; </button>
			<% } %>
		</div>
		<% } else if(list.get(0).getCategory().equals("질문게시판")){ %>
            <div align="center" class="paging-area">
			<% if(currentPage != 1) { %>  <!-- 페이징바에서 <를 담당 -->
			<button class="btn btn-info" onclick="location.href='<%= contextPath %>/list.bo?cpage=<%= currentPage-1 %>&cno=60'"> &lt; </button>
			<% } %>
			<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(i != currentPage) { %>
				<button class="btn btn-info" onclick="location.href='<%= contextPath %>/list.bo?cpage=<%=i %>&cno=60'" ><%= i %></button>
				<% } else { %>
					<button class="btn btn-info" disabled><%= i %></button>
				<% } %>
			<% } %>
			<% if(currentPage != maxPage) { %>
			<button class="btn btn-info" onclick="location.href='<%= contextPath %>/list.bo?cpage=<%= currentPage+1 %>&cno=60'"> &gt; </button>
			<% } %>
		</div>
      	
      	<% } %>
         
      </div>
      <div class="content_3"></div>

   </div>
   <br clear="both"><br><br><br>
   
   
   <%@ include file="../common/footer.jsp" %>
</body>
</html>