f<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>���� ����</title>
<link rel="stylesheet" href="/gymsystem/css/custom.css">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: "Trebuchet MS", Dotum;
	display: flex;
	flex-flow: column nowrap;
	justify-content: center;
	align-items: center;
	overflow-x: hidden;
}

h1 {
	margin: 2em 0 1.5em 0;
}

nav {
	width: 100%;
	display: flex;
	justify-content: center;
	position: relative;
	background: #CED8F6;
}

ul, li {
	margin: 0;
	padding: 0;
	list-style: none;
}

#main-menu>li {
	float: left;
	position: relative;
}

#main-menu>li>a {
	font-size: 0.85rem;
	color: black;
	text-align: center;
	text-decoration: none;
	letter-spacing: 0.05em;
	display: block;
	padding: 14px 36px;
	border-right: 1px solid rgba(0, 0, 0, 0.15);
	text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);
}

#main-menu>li:nth-child(1)>a {
	border-left: 1px solid rgba(0, 0, 0, 0.15);
}

#sub-menu {
	position: absolute;
	background: #CED8F6;
	opacity: 0;
	visibility: hidden;
	transition: all 0.15s ease-in;
}

#sub-menu>li {
	padding: 16px 28px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.15);
}

#sub-menu>li>a {
	color: black;
	text-decoration: none;
}

#main-menu>li:hover #sub-menu {
	opacity: 1;
	visibility: visible;
}

#sub-menu>li>a:hover {
	text-decoration: underline;
}

#main1, #main2 {
	text-align: center;
	border: 1px solid #BDBDBD;
	margin: 20px;
	width: 700px;
	height: 30px;
}

#userlist, #applyList {
	text-align: center;
	border: 1px solid #BDBDBD;
	margin-bottom: 50px;
	width: 700px;
	height: 30px;
}

th, #bc {
	width: 150px;
	background: #CED8F6;
	color: white;
}

a {
	text-decoration: none;
}

a:link {
	color: black;
}

a:visited {
	color: black;
}

#acceptB, #rejectB {
	float: right;
}

#acceptB {
	color: blue;
}

#rejectB {
	color: red;
}

#gManageUpdateB, #gDeleteB {
	background: #E6E6E6;
	color: black;
	float: right;
}
</style>

