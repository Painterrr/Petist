<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Petist-반려동물 등록</title>
<link href="/Petist/resources/common/logo_mint.png" rel="shortcut icon" type="image/x-icon">
<style>
	.outer{
		width : 800px;
		margin : 0 auto;
		border : 1px solid;
		height : 600px;
		justify-content : center;
		align : center;
		border-radius : 6px;
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
    .outer {
		border : 1px solid;
		height : 600px;
		display : flex;
		justify-content : center;
		align-items : center;
    }
    table thead tr td{
    	font-size : 8px;
    	font-style : italic;
    }
    table tbody tr td{
    	font-size : 17px;
    	padding : 10px;
    	font-weight: bold;
    }
    .values{
		height : 34px;
    	font-size : 16px;
    	background-color : #ededed;
    	border : none;
    	padding : 0 10px;
    	box-sizing : border-box;
    	border-radius : 6px;
	}
	#titleImg{
		width : 100px;
		height : 34px;
		border-radius : 6px;
	}
	.pet_cate{
		
	}
	
    
 
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<div class="content">
	    <div class="content_1">
			<div id="petMain">등록 절차</div>
		</div>
		<div class="content_2" >
			<div class="outer">
				<form id="enroll-form" enctype="multipart/form-data" action="<%= contextPath %>/insert.pe" method="post">
					
					<table id="petenroll">
						<thead>
							<tr>
								<td>* 는 반드시 입력해주세요.</td>
							</tr>
							<tr>
								<td><input type="hidden" name="petOwner" value="<%=loginUser.getMemberId() %>" required></td>		
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>* 반려동물 사진을 올려주세요.</td>
								<td><img id="titleImg" src="https://img.freepik.com/free-vector/touch-icon-hand-symbol-finger-touching-screen_543062-137.jpg?w=740"></td>
								<td></td>
							</tr>
							<tr>
								<td>* 반려동물 종류을 골라주세요.</td>
								<td>
								<select class="values" name="petKind">
									<option value="강아지">강아지</option>
									<option value="고양이">고양이</option>
									<option value="새">새</option>
									<option value="물고기">물고기</option>
									<option value="기타">기타</option>
								</select>
								</td>
							</tr>
						
							<tr>
								<td>* 마이펫 이름 : </td>
								<td><input class="values" type="text" name="petName" maxlength="20" placeholder="펫 이름을 입력해주세요." required></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;등록번호 : </td>
								<td><input class="values" type="text" name="petInputNo" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="20" placeholder="인식표 등록 번호"> </td>
							</tr>
							<tr>
								<td>* 품종 : </td>
								<td><input class="values" type="text" name="petBreed" maxlength="50" placeholder="품종을 입력해주세요" required></td>
							</tr>
							<tr>
								<td>* 생일 : </td>
								<td><input class="values" type="date" name="petBirth"required></td>
							</tr>
							<tr>
								<td>* 중성화 : </td>
								<td><input class="values" type="text" name="petNeutral" maxlength="1" placeholder="Y / N" onkeyup="neutralCheck();" required></td>
							</tr>
							<tr>
							<td>
								<div class="check checkFalse" id="neutralFalse" style="display:none;">Y,N 만 입력해주세요.</div>
							</td>
						</tr>	
						</tbody>
						
					</table>
					<div id="file-area">
							<input type="file" name="file" id="file" required onchange="loadImg(this);"> <!-- 대표 이미지 : 필수 입력 -->
					</div>
					<script>
		
						$(function(){
							$("#file-area").hide();
		
							$("#titleImg").click(function(){
								$("#file").click();
							});
						});
							
						function loadImg(inputFile){
							if(inputFile.files.length == 1){ 
								
								var reader = new FileReader();
		
								
								reader.readAsDataURL(inputFile.files[0]);
								
								reader.onload = function(e){
									$("#titleImg").attr("src", e.target.result);
								}
							}
							else{
								$("#titleImg").attr("src", "https://cnucc.jbnu.ac.kr/korean/system/concert/image/noimage02.gif");
										
							}
						}
						
						function neutralCheck(){
							var $petNeutral = $("#enroll-form input[name=petNeutral]");
							var regExp = /^[NY]$/;
							if(!regExp.test($petNeutral.val())){ 
								$("#neutralFalse").attr('style',"display:block");
							} else { 
								$("#neutralFalse").attr('style',"display:none");
								var ok3 = "Y";
								return ok3;
							}
							if($petNeutral.val() == ""){
								$("#neutralFalse").attr('style',"display:none");
							}
					    }
					</script>
					<br>
		
					<div align="center">
						<button type="submit" class="btn btn-sm btn-info">작성하기</button>
						<button type="reset" class="btn btn-sm btn-danger">되돌리기</button>
						<button onclick="history.back()" class="btn btn-sm btn-warning">뒤로가기</button>
					</div>
				</form>
			</div>
		</div>
		<div class="content_3"></div>
    </div>

	<br clear="both"><br><br>
    <%@ include file="../common/footer.jsp" %>
</body>
</html>