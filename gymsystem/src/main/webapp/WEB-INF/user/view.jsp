<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>사용자 관리 - 정보 조회</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function userRemove() {
	return confirm("정말 삭제하시겠습니까?");		
}
</script>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>

<div class="container">  
	<br>
	<h4 align="center">사용자 정보 조회</h4>
	<br>
	<table class="table table-sm table-striped" style="text-align: center;">
    	<tbody> 
	  	  <tr>
			<th scope="col">사용자 ID</th>
			<th scope="col">이름</th>
			<th scope="col">이메일 주소</th>
			<th scope="col">핸드폰 번호</th>
		  </tr>
		  <tr>
			<td>${user.userId}</td>
			<td>${user.name}</td>
			<td>${user.email}</td>
			<td>${user.phone}</td>
		  </tr>
		</tbody>
	</table>
	<br> 	
	
	<a class="btn btn-primary" 
    	href="<c:url value='/user/update' >
     		     <c:param name='userId' value='${user.userId}'/>
		 	  </c:url>">회원정보 수정</a>
    <a class="btn btn-warning" 
   		href="<c:url value='/user/delete'>
		     	 <c:param name='userId' value='${user.userId}'/>
	 	      </c:url>" onclick="return userRemove();">회원 탈퇴</a>
	
	<c:if test="${userId eq 'admin' }">   
	    <a class="btn btn-success" href="<c:url value='/user/list' />">사용자 목록</a> 	    
	    <br>	
    </c:if>
    
		     
	<!-- 예약확인 테이블 (이거는 유저일 때만 나옴)-->
	<c:if test="${userType eq 'user' && userId != 'admin'}">
	<hr color="black" size="10px"><br>
	<h4 align="center">나의 운동 예약확인</h4><br><br>
	
	<table class="table table-sm table-striped" style="text-align: center;">
		<tr>
			<th scope="col">강사</th>
			<th scope="col">운동</th>
			<th scope="col">예약날짜</th>
			<th scope="col">예약상태</th>
			<th scope="col">신청취소</th>
			<th scope="col">삭제</th>
		</tr> 

		<c:forEach var="res" items="${reservation}">
		<tr>
			<td> ${res.trainerName}</td>
			<td> ${res.exerciseName}</td>
			<td> ${res.reservationDate}</td>
			<td> ${res.status}</td>
			<td> <c:if test="${res.getStatus() eq '대기'}">
			<a onclick="return confirm('취소하시겠습니까?')" href="<c:url value='/user/mypage/cancel'><c:param name='reservationId' value='${res.getResId()}'/>
							<c:param name='resUserId' value='${res.getUserId()}'/>
							<c:param name='resExerId' value='${res.getExerciseId()}'/></c:url>">취소</a></c:if></td>
						<c:if test="${res.getStatus() eq '취소' || res.getStatus() eq '거절' }">
							<td><a href="<c:url value='/user/mypage/delete'><c:param name='reservationId' value='${res.getResId()}'/>
							</c:url>">삭제</a></td>
						</c:if>
					</tr>
		</c:forEach>
	</table>
	</c:if>
	    
	<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<c:if test="${updateFailed || deleteFailed}">
		<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
    </c:if>  
</div>  
<%@include file="/WEB-INF/footer.jsp"%>
</body>
</html>
