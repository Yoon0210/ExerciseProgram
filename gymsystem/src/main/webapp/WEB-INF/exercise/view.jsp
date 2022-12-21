<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "model.Exercise" %>
<!DOCTYPE html>
<html>
<head>
<!-- 글씨체 적용 -->
<link rel="stylesheet" href="/gymsystem/css/custom.css">

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta charset="EUC-KR">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>운동 상세페이지</title>
<script>
function exerciseReservation() {
	return confirm("예약하시겠습니까? ");
}
</script>
</head>
<body>
	<nav class="gymsystembar">
		<div class="gymsystem_name">
			<h1><a href="<c:url value='/user/main'></c:url>">gym system</a></h1>
		</div>

		<ul class="gymsystem_menu">
			<!-- 회원용 상단바 -->
			<% if(session.getAttribute("divTrainer") == null){ %>
				<li><a href="<c:url value='/user/mypage'></c:url>">마이페이지</a></li>
				<li><a href="<c:url value='/user/myreservation'></c:url>">예약 확인</a></li>
				<li>커뮤니티</li>
			<%} else{ %>  <!-- 트레이너용 상단바 -->
				<li><a href="<c:url value='/trainer/addreservation/form'></c:url>">운동 추가</a></li>
				<li><a href="<c:url value='/trainer/checkreservation'></c:url>">운동 조회</a></li>
			<%} %>
		</ul>
		
		<ul class="gymsystem_login">
			<% if(session.getAttribute("userId") == null){ %>
				<li><a href="<c:url value='/user/login/form'></c:url>">로그인</a></li>
			<%} else{ %>
				<li><a href="<c:url value='/user/logout'></c:url>">로그아웃(${curUserId} 님)</a></li>
			<%} %>
			<li><a href="<c:url value='/user/register/form'></c:url>">회원가입</a></li>
		</ul>
	</nav>
	<hr>
	
	<!-- 운동 정보 -->
	<h1 align="center">운동 상세정보</h1>
	<table align="center" width="500" height="40" style="border:1px solid" style="border-collapse:collapse" >
		<tr>
			<th> <font size="2px">운동명</font></th>
			<th> <font size="2px">강도</font></th>
			<th> <font size="2px">시작 날짜</font></th>
			<th> <font size="2px">종료 날짜</font></th>
			<th> <font size="2px">카테고리</font></th>
		</tr> 
		<tr>
			<td align ="center"><font size="2px"> ${viewExercise.name}</font></td>
			<td align ="center"><font size="2px"> ${viewExercise.price}</font></td>
			<td align ="center"><font size="2px"> ${viewExercise.strength}</font></td>
			<td align ="center"><font size="2px"> ${viewExercise.startTime}</font></td>
			<td align ="center"><font size="2px"> ${viewExercise.endTime}</font></td>
			<td align ="center"><font size="2px"> ${viewExercise.category}</font></td>
		</tr>
	</table>
	
	<!-- 세부 정보 -->
	<br><br><br><br>
	<h1 align="center">세부 일정표</h1>
	<table align="center" width="500" height="40" style="border:1px solid" style="border-collapse:collapse" >
		<tr>
			<th> <font size="2px">일정번호</font></th>
			<th> <font size="2px">일정명</font></th>
			<th> <font size="2px">시간</font></th>
			<th> <font size="2px">상세설명</font></th>
			<th> <font size="2px">운동번호</font></th>
		</tr> 
		
		<c:forEach var="sche" items="${scheList}">
		<tr>
			<td align ="center"><font size="2px"> ${sche.scheId}</font></td>
			<td align ="center"><font size="2px"> ${sche.name}</font></td>
			<td align ="center"><font size="2px"> ${sche.time}</font></td>
			<td align ="center"><font size="2px"> ${sche.description}</font></td>
			<td align ="center"><font size="2px"> ${sche.exerciseId}</font></td>
		</tr>
		</c:forEach>
	</table>
	
	<br><br><br>
	<p align="center">
	<a href="<c:url value='/exercise/reservation'>
	<c:param name='exerciseReservation' value='${viewExercise.exerciseId}'/></c:url>"
	 onclick="exerciseReservation();"><font size="2">예약하기</font></a> &nbsp;
	 </p>
	<%@include file="/WEB-INF/footer.jsp"%>
</body>
</html>