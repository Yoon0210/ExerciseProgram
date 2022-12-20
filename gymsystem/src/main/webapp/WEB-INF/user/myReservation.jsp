<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="model.Reservation" %>
<%@page import="java.util.*" %>
<%
	String curUserId = (String)request.getAttribute("curUserId");
%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>운동 예약 확인</title>

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- 글씨체 적용 -->
<link rel="stylesheet" href="/css/custom.css">

<script>
function userCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
	<!--<nav class="modbar">
		<div class="mod_name">
			<h1><a href="<c:url value='/user/main'></c:url>">Mod's Tour</a></h1>
		</div>

		<ul class="mod_menu"> -->
			<!-- 회원용 상단바 -->
			<!-- <% if(session.getAttribute("divGuide") == null){ %>
				<li><a href="<c:url value='/user/safeGrade'></c:url>">안전등급</a></li>
				<li><a href="<c:url value='/user/mypage'></c:url>">마이페이지</a></li>
				<li><a href="<c:url value='/user/myreservation'></c:url>">예약확인</a></li>
				<li>커뮤니티</li> -->
			<%} else{ %>  <!-- 가이드용 상단바 -->
				<!--<li><a href="<c:url value='/guide/addreservation/form'></c:url>">상품추가</a></li>
				<li><a href="<c:url value='/guide/checkreservation'></c:url>">상품조회</a></li>
			<%} %>
		</ul>
		
		<ul class="mod_login">
			<% if(session.getAttribute("userId") == null){ %>
				<li><a href="<c:url value='/user/login/form'></c:url>">로그인</a></li>
			<%} else{ %>
				<li><a href="<c:url value='/user/logout'></c:url>">로그아웃(<%= curUserId %>님)</a></li>
			<%} %>
			<li><a href="<c:url value='/user/register/form'></c:url>">회원가입</a></li>
		</ul>
	</nav>
	<hr>
	-->
	
	<!-- 예약확인 테이블 -->
	<h1 align="center"> 나의 운동 예약확인</h1>
	<table align="center" width="500" height="40" style="border:1px solid" style="border-collapse:collapse" >
		<tr>
			<th> <font size="2px">강사</font></th>
			<th> <font size="2px">운동</font></th>
			<th> <font size="2px">예약날짜</font></th>
			<th> <font size="2px">예약상태</font></th>
		</tr> 

		<c:forEach var="res" items="${reservationList}">
		<tr>
			<td align ="center"><font size="2px"> ${res.trainerName} </font></td>
			<td align ="center"><font size="2px"> ${res.exerciseName}</font></td>
			<td align ="center"><font size="2px"> ${res.reservationDate}</font></td>
			<td align ="center"><font size="2px"> ${res.status}</font></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>