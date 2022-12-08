<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String curUserId = (String)request.getAttribute("curUserId");
%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>����� ����</title>
<link rel="stylesheet" href="/gymsystem/css/custom.css">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	

<script>
function itemCreate() {
	if (form.category.value == "") {
	alert("������� �Է��Ͻʽÿ�.");
	form.category.focus();
	return false;
}
	if (form.name.value == "") {
		alert("��� �̸��� �Է��Ͻʽÿ�.");
		form.name.focus();
		return false;
	}
	if (form.strength.value == "") {
		alert("������� �Է��Ͻʽÿ�.");
		form.strength.focus();
		return false;
	}
	var dateExp = /^\d{4}-\d{2}-\d{2}$/;
	if(dateExp.test(form.startTime.value)==false) {
		alert("��������� ������ �ùٸ��� �ʽ��ϴ�.");
		form.startTime.focus();
		return false;
	}
	if(dateExp.test(form.endTime.value)==false) {
		alert("��������� ������ �ùٸ��� �ʽ��ϴ�.");
		form.endTime.focus();
		return false;
	}
	form.submit();
}
</script>

<title>� �߰�</title>
</head>
<body>
<%@include file="/WEB-INF/header.jsp"%>
<%@include file="/WEB-INF/navbar.jsp" %>

		<ul class="mod_menu">
			<!-- ȸ���� ��ܹ� -->
			<% if(session.getAttribute("divGuide") == null){ %>
				<li><a href="<c:url value='/user/mypage'></c:url>">����������</a></li>
				<li><a href="<c:url value='/user/myreservation'></c:url>">����Ȯ��</a></li>
				<li>Ŀ�´�Ƽ</li>
			<%} else{ %>  <!-- Ʈ���̳ʿ� ��ܹ� -->
				<li><a href="<c:url value='/trainer/addreservation/form'></c:url>">��߰�</a></li>
				<li><a href="<c:url value='/trainer/checkreservation'></c:url>">���ȸ</a></li>
			<%} %>
		</ul>

	<hr>

<!-- register ExerciseForm -->
<form name="form" method ="POST" action="<c:url value='/trainer/addreservation' />" >
	<h1 align="center">���ο� � ���</h1>
	
	<table align="center">
	  	  <!--<tr>
			<td align = "center"> <font size="2px">� ID</td>
			<td> <input type="text" style="width: 300" name="id"> </td>
		  </tr>-->
		  <tr>
			<td align = "center"> <font size="2px">�ī�װ�</td>
			<td> 
				<input type="radio" style="width: 300" name="category" value="�ｺ"/><font size="2px">�ｺ</font>
				<input type="radio" style="width: 300" name="category" value="�䰡"/><font size="2px">�䰡</font>
				<input type="radio" style="width: 300" name="category" value="�ʶ��׽�"/><font size="2px">�ʶ��׽�</font>
				<input type="radio" style="width: 300" name="category" value="PT"/><font size="2px">PT</font>
			</td>
		  </tr>
	  	  <tr>
			<td align = "center"> <font size="2px">��̸�</td>
			<td> <input type="text" style="width: 300" name="name"> </td>
		  </tr>
		  <tr>
			<td align = "center"> <font size="2px">�����</td>
			<td> 
				<input type="radio" style="width: 300" name="strength" value="��"/><font size="2px">��</font>
				<input type="radio" style="width: 300" name="strength" value="��"/><font size="2px">��</font>
				<input type="radio" style="width: 300" name="strength" value="��"/><font size="2px">��</font>
			</td>
		  </tr>
	  	  <tr>
			<td align = "center"> <font size="2px">�������</td>
			<td width="250"> <input type="text" style="width: 300" name="startTime" placeholder="��ü�⵵ - �� - ��"></td>
		  </tr>
		  
		  <tr>
			<td align = "center"> <font size="2px">�������</td>
			<td> <input type="text" style="width: 300" name="endTime" placeholder="��ü�⵵ - �� - ��"> </td>
		  </tr>
		  
	  	  
	    </table>
	    <br>
	    
	    <table align="center">
		  <tr>
			<td>
			<input type="button" value="����" onClick="exerciseCreate()"> &nbsp;
			</td>
		  </tr>
	    </table>
	</form>
</body>
</html>