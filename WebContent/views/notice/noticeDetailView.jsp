<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.notice.model.vo.Notice, com.kh.common.model.vo.PageInfo, com.kh.notice.model.vo.*" %>
<%@ page import="java.util.ArrayList"%>
<%
   Notice n = (Notice)request.getAttribute("n");
   int cpage = (Integer)request.getAttribute("cpage");
   ArrayList<NoticeComment> list = (ArrayList<NoticeComment>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-공지사항 상세보기</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
    .outer{
      background-color: white;
      color: black;
      width: 1000px;
      margin: auto;
      margin-top: 30px;
   }

    table.detail-area{
      border: 1px solid white;
      width: 100%;

    }
    table.detail-area tr{
      border-bottom: 1px solid lightgray;
    }
    table.detail-area th{
      width: 130px;
   }
    table th{
       text-align: center;
    }
    table td{
       vertical-align: middle;
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

   li>a{
      color: black;
   }
   li{
      list-style-type: none;
      font-size: 18px;
   }
   #coReply{
      padding-right: 65px;
   }
   
   table.comment-area{
      margin-left:100px; 
      width: 700px;
   }
   #comment-content{
      border-bottom: 1px solid gray;
      width: 700px;
      height: 70px;
      mar
   }
   .cmtBtn{
      background-color: rgb(26, 188, 156);
      width: 90px;
      border-radius: 10px;
      color: white;
      border: none;
      display: inline-block;
   }
   #cmtEnrollBtn{
      float: right;
      margin-right: 60px;
   }
   .content2{
      overflow:hidden;
      position: relative;
   }
</style>

