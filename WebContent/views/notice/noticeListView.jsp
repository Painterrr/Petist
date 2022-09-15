<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList, com.kh.notice.model.vo.Notice, com.kh.common.model.vo.PageInfo" %>
<%
   ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
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
<title>Petist-공지사항</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
    .outer{
      background-color: white;
      color: black;
      width: 1000px;
      margin: auto;
      margin-top: 30px;
   }

    .list-area{
       border: 1px solid white;
        text-align: center;
    }

    .list-area>tbody>tr:hover{
        cursor: pointer;
        background-color: rgb(240, 240, 240);
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
   .noticeEnroll{
      background-color: rgb(26, 188, 156);
      border-radius: 10px;
      color: white;
      border:none;
      width: 100px;
      padding: 5px;
      margin-right: 30px;
   }
</style>
</head>
<body>

   <%@ include file="../common/menubar.jsp" %>
   
   
      <br><br><br>
      <h2 align="center">공지사항</h2>
      <br>


      <div class="content"> <!--  -->
         <div class="content_1">
            
             </div>
         <div class="content_2">
            <!-- 로그인 전 화면에서 공지사항에 바로 들어가면 세선에 값이 없어서 문자열 비교를 할 수 없어서 500에러 발생 -->
            <% if(loginUser != null && loginUser.getMemberId().equals("admin")) { %>
               <div align="right" style="width:850px;">
                  <a href="<%= contextPath %>/enrollForm.no" class="noticeEnroll">공지사항 등록</a>
                  <br><br>
               </div>
            <% } %>

            <table class="list-area" align="center" width="700">
               <thead>
                  <tr>
                     <th width="70">글번호</th>
                     <th width="300">제목</th>
                     <th width="100">작성자</th>
                     <th width="50">조회수</th>
                     <th width="100">작성일</th>
                  </tr>
               </thead>
               <tbody>
                  <%if(list.isEmpty()) { %> <!-- 게시글 없음 -->
                     <tr>
                        <td colspan="5">조회된 공지사항이 없어요</td>
                     </tr>
                  <% } else { %>
                     <% for(Notice n : list) { %>
                        <tr>
                           <td><%= n.getNoticeNo() %></td>
                           <td><%= n.getNoticeTitle().substring(0,8) %></td>
                           <td><%= n.getNoticeWriter() %></td>
                           <td><%= n.getCount() %></td>
                           <td><%= n.getCreateDate() %></td>
                        <tr>
                     <% } %>
                  <% } %>
               </tbody>
            </table>
            <br><br><br><br><br><br><br><br><br>
            <div align="center">
      
               <% if(currentPage != 1) { %>   
                  <button class="btn btn-sm btn-info" style="background-color: rgb(26, 188, 156); color:white; height: 31px; border:none;" onclick="location.href='<%= contextPath %>/list.no?cpage=<%= currentPage - 1 %>'"> &lt; </button>
               <% } %>
               
               <% for(int i = startPage; i <= endPage; i++) { %>
                  <% if(i != currentPage) {%>
                     <button class="btn btn-sm btn-info" style="background-color: rgb(26, 188, 156); color:white; height: 31px; border:none;"onclick="location.href='<%= contextPath %>/list.no?cpage=<%= i %>'"><%= i %></button>
                  <% } else { %>
                     <button class="btn btn-sm btn-secondary" style="background-color: rgb(245, 245, 245); color:black; height: 31px; border:none;" disabled><%= i %></button>
                  <% } %>
               <% } %>
               
               <% if(currentPage != maxPage) { %>
                  <button class="btn btn-sm btn-info" style="background-color: rgb(26, 188, 156); color:white; height: 31px; border:none;" onclick="location.href='<%= contextPath %>/list.no?cpage=<%= currentPage + 1 %>'"> &gt; </button>
               <% } %>
      
            </div>
         </div>
         <div class="content_3">
         </div>
      </div>

      <br><br><br>
      
      
      
               
      
      <br clear="both"><br>
      
      <%@ include file="../common/footer.jsp" %>
   
       <script>
           $(function(){
               $(".list-area>tbody>tr").click(function(){
                   var nno = $(this).children().eq(0).text();
                   location.href="<%= contextPath %>/detail.no?nno=" + nno + "&cpage=" + <%= currentPage %>;
               })
           })
       </script>

</body>
</html>