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
<title>Petist-1대1 문의글</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>   
      
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
   .content_2{
      padding-left:150px;
      padding-top: 130px;
   }
   #reachMem{
      margin-bottom: 20px;
   }
   #selectMem{
      text-align: center;
   }
   .listButton{
      padding-top: 40px;
      text-align:center;
   }
   #loginUserB{
      margin-top : 40px;
   }
   .paging-area{
   	  padding-top: 40px;
   }
   .list-area>tbody>tr:hover{
      cursor : pointer;
      background-color: lightgrey;
   }
   #searchMem{
      margin-bottom: 20px;
   }
   #searchMem button{
   	   border:1px white;
   	   background-color: rgb(26, 188, 156);
   	   color : white;
   	   height:32px;
   	   border-radius:5px;
   }
   .report{
    	font-size: 18px;
      font-weight:bolder;
      width: 170px;
      color: rgb(26, 188, 156);
      margin-top: 25px;
    }
     #searchTable{
    	padding-bottom:10px;
    }
    .searchList{
    	text-align:center;
    }
   .list-area>tbody>tr:hover{
        cursor : pointer;
        background-color: lightgrey;
    }
    .list-area>tbody>tr:hover{
        cursor : pointer;
        background-color: rgb(235,235,235);
    }
    .searchList>tbody>tr:hover{
        cursor : pointer;
        background-color: rgb(235,235,235);
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
</style>
</head>
<body>

   <%@ include file="../common/menubar.jsp" %>

   <div class="content" style="min-height:700px;">
      <div class="content_1">
         <div id="adminPageMenu">관리자 페이지</div>
         <ul class="nav">
            <li id="management">
              	 회원관리
            </li>
            <li> 
               <ul>
                  <li>
                     <a href="<%= contextPath %>/list.mem?cpage=1">회원검색/탈퇴</a>
                  </li>
                  <li>
                     <a href="<%= contextPath %>/list.re?cpage=1">신고내역</a>
                  </li>
                  <li>
                     <a href="<%= contextPath %>/list.in?cpage=1" style="font-weight:bold">1대1 문의</a>
                  </li>
               </ul>
            </li>
            <li id="board">
              	 게시판
            </li>
            <li>
               <ul>
                  
                  <li>
                     <a href="<%= contextPath %>/admin.ad">게시글 관리</a>
                  </li>
               </ul>
            </li>
            


         </ul>
      </div>
      <div class="content_2">
      		<table id="searchMem">
               <tr>
                  <th width="730px">
                     <input type="text" id="searchId" placeholder="아이디를 입력하시오" >
                     <button type="submit" onclick="searchIdInquiry();">검색</button>
                  </th>
                   <th><button class="checkOk" onclick="checkOk();" >답변완료</button></th>
               </tr>
            </table>
            
       		<div id="searchTable">
            	<div class="report">아이디조회결과</div>
            <table id="report" class="searchList">
               <thead>
                  <tr>
                     <th width="60px">선택</th>
                     <th width="70px">번호</th>
                     <th width="90px">카테고리</th>
                     <th width="90px">아이디</th>
                     <th width="150px">문의제목</th>
                     <th width="230px">문의내용</th>
                     <th width="100px">작성일</th>
                     <th width="100px">답변상태</th>
                  </tr>
               </thead>
               <tbody>
	              
               </tbody>
            </table>
            </div>
            
            <div class="report">전체 문의내역</div>
   		   <table id="selectMem" class="list-area">
               <thead>
                  <tr>
                  	 <th width="60px">선택</th>
                     <th width="70px">번호</th>
                     <th width="90px">카테고리</th>
                     <th width="90px">아이디</th>
                     <th width="150px">문의제목</th>
                     <th width="230px">문의내용</th>
                     <th width="100px">작성일</th>
                     <th width="100px">답변상태</th>
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
                           <th><input type="radio" name="okCheck" value="<%=i.getInquiryNo() %>"></th>
                           <td><%= i.getInquiryNo() %></td>
                           <td><%= i.getCategory() %></td>
                           <td><%= i.getUserId() %></td>
                           <td><%= i.getInquiryTitle().substring(0,5) %>.....</td>
                           <td class='inquiryContent'><%= i.getInquiryContent().substring(0,8) %>.....</td>
                           <td><%= i.getCreateDate()%></td>
                           <td><%= i.getCheckStatus() %></td>
                        </tr>
                     <% } %>
                  <% } %>
               </tbody>
            </table>
            
            <script>
            	$(function(){
            		$(".list-area>tbody>tr>td").click(function(){
	            		var ino = $(this).parent().children().eq(1).text();
						location.href="<%= contextPath %>/detail.in?ino=" + ino;
	            	});
	            	
	            	
	            	$(document).ready(function(){
	            		$(document).on("click",".searchList>tbody>tr>td",
	            		function(){
	            		
	            		var ino =$(this).parent().children().eq(1).text();
	            		
						location.href="<%= contextPath %>/detail.in?ino=" + ino;
	            		});
            		
	            	});
            	})
            	
            	
            	
            	
            	
            	
            	
            	
            	
            </script>
   		
   		 <div align="center" class="paging-area">
            <% if(currentPage != 1) { %> <!-- 페이징바에서 <를 담당 -->
               <button class="btnpage" onclick="location.href='<%= contextPath %>/list.in?cpage=<%= currentPage - 1 %>'"> &lt; </button>
            <% } %>
            
            <% for(int i = startPage; i<=endPage; i++) { %>
               <% if(i != currentPage) { %>   
               	  <button class="btnpage" onclick="location.href='<%= contextPath %>/list.in?cpage=<%= i %>'"><%= i %></button>
               <% } else { %>
                  <button class="hpage" disabled><%= i %></button>
               <% } %>
            <% } %>
            
            
            <% if(currentPage != maxPage) { %>
               <button class="btnpage" onclick="location.href='<%= contextPath %>/list.in?cpage=<%= currentPage + 1 %>'"> &gt; </button>
            <% } %>
         </div>

   
      </div>

      <div class="content_3"></div>

   </div>
   <br clear="both"><br><br><br>
   
	<script>
		function searchIdInquiry(){
			$.ajax({
				url : "searchId.in",
				data : {searchId : $("#searchId").val() },
				success : function(list){
				
					var result = "";
					for(var i in list){
						result += "<tr>"
								+ "<th><input type='radio' name='okCheck' value='"+list[i].inquiryNo+"'></th>"
							  	+ "<td>" + list[i].inquiryNo + "</td>"
							    + "<td>" + list[i].category + "</td>"
							    + "<td>" + list[i].userId + "</td>"
							    + "<td>" + list[i].inquiryTitle.substring(0,5)+'.....'+"</td>"
							    + "<td>" + list[i].inquiryContent.substring(0,8)+'.....'+"</td>"
							    + "<td>" + list[i].createDate + "</td>"
							    + "<td>" + list[i].checkStatus + "</td>"
							    + "</tr>";
				}
				$('.searchList tbody').html(result);
			},
			error : function(){
				console.log("댓글 읽어오기 실패")
			}
		});
	}

	$(function(){
		searchIdInquiry();
		
	});

	
	function checkOk(){
		$.ajax({
			url:"okCheck.in",
			data:{okCheck : $("input[name='okCheck']:checked").val()
			},
			success: function(result){
				location.href = location.href;
				
			},
			error : function(){
				console.log('삭제실패')
			}
		});
	}


	
	
	</script>

    <%@ include file="../common/footer.jsp" %>
   

</body>
</html>