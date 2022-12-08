<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">

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
	function exerciseCreate() {
		if (form.exerciseType.value == "") {
			alert("운동종목을 입력하십시오.");
			form.category.focus();
			return false;
		}
		if (form.exerciseName.value == "") {
			alert("운동의 이름을 입력하십시오.");
			form.name.focus();
			return false;
		}
		if (form.difficulty.value == "") {
			alert("운동강도를 입력하십시오.");
			form.difficulty.focus();
			return false;
		}

		if (form.exerciseDay.value == "") {
			alert("요일을 입력하십시오.");
			form.exerciseDay.focus();
			return false;
		}
		if (form.exerciseTime.value == "") {
			alert("시간을 입력하십시오.");
			form.exerciseTime.focus();
			return false;
		}

		form.submit();
	}
</script>

<title>운동 추가</title>
</head>
<body>
	<%@include file="/WEB-INF/header.jsp"%>

	<ul class="mod_menu">
		<!-- 회원용 상단바 -->
		<c:if test="${userType eq 'user' }">

			<li><a href="<c:url value='/user/mypage'></c:url>">마이페이지</a></li>
			<li><a href="<c:url value='/user/myreservation'></c:url>">예약확인</a></li>
			<li>커뮤니티</li>

		</c:if>

		<!-- 트레이너용 상단바 -->

		<c:if test="${userType eq 'trainer' }">
			<li><a href="<c:url value='/trainer/exercise/form'></c:url>">운동추가</a></li>
			<li><a href="<c:url value='/trainer/check'></c:url>">운동조회</a></li>
		</c:if>
	</ul>

	<hr>

	<!-- register ExerciseForm -->
	<form name="form" method="POST" action="<c:url value='/trainer/add' />">
		<h1 align="center">새로운 운동 등록</h1>

		<table align="center">
			<!--<tr>
			<td align = "center"> <font size="2px">운동 ID</td>
			<td> <input type="text" style="width: 300" name="id"> </td>
		  </tr>-->
			<tr>
				<td align="center">운동카테고리</td>
				<td><input type="radio" name="exerciseType" value="헬스" />헬스<input
					type="radio" name="exerciseType" value="요가" />요가 <input
					type="radio" name= "exerciseType" value="필라테스" />필라테스 <input
					type="radio" name="exerciseType" value="PT" />PT</td>
			</tr>
			<tr>
				<td align="center">운동이름</td>
				<td><input type="text" name="exerciseName"></td>
			</tr>
			<tr>
				<td align="center">난이도</td>
				<td><input type="radio" name="difficulty" value="상" />상 <input
					type="radio" name="difficulty" value="중" />중 <input type="radio"
					name="difficulty" value="하" />하</td>
			</tr>
			<tr>
				<td align="center">요일</td>
				<td width="250"><input type="text" name="exerciseDay"></td>
			</tr>

			<tr>
				<td align="center">시간</td>
				<td><input type="text" name="exerciseTime"></td>
			</tr>
			<tr>
				<td><input type="button" value="운동등록"
					onClick="exerciseCreate()" /> &nbsp;</td>
			</tr>

		</table>

	</form>
	
	
</body>
<c:if test='${registerFailed }'>
	<script type="text/javascript">
		confirm('${exception.getMessage()}')
	</script>
</c:if>
</html>