<script>
function memberDelete() {
	return confirm("���� �����Ͻðڽ��ϱ�?");		
}
function memberAccept() {
	return confirm("����� �����Ͻðڽ��ϱ�?");
}
</script>
</head>
<body>
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<table id="main1">
		<tr>
			<td id="bc">�׷� ��</td>
			<td>${Group.groupName }</td>
			<td id="bc">�����ο�</td>
			<td><c:set var="num" value="${Group.numberOfUsers }" /> <c:if
					test="${num eq '3' }">
					<c:out value="3��" />
				</c:if> <c:if test="${num eq '4' }">
					<c:out value="4��" />
				</c:if> <c:if test="${num eq '5' }">
					<c:out value="5��" />
				</c:if> <c:if test="${num eq '6' }">
					<c:out value="6��" />
				</c:if> <c:if test="${num eq '7' }">
					<c:out value="7��" />
				</c:if> <c:if test="${num eq '8' }">
					<c:out value="8��" />
				</c:if> <c:if test="${num eq '9' }">
					<c:out value="9��" />
				</c:if> <c:if test="${num eq '10' }">
					<c:out value="10��" />
				</c:if> <c:if test="${num eq '11' }">
					<c:out value="10���̻�" />
				</c:if> <c:if test="${num eq '0' }">
					<c:out value="�������" />
				</c:if></td>
			<td id="bc">����Ⱓ</td>
			<td><c:set var="term" value="${Group.term }" /> <c:if
					test="${term eq '1' }">
					<c:out value="1��2��" />
				</c:if> <c:if test="${term eq '2' }">
					<c:out value="2��3��" />
				</c:if> <c:if test="${term eq '3' }">
					<c:out value="3��4��" />
				</c:if> <c:if test="${term eq '7' }">
					<c:out value="1���� " />
				</c:if> <c:if test="${term eq '30' }">
					<c:out value="�Ѵ�" />
				</c:if> <c:if test="${term eq '0' }">
					<c:out value="������" />
				</c:if></td>
		</tr>
	</table>

	<table id="main2">
		<tr>
			<td id="bc">�������</td>
			<td>${Group.place}</td>
			<td id="bc">�������</td>
			<td><c:set var="tra" value="${Group.trafficType }" /> <c:if
					test="${tra eq '����'}">
					<c:out value="����" />
				</c:if> <c:if test="${tra eq '�����'}">
					<c:out value="�����" />
				</c:if> <c:if test="${tra eq '����ö'}">
					<c:out value="����ö" />
				</c:if> <c:if test="${tra eq 'KTX'}">
					<c:out value="KTX" />
				</c:if> <c:if test="${tra eq '��Ʈī'}">
					<c:out value="��Ʈī" />
				</c:if></td>
		</tr>
		<tr>
			<td id="bc">���� ����</td>
			<td>${Group.createdDate }</td>
			<td id="bc">��������</td>
			<td><c:set var="gen" value="${Group.genderType }" /> <c:if
					test="${gen eq '0' }">
					<c:out value="�������" />
				</c:if> <c:if test="${gen eq '1'}">
					<c:out value="����" />
				</c:if> <c:if test="${gen eq '2'}">
					<c:out value="����" />
				</c:if></td>
		</tr>
		<tr>
			<%-- <td id="bc">����</td>
		<td>${Group.leaderId}</td> --%>
			<td id="bc">�������̴�</td>
			<td><c:set var="age" value="${Group.age }" /> <c:if
					test="${age eq '1' }">
					<c:out value="10��" />
				</c:if> <c:if test="${age eq '2' }">
					<c:out value="20��" />
				</c:if> <c:if test="${age eq '3' }">
					<c:out value="30��" />
				</c:if> <c:if test="${age eq '4' }">
					<c:out value="40��" />
				</c:if> <c:if test="${age eq '0' }">
					<c:out value="�������" />
				</c:if></td>
		</tr>
		<tr>
			<td id="bc">�Ұ�</td>
			<td>${Group.description }</td>
		</tr>
	</table>

	<a><h3>��� ����Ʈ</h3></a>

	<table id="userlist">
		<tr>
			<th>&nbsp;</th>
			<th>�̸�</th>
			<th>���̴�</th>
			<th>����</th>
		</tr>

		<c:forEach var="gmList" items="${Group.groupMembers}"
			varStatus="status">
			<tr>
				<td><c:out value='${status.count }' /></td>
				<td>${gmList.name }</td>
				<td><c:set var="age" value="${gmList.age}" /> <c:if
						test="${age eq '1'}">
						<c:out value="10��" />
					</c:if> <c:if test="${age eq '2'}">
						<c:out value="20��" />
					</c:if> <c:if test="${age eq '3'}">
						<c:out value="30��" />
					</c:if> <c:if test="${age eq '4'}">
						<c:out value="40��" />
					</c:if></td>
				<td><c:set var="gen" value="${gmList.gender}" /> <c:if
						test="${gen eq '1'}">
						<c:out value="����" />
					</c:if> <c:if test="${gen eq '2'}">
						<c:out value="����" />
					</c:if></td>
			</tr>
		</c:forEach>

	</table>

	<a><h3>��û�� ����Ʈ</h3></a>
	<table id="applyList">

		<tr>
			<th>&nbsp;</th>
			<th>��� id</th>
			<th>�̸�</th>
			<th>�ۼ��� �ڸ�Ʈ</th>
			<th>��û ��¥</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>

		<c:forEach var="aList" items="${applyList}" varStatus="status">
			<c:if test="${aList.isApproved eq '0'}">
				<tr>
					<td><c:out value="${status.count }" />
					<td>${aList.memberId}</td>
					<td>${aList.memberName}</td>
					<td style="width: 300px;">${aList.comment }</td>
					<td>${aList.applyDate }</td>
					<td><a
						href="<c:url value='/group/manageGroup/applyAccept' > 
						<c:param name="groupId" value="${Group.groupId}" />
						<c:param name="memberId" value="${aList.memberId}" />
						<c:param name="isAccepted" value="true" />
						</c:url>"
						id="acceptB">����</a></td>
					<td><a
						href="<c:url value='/group/manageGroup/applyAccept' > 
						<c:param name="groupId" value="${Group.groupId}" />
						<c:param name="memberId" value="${aList.memberId}" />
						<c:param name="isAccepted" value="false" />
						</c:url>"
						id="rejectB">����</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>

	<table>
		<tr>
			<td><a
				href="<c:url value='/group/manageGroup/update/form' >
				<c:param name='groupId' value='${Group.groupId}' />
				</c:url> "
				id="gManageUpdateB">���� ����</a></td>
			<td><a
				href="<c:url value='/group/manageGroup/delete' >
				<c:param name="groupId" value="${Group.groupId }" />
				</c:url>"
				id="gDeleteB">���� ����</a></td>
		</tr>
	</table>
	<br>
	<br>
</body>
</html>