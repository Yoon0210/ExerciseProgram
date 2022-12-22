<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>사용자 리스트</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>

<div class="container">   
	<br>
	<h4>사용자 목록</h4>
	<br>
	<table class="table table-bordered">
      <thead class="thead-inverse">
      	<tr>
		  <td>사용자 ID</td>
		  <td>이메일</td>
		</tr>
      </thead>
      <tbody> 
		<c:forEach var="user" items="${userList}">  			  	
	  	    <tr>
			  <td>
			  	${user.userId}     
			  </td>
			  <td>
			    ${user.email} 
			  </td>
			</tr>
		 </c:forEach> 
	  </tbody>
	</table>		  	 
	<br>   
	<a href="<c:url value='/user/register' />" class="btn btn-primary">사용자 추가 </a>    		     
</div>
<%@include file="/WEB-INF/footer.jsp"%>
</body>
</html>
