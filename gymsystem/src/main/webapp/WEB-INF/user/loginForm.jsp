<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>로그인</title>

<!-- 글씨체 적용 -->
<link rel="stylesheet" href="/gymsystem/css/custom.css">

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
	function login() {
		if (form.userId.value == "") {
			alert("사용자 ID를 입력하십시오.");
			form.userId.focus();
			return false;
		}
		if (form.password.value == "") {
			alert("비밀번호를 입력하십시오.");
			form.password.focus();
			return false;
		}
		form.submit();
	}
</script>
</head>
<body>
	<!-- navigation bar -->
	<%@include file="/WEB-INF/navbar.jsp"%>

	<div class="container">

		<!-- 로그인이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
		<div class="col-lg-12" style="text-align: center">
			<c:if test="${loginFailed}">
				<h6 class="text-danger">
					<c:out value="${exception.getMessage()}" />
				</h6>
			</c:if>
		</div>
		<!-- login form  -->
		<section class="container mt-3" style="max-width: 560px;">
			<form name="form" method="POST"
				action="<c:url value='/user/login' />">
				<div class="form-group">
					<label for="loginType" ></label>
					<div class="form-check form-check-inline">
						<input value="trainer" class="form-check-input" type="radio"
							name="loginType" id="flexRadioDefault1" checked> <label
							class="form-check-label" for="flexRadioDefault1"> 강사
						</label>
					</div>
					<div class="form-check form-check-inline">
						<input value="user" class="form-check-input" type="radio"
							name="loginType" id="flexRadioDefault2"> <label
							class="form-check-label" for="flexRadioDefault2"> 회원
						</label>
					</div>
				</div>
				<div class="form-group">
					<label for="userId">아이디</label> <input type="text" name="userId"
						class="form-control" placeholder="아이디">
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label> <input type="password"
						name="password" class="form-control" placeholder="비밀번호">
				</div>
				<div class="form-group">
					<input type="button" class="btn btn-primary" value="로그인"
						onClick="login()"> <a
						href="<c:url value='/user/register' />" class="btn btn-link">회원
						가입 </a>
				</div>
			</form>
		</section>
	</div>

	<!-- footer -->
	<%@include file="/WEB-INF/footer.jsp"%>
</body>
</html>