</head>
<body>

   <%@ include file="../common/menubar.jsp" %>
   
   <div class="outer">
      <br>
      <h2 style="margin-left: 390px;">공지사항 상세보기</h2>
      <br><br>
      
      <div class="content" border="1px solid black;">
         <div class="content_1"> 
          </div>

         <div class="content_2" width="700px">
            <table align="center" class="detail-area">
               <tr height="60px">
                  <th align="center">제목</th>
                  <td colspan="3"><%= n.getNoticeTitle() %></td>
               </tr>
               <tr height="60px">
                  <th>작성자</th>
                  <td><%= n.getNoticeWriter() %></td>
                  <th>작성일</th>
                  <td><%= n.getCreateDate() %></td>
               </tr>
               <tr>
                  <th></th>
                  <td colspan="3" height="500px"><pre  style="white-space: pre-wrap;"><%= n.getNoticeContent() %></pre></td>
               </tr>
            </table>
            <br><br>

            <div align="center">
               <!-- <button type="button" onclick="history.back();" class="btn btn-sm btn-secondary">뒤로가기</button>  -->
               <!--history.back() >> 이전 페이지로 이동-->
               <!-- 아래 조건을 어떻게 걸지 확인:  -->
               <!-- a링크의 링크값 다시 설정: 현재 페이지로 가는 게 아닌 cpage=1로 해보기 -->
               <!-- 페이징 바의 넘버를 어떻게 확인할 지? -->
               <a href="<%= contextPath %>/list.no?cpage=<%=cpage %>" class="btn btn-secondary btn-sm">목록가기</a> 
               
               <!-- 로그인된 상태  && 로그인된 사용자 == 작성자 -->
               <% if(loginUser != null && loginUser.getMemberId().equals(n.getNoticeWriter()) ) {%>
                  <a href="<%= contextPath %>/updateForm.no?nno=<%= n.getNoticeNo() %>" class="btn btn-secondary btn-sm" style="background-color: rgb(26, 188, 156); color:white; height: 31px; border:none;">수정하기</a> <!-- /updateForm.no -->
                  <a href="<%= contextPath %>/delete.no?nno=<%= n.getNoticeNo() %>" class="btn btn-secondary btn-sm">삭제하기</a> <!-- /deleteForm.no -->
               <% } %>         
            </div>
            <br><br>
            <!-- 댓글 div -->
            <div id="noticeComment">         
               <% if(loginUser != null){ %>
               <input type='hidden' name="userId" value="<%= loginUser.getMemberId() %>">
               <% } else if(loginHosp != null) { %>
               <input type='hidden' name="userId" value="<%= loginHosp.getHospId() %>">
               <% } %>
               <table class="comment-area">
                  <thead align="center">
                     <tr>
                        <th width="700px" height="90px" colspan="3">
                           <textarea name="comment-area" id="comment-content" rows="5"></textarea>
                        </th>
                        <button type="submit" id="cmtEnrollBtn" class="cmtBtn" onclick="commentEnroll();">댓글 등록</button>
                     </tr>
                  </thead>
                  <tbody id="commentList">

                  </tbody>
               </table>
            </div>

         </div>
         <div class="content_3">
            
         </div>
         
         <!-- 댓글 리스트 조회
         댓글 등록
         댓글 삭제 -->
         
         <script>
            //댓글 조회
            function commentSelect() {
               
               console.log("nno: " + <%= n.getNoticeNo() %>)
               $.ajax({
                  url: "<%= contextPath %>/commentSelect.no",
                  data: {
                     nno : <%= n.getNoticeNo() %>
                  },
                  success: function(list){
                     var result = "";
                     var writer = [];
                     for(var i in list){
                           writer = list[i].cmtWriter;
                        //작성자 or 관리자만 삭제버튼 보임
                        
                              result += "<tr>"
                                    + "<input type='hidden' name='cmtNo' value='" + list[i].cmtNo + "'>" 
                                    + "<td>" + list[i].cmtWriter + "</td>"
                                    + "<td width='450px' style='word-break:break-all'>" + list[i].cmtContent + "</td>"
                                    + "<td width='120px'>" + list[i].createDate + "</td>"
                                    + "<td>" + 
                                    
                                          showButton(writer);
                                    
                                    + "</td>"
                                    + "</tr>";
                           
                     }
                     $("#commentList").html(result);
                     
                  },
                  error: function(){
                     alert("댓글조회 실패")      
                  }
               });
            }
            
            function showButton(writer){
               // 로그인 유저가 관리자면 전부 댓글삭제 가능
               <% if(loginUser != null && loginHosp == null) { %>
               if(("<%= loginUser.getMemberId() %>") == "admin") {
                  return '<button type="submit" class="cmtBtn" onclick="commentDelete(this);">댓글 삭제</button>';
               } else if( ("<%= loginUser.getMemberId() %>") == writer ) {
                  return '<button type="submit" class="cmtBtn" onclick="commentDelete(this);">댓글 삭제</button>';
               } else {return "";}
               <% } else if(loginHosp != null && loginUser == null) { %>
               // 로그인 유저가 개인or병원 회원이면 자기 꺼만 댓글 삭제 가능
                if (("<%= loginHosp.getHospId() %>") == writer) {
                  return '<button type="submit" class="cmtBtn" onclick="commentDelete(this);">댓글 삭제</button>';
               }else {return "";}
               <% } %>
            }
         
            $(function(){
               commentSelect();
            });
            
            //댓글 등록
            function commentEnroll() {

                  $.ajax({
                     url: "<%= contextPath %>/commentInsert.no",
                     data: {
                        cmtNo: <%= n.getNoticeNo() %>,
                        content: $("#comment-content").val(),
                        userId: $("input[name='userId']").val()
                     },
                     type: "post",
                     success: function(result){
                        if(result > 0) {
                           commentSelect();
                           
                           $("#comment-content").val("");
                        }
                     },
                     error: function(){
                        alert("댓글 작성 실패");
                     }
                  })
            }
            
            //댓글 삭제
            function commentDelete(e) {
               console.log($("input[name='cmtNo']").val());
               console.log("sss" + $(e).parent().parent().children().eq(0).val());
               
               $.ajax({
                  url: "commentDelete.no",
                  data: {
                     cmtNo: $(e).parent().parent().children().eq(0).val()
                  },
                  success: function(result){
                     if(result > 0) {
                        alert("삭제 성공");
                        commentSelect();
                     }
                  },
                  error: function(){
                     alert("댓글 삭제 실패");
                  }
               });
            }
         
            // 스위트알러창
             function alert(a){
                Swal.fire({
                    title: 'Petist',
                    text: a,
                    imageUrl: '/Petist/resources/common/logo_mint.png',
                    imageWidth: 130,
                    imageHeight: 110,
                    confirmButtonText: '확인',
                    confirmButtonColor: '#1abc9c'
                  });
             }
            
         </script>
         
      </div>
      </div>
   <br clear="both"><br>
      
      <%@ include file="../common/footer.jsp" %>
      

</body>
</html>