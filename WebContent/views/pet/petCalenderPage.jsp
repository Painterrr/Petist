<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.kh.pet.model.vo.*" %>
<%
	Animal a = (Animal)session.getAttribute("a");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-캘린더</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<link href='/Petist/resources/pet/fullcalendar/main.css' rel='stylesheet' />
<script src='/Petist/resources/pet/fullcalendar/main.js'></script>

<style>
    .calendar-wrapper{
    	width : 800px;
    	margin : 0 auto;
    	padding : 24px 0;
    	
    }
    #petMain {
      font-size: 27px;
      font-weight:bolder;
      border-bottom: 1px solid black;
      width: 170px;
      text-align: center;
      color: rgb(26, 188, 156);
      margin-top: 25px;
    }

</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<script>
		document.addEventListener('DOMContentLoaded', function() {
	    var calendarEl = document.getElementById('calendar');

	    var calendar = new FullCalendar.Calendar(calendarEl, {
		    initialView: 'dayGridMonth',
		    navlinks: true,
		    editable: true,
		    selectable: true, 
	      	dateClick: function(info) {
	     	// alert('clicked ' + info.dateStr);
	     	
	     		alert('일기로 이동합니다.');
	     	
	     		location.href = "<%= contextPath %>/detail.pe?selectDate=" + info.dateStr + "&pno=" + <%= a.getPetNo() %>;
	      	}
	    });
	    calendar.render();
	  });
	
	</script>
<div class="content">
	<div class="content_1">
		<div id="petMain"><%=a.getPetName() %><br> 다이어리</div>
	</div>
		<div class="content_2" >
			<div class="calendar-wrapper">
				<div id='calendar'></div>
				<div align="center">
					<a href="<%= contextPath %>/list.pe" class="btn btn-sm btn-info">마이펫 리스트</a>
					<a href="<%= contextPath %>/diarylist.pe?cpage=1&pno=<%= a.getPetNo() %>" class="btn btn-sm btn-warning">다이어리 리스트 모아보기</a>
				</div>
			</div>
		</div>
		<div class="content_3"></div>
	</div>
    <br clear="both"><br><br>
    <%@ include file="../common/footer.jsp" %>
</body>
</html>