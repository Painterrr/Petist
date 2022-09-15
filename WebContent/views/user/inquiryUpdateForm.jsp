<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.admin.model.vo.*, java.util.ArrayList" %>
<%
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
	Inquiry i = (Inquiry)request.getAttribute("i");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-문의글 수정하기</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
</head>
<body>
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
         
         <form id="update-form" action="<%= contextPath %>/update.in" method="post">

         
         <input type="hidden" name="inquiryNo" value="<%= i.getInquiryNo() %>">       
         
         <table align="center">
            <tr>
               <th width="90px">카테고리</th>
               <td width="600px">
                  <select name="category">
                  	<% for(Category c : list) { %>
                    	<option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>
                    <% } %>
                  </select>
                 	 <script>
						$(function(){
							$('#update-form option').each(function(){
								//옵션태그의 카테고리명이랑 보드객체에 있는 카테고리랑 동일하면
								if($(this).text() == "<%= i.getCategory() %>"){
									$(this).attr("selected", "true")
								}
							})
						})
					</script>
               </td>
            </tr>
            <tr>
               <th>제목</th>
               <td><input type="text" name="title" value="<%= i.getInquiryTitle() %>"required minlength="5"></td>
            </tr>
            <tr>
               <th>내용</th>
               <td>
                  <textarea name="content" rows="10"  cols="92" required minlength="8" style="resize: none;"><%= i.getInquiryContent() %></textarea>
               </td>
            </tr>
            
         </table>
         <br>
            <div id="enrollbtn">
               <button type="submit" class="btn btn-outline-secondary">수정하기</button>
               <button type="button" class="btn btn-outline-secondary" onclick="history.back();">뒤로가기</button>
            </div>




         



      </form>
         
      </div>
      <div class="content_3"></div>

   </div>
   <br clear="both"><br><br><br>
   
   
   <%@ include file="../common/footer.jsp" %>
</body>
</html>