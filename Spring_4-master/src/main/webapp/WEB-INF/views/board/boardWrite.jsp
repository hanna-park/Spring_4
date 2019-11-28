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
		  <h2>${PageName} Page</h2>
		  <form action="./${board}Write" method="post" id="frm" onsubmit=true enctype="multipart/form-data">
		  
		    <div class="form-group">
		      <label for="name">TITLE</label>
		      <input type="text" class="form-control" id="title" placeholder="Enter TITLE" name="title" value="${dto.title}">
		    </div>
		    
		    <div class="form-group">
		      <label for="writer">WRITER</label>
		      <input type="text" class="form-control" id="writer" value="${member.id}" readonly="readonly" name="writer">
		    </div>
		    
		    <div class="form-group" id="editor">
	      		<label for="comment">Contents:</label>
	      		<textarea class="form-control" rows="5" id="contents" placeholder="Enter contents" name="contents">${dto.contents}</textarea>
	      		
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
	   		
	   		<input type="button" value="add file" class="btn btn-default" id="btn">
		    
		    
		    <button class="btn btn-default" id="submit">SUBMIT</button>
			<a href="./${board}List.jsp" class="btn btn-default">LIST</a>
		  </form>
		</div>
		
		<script type="text/javascript">
		
			$("#test").click(function() {
				alert($("#contents").summernote('code'));
			});
		
			$("#contents").summernote({
				height: 300,
				callbacks:{
					onImageUpload: function(files, editor) {
						uploadFile(files[0],this);
					}, // upload end
					onMediaDelete: function(files, editor) {
						deleteFile(files[0], this);
					} // delete end
					
				} // callback enad
			});
			
			function deleteFile(file, editor) {
				var fileName = $(file).attr("src");
				fileName = fileName.substring(fileName.lastIndexOf("/")+1);
				$.ajax({
					type:"POST",
					url:"./summerFileDelete",
					data:{
						file:fileName
					},
					success:function(data){
						console.log(data);
					}
					
				});
			}
			
			
			function uploadFile(file, editor) {
				var formData = new FormData();
				formData.append('file', file); // 파라미터 추가
				$.ajax({
					data:formData,				
					type:"POST",
					url:"./summerFile",
					enctype:"multipart/form-data",
					contentType:false,
					cache:false,
					processData:false,
					success:function(data){
						data = data.trim();
						data = '../resources/upload/summerFile/'+data;
						$(editor).summernote('insertImage', data);
					}
					
				})
			}
			
		
			var files = $('#files').html();
			$('#files').empty();
			var count = 0;
			
// 			$(".del").click(function() {
// 				alert("del");	
// 			});
			
			$('#btn').click(function() {
				if(count<5){
					$('#files').append(files);
					count++;
				}else {
					alert("5개 이상 금지");
				}
			});
			
			$(".del").click(function() {
				alert("del");	
			});
			
			
			// 1. closest 사용
// 			$('#frm').on('click', '.del', function() {
// 				$(this).closest($('.form-group')).remove();
// 				count--;
// 			});
			
			$('#files').on("click",".del", function() {
			// 2. parent 사용
// 				$(this).parent().parent().remove();
			
			// 3. parents 사용
				$(this).parents(".form-group").remove();
				count--;
				
			});
		
		</script>
	
</body>
</html>