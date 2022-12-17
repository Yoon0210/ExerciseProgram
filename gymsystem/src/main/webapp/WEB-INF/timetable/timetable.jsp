<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<style>
        table,th,td{
            border: 1px solid black;
            border-collapse: collapse;
            text-align: center;
            width: 100px;
            height: 70px;
            
        }
        th {
           text-align: center;
           font-size: 20px;
           text-decoration: solid;
        }
        td{
            font-size: 13px;
            text-align: center;
        }
    </style>
    
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
	<div>
	<br><br>
	<h1 align="center">내 시간표 확인</h1>
	<br><br>
    <table align="center"; style="width:80%";>
    <tr>
        <th>&nbsp;</th>
        <th>일</th>
        <th>월</th>
        <th>화</th>
        <th>수</th>
        <th>목</th>
        <th>금</th>
        <th>토</th>
    </tr>
    <!-- 
    <tr>
        <th>09:00 - 09:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>10:00 - 10:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>11:00 - 11:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
   <tr>
        <th>12:00 - 12:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>13:00 - 13:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>14:00 - 14:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>15:00 - 15:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>16:00 - 16:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>17:00 - 17:50</th>
       <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>18:00 - 18:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>19:00 - 19:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>20:00 - 20:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <th>21:00 - 21:50</th>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</table>
 -->
</div>
<%	
	String[][] sche = (String[][])request.getAttribute("sche");

	for (int i = 0; i < 13; i++){ //times 행
		  out.println("<tr>");
		  out.println("<td>"+(i+9)+"시 <td>");
		  for(int j=0; j<7; j++){ //days 열 
			  if(sche[i][j] == null){
				  out.println("&nbsp;");
			  }
			  else{
			  	out.println(sche[i][j]);
			  }
		  }
		  out.println("</tr>");
	}
	
%>
</body>
</html>