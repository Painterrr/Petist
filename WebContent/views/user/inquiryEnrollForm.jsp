<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.admin.model.vo.Category, java.util.ArrayList" %>
<%
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");




%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-문의글 작성하기</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>   
      
  
   
   table{
      text-align: center;
      border:1px solid lightgray;
      width:800px;
   }
   table td{
   		text-align:left;
   		vertical-align: top;
   		
   }
  
   table th{
   		height:30px;
   }
  	
  	tr input{
  		width:699px
  	}
	#enrollbtn{
		text-align:right;
		padding-right:80px;
	}
   
</style>
</head>
<body>
   <%@ include file="../common/menubar.jsp" %>
   
   <div class="content">
      <div class="content_1">
      
      </div>
      
      <div class="content_2">
      	<br><br>
         <h2>1대1 문의</h2>
         <hr>
         
         <form id="enroll-form" action="<%= contextPath %>/insert.in" method="post">

         <!-- 일반유저의 문의글 작성폼 -->
         <% if(loginUser!=null) {%>
         <input type="hidden" name="userId" value="<%= loginUser.getMemberId() %>">       
         
         <table align="center">
            <tr>
               <th width="90px">카테고리</th>
               <td width="600px">
                  <select name="category">
                  	<% for(Category c : list) { %>
                    	<option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>
                    <% } %>
                  </select>
               </td>
            </tr>
            <tr>
               <th>제목</th>
               <td><input type="text" name="title" required minlength="5"></td>
            </tr>
            <tr>
               <th>내용</th>
               <td>
                  <textarea name="content" rows="10"  cols="92" required style="resize: none;" minlength="8"></textarea>
               </td>
            </tr>
            
         </table>
         <br>
            <div id="enrollbtn">
               <button type="submit" class="btn btn-outline-secondary">작성하기</button>
               <button type="reset" onclick="history.back();" class="btn btn-outline-secondary">취소하기</button>
            </div>

		<% } else { %>
		<!-- 병원회원의 문의글 작성폼 -->
		<input type="hidden" name="userId" value="<%= loginHosp.getHospId() %>">       
         
         <table align="center">
            <tr>
               <th width="90px">카테고리</th>
               <td width="600px">
                  <select name="category">
                  	<% for(Category c : list) { %>
                    	<option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>
                    <% } %>
                  </select>
               </td>
            </tr>
            <tr>
               <th>제목</th>
               <td><input type="text" name="title" required minlength="5"></td>
            </tr>
            <tr>
               <th>내용</th>
               <td>
                  <textarea name="content" rows="10"  cols="92" required style="resize: none;" minlength="8"></textarea>
               </td>
            </tr>
            
         </table>
         <br>
            <div id="enrollbtn">
               <button type="submit" class="btn btn-outline-secondary">작성하기</button>
               <button type="reset" onclick="history.back();" class="btn btn-outline-secondary">취소하기</button>
            </div>
		
		
		<% } %>
      </form>
         
      </div>
      <div class="content_3"></div>

   </div>
   <br clear="both"><br><br><br>
   
   
   <%@ include file="../common/footer.jsp" %>
</body>
</html>