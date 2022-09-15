<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.hospital.model.vo.*, com.kh.common.model.vo.*" %>
<%
	HospitalInfo hi = (HospitalInfo)request.getAttribute("hi");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist - 동물병원 관리 페이지</title>
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
	
    #map {
    	width: 800px;
        height: 480px;
        margin: auto;
    }
    
	.common-area, .detail-area, .map-area {
		width: 800px;
		margin: auto;
	}
    
    
    .common-area td, .common-area th, .detial-area td, .detail-area th, .map-area td, .map-area th {
		padding-left: 10px;
	}
	
	.common-area tr, .detail-area tr, .map-area tr {
		width: 100%;
		border-top: 1px solid #433520;
        border-bottom: 1px solid #433520;
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

    #detail a {
        border-bottom: 5px solid rgb(26, 188, 156);
    }
    
    #detail:hover a, #review:hover a {
        color : rgb(26, 188, 156);
        font-weight: bold;
        cursor: pointer;
	}
	
	.title {
		font-size: 24px;
		font-weight: bold;
	}
</style>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=285d497319eb8a5656f1d05e15d0bf96&libraries=services"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<div class="content">
		<div class="content_1"><br><div id="hospital-title">동물병원</div></div>
		<div class="content_2">
			<br>
			<div class="common-area title">병원 정보 확인</div>
			<br><br>
			<% if(hi != null) { %>
				<table class="common-area" align="center">
					<tr class="hospital-info">
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
							<input type="hidden" name="hospNo" value="<%= hi.getHospNo() %>">
							<h4><%= hi.getHospName() %></h4>
							<div><%= hi.getAddress() %>, <%= hi.getAddressDetail() %></div>
							<div class="review">
								<div class="review-icon"><img src="/Petist/resources/hospital/review-icon.png"></div>
								<div>리뷰 <%= hi.getReviewCount() %>개</div>
							</div>
						</td>
					</tr>
				</table>
				<table class="detail-area">
					<tr style="border: none; color: white;"><th>&nbsp;</th><td></td></tr>
					<tr style="border: none;">
						<th style="width: 85px;">운영시간</th>
						<td><%= (hi.getOpertingTime() == null) ? " - " : hi.getOpertingTime() %></td>
					</tr>
					<tr style="border: none;">
						<th>휴일</th>
						<td><%= (hi.getHoliday() == null) ? " - " : hi.getHoliday() %></td>
					</tr>
					<tr style="border: none;">
						<th>주차여부</th>
						<td><%= (hi.getParking() == null) ? " - " : hi.getParking() %></td>
					</tr>
					<tr style="border: none;">
						<th>연락처</th>
						<td><%= hi.getTel() %></td>
					</tr>
					<tr style="border: none;">
						<th>웹사이트</th>
						<td><a href="<%= (hi.getHomepage() == null) ? " - " : hi.getHomepage() %>" target="_blank"><%= (hi.getHomepage() == null) ? " - " : hi.getHomepage() %></a></td>
					</tr>
					<tr style="border: none;">
						<th>병원소개</th>
						<td><%= hi.getIntoduce() %></td>
					</tr>
					<tr style="border: none; color: white;"><th>&nbsp;</th><td></td></tr>
				</table>
				<table class="map-area">
					<tr>
						<td style="padding: 0;">
							<div id="map"></div>
						</td>
					</tr>
		
				</table>
				<script>
			    	
			    	var y_coordinate = <%= hi.getyCoordinate() %>;
			    	var x_coordinate = <%= hi.getxCoordinate() %>;
			    
			        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			            mapOption = {
			                center: new kakao.maps.LatLng(y_coordinate, x_coordinate), // 지도의 중심좌표
			                level: 3 // 지도의 확대 레벨
			            };  
			        
			        // 지도를 생성합니다    
			        var map = new kakao.maps.Map(mapContainer, mapOption); 
			        
			        // 마커가 표시될 위치입니다 
			        var markerPosition  = new kakao.maps.LatLng(y_coordinate, x_coordinate); 
			
			        // 마커를 생성합니다
			        var marker = new kakao.maps.Marker({
			            position: markerPosition
			        });
			        
				    // 마커가 지도 위에 표시되도록 설정합니다
			        marker.setMap(map);
			        
			     	// 마커 위에 표시할 인포윈도우를 생성한다
					var infowindow = new kakao.maps.InfoWindow({
					    content : '<div id="infowindow" style="padding:5px;"><p><b><%= hi.getHospName() %></b></p><span style="font-size: 14px;"><%= hi.getAddress() %>, <%= hi.getAddressDetail() %></span></div>' // 인포윈도우에 표시할 내용
					});
	
					// 인포윈도우를 지도에 표시한다
					infowindow.open(map, marker);
					
					$(function(){
						var hospitalAddress = $('#infowindow').children().eq(1);
						$(hospitalAddress).parent().css('width', hospitalAddress.text().length * 15 + 'px');
					})
					
				</script>
		
				<div align="center">
					<br><br>
					<a href="<%= contextPath %>/hospitalUpdateForm.ho?hospId=<%= hi.getHospId() %>" class="btn btn-sm btn-info" style="margin-right: 45px;"> 수정하기 </a>
					<a href="<%= contextPath %>/hospitalDelete.ho?hno=<%= hi.getHospNo() %>&hospId=<%= hi.getHospId() %>" class="btn btn-sm btn-info"> 삭제하기 </a>
				</div>
				
				<br><br>
			
			<% } else { %>
		
				<table class="common-area" align="center">
					<tr>
						<td>
							등록된 병원 정보가 없습니다.
							병원을 등록해주세요.
						</td>
					</tr>
				</table>
				<div align="center">
					<br><br>
					<a href="<%= contextPath %>/views/hospital/hospitalEnrollForm.jsp" class="btn btn-sm btn-info"> 등록하기 </a>
				</div>
				
			<% } %>
			
		</div>
		<div class="content_3"></div>
	</div>

	<br clear="both"><br><br><br>

    <%@ include file="../common/footer.jsp" %>

</body>
</html>