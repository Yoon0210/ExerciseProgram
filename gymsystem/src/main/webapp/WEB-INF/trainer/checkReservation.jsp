<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="model.Exercise" %>
<%@page import="java.util.ArrayList" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/gymsystem/css/custom.css">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<title>운동목록 확인</title>
</head>
<body>
<%@include file="/WEB-INF/header.jsp"%>
<%@include file="/WEB-INF/navbar.jsp" %>


	
	<!-- 고객의 여행상품 조회 -->
	<br>
	<h1 align="center">내가 맡은 운동 조회</h1>
	<table align="center" width="600" height="70" style="border:1px solid" style="border-collapse:collapse" >
		<tr>
			<th> <font size="2px">운동종목명</th>
			<th> <font size="2px">운동이름</th>
			<th> <font size="2px">운동강도</th>
			<th> <font size="2px">요일</th>
			<th> <font size="2px">시간</th>
			<th> <font size="2px">삭제</th>
		</tr> 
		  
		<c:forEach var="exercise" items="${exerciseList}">
		<tr>
			<td align ="center"><font size="2px"> ${exercise.getExerciseType()} </td>
			<td align ="center"><font size="2px"> ${exercise.getExerciseName()}
			</td>
			<td align ="center"><font size="2px"> ${exercise.getDifficulty()}</td>
			<td align ="center"><font size="2px"> ${exercise.getExerciseDay()}</td>
			<td align ="center"><font size="2px"> ${exercise.getExerciseTime()}</td>
			<td align="center"><a onclick="return confirm('삭제하시겠습니까?')"  href="<c:url value='/trainer/delete'>
							<c:param name='exerciseReservation' value='${exercise.getExerciseId()}'/></c:url>"
	 						> 삭제 </a></td>
		</tr>
		</c:forEach>
	</table>	
	
</body>
<c:if test='${deleteFailed }'>
	<script type="text/javascript">
		confirm('${exception.getMessage()}')
	</script>
</c:if>
</html>