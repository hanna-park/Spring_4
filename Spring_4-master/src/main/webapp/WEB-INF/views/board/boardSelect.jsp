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
	<c:import url="../layout/nav.jsp" />
	<h1>${PageName} Select Page</h1>
	<div class="container">
		<table class="table">
			<thead>
				<tr class="info"><th>NUM</th><th>TITLE</th><th>WRITER</th><th>DATE</th><th>HIT</th></tr>
			</thead>
			<tbody>
				<tr class="active">
					<td>${boardVO.num}</td>
					<td>${boardVO.title}</td>
					<td>${boardVO.writer}</td>
					<td>${boardVO.reg_date}</td>
					<td>${boardVO.hit}</td>
				</tr>
			</tbody>
		</table>
		<div>

			<c:forEach items="${boardVO.files}" var="file">
				<a href="./fileDown?fnum=${file.fnum}">${file.oname}</a>
			</c:forEach>
		
		</div>
		
		<div class="container">
 			<form>
    		<div class="form-group">
      			<label for="comment">Contents:</label>
      			<div class="well" id="contents">${boardVO.contents}</div>
    		</div>
  			</form>
  			<c:if test="${member.id eq dto.writer}">
  			<a href="./${board}Update?num=${boardVO.num}" class="btn btn-default">Update</a>
  			<a href="./${board}Delete?num=${boardVO.num}" class="btn btn-default">Delete</a>
  			</c:if>
  			<c:if test="${board ne 'notice'}" >
  				<a href="./${board}Reply?num=${boardVO.num}" class="btn btn-default">Reply</a>
  			</c:if>
  			<a href="./${board}List?num=${boardVO.num}" class="btn btn-default">List</a>
  			
  		</div>
  		
		
	</div>
	
</body>
</html>