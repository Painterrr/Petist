<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "com.kh.community.model.vo.Community, com.kh.community.model.vo.CoAttachment , com.kh.community.model.vo.Reply, java.util.*" %>    
    			
<%

		Community c = (Community)request.getAttribute("c");
		// 게시글번호, 카테고리명, 제목, 내용, 작성자 아이디 ,작성일
		
		CoAttachment coat = (CoAttachment)request.getAttribute("coat");
		// 파일번호 , 원본명, 수정명, 저장경로
		
		ArrayList<Reply> list = (ArrayList<Reply>)request.getAttribute("list");
		
		
%>       
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-게시글 상세보기</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
    .outer{
       
		color: black;
		width: 1100px;
		height: 1600px;
		margin: auto;
		margin-top: 50px;
    }
    #detail-area{
        border: 2px solid black;
        width : 830px;
        height : 600px;
    }
    #detail-area th{
        text-align: center;
    }
    
    #reply-area2 {
    
    	border: 2px solid black;
    }
    
    #reply-area2>thead>tr>th {
    
    	text-align: center;
    }
    
     #reply-area2>thead>tr>td {
    
    	text-align: center;
    }
    
       #replyContent{
         width:830px;
         border : 1px solid lightgrey;
   }
   
     #replyBtn{
         text-align:center;
         padding-top : 10px;
         margin-left : 750px;
   }
   
    #replyBtn2{
         text-align:center;
         padding-top : 10px;
         margin-left : 750px;
   }
    
    .colist{
      background-color : white;
      padding-left:890px;
      margin-top: 90px;
      margin-bottom : 10px;
    }
    
    #file{
    
    width : 100px;
    height : 25px;
    }
    
    .updel {
    
      margin-left: 670px;
    }
    
    .reply_tbody td {
    
     border-bottom: 1px solid lightgrey;
     align : center;
	 padding-top:10px
     
    }
    .reply{
    	margin-left:135px;
    	width:820px;
    }
    
    .date{
    
    	width : 200px;
    }
	
	.delb {
		text-align : right;
		width : 110px;
	}
	
	#datarea {
	
		width : 1600px;
	
	
	}
	
	.conten{
	
		width : 600px;		
	}
	
	.writer {
	
	 	width : 100px;
	}
    
</style>

</head>
<body>

<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
	
	
	<br>
	<h2 align="center"></h2>
	<br>
	
	<div class= colist>
		<a href = "<%= contextPath %>/list.bo?cpage=1&cno=50" class = "btn btn-sm btn-secondary" >목록가기</a>
	</div>
	<table id="detail-area" align="center" border="1">

		<tr>
			<th width="70" >제목</th>
			<td width="370">&nbsp;<%=c.getCommTitle() %></td>
			<th width="70" height="50">카테고리</th>
			<td width="150">&nbsp;<%=c.getCategory() %></td>
		</tr>
		<tr>
			<th height="40">작성자</th>
			<td width="100">&nbsp;<%=c.getCommWriter() %></td>
			<th>작성일</th>
			<td>&nbsp;<%=c.getCreateDate() %></td>
		</tr>
		<tr>
			<th height="300">내용</th>
			<td colspan="3">
				<p style="height:200px;"> &nbsp;<%=c.getCommContent() %></p>
			</td>
		</tr>
		<tr>
			<th id = "file">첨부파일</th>
			<td colspan="3">&nbsp;
				<!-- 첨부파일이 없을수도있다.-->
				<% if(coat == null) { %>
				첨부파일이 없습니다.
				
				<% } else {%>
				<!-- 첨부파일이 있을수도있따-->
				<a download="<%= coat.getOriginName() %>" href="<%=contextPath %>/<%=coat.getFilePath()%>/<%=coat.getChangeName()%>"><%=coat.getOriginName() %></a>
				<% } %>
			</td>
		</tr>
	</table>
	
	<br>
		<div class="updel" align="center">

		<!-- 로그인한 사용자고 그리고 현재 이 게시글의 작성자일 경우에만 수정하기 삭제하기 보여지게끔 해보자!-->
		<% if( loginUser != null && c.getCommWriter().equals(loginUser.getMemberName()) ) { %>
			<a href ="<%= contextPath %>/updateForm.bo?ccno=<%= c.getCommNo() %>" class ="btn btn-sm btn-warning">수정하기</a>
		<!--	<a href ="<%= contextPath %>/delete.bo" class="btn btn-sm btn-danger">삭제하기</a>-->
			<button data-toggle="modal" data-target="#myModal" class ="btn btn-sm btn-danger">삭제하기</button>

		<% } %>
		<% if( loginHosp != null && c.getCommWriter().equals(loginHosp.getHospId()) ) { %>
			<a href ="<%= contextPath %>/updateForm.bo?ccno=<%= c.getCommNo() %>" class ="btn btn-sm btn-warning">수정하기</a>
		<!--	<a href ="<%= contextPath %>/delete.bo" class="btn btn-sm btn-danger">삭제하기</a>-->
			<button data-toggle="modal" data-target="#myModal" class ="btn btn-sm btn-danger">삭제하기</button>

		<% } %>
	
	</div>
	
	<br><br>
	
	
	<!-- 댓글창 !! -->
	<% if(loginUser != null){ %>
	<input type='hidden' name="userId" value="<%= loginUser.getMemberId() %>">
	<% } else if(loginHosp != null) { %>
	<input type='hidden' name="userId" value="<%= loginHosp.getHospId() %>">
	<% } %>
	

	<table border="1" align ="center" id="reply-area1" width ="700" height="80">
			<% if( loginUser!=null || loginHosp!=null )  {%>
		    <thead>
                     <tr>
                        <td colspan="4"  style="border:none;">
                           <textarea id="replyContent" rows="5"></textarea>
                        </td>
                         
                     </tr>
                  </thead>
               </table>
               <div id="replyBtn">
                     <button type="submit" class="btn btn-outline-secondary" onclick="insertReply();">댓글등록</button>
                    
               </div>
               <table class="reply">
	               <br><br><br>
	              
	               <tbody class="reply_tbody">
	              	
	               </tbody>
               
               </table>
      
               
	

			<% } else { %> 
			
			<!-- 로그인이 x -->
		<table border="1" align ="center" id="reply-area2" width ="700" height="80">
			<thead>
				<tr height = "80">

					
					 <td colspan="4"  style="border:none;">

					로그인 후 이용 가능한 서비스입니다.
					</td>
	
				</tr>
			</thead>
		</table>
		
		<tbody class="reply_tbody">
			


		</tbody>
		
				<% } %>

	


  	<br><br><br>
	
	

	<br><br><br><br>
