<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String curUserId = (String)request.getAttribute("curUserId");
%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>운동강좌 관리</title>
<link rel="stylesheet" href="/gymsystem/css/custom.css">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	

<script>
function itemCreate() {
	if (form.category.value == "") {
	alert("운동종목을 입력하십시오.");
	form.category.focus();
	return false;
}
	if (form.name.value == "") {
		alert("운동의 이름을 입력하십시오.");
		form.name.focus();
		return false;
	}
	if (form.strength.value == "") {
		alert("운동강도를 입력하십시오.");
		form.strength.focus();
		return false;
	}
	var dateExp = /^\d{4}-\d{2}-\d{2}$/;
	if(dateExp.test(form.startTime.value)==false) {
		alert("운동시작일의 형식이 올바르지 않습니다.");
		form.startTime.focus();
		return false;
	}
	if(dateExp.test(form.endTime.value)==false) {
		alert("운동마감일의 형식이 올바르지 않습니다.");
		form.endTime.focus();
		return false;
	}
	form.submit();
}
</script>

<title>운동 추가</title>
</head>
<body>
<%@include file="/WEB-INF/header.jsp"%>
<%@include file="/WEB-INF/navbar.jsp" %>

		<ul class="mod_menu">
			<!-- 회원용 상단바 -->
			<% if(session.getAttribute("divGuide") == null){ %>
				<li><a href="<c:url value='/user/mypage'></c:url>">마이페이지</a></li>
				<li><a href="<c:url value='/user/myreservation'></c:url>">예약확인</a></li>
				<li>커뮤니티</li>
			<%} else{ %>  <!-- 트레이너용 상단바 -->
				<li><a href="<c:url value='/trainer/addreservation/form'></c:url>">운동추가</a></li>
				<li><a href="<c:url value='/trainer/checkreservation'></c:url>">운동조회</a></li>
			<%} %>
		</ul>

	<hr>

<!-- register ExerciseForm -->
<form name="form" method ="POST" action="<c:url value='/trainer/addreservation' />" >
	<h1 align="center">새로운 운동 등록</h1>
	
	<table align="center">
	  	  <!--<tr>
			<td align = "center"> <font size="2px">운동 ID</td>
			<td> <input type="text" style="width: 300" name="id"> </td>
		  </tr>-->
		  <tr>
			<td align = "center"> <font size="2px">운동카테고리</td>
			<td> 
				<input type="radio" style="width: 300" name="category" value="헬스"/><font size="2px">헬스</font>
				<input type="radio" style="width: 300" name="category" value="요가"/><font size="2px">요가</font>
				<input type="radio" style="width: 300" name="category" value="필라테스"/><font size="2px">필라테스</font>
				<input type="radio" style="width: 300" name="category" value="PT"/><font size="2px">PT</font>
			</td>
		  </tr>
	  	  <tr>
			<td align = "center"> <font size="2px">운동이름</td>
			<td> <input type="text" style="width: 300" name="name"> </td>
		  </tr>
		  <tr>
			<td align = "center"> <font size="2px">운동강도</td>
			<td> 
				<input type="radio" style="width: 300" name="strength" value="강"/><font size="2px">강</font>
				<input type="radio" style="width: 300" name="strength" value="중"/><font size="2px">중</font>
				<input type="radio" style="width: 300" name="strength" value="약"/><font size="2px">약</font>
			</td>
		  </tr>
	  	  <tr>
			<td align = "center"> <font size="2px">운동시작일</td>
			<td width="250"> <input type="text" style="width: 300" name="startTime" placeholder="전체년도 - 월 - 일"></td>
		  </tr>
		  
		  <tr>
			<td align = "center"> <font size="2px">운동마감일</td>
			<td> <input type="text" style="width: 300" name="endTime" placeholder="전체년도 - 월 - 일"> </td>
		  </tr>
		  
	  	  
	    </table>
	    <br>
	    
	    <table align="center">
		  <tr>
			<td>
			<input type="button" value="운동등록" onClick="exerciseCreate()"> &nbsp;
			</td>
		  </tr>
	    </table>
	</form>
</body>
</html>
