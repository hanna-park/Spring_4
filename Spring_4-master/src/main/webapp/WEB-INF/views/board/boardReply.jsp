<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../layout/bootStrap.jsp"></c:import>
<c:import url="../layout/summerNote.jsp"></c:import>
</head>
<body>
	<c:import url="../layout/nav.jsp" />
		<div class="container">
		  <h2>QnA</h2>
		  <form action="./qnaReply" method="POST" id="frm" onsubmit=true>
		  	<input type="hidden" name="num" value="${dto.num}">
		    <div class="form-group">
		      <label for="name">TITLE</label>
		      <input type="text" class="form-control" id="title" placeholder="Enter TITLE" name="title" value="${dto.title}">
		    </div>
		    
		    <div class="form-group">
		      <label for="writer">WRITER</label>
		      <input type="text" class="form-control" id="writer" placeholder="Enter your ID or name" name="writer" value="${dto.writer}">
		    </div>
		    
		    <div class="form-group">
	      		<label for="comment">Contents:</label>
	      		<textarea class="form-control" id="contents" placeholder="Enter your text" name="contents">${dto.contents}</textarea>
	   		</div>
		    
		    
		    <button class="btn btn-default" id="submit">SUBMIT</button>
			<a href="./qnaList.jsp" class="btn btn-default">LIST</a>
		  </form>
		</div>
	<script type="text/javascript">
		$("#contents").summernote({
			height: 300
		});	
		
	
	</script>
</body>
</html>