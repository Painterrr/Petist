<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.admin.model.vo.Inquiry, com.kh.common.model.vo.PageInfo" %>
<%
	ArrayList<Inquiry> list = (ArrayList<Inquiry>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-1대1 문의</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
</head>
<style>

	
   #myPageMenu {
      font-size: 27px;
      font-weight:bolder;
      border-bottom: 1px solid black;
      width: 170px;
      text-align: center;
      color: rgb(26, 188, 156);
      margin-top: 30px;
   }   
   .content_1{
   		padding-right:50px;
   		padding-bottom:500px;
   		align:center;
   }
 
   .nav{
      width: 160px;
      margin: 15px;
   }
  #myInfo {
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
	.content_2{
     	padding-top: 100px;
    }
    #myinquiry{
    	margin-left:125px;
    	width:800px;
    	text-align:center;
    	border: 1px solid black;
    	
    }
    #myinquiry th{
    	text-align:center;
		border-bottom:0.5px solid black;    	
    }
    #myinquiry td{
    	padding-left:5px;
    }
	.inquiry{
	  	font-size: 29px;
      	font-weight:bolder;
      	border-bottom: 1px solid black;
      	width: 140px;
      	text-align: center;
      	color: black;;
      	margin-bottom : 50px;
      	margin-left: 125px;
	}
    .enrollFormBtn{
		margin-left : 835px;
		margin-bottom : 15px;
    }
    .list-area>tbody>tr:hover{
      cursor : pointer;
      background-color: lightgrey;
   }
   .btnpage{
    	border:none;
    	background-color:white;
    	color: rgb(26, 188, 156);
    	width:28px;
    	text-align:center;
    	border-radius: 8px;
    	font-weight:bold;
    }
    .hpage{
    	background-color: rgb(26, 188, 156);
    	border:1px solid rgb(26, 188, 156);
		color:white;
		width:28px;
		text-align:center;		
    	border-radius: 8px;
    	font-weight:bold;

    }
    .paging-area{
      padding-top: 40px;
      text-align:center;
   }
