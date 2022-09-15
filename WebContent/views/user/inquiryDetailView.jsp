<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.admin.model.vo.Inquiry" %>
<%
	Inquiry i = (Inquiry)request.getAttribute("i");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-문의글 상세보기</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>   
   .content_2{
      padding-left:130px;
      padding-top: 90px;
   }
   
   #text-area{
      text-align: center;
      border:1px solid lightgray;
      width:830px;
  		
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
   .type, .answer{
   		border: 1px solid gray;
   		width :80px;
   		text-align : center;
   		border-radius: 5px;
   		margin-bottom: 10px;
   }
   #reply-area th{
   		text-align : left;
   }
   
   #replyBtn, #inquiryBtn{
   		text-align:right;
   		padding-top : 20px;
   }
   #replyContent{
   		width:830px;
   		border : 1px solid lightgrey;
   }
   
   
   #reply_tbody {
   		width:810px;
   }
   #reply_tbody1 {
   		background:white;
   }
  
   #reply-area td{
   		border-bottom:1px solid lightgrey;
   		height:20px;
   		vertical-align: middle;
   		padding-top:20px;
   }
   
   .td1{
   		width:460px;
   }
   .td2{
   		width:100px;
   		text-align:right;
   }
   .td3{
   		width:160px;
   		text-align:center;
   }
   .deleteBtn{
   		width:150px;
   } 
   .green-border-focus .form-control:focus {
    border: 1px solid rgb(26, 188, 156);
    box-shadow: 0 0 0 0.2rem rgb(136, 221, 179, .5);
}
</style>
</head>
<body>
   <%@ include file="../common/menubar.jsp" %>
   
   <div class="content">
      <div class="content_1">
      
      </div>
      
      <div class="content_2">
      
         <h2>1대1문의</h2>
         	<hr>
         	<div class="type"><%= i.getCategory() %></div>
         	
            <table id="text-area" >
               
               <tr>
                  <th width="110">제목</th>
                  <td  colspan="3"><%= i.getInquiryTitle() %></td>
               </tr>
               <tr>
                  <th>작성자</th>
                  <td width="350"><%= i.getUserId() %></td>
				  <th>작성일</th>
				  <td><%= i.getCreateDate() %></td>
               </tr>
               <tr>
               	  <th>문의내용</th>
                  <td colspan="3" height="500px"><%= i.getInquiryContent() %></td>
               </tr>
            </table>
            
            <div id="inquiryBtn">
            
            <% if(loginUser != null && i.getUserId().equals(loginUser.getMemberId())) { %>
                <button type="button" class="btn btn-outline-secondary" onclick="location.href='<%= contextPath %>/inquiry.in?memberId=<%= loginUser.getMemberId() %>&cpage=1';" style="margin-right:550px">뒤로가기</button>
				<a href="<%= contextPath %>/updateForm.in?ino=<%= i.getInquiryNo() %>" class="btn btn-outline-secondary">수정하기</a>
				<a href="<%= contextPath %>/delete.in?ino=<%= i.getInquiryNo() %>&userId=<%= i.getUserId() %>" class="btn btn-outline-secondary">삭제하기</a>
			<% } else if(loginHosp != null && i.getUserId().equals(loginHosp.getHospId())){%>
                <button type="button" class="btn btn-outline-secondary" onclick="location.href='<%= contextPath %>/inquiry.in?memberId=<%= loginHosp.getHospId() %>&cpage=1';"  style="margin-right:550px">뒤로가기</button>
				<a href="<%= contextPath %>/updateForm.in?ino=<%= i.getInquiryNo() %>" class="btn btn-outline-secondary">수정하기</a>
				<a href="<%= contextPath %>/delete.in?ino=<%= i.getInquiryNo() %>&userId=<%= i.getUserId() %>" class="btn btn-outline-secondary">삭제하기</a>
			<% } else { %>
			  	<button type="button" class="btn btn-outline-secondary" onclick="history.back();" >뒤로가기</button>
			<% } %>
            </div>
            
            <hr>
            
            
            <!-- 댓글창!! 일단 화면만 -->
			<div id="reply-area">
				<div class="answer">문의답변</div>

						<!-- 로그인이 O -->
					<% if(loginUser != null && loginUser.getMemberId().equals("admin"))  { %>
					<table id="reply-area1">
						<thead>
							<tr>
							
							<div class="form-group green-border-focus">
  							<textarea class="form-control" id="replyContent" rows="6" style="resize:none;" placeholder="문의 답변을 입력해주세요"></textarea>
							</div>
							</tr>
						</thead>
					</table>
					<div id="replyBtn">
	            		<button type="submit" class="btn btn-outline-secondary" onclick="insertReply();">답변등록</button>
	            	</div>
					<table>
					<tbody id="reply_tbody">
						
					</tbody>
					</table>
					
					<% } else {%>
						<table>
							<thead>
							<tr>
								<td colspan="3">
									<textarea id="replyContent" cols="50" style="resize:none;" disabled>관리자만 이용가능한 서비스입니다.</textarea>
								</td>
							</tr>
							
							</thead>
						
							<tbody id="reply_tbody1">
							
							</tbody>
						</table>
					
					<% } %>
          </div>
         
         <script>
         function selectReplyList(){
				$.ajax({
					url : "rlist.in",
					data : {ino : <%= i.getInquiryNo() %>},
					success : function(list){
						
						var result = "";
						for(var i in list){
							result += "<tr>"
									+ "<input type='hidden' name='replyNo' value='"+list[i].replyNo+"'>" 
								    + "<td class='td1' style='word-break:break-all'>" + list[i].replyContent + "</td>"
								    + "<td class='td2'>" + list[i].replyWriter + "</td>"
								    + "<td class='td3'>" + list[i].createDate + "</td>"
	            					+ "<td><button type='submit' class='btn btn-outline-secondary' onclick='deleteReply();' class='deleteBtn'>답변삭제</button></td>"
								    + "</tr>";
								    
						}
						
						var result2="";
						for(var i in list){
							result2 += "<tr>"
									 + "<input type='hidden' name='replyNo' value='"+list[i].replyNo+"'>" 
								     + "<td class='td1' style='word-break:break-all'>" + list[i].replyContent + "</td>"
								     + "<td class='td2'>" + list[i].replyWriter + "</td>"
								     + "<td class='td3'>" + list[i].createDate + "</td>"
								     + "</tr>";
								    
						}
						$("#reply_tbody").html(result);
						$("#reply_tbody1").html(result2);
						
					},
					error : function(){
						console.log("댓글 읽어오기 실패~~")
					}
				});
			}
         
         $(function(){
        	 selectReplyList();
         });
         
         function insertReply(){

        	 $.ajax({
					url : "rinsert.in",
					data : {
						ino : <%= i.getInquiryNo() %>,
						content : $("#replyContent").val()
					},
					type : "post",
					success : function(result){
						
						if(result>0){

							alert("댓글작성에 성공하셨습니다");
							
							selectReplyList();
							$('#replyContent').val("");
						}
						
						
						
					},
					error : function(){
						console.log("댓글작성 실패!");
					}
					
				});
        	 
         }
     
         function deleteReply(){

        	 $.ajax({
					url : "rdelete.in",
					data : {
						replyNo : $("input[name='replyNo']").val()
					},
					success : function(result){
						
						if(result>0){

							alert("댓글삭제에 성공하셨습니다");
							
							selectReplyList();
						}
						
					},
					error : function(){
						console.log("댓글삭제 실패!");
					}
					
				});
        	 
         }
        	 
      
         </script>
            
      
         
      </div>
      <div class="content_3"></div>

   </div>
   <br clear="both"><br><br><br>
   
   
   <%@ include file="../common/footer.jsp" %>
</body>
</html>