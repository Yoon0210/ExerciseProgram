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
<%@include file="/WEB-INF/navbar.jsp" %>
<%@include file="/WEB-INF/header.jsp"%>

	<section class="container text-center">
	<br>
	<h1 align="center">내가 맡은 운동 조회</h1>
	<table class="table" >
		<tr class="table-primary">
			<th scope="col"> 운동종목명</th>
			<th scope="col"> 운동이름</th>
			<th scope="col"> 운동강도</th>
			<th scope="col"> 요일</th>
			<th scope="col"> 시간</th>
			<th scope="col"> 삭제</th>
		</tr> 
		  
		<c:forEach var="exercise" items="${exerciseList}">
		<tr>
			<td align ="center"> ${exercise.getExerciseType()} </td>
			<td align ="center"> ${exercise.getExerciseName()}</td>
			<td align ="center"> ${exercise.getDifficulty()}</td>
			<td align ="center"> ${exercise.getExerciseDay()}</td>
			<td align ="center"> ${exercise.getExerciseTime()}</td>
			<td align="center"><a onclick="return confirm('삭제하시겠습니까?')"  href="<c:url value='/trainer/delete'>
							<c:param name='exerciseReservation' value='${exercise.getExerciseId()}'/></c:url>"
	 						> 삭제 </a></td>
		</tr>
		</c:forEach>
	</table>	
	<br>
	<h1 align="center">고객 신청 목록</h1>
	<table class="table"  >
		<tr class="table-primary">
			<th scope="col"> 운동종목명</th>
			<th scope="col"> 운동이름</th>
			<th scope="col"> 신청자 이름</th>
			<th scope="col"> 상태</th>
			<th scope="col"> 수락</th>
			<th scope="col"> 거절</th>
		</tr> 
		<c:forEach var="reservation" items="${resList}">
		<tr>
			<td align ="center"> ${reservation.getExerciseType()} </td>
			<td align ="center"> ${reservation.getExerciseName()}</td>
			<td align ="center"> ${reservation.getName()}</td>
			<td align ="center"> ${reservation.getStatus()}</td>
			<td align ="center"> <a onclick="return confirm('수락하시겠습니까?')"  href="<c:url value='/reservation/accept'>
							<c:param name='reservationId' value='${reservation.getResId()}'/>
							<c:param name='resUserId' value='${reservation.getUserId()}'/>
							<c:param name='resExerId' value='${reservation.getExerciseId()}'/></c:url>"
	 						> 수락 </a> </td>
			<td align="center"><a onclick="return confirm('거절하시겠습니까?')"  href="<c:url value='/reservation/reject'>
							<c:param name='reservationId' value='${reservation.getResId()}'/>
							<c:param name='resUserId' value='${reservation.getUserId()}'/>
							<c:param name='resExerId' value='${reservation.getExerciseId()}'/></c:url>"
	 						> 거절 </a></td>
		</tr>
		</c:forEach>
	</table>	
	</section>
	<%@include file="/WEB-INF/footer.jsp"%>
</body>
<c:if test='${deleteFailed }'>
	<script type="text/javascript">
		confirm('${exception.getMessage()}')
	</script>
</c:if>
</html>