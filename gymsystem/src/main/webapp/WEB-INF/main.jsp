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
	
	<section class="container text-center">
	<br><br>
	<br>
	<div class="row">
		<div class="col">
			<div class="card center border-dark w-80">
				<h3 class="card-header ">인기 수업 TOP 3</h3>
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<c:forEach var="ex" items='${topEList}'>
								<li class="list-group-item" ><h4> ${ex.getExerciseName() } &#40;${ex.getExerciseType()}&#41; -  ${ex.getTrainerName()}</h4> 
								</li>	
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="card border-dark w-80" >
				<h3 class="card-header">인기 강사 TOP 3</h3>
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<c:forEach var="tr" items='${topTrList}'>
								<li class="list-group-item" ><h4>${tr.getName()}</h4>
								</li>	
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	</section>

	<%@include file="/WEB-INF/footer.jsp"%>
</body>
</html>