</style>
<body>
 <%@ include file="../common/menubar.jsp" %>

   <div class="content" style="min-height:700px;">
      <div class="content_1">
      	 <div id="myPageMenu">마이페이지</div>
         <ul class="nav">
            <li id="myInfo">
              	 회원관리
            </li>
            <li> 
               <ul>
                  <% if(loginUser != null && loginHosp == null) { %>
                  <li>
                     <a href="<%= contextPath %>/myPage.us">내 정보 수정</a>
                  </li>
                  <li>
                     <a href="<%= contextPath %>/myPagePwdUpdate.us">비밀번호 변경</a>
                  </li>
					<% } else { %>
					<li>
                     <a href="<%= contextPath %>/myPage.us">비밀번호 변경</a>
                  </li>
                  <% } %>
                  <li>
                     <a href="<%= contextPath %>/myPageDelete.us">회원 탈퇴</a>
                  </li>
                  <li>
                  	<% if(loginUser != null && loginHosp == null) { %>
                     <a href="<%= contextPath %>/inquiry.in?memberId=<%= loginUser.getMemberId() %>&cpage=1" style="font-weight:bold">1대1 문의</a>
                  	<% } else { %>
        		     <a href="<%= contextPath %>/inquiry.in?memberId=<%= loginHosp.getHospId() %>&cpage=1" style="font-weight:bold">1대1 문의</a>
                  	<% } %>
                  </li>
               </ul>
            </li>

         </ul>
      </div>
      <div class="content_2">
      
      		<% if(loginUser != null && loginHosp == null) { %>
	   		   	<div class="inquiry">1대1 문의</div>
	      		<div class="enrollFormBtn">
	      			<% if(loginUser!=null) { %>
	      				<a href="<%= contextPath %>/enrollForm.in" class="btn btn-outline-secondary">문의작성</a>
	      			<% } %>
	      		</div>
	      		 <input type="hidden" name="userId" value="<%= loginUser.getMemberId() %>">
	   		   <table id="myinquiry" align="center" class="list-area">
	               <thead>
	                  <tr>
	                     <th width="100px">카테고리</th>
	                     <th width="100px">아이디</th>
	                     <th width="150px">문의제목</th>
	                     <th width="300px">문의내용</th>
	                     <th width="100px">작성일</th>
	                  </tr>
	               </thead>
	               <tbody>
					  <% if(list.isEmpty()) { %>
	                     <tr>
	                        <td colspan="5">조회된 게시글이 없습니다.</td>
	                     </tr>
	                  <% } else { %>
	                     <% for(Inquiry i : list) { %>
	                        <tr>
	                       		<td style="display:none;"><%= i.getInquiryNo() %></td>
	                           <td><%= i.getCategory() %></td>
	                           <td><%= i.getUserId() %></td>
	                           <td><%= i.getInquiryTitle().substring(0,5) %>.....</td>
	 	                       <td><%= i.getInquiryContent().substring(0,8) %>.....</td>
	                           <td><%= i.getCreateDate()%></td>
	                        </tr>
	                     <% } %>
	                  <% } %>
	               </tbody>
	            </table>
            
            
            
            <script>
            	$(function(){
	            	$(".list-area>tbody>tr").click(function(){
	            		var ino = $(this).children().eq(0).text();
	            		
						location.href="<%= contextPath %>/detail.in?ino="+ino;
						
	            	})
            		
            	})
            	
            	
            
            
            
            </script>
   		<% } else { %>
   		<div class="inquiry">1대1 문의</div>
	      		<div class="enrollFormBtn">
	      			<% if(loginHosp!=null) { %>
	      				<a href="<%= contextPath %>/enrollForm.in" class="btn btn-outline-secondary">문의작성</a>
	      			<% } %>
	      		</div>
	      		 <input type="hidden" name="userId" value="<%= loginHosp.getHospId() %>">
	   		   <table id="myinquiry" align="center" class="list-area">
	               <thead>
	                  <tr>
	                     <th width="100px">카테고리</th>
	                     <th width="100px">아이디</th>
	                     <th width="150px">문의제목</th>
	                     <th width="300px">문의내용</th>
	                     <th width="100px">작성일</th>
	                  </tr>
	               </thead>
	               <tbody>
					  <% if(list.isEmpty()) { %>
	                     <tr>
	                        <td colspan="5">조회된 게시글이 없습니다.</td>
	                     </tr>
	                  <% } else { %>
	                     <% for(Inquiry i : list) { %>
	                        <tr>
	                       		<td style="display:none;"><%= i.getInquiryNo() %></td>
	                           <td><%= i.getCategory() %></td>
	                           <td><%= i.getUserId() %></td>
	                           <td><%= i.getInquiryTitle().substring(0,5) %>.....</td>
	 	                       <td><%= i.getInquiryContent().substring(0,8) %>.....</td>
	                           <td><%= i.getCreateDate()%></td>
	                        </tr>
	                     <% } %>
	                  <% } %>
	               </tbody>
	            </table>
            
            
            
            <script>
            	$(function(){
	            	$(".list-area>tbody>tr").click(function(){
	            		var ino = $(this).children().eq(0).text();
	            		
						location.href="<%= contextPath %>/detail.in?ino="+ino;
						
	            	})
            		
            	})
            	
            	
            
            
            
            </script>
   		
   		
   		<% } %>
   		
   		<div align="center" class="paging-area">
   		  <% if(loginUser != null && loginHosp == null) { %>
	           <% if(currentPage != 1) { %> <!-- 페이징바에서 <를 담당 -->
	               <button class="btnpage" onclick="location.href='<%= contextPath %>/inquiry.in?memberId=<%= loginUser.getMemberId() %>&cpage=<%= currentPage - 1 %>'"> &lt; </button>
	            <% } %>
	            
	            <% for(int i = startPage; i<=endPage; i++) { %>
	               <% if(i != currentPage) { %>   
	               	  <button class="btnpage" onclick="location.href='<%= contextPath %>/inquiry.in?memberId=<%= loginUser.getMemberId() %>&cpage=<%= i %>'"><%= i %></button>
	               <% } else { %>
	                  <button class="hpage" disabled><%= i %></button>
	               <% } %>
	            <% } %>
	            
	            
	            <% if(currentPage != maxPage) { %>
	               <button class="btnpage" onclick="location.href='<%= contextPath %>/inquiry.in?memberId=<%= loginUser.getMemberId() %>&cpage=<%= currentPage + 1 %>'"> &gt; </button>
	            <% } %>
            <% } else { %>
	            	<% if(currentPage != 1) { %> <!-- 페이징바에서 <를 담당 -->
		               <button class="btnpage" onclick="location.href='<%= contextPath %>/inquiry.in?memberId=<%= loginHosp.getHospId() %>&cpage=<%= currentPage - 1 %>'"> &lt; </button>
		            <% } %>
		            
		            <% for(int i = startPage; i<=endPage; i++) { %>
		               <% if(i != currentPage) { %>   
		               	  <button class="btnpage" onclick="location.href='<%= contextPath %>/inquiry.in?memberId=<%= loginHosp.getHospId() %>&cpage=<%= i %>'"><%= i %></button>
		               <% } else { %>
		                  <button class="hpage" disabled><%= i %></button>
		               <% } %>
		            <% } %>
		            
		            
		            <% if(currentPage != maxPage) { %>
		               <button class="btnpage" onclick="location.href='<%= contextPath %>/inquiry.in?memberId=<%= loginHosp.getHospId() %>&cpage=<%= currentPage + 1 %>'"> &gt; </button>
		            <% } %>
            <% } %>
         </div>

   
      </div>

      <div class="content_3"></div>

   </div>
   <br clear="both"><br><br><br>


    <%@ include file="../common/footer.jsp" %>
   
</body>
</html>