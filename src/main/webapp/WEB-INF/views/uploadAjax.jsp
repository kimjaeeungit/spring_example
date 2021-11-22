<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="includes/head.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
</head>
<body>
<div class="uploadDiv">
   <input type="file"  multiple id="files">
   <!-- <button id="btnUpload">서버 전송</button> -->
</div>
<div class="uploadResult">
	<ul class="list-group">
	
	</ul>
</div>
   <script>
   		function showImage(fileCallPath) {
   			$("#pictureModal")
   			.find("img").attr("src","/display?fileName="+fileCallPath)
   			.end().modal("show");
		//	alert(fileCallPath);
		}
   		
     	$(function() {
    	  var cloneObj=$(".uploadDiv").clone(); //부모태그 가져옴(div)
    	  
         var regex = /(.*?)\.(exe|sh|zip|alz)$/;
            var maxSize = 1024 * 1024 * 5;

            
            function checkExtension(fileName, fileSize) {
               if(fileSize >= maxSize) {
                  alert("파일 사이즈 초과");
                  return false;
               }
               if(regex.test(fileName)) {
                  alert("해당 종류의 파일은 업로드 할 수 없습니다.");
                  return false;
               }
               return true;
            }

            function showUploadedFile(resultArr) {
                var str = "";
                for(var i in resultArr) {
                   str += "<li class='list-group-item'>"
                   if(resultArr[i].image) {
                	   //알럿창에 이미지 이름 띄우기
                	  str += "<a href='javascript:showImage(\""+resultArr[i].fullPath+"\")'>"
                      str += "<img src='/display?fileName=" +  resultArr[i].thumb  + "'>";
                	  str += "</a>";
                   } else {
                   str += "<a href='/download?fileName=" + resultArr[i].fullPath + "'>";
                   str += "<i class='fas fa-paperclip'></i> " + resultArr[i].origin + "</a>"
                      
                   }
                   str += "  <small><i data-file='"+ resultArr[i].fullPath +"' data-image='"+resultArr[i].image+"'"
                   str += "class='fas fa-trash-alt text-danger'></i></small></li>";
                }
                $(".uploadResult ul").append(str);
             }

            
            
         $(".uploadDiv").on("change","#files",function() {
            var files = $("#files")[0].files;
            console.log(files);

            var formData = new FormData();
            for(var i in files) {
                     if(!checkExtension(files[i].name, files[i].size)){
                        return false;
                     }
               formData.append("files", files[i]);
            }

            $.ajax("/upload", {
               processData:false,
               contentType:false,
               data:formData,
               dataType:'json',
               type:"POST",
               success:function(result) {
                 // alert(result);
                 console.log(result);
                 $(".uploadDiv").html(cloneObj.html());
                 showUploadedFile(result);
               }
            })
         });
        // $("#pictureModal").modal("show");
        $(".uploadResult").on("click","small i", function(){
        	var $li=$(this).closest("li");
        	$.ajax("/deleteFile", {
        		type : "post",
        		data : {fileName:$(this).data("file"),image:$(this).data("image")}, //data("image")->true ,false들어가있음
        		success : function(result){
						$li.remove();
        		}
        	})
        });
      })
   </script>
       <div class="modal fade" id="pictureModal">
        <div class="modal-dialog  modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Image Detail</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body text-center">
                	<img class="mw-100" src="">
                </div>
            </div>
        </div>
    </div>
   <jsp:include page="includes/foot.jsp"></jsp:include>
</body>
</html>