	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.user.model.vo.Member, com.kh.common.model.vo.PageInfo" %>
<%
ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");
PageInfo pi = (PageInfo)request.getAttribute("pi");

//페이징바 만들때 필요한 변수 미리 셋팅
int currentPage = pi.getCurrentPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-관리자페이지</title>
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
   #searchMem{
      margin-bottom: 20px;
   }
   
   #selectMem{
      text-align: center;
   }
   .paging-area{
      padding-top: 40px;
      text-align:center;
   }
   .searchMem button{
   	   border:1px white;
   	   background-color: rgb(26, 188, 156);
   	   color : white;
   	   height:32px;
   	   border-radius:5px;
   }
   #deleteCancel{
   		width:80px;
   }
   .searchList{
   		text-align:center;
   		 cursor : pointer;
   }
   #selectMem{
   		text-align:center;
   		 cursor : pointer;
   }
   .member{
    	font-size: 18px;
     	font-weight:bolder;
     	width: 170px;
      	color: rgb(26, 188, 156);
      	margin-top: 25px;
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
     #selectMem>tbody>tr:hover{
        cursor : pointer;
        background-color: rgb(235,235,235);
    }
    .searchList>tbody>tr:hover{
        cursor : pointer;
        background-color: rgb(235,235,235);
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
                     <a href="<%= contextPath %>/list.mem?cpage=1" style="font-weight:bold">회원검색/탈퇴</a>
                  </li>
                  <li>
                     <a href="<%= contextPath %>/list.re?cpage=1">신고내역</a>
                  </li>
                  <li>
                     <a href="<%= contextPath %>/list.in?cpage=1">1대1 문의</a>
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
     		<table class="searchMem">
               <tr>
                  <th width="800">
                     <input type="text" id="searchId" placeholder="아이디를 입력하시오" >
                     <button type="submit" onclick="searchIdMember();">검색</button>
                  </th>
                  <th width="70px"><button id="deleteBtn" onclick="blockBtn();">차단</button></th>
                  <th width="70px"><button id="deleteCancel" onclick="UnBlockBtn();">차단해제</button></th>
               </tr>
            </table>
            
             <div id="searchTable">
            	<div class="member">아이디조회결과</div>
            	<table id="report" class="searchList">
               <thead>
                  <tr>
                     <th width="60px">선택</th>
	                  <th width="80px">아이디</th>
	                  <th width="250px">병원이름 / 회원이름</th>
	                  <th width="150px">연락처</th>
	                  <th width="200px">이메일</th>
	                  <th width="60px">상태</th>
                  </tr>
               </thead>
               <tbody>
	              <tr>
	              	<td colspan='6'>검색한 결과가 없습니다.</td>
	              </tr>
               </tbody>
            </table>
            </div>
            
            <div class="member">전체 회원</div>
            <table id="selectMem">
            	<thead>
	               <tr>
	               	  <th width="60px">선택</th>
	                  <th width="80px">아이디</th>
	                  <th width="250px">병원이름 / 회원이름</th>
	                  <th width="150px">연락처</th>
	                  <th width="200px">이메일</th>
	                  <th width="60px">상태</th>
	               </tr>
               </thead>
               <tbody>
               	<% if(list.isEmpty()) { %> <!-- 조회글 없음 -->
					<tr>
						<td colspan="6">조회된 게시글이 없습니다.</td>
					</tr>
				<% } else { %>
					<!-- 반복 : list에 있는 값을 순차적으로 접근해서 뽑아오기 -->
					<% for(Member m : list) { %>
						<tr>
							<td><input type="radio" name="Chk_list" value="<%=m.getMemberId() %>"></td>
							<td><%= m.getMemberId() %></td>
							<td><%= m.getMemberName() %></td>
							<td><%= m.getPhone() %></td>
							<td><%= m.getEmail() %></td>
							<td><%= m.getStatus() %></td>
						</tr>
					<% } %>
				<% } %>
               </tbody>
          		
				
            </table>
            

         <div align="center" class="paging-area">
            <% if(currentPage != 1) { %> <!-- 페이징바에서 <를 담당 -->
               <button class="btnpage" onclick="location.href='<%= contextPath %>/list.mem?cpage=<%= currentPage - 1 %>'"> &lt; </button>
            <% } %>
            
            <% for(int i = startPage; i<=endPage; i++) { %>
               <% if(i != currentPage) { %>   
               	  <button class="btnpage" onclick="location.href='<%= contextPath %>/list.mem?cpage=<%= i %>'"><%= i %></button>
               <% } else { %>
                  <button class="hpage" disabled><%= i %></button>
               <% } %>
            <% } %>
            
            
            <% if(currentPage != maxPage) { %>
               <button class="btnpage" onclick="location.href='<%= contextPath %>/list.mem?cpage=<%= currentPage + 1 %>'"> &gt; </button>
            <% } %>
         </div>
         
         
      </div>
      <div class="content_3"></div>

      








   </div>
   <br clear="both"><br><br><br>


   <script>
   function searchIdMember(){
       $.ajax({
          url : "searchId.mem",
          data : {searchId : $("#searchId").val() },
          success : function(list){
             
             if(list != null){
                var result = "";
                   for(var i in list){
                      result += "<tr>"
                              + "<td><input type='radio' name='Chk_list' value='"+list[i].memberId+"'></td>"
                              + "<td>" + list[i].memberId + "</td>"
                              + "<td>" + list[i].memberName + "</td>"
                              + "<td>" + list[i].phone + "</td>"
                              + "<td>" + list[i].email + "</td>"
                              + "<td>" + list[i].status + "</td>"
                              + "</tr>";
                   }
             } else {
                var result2 ="";
                   result2 +="<tr>"
                         +"<td colspan='6'>"+"검색한 결과가 없습니다"+"</td>"
                         +"</tr>"
             }
             $('.searchList tbody').html(result);
             $('.searchList tbody').html(result2);
             
          },
          error : function(){
             
          }
          
       });
    }
   
		
   		
		function blockBtn(){
			$.ajax({
				url:"blockMem.mem",
				data:{blockMem : $("input[name='Chk_list']:checked").val()
				},
				success: function(result){
					location.href = location.href;
				},
				error : function(){
					console.log('삭제실패')
				}
			});
		}
		
   
   
   		function UnBlockBtn(){
			$.ajax({
				url:"unBlockMem.mem",
				data:{unBlockMem : $("input[name='Chk_list']:checked").val()
				},
				success: function(result){
					location.href = location.href;
					
				},
				error : function(){
					console.log('해제실패')
				}
			});
		}
   		
		
   


   </script>

    <%@ include file="../common/footer.jsp" %>
   

</body>
</html>