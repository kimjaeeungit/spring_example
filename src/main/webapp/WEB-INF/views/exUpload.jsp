<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<input type="file" multiple id="files">
	<button id="btnUpload">서버전송</button>
	<script>
		$(function(){
			$("#btnUpload").click(function(){
				var files = $("#files")[0].files
				console.log(files); 

				var formData= new FormData();
				for(var i in files){
					formData.append("files", files[i]);
				}

				$.ajax("/upload",{
					processData:false,
					contentType:false,
					data:formData,
					type:"POST",
					success:function(result){
						alert("uploaded");
					}
				})
			});
		})
	</script>
</body>
</html>