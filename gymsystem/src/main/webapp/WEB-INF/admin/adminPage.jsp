<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>관리자 페이지</title>
</head>
<body>
<%@include file="/WEB-INF/header.jsp"%>
	<%@include file="/WEB-INF/navbar.jsp"%>
	<section style="text-align:center">
	<a href='<c:url value="/admin/user" />'> 사용자 목록 </a> <br>
	<a href='<c:url value="/admin/lesson" />'> 운동 관리 </a> <br>
	<a href='<c:url value="/admin/report" />'> 신고 처리 </a> <br>
	</section>

</body>
</html>