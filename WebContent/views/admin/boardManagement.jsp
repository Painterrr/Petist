   <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.community.model.vo.Community"%>
<%
   ArrayList<Community> list = (ArrayList<Community>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-게시글 관리</title>
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
      cursor: pointer;
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
   .text{
      float: right;   
   }
   .push, .delete{
      background-color: rgb(26, 188, 156);
      color: white;
      height: 30px;
      border-radius: 10%;
      float: right;
      border: 0;
      margin-left: 5px;
   }
   #contentForm{
      margin-top: 100px;
   }
   .row{
      margin-top: 100px;
       border: 1px solid white;
        text-align: center;
    }

    .list-area>tbody>tr:hover{
        cursor: pointer;
        background-color: rgb(240, 240, 240);
    }
    
</style>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>

    <div class="content">
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
                           <a href="<%= contextPath %>/admin.ad"  style="font-weight:bold">게시글 관리</a>
                        </li>
                     </ul>
                  </li>
                  
  
  
              </ul>
          </div>
          <div class="content_2">
            <h3 align="center">게시글 관리</h3>
            
             <div>
               <hr>
            </div>
            <table style="float: right">
                     <tr>
                  <td>
                     <input type="text" class="text" id="searchWord" placeholder="제목 검색어 입력">
                  </td>
                  <td style="float: right;">
                     <button class="push" onclick="searchWord();">검색</button>
                  </td>
                  <td style="float: left;">
                     <button class="delete" onclick="deleteBoard();">선택 삭제</button>
                  </td>
               </tr>               
               </table>
            
            <div class="row">
               <table class="list-area" align="center">
                  <tbody>

                  </tbody>
               </table>
            </div>
          
          </div>
          
          <div class="content_3"></div>
    </div>
     <br clear="both"><br><br><br>

     <%@ include file="../common/footer.jsp" %>

   <script>
      //게시글 검색어 조회
      function searchWord() {
         $.ajax({
            url: "searchBoard.ad",
            data: {
               searchWord: $("#searchWord").val()
            },
            success: function(list){
               console.log("searchBoardList: " + list);
               var result = "";
                for(var i in list) {
                  result += "<tr>"
                        + "<td>" + "<input type='radio' name='selected' value='" + list[i].commNo + "'><td>"
                        + "<td width='50' class='jae'>" + list[i].commNo + "</td>"
                        + "<td width='300'>" + list[i].commTitle + "</td>"
                        + "<td width='100'>" + list[i].commWriter + "</td>"
                        + "<td width='50'>" + list[i].count + "</td>"
                        + "<td width='180'>" + list[i].category + "</td>"
                        + "<td width='120'>" + list[i].createDate + "</td>"
                        + "</tr>";
                }
               $(".list-area>tbody").html(result);
            },
            error: function(){
               alert("게시글 검색 실패!");
            }
         });
      }
      
      $(function(){
         searchWord();
      });

      //게시글 상세보기
      $(function(){
         $(".list-area>tbody>tr").click(function(){
            var nno = $(this).children().eq(0).text();
            location.href="<%= contextPath %>/detail.bo?nbo=" + nbo;
         })
      })

      
      //체크된 라디오 버튼 게시글 삭제
      function deleteBoard() {
         
         $.ajax({
            url: "deleteCheckedBoard.ad",
            data: {commNo: $('input[name="selected"]:checked').val()},
            success: function(result){
               alert("삭제 완료")
               location.href = "<%= contextPath %>/admin.ad";
            },
            error: function(){
               alert("삭제 실패");      
            }
         })
      }
         
         
         
         
         
//         var checkboxArray = [];//체크 값 담을 배열
         
//         $("input[name='selected']:checked").each(function() {
//            checkboxArray.push($(this).val());
//            console.log("checkboxArray: " + checkboxArray);
//         });//체크박스 값 지시
         
//         var c = String(checkboxArray.map((i)=>Number(i)));
         //var c = String(checkboxArray.map((i)=>Number(i)));
         
//         var check = [];
//         check.push(c);
//         console.log("check: " + check);
//         var StringCheck = check.join();
//         console.log("StringCheck: " + StringCheck);
         
//         $.ajax({
//            url: "deleteCheckedBoard.ad",
//            type: 'post',
//            //dataType: "json",
//            //contentType: 'application/json; charset=UTF-8',
//            data: {check: check},
//               //JSON.stringify(check),
//            success: function(result){
//               alert("삭제 완료")

//            },
//            error: function(){
//               alert("삭제 실패");      
//            }
//         })
         

   </script>

   

</body>
</html>