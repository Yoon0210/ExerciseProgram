<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="model.Exercise" %><%@page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/gymsystem/css/custom.css">
<title>예약자 확인</title>
</head>
<body>
	<nav class="modbar">

		<ul class="mod_menu">
			<!-- 회원용 상단바 -->
		<!-- 	<% if(session.getAttribute("divGuide") == null){ %>-->
			<!-- 	<li><a href="<c:url value='/user/mypage'></c:url>">마이페이지</a></li> -->
			<!-- 	<li><a href="<c:url value='/user/myreservation'></c:url>">예약확인</a></li> -->
				<li>커뮤니티</li>
			<%} else{ %>  <!-- 가이드용 상단바 -->
				<li><a href="<c:url value='/guide/addreservation/form'></c:url>">운동추가</a></li>
				<li><a href="<c:url value='/guide/checkreservation'></c:url>">운동목록조회</a></li>
			<%} %>
		</ul>
		
		<ul class="mod_login">
			<% if(session.getAttribute("userId") == null){ %>
				<li><a href="<c:url value='/user/loginForm'></c:url>">로그인</a></li>
			<%}  %>
			<li><a href="<c:url value='/user/registerForm'></c:url>">회원가입</a></li>
		</ul>
	</nav>
	<hr>
	
	<!-- 회원의 운동목록 조회 -->
	<h1 align="center">내가 맡은 운동 조회</h1>
	<table align="center" width="500" height="40" style="border:1px solid" style="border-collapse:collapse" >
		<tr>
			<th> <font size="2px">회원 ID</th>
			<th> <font size="2px">운동이름</th>
			<th> <font size="2px">운동강도</th>
			<th> <font size="2px">운동시작일</th>
			<th> <font size="2px">운동마감일</th>
			<th> <font size="2px">운동카테고리</th>
		</tr> 
		  
		<c:forEach var="exericise" items="${exerciseList}">
		<tr>
			<td align ="center"><font size="2px"> ${item.itemId} </td>
			<td align ="center"><font size="2px"> 
				<a href = "<c:url value='/guide/checkschedule'>
					<c:param name='clickName' value='${exercise.name}'/>
					<c:param name='clickId' value='${exercise.exerciseId}'/> </c:url>">
				${item.name}</a>
			</td>
			<td align ="center"><font size="2px"> ${exercise.category}</td>
			<td align ="center"><font size="2px"> ${exercise.strength}</td>
			<td align ="center"><font size="2px"> ${exercise.startTime}</td>
			<td align ="center"><font size="2px"> ${exercise.endTime}</td>
		</tr>
		</c:forEach>
	</table>	
	
</body>
</html>