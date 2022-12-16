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
<link rel="stylesheet" href="./css/custom.css">
</head>
<body>

	<%@include file="/WEB-INF/header.jsp"%>
	<!-- 헤더 -->

	<section class="container">

		<!-- 운동 검색 폼 -->
		<!--  <form method="get" action="<c:url value="/exercise/search" />"
			class="form-inline mt-3">
			<select name="allContent" class="form-control mx-1 mt-2">
				<option value="-1">전체</option>
				<c:forEach var="all" items="${allList}">
					<option value="${all.getExerciseId()}"
						<c:if test='${all.getExerciseId() eq allContent}'> selected="selected" </c:if>>${all.toString()}</option>
				</c:forEach>
			</select> <input type="text" name="searchContent"
				class="form-control mx-1 mt-2" placeholder="내용을 입력하세요"
				<c:if test='${searchContent ne "-"}'>value='${searchContent }'</c:if>>
			<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
		</form>-->
		<div class="col-lg-12 text-center text-danger">
			<c:if test="${exerciseList.size() eq 0 }">
			<br>
				검색 결과가 없습니다.
			</c:if>
		</div>
		<c:forEach var="exercise" items='${exerciseList}'>
			<div class="card bg-light mt-3">

				<div class="card-header bg-light">
					<div class="row">
						<div class="col-8 text-left">${exercise.getExerciseName() }
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="card-title">
						<div
							style="vertical-align: middle; display: flex; align-items: center;">
							<h5 style="color: grey">${exercise.getTrainerName() }</h5> &nbsp;
							<h5 style="color: grey">${exercise.getExerciseTime() }</h5> &nbsp;
							<h5 style="color: grey">${exercise.getExerciseType() }</h5>
						</div>
					</div>
					<div class="row" style="margin: 0; width: 100%; float: right;">

						<div class="col-9 text-left">
							<span style="color: green;">( 난이도 : ${exercise.getDifficulty() }
								) &nbsp; 
							<a href="<c:url value='/exercise/reservation'>
							<c:param name='exerciseReservation' value='${exercise.getExerciseId()}'/></c:url>"
	 						onclick="return confirm('등록하시겠습니까?')"><font size="2">등록하기</font></a> &nbsp;
							</span>
						</div>
					</div>
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