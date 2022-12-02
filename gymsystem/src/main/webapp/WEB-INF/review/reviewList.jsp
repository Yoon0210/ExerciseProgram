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
<link rel="stylesheet" href="./css/custom.css">

<script>
function report(a, b){
	document.getElementById("rrt").value = String(a);
	document.getElementById("rri").value = String(b);
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
				<option value="-1">전체</option>
				<c:forEach var="workout" items="${wList}">
					<option value="${workout.getWorkoutId()}"
						<c:if test='${workout.getWorkoutId() eq workoutType}'> selected="selected" </c:if>>${workout.getName()}</option>
				</c:forEach>
			</select> <select name="orderType" class="form-control mx-1 mt-2">
				<option value="reviewId DESC">최신순</option>
				<option value="likeCount DESC"
					<c:if test='${orderType eq "likeCount DESC"}'>selected="selected"</c:if>>추천순</option>

			</select> <input type="text" name="searchContent"
				class="form-control mx-1 mt-2" placeholder="내용을 입력하세요"
				<c:if test='${searchContent ne "-"}'>value='${searchContent }'</c:if>>
			<button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>
			<a class="btn btn-primary mx-1 mt-2" data-toggle="modal"
				href="#registerModal">등록하기</a>
		</form>
		<div class="col-lg-12 text-center text-danger">
			<c:if test="${reviewList.size() eq 0 }">
				검색 결과가 없습니다.
			</c:if>
		</div>
		<c:forEach var="review" items='${reviewList}'>
			<div class="card bg-light mt-3">

				<div class="card-header bg-light">
					<div class="row">
						<div class="col-8 text-left">${wList.get(review.getWorkoutId()-1).getName() }
							&nbsp;점수&nbsp; &nbsp;<small style="color: grey">${review.getPostedDate() }</small>
						</div>
					</div>
				</div>
				<div class="card-body">
					<h4 class="card-title">${review.getTitle() }
						<div class="wrap-star">
							<div class='star-rating'>
								<span style="width:${review.getScore()*20 }%"></span>
							</div>
						</div>
					</h4>
					<p class="card-text">${review.getContent() }</p>
					<div class="row" style="margin: 0; width: 100%; float: right;">

						<div class="col-9 text-left">
							<span style="color: green;">( 추천 : ${review.getLikeCount() }
								) &nbsp; <a
								href="<c:url value='/review/like' > <c:param name='reviewId' value='${review.getReviewId()}'/> </c:url>">추천&nbsp;</a>
							</span>
						</div>

						<div class="col-3 text-right">
							<a style="color: red;" data-toggle="modal"
								data-target="#reportModal"
								onclick="report(${review.getTitle()}, ${review.getReviewId() });">신고</a>

							<!-- 작성자==현재로그인유저 일 경우 삭제버튼 표시 -->
							<c:if
								test="${curUserId eq review.getUserId() || curUserId eq 'admin'}">
								<a onclick="return confirm('삭제하시겠습니까?')"
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
							<!-- 강사명 trainer 테이블에서 불러오기 -->
							<div class="form-group col-sm-6">
								<label>강사명</label> <select id="trainerSelect" name="trainerId"
									class="form-control">
									<c:forEach var="tr" items="${trList}">
										<option value="${tr.getTrainerId()}">${tr.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<!-- 운동 workout 테이블에서 불러오기 -->
						<div class="form-row">
							<div class="form-group col-sm-4">
								<label>운동 구분</label> <select name="workoutId"
									class="form-control">
									<c:forEach var="w" items="${wList}">
										<option value="${w.getWorkoutId()}">${w.getName()}</option>
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
					<form action="./repoartAction.jsp" method="post">
						<div class="form-group">
							<label><input id="rrt" type="text"
								name="reportReviewTitle" class="form-control" maxlength="30"
								value=" "></label> <input id="rri" type="hidden"
								name="reportReviewId" value=" ">
						</div>
						<div class="form-group">
							<label>신고 내용</label>
							<textarea name="reportContent" class="form-control"
								maxlength="2048" style="height: 180px;"></textarea>
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