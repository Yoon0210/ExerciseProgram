<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>강사후기</title>

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
function report(a, b){
	$('#rrt').attr("value", a);
	$('#rri').attr("value", b);
}
</script>

</head>
<body>

	<%@include file="/WEB-INF/header.jsp"%>
	<!-- 헤더 -->

	<section class="container">

		<!-- 리뷰 검색 폼 -->
		<form method="get" action="<c:url value="/review/search" />"
			class="form-inline mt-3">
			<select name="workoutType" class="form-control mx-1 mt-2">
				<option value="전체">전체</option>
				<c:forEach var="workout" items="${wList}">
					<option value="${workout.getExerciseId()}"
						<c:if test='${workout.getExerciseId() eq workoutType}'> selected="selected" </c:if>>${workout.toString()}</option>
				</c:forEach>
			</select> <select name="orderType" class="form-control mx-1 mt-2">
				<option value="r.reviewId">최신순</option>
				<option value="r.likeCount"
					<c:if test='${orderType eq "r.likeCount"}'>selected="selected"</c:if>>추천순</option>

			</select> <input type="text" name="searchContent"
				class="form-control mx-1 mt-2" placeholder="내용을 입력하세요"
				<c:if test='${searchContent ne null}'>value='${searchContent }'</c:if>>
			<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
			<a class="btn btn-primary mx-1 mt-2" data-toggle="modal"
				href="#registerModal">등록하기</a>
		</form>
		<div class="col-lg-12 text-center text-danger">
			<c:if test="${reviewList.size() eq 0 }">
			<br>
				검색 결과가 없습니다.
			</c:if>
		</div>
		<c:forEach var="review" items='${reviewList}'>
			<div class="card bg-light mt-3">

				<div class="card-header bg-light">
					<div class="row">
						<div class="col-8 text-left">${review.getWorkoutName() }
							&nbsp;<small>${review.getTrainerName() }</small> &nbsp;<small
								style="color: grey">${review.getPostedDate() }</small>
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="card-title">
						<div
							style="vertical-align: middle; display: flex; align-items: center;">
							<h5 style="display: inline-block; margin: auto 0">${review.getTitle() }&nbsp;</h5>
							<div style="display: inline-block; margin: auto 0"
								class="wrap-star">

								<div class='star-rating'>
									<span style="width:${review.getScore()*20 }%"></span>
								</div>
							</div>
						</div>
					</div>
					<p class="card-text">${review.getContent() }</p>
					<div class="row" style="margin: 0; width: 100%; float: right;">

						<div class="col-9 text-left">
							<span style="color: green;">( 추천수 : ${review.getLikeCount() }
								) &nbsp; <a
								href="<c:url value='/review/like' > <c:param name='reviewId' value='${review.getReviewId()}'/> </c:url>">추천&nbsp;</a>
							</span>
						</div>

						<div class="col-3 text-right">
							<a  data-toggle="modal" class="btn btn-warning"
								data-target="#reportModal"
								onclick="report('<c:out value='${review.getTitle()} (작성자: ${review.getUserId() })'/>', '<c:out value='${review.getReviewId() }'/>');">신고</a>

							<!-- 작성자==현재로그인유저 일 경우 삭제버튼 표시 -->
							<c:if
								test="${curUserId eq review.getUserId() || curUserId eq 'admin'}">
								<a class="btn btn-danger" onclick="return confirm('삭제하시겠습니까?')"
									href="<c:url value='/review/delete' > <c:param name='reviewId' value='${review.getReviewId()}'/> </c:url>">삭제</a>
							</c:if>
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

	<div class="modal fade" id="registerModal" tabindex="-1" role="dailog"
		aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-tittle" id="modal">평가 등록</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<form action="<c:url value='/review/create/form'/>" method="post">
						<div class="form-row">
							<!-- Exercise List 불러오기 -->
							<div class="form-group col-sm-6">
								<label>운동 선택</label> <select id="trainerSelect" name="workoutId"
									class="form-control">
									<c:forEach var="ex" items="${wList}">
										<option value="${ex.getExerciseId()}">${ex.toString()}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label>제목</label> <input type="text" name="reviewTitle"
								class="form-control" maxlength="30">
						</div>

						<div class="form-group">
							<label>내용</label>
							<textarea name="reviewContent" class="form-control"
								maxlength="2048" style="height: 180px;"></textarea>
						</div>

						<div class="form-row">
							<div class="form-group col-sm-3">
								<label>점수</label> <select name="score" class="form-control">
									<option value="5" selected>5</option>
									<option value="4">4</option>
									<option value="3">3</option>
									<option value="2">2</option>
									<option value="1">1</option>
								</select>
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">취소</button>
							<button type="submit" class="btn btn-primary">등록하기</button>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="reportModal" tabindex="-1" role="dailog"
		aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">

					<h5 class="modal-title" id="modal">신고 하기</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<form action="<c:url value='/review/report'/>" method="post">
						<div class="form-group">
							<div class="col-xs-4">
								<label>신고할 리뷰</label><input id="rrt" type="text"
									name="reportReviewTitle" class="form-control" maxlength="60"
									value=" "> <input id="rri" type="hidden"
									name="reportReviewId" value=" ">
							</div>
						</div>
						<div class="form-group">
							<label>신고 사유를 선택해주세요.</label>
							<div class="form-check">
								<input value="폭력적인 컨텐츠" class="form-check-input" type="radio"
									name="reportReason" id="flexRadioDefault1" checked> <label
									class="form-check-label" for="flexRadioDefault1"> 폭력적
									컨텐츠 </label>
							</div>
							<div class="form-check">
								<input value="성적인 컨텐츠" class="form-check-input" type="radio"
									name="reportReason" id="flexRadioDefault2">
								<label class="form-check-label" for="flexRadioDefault2">
									성적인 컨텐츠 </label>
							</div>
							<div class="form-check">
								<input value="악의적인 컨텐츠" class="form-check-input" type="radio"
									name="reportReason" id="flexRadioDefault3"> <label
									class="form-check-label" for="flexRadioDefault3"> 악의적인
									컨텐츠 </label>
							</div>
							<div class="form-check">
								<input value="잘못된 정보" class="form-check-input" type="radio"
									name="reportReason" id="flexRadioDefault4"> <label
									class="form-check-label" for="flexRadioDefault4"> 잘못된 정보 </label>
							</div>
							<div class="form-check">
								<input value="스팸" class="form-check-input" type="radio"
									name="reportReason" id="flexRadioDefault5"> <label
									class="form-check-label" for="flexRadioDefault5"> 스팸 </label>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">취소</button>
							<button type="submit" class="btn btn-danger">신고하기</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/footer.jsp"%>
</body>

<c:if test='${likeFailed }'>
	<script type="text/javascript">
		confirm('${exception.getMessage()}')
	</script>
</c:if>
<c:if test='${removeFailed }'>
	<script type="text/javascript">
		confirm('${exception.getMessage()}')
	</script>
</c:if>

</html>