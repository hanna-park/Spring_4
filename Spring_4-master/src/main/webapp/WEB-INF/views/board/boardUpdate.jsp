<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../layout/bootStrap.jsp" />
<c:import url="../layout/summerNote.jsp" />
</head>
<body>
<c:import url="../layout/nav.jsp" />

	<div class="container">
	  <h2>${fn:toUpperCase(PageName)} Update Page</h2>
	  <form action="./${board}Update?num=${dto.num}" method="post" id="frm" onsubmit=true enctype="multipart/form-data">
	  	<input type="hidden" name="num" value="${dto.num}">
	  	
	    <div class="form-group">
	      <label for="name">TITLE</label>
	      <input type="text" class="form-control" id="title" value="${dto.title}" name="title">
	    </div>
	    
	    <div class="form-group">
	      <label for="name">WRITER</label>
	      <input type="text" class="form-control" id="writer" value="${dto.writer}" name="writer" readonly="readonly">
	    </div>
	    
	    <div class="form-group">
      		<label for="comment">Contents:</label>
      		<textarea class="form-control" rows="5" id="contents" placeholder="Enter contents" name="contents"></textarea>
   		</div>
   		
		<div id="files">	   		
	    	<div class="form-group" title="parent" id="a1">
      			<label class="control-label col-sm-2" for="file">File:</label>
      			<div class="col-sm-9">
      				<input type="file" class="form-control" id="file" name="file">
      			</div>
	    	<div class="col-sm-1">
      			<input type="button" name="file" value="del" class= "btn btn-danger del">
   			</div>
			</div>
		</div>	   		
	   		
	   		<input type="button" value="add file" class="btn btn-default ins_file" id="${file.fnum}">
	   		
   		<div>
	   		<c:forEach items="${dto.files}" var="file" >
	   		<div id="f${file.fnum}">
	   			<p>${file.oname} <input type="button" id="${file.fnum}" value="del" class="del_file"> </p>
	   		</div>
	   		</c:forEach>
   		</div>
   		
   		<button type="submit" class="btn btn-default" id="submit">UPDATE</button>
		<a href="./${board}List" class="btn btn-default">LIST</a>
	  </form>
	 </div>
	 
	 <script type="text/javascript">
	 	$("#contents").summernote({
	 		height:300
	 	});
	 	
	 	$("#contents").summernote('code', '${dto.contents}');
	 	
	 	var files = $("#files").html();
	 	var count = ${fn:length(dto.files)};
	 	$("#files").empty();
	 	
	 	// file 추가
	 	$(".ins_file").click(function() {
	 		if(count < 5){
				$("#files").append(files);
				count++;
	 		}else{
	 			alert("최대 5개까지 첨부");
	 		}
		});
	 	
	 	// 추가된 폼 삭제
	 	$("#files").on("click", ".del", function() {
			$(this).parents(".form-group").remove();
			count--;
		})
	 
		// 기존에 추가된 첨부파일 삭제
	 	$(".del_file").click(function() {
	 		var fnum = $(this).attr("id");
	 		
			$.post("./fileDelete",{fnum:fnum}, function(data) {
				data = data.trim();
				if(data == '1'){
					$("#f"+fnum).remove();
					count--;
				}
				alert(data);
			});
		});
	 
	 </script>
	 
	 
	 

</body>
</html>