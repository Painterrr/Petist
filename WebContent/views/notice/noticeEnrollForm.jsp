<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-공지사항 등록</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
   .outer{
      color: black;
      width: 1000px;
      height: 800px;
      margin: auto;
      margin-top: 50px;
   }
   #enroll-form>table{border: 1px solid white;}
   #enroll-form input, #enroll-form textarea{
      width: 100%;
      box-sizing: border-box;
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
</style>
</head>
<body>
   <%@ include file="../common/menubar.jsp" %>
   
   
   <div class="outer">
      <div class="content">
         <div class="content_1">
            
          </div>
         <div class="content_2">
            <br>
            <h2 align="center">공지사항 등록하기</h2>
            <br><br>
            
            <form action="<%= contextPath %>/insert.no" id="enroll-form" method="post">
            
               <input type="hidden" name="memberId" value="<%= loginUser.getMemberId() %>">
               
               <table align="center" style="width: 800px">
                  <tr>
                     <th width="50">제목</th>
                     <td width="380"><input type="text" name="title" required minlength="8" placeholder="8글자 이상 입력하세요."></td>
                  </tr>
                  <tr>
                     <th>내용</th>
                     <td></td>
                  </tr>
                  <tr>
                     <td colspan="2">
                        <textarea name="content" rows="10" style="resize:none" required placeholder="(공백포함 1000자 이내)"></textarea>
                     </td>
                  </tr>
               </table>
               <br><br>

               <div align="center">
                  <button type="submit" class="btn btn-sm btn-info" style="background-color: rgb(26, 188, 156); color:white; height: 31px; border:none;">등록하기</button>
                  <button type="button" onclick="history.back();" class="btn btn-sm btn-secondary">뒤로가기</button>
                  <!--history.back() >> 이전 페이지로 이동-->
               </div>

            </form>
         </div>
         <div class="content_3">
            
         </div>
      </div>
   </div>
</body>
</html>