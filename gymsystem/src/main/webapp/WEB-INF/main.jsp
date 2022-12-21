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

<title>내 시간표</title>
</head>
<body>
	<%@include file="/WEB-INF/header.jsp"%>
	
	<section>
	인기 수업 TOP 3
	<c:forEach var="ex" items='${topEList}'>
			<div class="card bg-light mt-3">
				<div class="card-body bg-light">
					<div class="col-10 text-left" >${ex.getExerciseName() } &#40;${ex.getExerciseType()}&#41; -  ${ex.getTrainerName()} 
					</div>	
				</div>
			</div>
		</c:forEach>
		인기 강사 TOP 3
		<c:forEach var="tr" items='${topTrList}'>
			<div class="card bg-light mt-3">
				<div class="card-body bg-light">
					<div class="col-10 text-left" >${tr.getName() } 
					</div>	
				</div>
			</div>
		</c:forEach>
	</section>

	<%@include file="/WEB-INF/footer.jsp"%>
</body>
</html>
