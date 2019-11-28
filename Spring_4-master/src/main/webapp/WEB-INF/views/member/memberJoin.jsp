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
		<h2>Member Join Form</h2>
		<form action="./memberJoin" id="frm" method="post" enctype="multipart/form-data">
			<div class="form-group" id="result">
				<label for="id" class="form-group" id="check">ID:</label>
				<input type="text" class="form-inline" id="id" placeholder="Enter ID" required="required" name="id">
		    	<div id="checkIdResult"></div>
			</div>	
		    <div class="form-group">
				<label for="pw">PW:</label>
				<input type="password" class="form-control" id="pw" placeholder="Enter PW" required="required" name="pw">
		    </div>
		    <div class="form-group">
				<label for="pw">2nd PW:</label>
				<input type="password" class="form-control" id="pw" placeholder="Enter PW" required="required" name="pw2">
		    </div>
		    <div class="form-group">
				<label for="name">NAME:</label>
				<input type="text" class="form-control" id="name" placeholder="Enter NAME" required="required" name="name">
		    </div>
		    <div class="form-group">
				<label for="email">email:</label>
				<input type="email" class="form-control" id="email" placeholder="Enter E-MAIL" required="required" name="email">
		    </div>
		    <div class="form-group">
				<label for="birth">BIRTH:</label>
				<input type="date" class="form-control" id="birth" placeholder="Enter BIRTH DAY" required="required" name="birth">
		    </div>
		    <div class="form-group">
				<label for="gender">GENDER:</label>
				<div class="checkbox">
					<label><input type="checkbox" name="gender" value="M">Male</label>
					<label><input type="checkbox" name="gender" value="F">Female</label>
				</div>
		    </div>
	    	<div class="form-group">
				<label for="file">FILE:</label>
				<input type="file" class="form-control" id="file" required="required" name="file">
		    </div>
			<input type="button" id="join" class="btn btn-default" value="join">
		</form>
	</div>
	
	<script type="text/javascript">
		var idCheck = false; // false : 중복된 아이디 혹은 중복검사를 하지 않은 경우
							 // true  : 중복되지 않은 아이디
		$('#join').click(function() {
			if(idCheck){
				$('#frm').submit();
			}else{
				$('#frm').reset();
			}
		});
		
		$('#id').blur(function() {
			var id = $(this).val();
			$.post("./memberIdCheck", {id:id}, function(data) {
				data = data.trim();
				if(data=='pass'){
					$('#checkIdResult').html('사용가능 ID');
					$('#checkIdResult').attr("class", "text-success");
					idCheck = true;
				}else{
					$('#checkIdResult').html('중복된 ID');
					$('#checkIdResult').attr("class", "text-danger");
					idCheck = false;
					$('#id').val("");
					$('#id').focus();
				}
				
			});
		});
		
	
	
	</script>
</body>
</html>