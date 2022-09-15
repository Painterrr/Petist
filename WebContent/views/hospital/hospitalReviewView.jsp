<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.hospital.model.vo.*, com.kh.common.model.vo.*" %>
<%
	ArrayList<HospitalReview> list = (ArrayList<HospitalReview>)request.getAttribute("list");
	HospitalInfo hi = (HospitalInfo) request.getAttribute("hi");
	Attachment at = (Attachment)request.getAttribute("at");
	int cpage = (int)request.getAttribute("cpage");
	
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
<title>Petist - 동물병원 리뷰 [<%= hi.getHospName() %>] (<%= currentPage %>페이지)</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
	
	#hospital-title {
		font-size: 24px;
		font-weight: bold;
		border-bottom: 1px solid black;
		width: 100px;
		text-align: center;
		color: rgb(26, 188, 156);
		font-weight: bold;
	}

	.hospital-info {
		margin: auto;	
	}
	
	.hospital-info>td {
		display: block;
	}
	
	.review>div {
		display: inline-block;
	}

	.review-icon {
		width: 24px;
		height: 24px;
	}
	
	.review-icon>img {
		width: 90%;
		height: 90%;
		margin-bottom: 2.4px;
	}
	
	.hospital-photo {
		width: 600px;
		height: 450px;
		display: block;
		margin: auto;
	}
	
    .common-area, .review-area {
		width: 800px;
		margin: auto;
	}
	
    .common-area td, .common-area th, .review-area td, .review-area th {
		padding-left: 10px;
	}
		
	.common-area tr, .review-area tr {
		width: 100%;
        border-bottom: 1px solid black;
        border-collapse: collapse;
	}

    #detail, #review {
        float: left;
        padding-left: 10px;
        padding-right: 10px;
	}
	
    #detail a, #review a {
        text-decoration: none;
        color: black;
	}

    #review a {
        border-bottom: 5px solid rgb(26, 188, 156);
    }
    
    #detail:hover a, #review:hover a {
        color : rgb(26, 188, 156);
        font-weight: bold;
        cursor: pointer;
	}
	
	.review-area * { display: block; }
	
	
