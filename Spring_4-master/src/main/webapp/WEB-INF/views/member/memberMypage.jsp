<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<c:import url="../layout/nav.jsp"></c:import>
	<div class="container">
		<h2>My Page Form</h2>
		<form action="./memberUpdate" id="frm" onsubmit=true method="post">
		   	<div class="form-group">
				<label for="id" class="form-group">ID:</label>
				<input type="text" class="form-control" id="id" placeholder="Enter ID" required="required" name="id" readonly="readonly" value="${member.id}">
			</div>	
		    <div class="form-group">
				<label for="pw">PW:</label>
				<input type="password" class="form-control" id="pw" placeholder="Enter PW" required="required" name="pw" readonly="readonly"  value="${member.pw}">
		    </div>
		    <div class="form-group">
				<label for="name">NAME:</label>
				<input type="text" class="form-control" id="name" placeholder="Enter NAME" required="required" name="name" readonly="readonly" value="${member.name}">
		    </div>
		    <div class="form-group">
				<label for="email">email:</label>
				<input type="email" class="form-control" id="email" placeholder="Enter E-MAIL" required="required" name="email"  readonly="readonly" value="${member.email}">
		    </div>
		    <div class="form-group">
				<label for="birth">BIRTH:</label>
				<input type="text" class="form-control" id="birth" placeholder="Enter BIRTH DAY" required="required" name="birth" readonly="readonly" value="${member.birth}">
		    </div>
		    <div class="form-group">
				<label for="gender">GENDER:</label>
				<div class="checkbox">
				<c:if test="${member.gender eq 'm' or member.gender eq 'M'}">
					<label><input type="checkbox" name="gender" value="M" checked="checked">Male</label>
					<label><input type="checkbox" name="gender" value="F">Female</label>
				</c:if>
				<c:if test="${member.gender eq 'f' or member.gender eq 'F'}">
					<label><input type="checkbox" name="gender" value="M" >Male</label>
					<label><input type="checkbox" name="gender" value="F" checked="checked">Female</label>
				</c:if>
				</div>
		    </div>
		    <div class="form-group">
				<label for="point">POINT:</label>
				<input type="text" class="form-control" id="point" placeholder="Your Point" required="required" name="point" readonly="readonly" value="${member.point}">
		    </div>
   		    <div class="form-group">
				<img alt="" src="../resources/upload/member/${member.fileName}">
		    </div>
			<a href="./memberUpdate"><input type="button" class="btn btn-default" value="Go Update"></a> 
			<a href="./memberDelete?id=${member.id}" class="btn btn-default">QUIT</a>
		</form>
	</div>
</body>
</html>