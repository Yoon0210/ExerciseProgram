<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>신고 처리</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
<div class="container">   
	<br>
	<h4>신고 목록</h4>
	<br>
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
							<span style="color: green;">( 추천 : ${review.getLikeCount() }) </span>
						</div>
					</div>
				</div>
			</div>
			(신고 이유 : ${report.getReportReason() }) <br>
			<a class="btn btn-primary mx-1 mt-2" href="<c:url value='/admin/report/delete' > <c:param name='reviewId' value='${report.getReviewId()}'/><c:param name='reportUserId' value='${report.getUserId()}'/> </c:url>">삭제</a>
			<a class="btn btn-primary mx-1 mt-2" href="<c:url value='/admin/report/return' > <c:param name='reviewId' value='${report.getReviewId()}'/><c:param name='reportUserId' value='${report.getUserId()}'/> </c:url>">기각</a>
	<br>   
</div>
</body>
</html>