</div>

<!-- The Modal -->
<div class="modal" id="myModal">
	<div class="modal-dialog">
	  <div class="modal-content">
  
		<!-- Modal Header -->
		<div class="modal-header">
		  <h4 class="modal-title">삭제하시겠습니까?</h4>
		  <button type="button" class="close" data-dismiss="modal">&times;</button>
		</div>

		<!-- Modal footer -->
		<div class="modal-footer">
			<a href ="<%= contextPath %>/delete.bo?ccno=<%=c.getCommNo()%>" class ="btn btn-sm btn-danger">삭제하기 </a>
		  <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">취소</button>
		</div>
  
	  </div>
	</div>
  </div>
  
	
  <br>
  
  <script>




	function selectReplyList() {

		$.ajax({

			url : "rlist.bo",
			data : {cono : <%= c.getCommNo() %>},
			success : function(list) {
			
				// 댓글 갯수만큼 반복 => 누적(문자열)
				var result = ""
				
				for(var i in list) {
						writer = list[i].replyWriter;
					
					result += "<tr id = 'datarea'>"
							+ "<input type='hidden' value='" + list[i].replyNo+ "' name='replyNo'>"
							+ "<td class='writer'>" + list[i].replyWriter + "</td>"
							+ "<td class='conten'>" + list[i].replyContent + "</td>"
							+ "<td class='date'>" + list[i].createDate + "</td>"
							+ "<td class='delb'>" + 
							
								showButton(writer);
							
							+ "</td>"
							+ "</tr>";
				}
				
				$(".reply_tbody").html(result);
				

			},
			error : function() {
				console.log("댓글 읽어오기 실패~");
			}
		});
	}
	
	// 자기꺼만 댓글 삭제
	function showButton(writer){

		<% if(loginUser != null && loginHosp == null) { %> // 개인회원,관리자 & 관리자인 사용자
		// 로그인 유저가 관리자면 전부 댓글삭제 가능
		if(("<%= loginUser.getMemberId() %>") == "admin") {
			return '<button onclick="deleteReply(this);">댓글 삭제</button>';
		} else if( ("<%= loginUser.getMemberId() %>") == writer ) {
			return '<button onclick="deleteReply(this);">댓글 삭제</button>';
		} else {return "";}
		<% } else if(loginHosp != null && loginUser == null) { %> // 병원회원 사용자
		// 로그인 유저가 병원 회원이면 자기 꺼만 댓글 삭제 가능
		 if (("<%= loginHosp.getHospId() %>") == writer) {
			return '<button onclick="deleteReply(this);">댓글 삭제</button>';
		}else {return "";}
		<% } %>
	}

	// 댓글은 화면이 로딩되었을 때 곧바로 뿌려줘야 함 => window.onload => $(function(){})
	
	$(function() {
		
		selectReplyList();
		
	});


	function insertReply() {
		
		$.ajax({
			
			url : "rinsert.bo",
			data : {
				
				cono : <%=c.getCommNo() %>,
				content : $("#replyContent").val(),
				userId: $("input[name='userId']").val()
			},
			type : "post",
			success : function(result) {
				
				// result값에 따라서 성공했으면 성공메세지 띄우기
				
				if(result > 0) {
					
					alert("댓글작성에 성공하셨습니다");
					
					selectReplyList();
					
					$('#replyContent').val("");
				}
				
			},
			error : function() {
				console.log("댓글 작성 실패!")
			}
			
		});
		
	}
		function deleteReply(e) {
			console.log($('input[name="replyNo"]').val());
			$.ajax({
				url : "rdelete.bo",
				data : {
					rcno : $(e).parent().parent().children().eq(0).val()	
				},
				
				success : function(result) {
					
					if(result > 0) {		
						alert("댓글 삭제 성공~");
						selectReplyList();
					}
				},		
				error : function() {
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

</body>
</html>