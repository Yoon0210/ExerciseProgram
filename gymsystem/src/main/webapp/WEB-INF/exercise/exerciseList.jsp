<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>운동 검색</title>

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- 글씨체 적용 -->
<link rel="stylesheet" href="/css/custom.css">
</head>
<body>

	<%@include file="/WEB-INF/header.jsp"%>
	<!-- 헤더 -->

	<section class="container">

		<!-- 운동 검색 폼 -->
		<form method="get" action="<c:url value="/exercise/search" />"
			class="form-inline mt-3">
			<select name="trainerId" class="form-control mx-1 mt-2">
				<option value="">강사</option>
				<c:forEach var="trainer" items="${trainerList}">
					<option value="${trainer.getUserId()}"
						<c:if test='${trainer.getUserId() eq trainerId}'> selected="selected" </c:if>>${trainer.getName()}</option>
					</c:forEach>
			</select>
			<select name="difficultyType" class="form-control mx-1 mt-2">
				<option value="">난이도</option>
				<option value="상"
					<c:if test='${difficultyType eq "상"}'> selected="selected" </c:if>>상</option>
				<option value="중"
					<c:if test='${difficultyType eq "중"}'> selected="selected" </c:if>>중</option>
				<option value="하"
					<c:if test='${difficultyType eq "하"}'> selected="selected" </c:if>>하</option>
			</select>
			<select name="exerciseType" class="form-control mx-1 mt-2">
				<option value="">종목</option>
				<option value="헬스"
					<c:if test='${exerciseType eq "헬스"}'> selected="selected" </c:if>>헬스</option>
				<option value="요가"
					<c:if test='${exerciseType eq "요가"}'> selected="selected" </c:if>>요가</option>
				<option value="필라테스"
					<c:if test='${exerciseType eq "필라테스"}'> selected="selected" </c:if>>필라테스</option>
				<option value="PT"
					<c:if test='${exerciseType eq "PT"}'> selected="selected" </c:if>>PT</option>
			</select>	
			<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
		</form>
		<div class="col-lg-12 text-center text-danger">
			<c:if test="${exerciseList.size() eq 0 }">
			<br>
				검색 결과가 없습니다.
			</c:if>
		</div>
		<c:forEach var="exercise" items='${exerciseList}'>
			<div class="card bg-light mt-3">

				<div class="card-header bg-light">
					<div class="col-10 text-left" ><h5>${exercise.getExerciseName() }</h5>
					</div>	
				</div>
				<div class="card-body">		
					<span class="col-10 text-left">담당 강사 : ${exercise.getTrainerName() } </span>
					<span class="col-10 text-left">${exercise.getExerciseDay() }요일</span>
					<span class="col-10 text-left">시작 시간 : ${exercise.getExerciseTime() } 시  </span>
					<span class="col-10 text-left">운동 종류 : ${exercise.getExerciseType() } </span>
					<span class="col-10 text-left" style="color: green;">( 난이도 : ${exercise.getDifficulty() } )</span>
					<span class="text-right">
						<a onclick="return confirm('등록하시겠습니까?')" class="btn btn-primary mx-1 mt-2" href="<c:url value='/exercise/reservation' >
						<c:param name='exerciseId' value='${exercise.getExerciseId()}'/> </c:url>">등록하기</a>
					</span> 
				</div>
			</div>
		</c:forEach>
	</section>



	<!-- 	<ul class="pagination justify-content-center mt-3"> -->
	<%-- 		<li class="page-item"><c:if test="${reviewList.size() le 0 }"> --%>
	<!-- 				<a class="page-link disabled">이전</a> -->
	<%-- 			</c:if> <c:if test="${reviewList.size() gt 0 }"> --%>
	<!-- 				<a class="page-link" -->
	<%-- 					href="<c:url value='/review/list'><c:param name='currentPage' value='${currentPage-1 }' /> --%>
	<%-- 						<c:param name='orderType' value='${orderType }' /></c:url> ">이전</a> --%>

	<%-- 			</c:if></li> --%>

	<%-- 		<li><c:if test="${reviewList.size() lt 6 }"> --%>
	<!-- 				<a class="page-link disabled">다음</a> -->
	<%-- 			</c:if> <c:if test="${reviewList.size() ge 6 }"> --%>
	<!-- 				<a class="page-link" -->
	<%-- 					href="<c:url value='/review/list'><c:param name='currentPage' value='${currentPage +1 }' /> --%>
	<%-- 						<c:param name='orderType' value='${orderType }' /></c:url> ">다음</a> --%>
	<%-- 			</c:if></li> --%>

	<!-- 	</ul> -->
	<%@include file="/WEB-INF/footer.jsp"%>
</body>
<c:if test='${reservationFailed }'>
	<script type="text/javascript">
		confirm('${exception}')
	</script>
</c:if>

</html>