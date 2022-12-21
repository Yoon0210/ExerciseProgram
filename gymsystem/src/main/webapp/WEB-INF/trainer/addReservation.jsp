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


<script>
	function exerciseCreate() {
		if (form.exerciseType.value == "") {
			alert("운동종목을 입력하십시오.");
			form.exerciseType.focus();
			return false;
		}
		if (form.exerciseName.value == "") {
			alert("운동의 이름을 입력하십시오.");
			form.name.focus();
			return false;
		}
		if (form.difficulty.value == "") {
			alert("운동강도를 입력하십시오.");
			form.difficulty.focus();
			return false;
		}

		if (form.exerciseDay.value == "") {
			alert("요일을 입력하십시오.");
			form.exerciseDay.focus();
			return false;
		}
		if (form.exerciseTime.value == "") {
			alert("시간을 입력하십시오.");
			form.exerciseTime.focus();
			return false;
		}

		form.submit();
	}
</script>

<title>운동 추가</title>
</head>
<body>
	<%@include file="/WEB-INF/navbar.jsp" %>
	<%@include file="/WEB-INF/header.jsp"%>
	<br>
	<section class="container mt-3" style="max-width: 600px;">
	<!-- register ExerciseForm -->
	<form name="form" method="POST" action="<c:url value='/trainer/add' />">
		<h1 align="center">새로운 운동 등록</h1>
		<hr>
		 <div class="form-group row">
		 <label class="col-form-label col-sm-2 pt-0">운동 종목</label>
		    <div class="col-sm-10">
		      <div class="form-check form-check-inline">
		        <input class="form-check-input" type="radio" name="exerciseType" id="inlineRadios1" value="헬스" checked>
		        <label class="form-check-label" for="inlineRadios1">
		          헬스
		        </label>
		        </div>
		        <div class="form-check form-check-inline">
		        <input class="form-check-input" type="radio" name="exerciseType" id="inlineRadios2" value="요가">
		        <label class="form-check-label" for="inlineRadios2">
		          요가
		        </label>
		     	</div>
		     	<div class="form-check form-check-inline">
		        <input class="form-check-input" type="radio" name="exerciseType" id="inlineRadios3" value="필라테스">
		        <label class="form-check-label" for="inlineRadios3">
		          필라테스
		        </label>
		        </div>
		        <div class="form-check form-check-inline">
		        <input class="form-check-input" type="radio" name="exerciseType" id="inlineRadios4" value="PT">
		        <label class="form-check-label" for="inlineRadios4">
		          PT
		        </label>
		      </div> 
		    </div>
		  </div>
			<!--<tr>
			<td align = "center"> <font size="2px">운동 ID</td>
			<td> <input type="text" style="width: 300" name="id"> </td>
		  </tr>-->
		   	<div class="form-group row">
			    <label for="exerciseName" class="col-lg-2 col-form-label">운동 이름</label>
			    <div class="col-lg-10">
			      <input type="text" class="form-control" name="exerciseName">
			    </div>
			</div>
			  <div class="form-group row">
		 		<label class="col-form-label col-sm-2 pt-0">강도</label>
			    <div class="col-sm-10">
			      <div class="form-check form-check-inline">
			        <input class="form-check-input" type="radio" name="difficulty" id="inlineRadios1" value="상" checked>
			        <label class="form-check-label" for="inlineRadios1">
			          상
			        </label>
			      </div>
			      <div class="form-check form-check-inline">
			        <input class="form-check-input" type="radio" name="difficulty" id="inlineRadios2" value="중">
			        <label class="form-check-label" for="inlineRadios2">
			          중
			        </label>
			      </div>
			      <div class="form-check form-check-inline">
			        <input class="form-check-input" type="radio" name="difficulty" id="inlineRadios3" value="하">
			        <label class="form-check-label" for="inlineRadios3">
			          하
			        </label>
			      </div>
		      	</div>
		      </div>
		      <div class="row g-3">
		      	<div class="col-md-6">
				<select class="form-select" name="exerciseDay">
					<option selected>요일 선택</option>
					<option value="일">일</option>
					<option value="월">월</option>
					<option value="화">화</option>
					<option value="수">수</option>
					<option value="목">목</option>
					<option value="금">금</option>
					<option value="토">토</option>
				</select>
				</div>
				<div class="col-md-6">
				<select class="form-select" name="exerciseTime">
					<option selected>시간 선택</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
				</select>
				</div>
				</div>
				<br>
				<span class="badge rounded-pill text-light bg-warning">!</span> 운동목록 조회를 원하면 상단의 '관리'를 클릭하세요
				<br>
				<br>
				<input class="btn btn-primary" type="submit" value="운동등록"
				onClick="exerciseCreate()" /> &nbsp;
				<input class="btn btn-light" type="reset" value="초기화">
				<br><br><br>

	</form>
	</section>
	<%@include file="/WEB-INF/footer.jsp"%>
</body>
<c:if test='${registerFailed }'>
	<script type="text/javascript">
		confirm('${exception.getMessage()}')
	</script>
</c:if>
</html>