</style>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=285d497319eb8a5656f1d05e15d0bf96&libraries=services"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<div class="content">
		<div class="content_1"><br><div id="hospital-title">동물병원</div></div>
		<div class="content_2">
			<br><br><br><br>
			<table class="common-area" align="center">
				<tr class="hospital-info" style="border-top: 1px solid black;">
					<% if(at != null){ %>
						<td>
							<img class="hospital-photo" src="<%= contextPath %>/<%= at.getFilePath() + at.getChangeName() %>">
						</td>
					<% } else { %>
						<td>
							<img class="hospital-photo" src="data:image/png;base64,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">
						</td>									
					<% } %>
				</tr>
				<tr class="hospital-info">
					<td>
						<input type="hidden" value="<%= hi.getHospNo() %>">
						<h4><%= hi.getHospName() %></h4>
						<div><%= hi.getAddress() %>, <%= hi.getAddressDetail() %></div>
						<div class="review">
							<div class="review-icon"><img src="/Petist/resources/hospital/review-icon.png"></div>
							<div>리뷰 <%= hi.getReviewCount() %>개</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding: 0;">
						<div id="detail">
							<a href="<%= contextPath %>/detail.ho?hno=<%= hi.getHospNo() %>&cpage=<%= cpage %>">상세정보</a>
						</div>
						<div id="review">
							<a href="#">리뷰(<%= hi.getReviewCount() %>)</a>
						</div>
					</td>
				</tr>
			</table>
			<table class="review-area">
				<% if(loginUser != null) { %>
					<tr>
						<td style="color: rgb(158, 158, 158);">&nbsp;</td>
						<td style="color: rgb(158, 158, 158);"><button type="button" class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/reviewEnrollFrom.ho?userId=<%= loginUser.getMemberId() %>&hno=<%= hi.getHospNo() %>&hname=<%= hi.getHospName() %>&cpage=<%= cpage %>'">리뷰 작성하기</button></td>
						<td style="color: rgb(158, 158, 158);">&nbsp;</td>
					</tr>
				<% } %>
				<% if(!list.isEmpty()) { %>
					<% for(HospitalReview hr : list) { %>
						<tr>
							<td style="color: rgb(158, 158, 158);">&nbsp;</td>
							<td style="color: rgb(158, 158, 158);">
								<%= hr.getUserId() %>&nbsp;(<%= hr.getCreateDate() %>) 
								<% if(loginUser != null) { %>
									<% if(hr.getUserId().equals(loginUser.getMemberId())) { %>
										<button type="button" class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/reviewUpdateForm.ho?rno=<%= hr.getReviewNo() %>&userId=<%= loginUser.getMemberId() %>&hno=<%= hi.getHospNo() %>&hname=<%= hi.getHospName() %>&cpage=<%= cpage %>&crpage=<%= pi.getCurrentPage() %>'">수정하기</button>
										<button type="button" class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/reviewDelete.ho?rno=<%= hr.getReviewNo() %>&hno=<%= hi.getHospNo() %>&cpage=<%= cpage %>'">삭제하기</button>
									<% } %>
								<% } %>
							</td>							
							<td style="color: rgb(158, 158, 158);">&nbsp;</td>
							<td style="color: rgb(158, 158, 158);"><%= (hr.getPetBreed() == null) ? "반려동물 정보 미선택" : hr.getPetBreed() %>&nbsp;/&nbsp;<%= (hr.getPetAge() == 0) ? "-" : hr.getPetAge()/12 + "년 " + hr.getPetAge()%12 + "개월" %>&nbsp;</td>							
							<td style="color: rgb(158, 158, 158);">&nbsp;</td>
							<td style="color: rgb(158, 158, 158);">방문 날짜 : <%= (hr.getClinicDate() == null) ? "정보 없음" : hr.getClinicDate().substring(0, 10) %></td>							
							<td style="color: rgb(158, 158, 158);">받은 진료 : <%= (hr.getClinicInfo() == null) ? "정보 없음" : hr.getClinicInfo() %></td>							
							<td style="color: rgb(158, 158, 158);">가격 : <%= (hr.getPrice() == 0) ? "정보 없음" : hr.getPrice() %></td>							
							<td style="color: rgb(158, 158, 158);">&nbsp;</td>
							<td><%= hr.getContent() %></td>
							<td style="color: rgb(158, 158, 158);">&nbsp;</td>
						</tr>							
					<% } %>
				<% } else { %>
					<tr>
						<td style="color: rgb(158, 158, 158);">&nbsp;</td>
						<td style="color: rgb(158, 158, 158);">
							리뷰가 존재하지 않습니다.
						</td>
						<td style="color: rgb(158, 158, 158);">&nbsp;</td>
					</tr>
				<% } %>
			</table>
			
			<div align="center" class="paging-area">
				<br>
				<% if(currentPage != 1) { %>
					<button class="btn btn-sm btn-light" onclick="location.href='<%= contextPath %>/review.ho?hno=<%= hi.getHospNo() %>&cpage=<%= cpage %>&crpage=<%= currentPage - 1 %>'">&lt;</button>
				<% } %>
				
				<% for(int i = startPage; i <= endPage; i++) { %>
					<% if(i != currentPage) { %>
						<button class="btn btn-sm btn-light" onclick="location.href='<%= contextPath %>/review.ho?hno=<%= hi.getHospNo() %>&cpage=<%= cpage %>&crpage=<%= i %>'"><%= i %></button>
					<% } else { %>
						<button class="btn btn-sm btn-info" disabled><%= i %></button>
					<% } %>
				<% } %>
				
				<% if(currentPage != maxPage) { %>
					<button class="btn btn-sm btn-light" onclick="location.href='<%= contextPath %>/review.ho?hno=<%= hi.getHospNo() %>&cpage=<%= cpage %>&crpage=<%= currentPage + 1 %>'">&gt;</button>
				<% } %>
			</div>
	
			<div align="center">
				<br><br>
				<a href="<%= contextPath %>/list.ho?cpage=<%= cpage %>" class="btn btn-sm btn-info"> 목록으로 돌아가기 </a>
			</div>
			
			<br><br>

		</div>
		<div class="content_3"></div>
	</div>

	<br clear="both"><br><br><br>

    <%@ include file="../common/footer.jsp" %>

</body>
</html>