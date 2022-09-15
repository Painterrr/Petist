<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.hospital.model.vo.*, com.kh.pet.model.vo.*, com.kh.common.model.vo.*" %>
<%
	int reviewNo = (int)request.getAttribute("rno");
	int hospitalNo = (int)request.getAttribute("hno");
	int cpage = (int)request.getAttribute("cpage");
	int crpage = (int)request.getAttribute("crpage");
	String hospitalName = (String)request.getAttribute("hname");
	ArrayList<Animal> list = (ArrayList<Animal>)request.getAttribute("list");
	HospitalReview hr = (HospitalReview)request.getAttribute("hr");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist - 리뷰 수정 [<%= hospitalName %>]</title>
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
	
	.update-area {
		width: 800px;
		margin: auto;
	}

	.update-area tr {
		border-bottom: 1px solid black;
		border-collapse: collapse;
	}

	.hospital-info {
		border-top: 1px solid black;
		font-weight: bold;
		text-align: center;
	}

	.update-area th, .update-area td {
		display: block;
		width: 600px;
		margin: auto;
	}

	.pet {
		border : 1px solid #686767;
		border-collapse: collapse;
		width: 600px;
		height: 100px;
		padding-left: 50px;
		color: #686767;
	}
	.pet>div {
		display: inline-block;
	}

	.checked {
		border : 1.5px solid rgb(26, 188, 156);
	}

	.pet img {
		width: 60px;
		height: 60px;
	}

	.petInfo>h4 { margin-top: 20px; }

	.label { 
		display: inline-block;
		width: 100px; 
		font-weight: bold;
		padding-left: 10px;
	}

	#clinicInfo, #price {
		width: 400px;
		margin-bottom: 10px;
	}

	.petKindSelect>img { 
		display: block;
	}

</style>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=285d497319eb8a5656f1d05e15d0bf96&libraries=services"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<div class="content">
		<div class="content_1"><br><div id="hospital-title">동물병원</div></div>
		<div class="content_2">
			<br><br><br><br>
			<form id="review-update-form" action="<%= contextPath %>/reviewUpdate.ho?cpage=<%= cpage %>&crpage=<%= crpage %>" method="post">
				<input type="hidden" name="reviewNo" value="<%= hr.getReviewNo() %>">
				<input type="hidden" name="hospitalNo" value="<%= hospitalNo %>">
				<input type="hidden" name="userId" value="<%= loginUser.getMemberId() %>">
				<table class="update-area" align="center">
					<tr class="hospital-info">
						<td>&nbsp;</td>
						<th colspan="2">
							<h3><%= hospitalName %></h3>
						</th>
						<td>&nbsp;</td>
					</tr>
					<tr class="review-content">
						<td>&nbsp;</td>
						<th>진료받은 아이를 선택해주세요</th>
						<td>&nbsp;</td>
						<td>
							<% if(!list.isEmpty()) { %>
								<% for(Animal a : list) { %>
								<div class="pet <% if(a.getPetNo() == hr.getPetNo()) { %>checked<% } %>">
									<div class="petKindSelect">
										<% if(a.getPetKind().equals("강아지")) { %>
											<img src="/Petist/resources/hospital/dog.png">
										<% } else if(a.getPetKind().equals("고양이")){ %>
											<img src="/Petist/resources/hospital/cat2.png">
										<% } else if(a.getPetKind().equals("새")){ %>
											<img src="/Petist/resources/hospital/bird.png">
										<% } else if(a.getPetKind().equals("물고기")){ %>
											<img src="/Petist/resources/hospital/fish.png">
										<% } else { %>
											<img src="/Petist/resources/hospital/etc.png">
										<% } %>
									</div>
									<div class="petInfo">
										<div style="display: none;" class="petNo"><%= a.getPetNo() %></div>
										<div style="display: none;" class="petKind"><%= a.getPetKind() %></div>
										<div style="display: none;" class="petBreed"><%= a.getPetBreed() %></div>
										<div style="display: none;" class="petAge"><%= a.getPetAge() %></div>
										<h4><%= a.getPetName() %></h4>
										<p><%= a.getPetBreed() %>&nbsp;|&nbsp;<%= a.getPetAge()/12 %>년 <%= a.getPetAge()%12 %>개월</p>
									</div>									
								</div>
								<% } %>
							<% } %>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="review-content">
						<td>&nbsp;</td>
						<th>병원 방문 날짜를 선택해주세요</th>
						<td>&nbsp;</td>
						<td><input type="date" name="clinicDate" id="clinicDate" required></td>
						<td>&nbsp;</td>
					</tr>
					<tr class="review-content">
						<td>&nbsp;</td>
						<th>진료 내역과 가격을 입력해주세요</th>
						<td>&nbsp;</td>
						<td>
							<div class="label">진료 내역</div><input type="text" name="clinicInfo" id="clinicInfo" required value='<%= (hr.getClinicInfo() != null) ? hr.getClinicInfo() : "" %>'><br>
							<div class="label">가격</div><input type="text" name="price" id="price" placeholder="원단위로 숫자만 입력해주세요" required value="<%= hr.getPrice() %>">
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr class="review-content">
						<td>&nbsp;</td>
						<th>리뷰를 작성해주세요</th>
						<td>&nbsp;</td>
						<td><textarea name="content" rows="10" style="resize: none; width: 600px;" required><%= hr.getContent() %></textarea></td>
						<td>&nbsp;</td>
					</tr>
				</table>
		
				<div align="center">
					<br><br>
					<button type="reset" class="btn btn-sm btn-light" style="font-weight: bold; margin-right: 40px;" onclick="history.back();">취소하기</button>
					<button type="submit" class="btn btn-sm btn-info" style="font-weight: bold;" onclick="createInputTag();">등록하기</button>
				</div>
				
				<script>
					document.getElementById('clinicDate').valueAsDate = new Date("<%= hr.getClinicDate() %>".substring(0, 10));
					console.log("<%= hr.getClinicDate() %>");
					console.log("<%= hr.getClinicDate() %>".substring(0, 4));
					console.log("<%= hr.getClinicDate() %>".substring(5, 7));
					console.log("<%= hr.getClinicDate() %>".substring(8, 10));
					$(function(){
						$('.pet').click(function(){
							$('.pet').removeClass('checked');
							$(this).addClass('checked');
						});
					})


					function createInputTag() {
						var input1 = document.createElement('input');
						$(input1).attr({ type: 'hidden', name: 'petNo', value: $('.checked .petNo').text() });

						var input2 = document.createElement('input');
						$(input2).attr({ type: 'hidden', name: 'petKind', value: $('.checked .petKind').text() });
						
						var input3 = document.createElement('input');
						$(input3).attr({ type: 'hidden', name: 'petName', value: $('.checked h4').text() });
						
						var input4 = document.createElement('input');
						$(input4).attr({ type: 'hidden', name: 'petBreed', value: $('.checked .petBreed').text() });
						
						var input5 = document.createElement('input');
						$(input5).attr({ type: 'hidden', name: 'petAge', value: $('.checked .petAge').text() });

						$('.petInfo').append(input1, input2, input3, input4, input5);
						
					}
				</script>
				
			</form>

			<br><br>

		</div>
		<div class="content_3"></div>
	</div>

	<br clear="both"><br><br><br>

    <%@ include file="../common/footer.jsp" %>

</body>
</html>