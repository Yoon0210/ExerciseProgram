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
			<th>사용자 ID</th>
			<td>${user.userId}</td>
		  </tr>
		  <tr>
			<th>이메일 주소</th>
			<td>${user.email}</td>
		  </tr>
		</tbody>
	</table>
	<br> 		     
		<!-- 예약확인 테이블 -->
	<hr color="black" size="10px"><br>
	<h4 align="center"> 나의 운동 예약확인</h4><br><br>
	<table class="table table-sm table-striped" style="text-align: center;">
		<tr>
			<th> <font size="4px">강사</font></th>
			<th> <font size="4px">운동</font></th>
			<th> <font size="4px">예약날짜</font></th>
			<th> <font size="4px">예약상태</font></th>
		</tr> 

		<c:forEach var="res" items="${reservationList}">
		<tr>
			<td align ="center"><font size="4px"> ${res.trainerName} </font></td>
			<td align ="center"><font size="4px"> ${res.exerciseName}</font></td>
			<td align ="center"><font size="4px"> ${res.reservationDate}</font></td>
			<td align ="center"><font size="4px"> ${res.status}</font></td>
		</tr>
		</c:forEach>
	</table>
	
	
    <a class="btn btn-primary" 
    	href="<c:url value='/user/update' >
     		     <c:param name='userId' value='${user.userId}'/>
		 	  </c:url>">수정</a>
    <a class="btn btn-warning" 
   		href="<c:url value='/user/delete'>
		     	 <c:param name='userId' value='${user.userId}'/>
	 	      </c:url>" onclick="return userRemove();">삭제</a>
    <a class="btn btn-success" href="<c:url value='/user/list' />">사용자 목록</a> 	    
    <br>	   
	    
	<!-- 수정 또는 삭제가  실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<c:if test="${updateFailed || deleteFailed}">
		<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
    </c:if>  
</div>  
</body>
</html>