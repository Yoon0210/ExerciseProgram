<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>모든 운동 리스트 보기</title>

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
			<select name="allContent" class="form-control mx-1 mt-2">
				<option value="-1">전체</option>
				<c:forEach var="all" items="${allList}">
					<option value="${all.getExerciseId()}"
						<c:if test='${all.getExerciseId() eq allContent}'> selected="selected" </c:if>>${all.toString()}</option>
				</c:forEach>
			</select>
			<select name="trainerType" class="form-control mx-1 mt-2">
				<option value="-1">강사</option>
				<c:forEach var="trainer" items="${trainerList}">
					<option value="${trainer.getTrainerId()}"
						<c:if test='${trainer.getExerciseId() eq trainerType}'> selected="selected" </c:if>>${trainer.toString()}</option>
					</c:forEach>
			</select>
			<select name="difficultyType" class="form-control mx-1 mt-2">
				<option value="-1">난이도</option>
				<c:forEach var="difficulty" items="${difficultyList}">
					<option value="${difficulty.getDifficulty()}"
						<c:if test='${difficulty.getExerciseId() eq difficultyType}'> selected="selected" </c:if>>${difficulty.toString()}</option>
					</c:forEach>
			</select>
			<select name="exerciseType" class="form-control mx-1 mt-2">
				<option value="-1">종목</option>
				<c:forEach var="exercise" items="${exerciseTypeList}">
					<option value="${exercise.getExerciseType()}"
						<c:if test='${exercise.getExerciseId() eq exerciseType}'> selected="selected" </c:if>>${exercise.toString()}</option>
					</c:forEach>
			</select>	
				<input type="text" name="searchContent"
				class="form-control mx-1 mt-2" placeholder="내용을 입력하세요"
				<c:if test='${searchContent ne "-"}'>value='${searchContent }'</c:if>>
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

</html>