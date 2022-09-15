<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.hospital.model.vo.HospitalInfo, com.kh.common.model.vo.PageInfo" %>
<%
	ArrayList<HospitalInfo> list = (ArrayList<HospitalInfo>)request.getAttribute("list");
	
	String area = (String)request.getAttribute("area");
	String keyword = (String)request.getAttribute("keyword");

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
<title>Petist - 동물병원 목록</title>
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

	.hospital-list tr {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
		width: 390px;
		display: inline-block;
		margin: 40px;
		margin-top: 30px;
		margin-bottom: 10px;
		padding-left: 5px;
		padding-right: 5px;
	}

	.hospital-list tr:hover {
		cursor: pointer;
	}
	
	.hospital-list td {
		width: 380px;
		display:block;
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
		width: 380px;
		height: 290px;
	}
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<div class="content">
		<div class="content_1"><br><div id="hospital-title">동물병원</div></div>
		<div class="content_2">
			<br><br><br><br>
			<div class="hospital-list">
				<table align="center" class="list-area">
					<tr style="display: block; border: none; width: 800px; margin-right: 0;" align="center">
						<td style="width: 800px;">
							<form action="<%= contextPath %>/search.ho" style="width: 800px;">
								<select name="area" id="area" style="margin-right: 10px; text-align: center;">
									<option value="전체">전체</option>
									<option value="서울">서울</option>
									<option value="경기">경기</option>
									<option value="인천">인천</option>
									<option value="강원">강원</option>
									<option value="세종">세종</option>
									<option value="대전">대전</option>
									<option value="충남">충남</option>
									<option value="충북">충북</option>
									<option value="광주">광주</option>
									<option value="전남">전남</option>
									<option value="전북">전북</option>
									<option value="부산">부산</option>
									<option value="대구">대구</option>
									<option value="울산">울산</option>
									<option value="경남">경남</option>
									<option value="경북">경북</option>
								</select>
								<input type="text" name="keyword" id="keyword" placeholder="병원명을 검색하세요" style="width: 300px;">
								<input type="hidden" name="cpage" value="<%= currentPage %>">
								<button type="submit" class="btn btn-sm btn-info" style="margin-bottom: 3px; margin-right: 10px;">검색</button>
								<button type="button" class="btn btn-sm btn-outline-info" style="margin-bottom: 3px;" onclick='location.href="<%= contextPath %>/list.ho?cpage=1";'>전체보기</button>
								<br><br>
							</form>
						</td>
					</tr>
					<% if(list.isEmpty()) { %>
						<tr style="width: 800px;">
							<td>조회된 병원이 없습니다.</td>
						</tr>
					<% } else { %>
						<% for(HospitalInfo hi : list) { %>
							<tr class="hospital-info">
								<% if(hi.getTitleImg() != null){ %>
									<td>
										<img class="hospital-photo" src="<%= contextPath %>/<%= hi.getTitleImg() %>">
									</td>
								<% } else { %>
									<td>
										<img class="hospital-photo" src="data:image/png;base64,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">
									</td>									
								<% } %>
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
						<% } %>
					<% } %>
				</table>
			</div>
			
			<script>
			$(function(){
				$('#area option[value=<%= area %>]').prop('selected', true);
				
				<% if(keyword!=null && !keyword.equals("")) { %>
					$('#keyword').val("<%= keyword %>");
				<% } %>

				$(".hospital-info").each(function(){
					var hospitalName = $(this).children().eq(1).children().eq(1);
					var hospitalAddress = $(this).children().eq(1).children().eq(2);
					if(hospitalName.text().length > 17) {
						hospitalName.text(hospitalName.text().substring(0, 17) + "...");
					};
					if(hospitalAddress.text().length > 29) {
						hospitalAddress.text(hospitalAddress.text().substring(0, 29) + "...");
					};
				});
				
				$(".hospital-info").click(function(){

					var hno = $(this).children().eq(1).children().eq(0).val();
					
                	location.href="<%= contextPath %>/detail.ho?hno=" + hno + "&cpage=" + <%= currentPage %>;
				});
				
				$('#area').on('change', function(){
					console.log($('#area option:selected').val());
					location.href = "<%= contextPath %>/list.ho?cpage=<%= currentPage %>&area=" + $('#area option:selected').val();
				});
			})
		</script>
			
			<br><br>

			<div align="center" class="paging-area">
				<% if(currentPage != 1) { %>
					<button class="btn btn-sm btn-light" onclick="location.href='<%= contextPath %>/list.ho?cpage=<%= currentPage - 1 %>'">&lt;</button>
				<% } %>
				
				<% for(int i = startPage; i <= endPage; i++) { %>
					<% if(i != currentPage) { %>
						<button class="btn btn-sm btn-light" onclick="location.href='<%= contextPath %>/list.ho?cpage=<%= i %>'"><%= i %></button>
					<% } else { %>
						<button class="btn btn-sm btn-info" disabled><%= i %></button>
					<% } %>
				<% } %>
				
				<% if(currentPage != maxPage) { %>
					<button class="btn btn-sm btn-light" onclick="location.href='<%= contextPath %>/list.ho?cpage=<%= currentPage + 1 %>'">&gt;</button>
				<% } %>
			</div>
		</div>
		<div class="content_3"></div>
	</div>

	<br clear="both"><br><br><br>

    <%@ include file="../common/footer.jsp" %>

</body>
</html>