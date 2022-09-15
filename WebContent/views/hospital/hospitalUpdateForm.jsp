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
<title>Petist - 동물병원 정보 수정</title>
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
	
	
    #map {
    	width: 400px;
        height: 240px;
    }
    
	#hospital-update-form {
		width: 800px;
		margin: auto;
		border: 1px solid #433520;
	}

	.common-area {
		width: 700px;
		margin: auto;
	}
    	
	.common-area th {
		padding-left: 30px;
		width: 145px;
	}
    
    .common-area td {
		padding-left: 10px;
	}

	.common-area tr {
		width: 100%;
	}
	
	.title {
		font-size: 24px;
		font-weight: bold;
	}
</style>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=285d497319eb8a5656f1d05e15d0bf96&libraries=services"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=285d497319eb8a5656f1d05e15d0bf96&libraries=services"></script>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

	<div class="content">
		<div class="content_1"><br><div id="hospital-title">동물병원</div></div>
		<div class="content_2">
			<br>
			<div class="common-area title">병원 정보 수정</div>
			<br><br>
			
			<form id="hospital-update-form" enctype="multipart/form-data" action="<%= contextPath %>/hospitalUpdate.ho" method="post">
	            <input type="hidden" name="hospNo" value="<%= hi.getHospNo() %>">
	            <input type="hidden" name="hospId" value="<%= hi.getHospId() %>">
	            <% if(at != null) { %>
		            <input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>">
		            <input type="hidden" name="originFileName" value="<%= at.getOriginName() %>">
				<% } %>
				<table class="common-area">
					<tr><td>&nbsp;</td></tr>
	                <tr>
	                    <th>병원이름</th>
	                    <td><input type="text" id="hospitalName" name="hospitalName" maxlength="20" required value="<%= hi.getHospName() %>" style="width: 400px;"></td>
	                </tr>
	                <tr>
	                    <th>사진</th>
	                    <td>
	                    	<img id="thumbnail" style="width: 400px; height: 300px;" src='<% if(at != null) { %><%= contextPath %>/<%= at.getFilePath() + at.getChangeName() %><% } else { %>data:image/png;base64,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<% } %>'>
	                    	<div id="file-area">
	                    		<input type="file" name="hopitalPhoto" id="hopitalPhoto" onchange="loadImg(this);">
	                    	</div>
	                    	<% if(at != null) { %>
		                        <input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>">
		                        <input type="hidden" name="originFileName" value="<%= at.getChangeName() %>">
		                    <% } %>
                    	</td>
	                </tr>
	                <tr>
	                    <th>주소</th>
	                    <td>
	                        <input type="button" onclick="execDaumPostcode()" value="주소검색" class="btn btn-info btn-sm">
	                        <input type="text" id="address" name="address" readonly required style="width: 300px; border: none;" value='<%= hi.getAddress() %>'>
	                        <input type="hidden" name="y_coordinate" id="y_coordinate" required value="<%= hi.getyCoordinate() %>">
	                        <input type="hidden" name="x_coordinate" id="x_coordinate" required value="<%= hi.getxCoordinate() %>">
	                    </td>
	                </tr>
	                <tr>
	                    <th>상세주소</th>
	                    <td>
	                        <input type="text" id="address_detail" name="address_detail" required style="width: 400px;" value='<%= hi.getAddressDetail() %>'>
	                    </td>
	                </tr>
	                <tr>
	                    <th></th>
	                    <td>
	                        <div id="map"></div>
	                    </td>
	                </tr>
	                <tr>
	                    <th>운영시간</th>
	                    <td><textarea name="operatingTime" rows="7" style="resize: none; width: 400px;"><%= (hi.getOpertingTime() != null) ? hi.getOpertingTime() : "-" %></textarea></td>
	                </tr>
	                <tr>
	                    <th>휴일</th>
	                    <td><input type="text" name="holiday" value='<%= (hi.getHoliday() != null) ? hi.getHoliday() : "-" %>' style="width: 400px;"></td>
	                </tr>
	                <tr>
	                    <th>주차여부</th>
	                    <td><input type="text" name="parking" value='<%= (hi.getParking() != null) ? hi.getParking() : "-" %>' style="width: 400px;"></td>
	                </tr>
	                <tr>
	                    <th>연락처</th>
	                    <td><input type="text" name="tel" placeholder="- 포함해서 입력" required value='<%= hi.getTel() %>' style="width: 400px;"></td>
	                </tr>
	                <tr>
	                    <th>사이트주소</th>
	                    <td><input type="text" name="homepage" value='<%= (hi.getHomepage() != null) ? hi.getHomepage() : "-" %>' style="width: 400px;"></td>
	                </tr>
	                <tr>
	                    <th>병원소개</th>
	                    <td><textarea name="introduce" rows="10" style="resize: none; width: 400px;" required><%= hi.getIntoduce() %></textarea></td>
	                </tr>
	                <tr>
	                    <td align="center" colspan="2">
	                    	<br><br>
	                        <button type="reset" class="btn btn-light btn-sm" onclick="location.href = history.back();" style="margin-right: 40px;">취소하기</button>
	                        <button type="submit" class="btn btn-info btn-sm">등록하기</button>
	                    </td>
	                </tr>
					<tr><td>&nbsp;</td></tr>
	            </table>
	        </form>
	
	        <script>
		        var y_coordinate = <%= hi.getyCoordinate() %>;
		    	var x_coordinate = <%= hi.getxCoordinate() %>;
		    	
				function drawMap(y_coordinate, x_coordinate) {
					
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
			        
					var hospitalName = document.getElementById("hospitalName").value;
					var address = document.getElementById("address").value;
					var address_detail = document.getElementById("address_detail").value;

			     	// 마커 위에 표시할 인포윈도우를 생성한다
					var infowindow = new kakao.maps.InfoWindow({
					    content : '<div id="infowindow" style="padding:5px; width: auto;"><p><b>' + hospitalName + '</b></p><span style="font-size: 14px;">' + address + ', ' + address_detail + '</span></div>' // 인포윈도우에 표시할 내용
					});
		
					// 인포윈도우를 지도에 표시한다
					infowindow.open(map, marker);
					
				}
		        
				
				$(function(){
					drawMap(y_coordinate, x_coordinate);
					var hospitalAddress = $('#infowindow').children().eq(1);
					console.log(hospitalAddress.text());
					$(hospitalAddress).parent().css('width', hospitalAddress.text().length * 15 + 'px');
					$('#hospitalName, #address, #address_detail').change(function(){
						$('#infowindow p').html('<b>' + $('#hospitalName').val() + '</b>');
						$('#infowindow span').text($('#address').val() + ', ' + $('#address_detail').val());
					});
				});
				
				
	            function execDaumPostcode() {
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	                mapOption = {
	                    center: new daum.maps.LatLng(<%= hi.getyCoordinate() %>, <%= hi.getxCoordinate() %>), // 지도의 중심좌표
	                    level: 3 // 지도의 확대 레벨
	                };
		
					//지도를 미리 생성
					var map = new daum.maps.Map(mapContainer, mapOption);
					//주소-좌표 변환 객체를 생성
					var geocoder = new daum.maps.services.Geocoder();
					//마커를 미리 생성
					var marker = new daum.maps.Marker({
						position: new daum.maps.LatLng(<%= hi.getyCoordinate() %>, <%= hi.getxCoordinate() %>),
						map: map
					});

	                new daum.Postcode({
	                    oncomplete: function(data) {
	                        var addr = data.address; // 최종 주소 변수
	
	                        // 주소 정보를 해당 필드에 넣는다.
	                        document.getElementById("address").value = addr;
	                        // 주소로 상세 정보를 검색
	                        geocoder.addressSearch(data.address, function(results, status) {
	                            // 정상적으로 검색이 완료됐으면
	                            if (status === daum.maps.services.Status.OK) {
	
	                                var result = results[0]; //첫번째 결과의 값을 활용
	
	                                document.getElementById("y_coordinate").value = result.y;
	                                document.getElementById("x_coordinate").value = result.x;
									
	                             	// 해당 주소에 대한 좌표를 받아서
	                                var coords = new daum.maps.LatLng(result.y, result.x);
	                                // 지도를 보여준다.
	                                mapContainer.style.display = "block";
	                                map.relayout();
	                                // 지도 중심을 변경한다.
	                                map.setCenter(coords);
	                                // 마커를 결과값으로 받은 위치로 옮긴다.
	                                marker.setPosition(coords);
	                                
	                            }
	                        });
	                    }
	                }).open();
	                
	            }
	            
	            $(function(){
					$('#file-area').hide();

					$('#thumbnail').click(function(){
						$('#hopitalPhoto').click();
					});
				});


				function loadImg(inputFile){
					
					if(inputFile.files.length == 1) {

						var reader = new FileReader();

						reader.readAsDataURL(inputFile.files[0]);

						reader.onload = function(e){
							$('#thumbnail').attr('src', e.target.result);
						};
					}
					else {
						$('#thumbnail').attr('src', "data:image/png;base64,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");
					}
				}
	        </script>
	
	        <br><br>

		</div>
		<div class="content_3"></div>
	</div>

	<br clear="both"><br><br><br>

    <%@ include file="../common/footer.jsp" %>

</body>
</html>