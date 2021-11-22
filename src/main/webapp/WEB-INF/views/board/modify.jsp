<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <jsp:include page="../includes/head.jsp"/>
 <sec:csrfMetaTags/>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->s
       
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
 			<!-- Topbar -->
            <jsp:include page="../includes/header.jsp"/>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Board Modify Page</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Board Modify Page</h6>
                        </div>
                        <div class="card-body">
                        <form method="post">
                       			 <div class="form-group">
                        			<label for="bno" class="text-dark font-weight-bold">bno</label>
                        			<input class="form-control" id="bno" name="bno" readonly value="${board.bno}">
                      			</div>
                        		<div class="form-group">
                        			<label for="title" class="text-dark font-weight-bold">Title</label>
                        			<input class="form-control" id="title" name="title" value="${board.title}">
                      			</div>
                      			<div class="form-group">
                        			<label for="content" class="text-dark font-weight-bold">Content</label>
                        			<textarea rows="10" class="form-control" id="content" name="content">${board.content}</textarea>
                      			</div>
                      			<div class="form-group">
                        			<label for="writer" class="text-dark font-weight-bold" >Writer</label>
                        			<input class="form-control" id="writer" name="writer" value="${board.writer}">
                      			</div>
                      			<input type="hidden" name="pageNum" value="${cri.pageNum}">
                      			<input type="hidden" name="amount" value="${cri.amount}">
                      			<input type="hidden" name="type" value="${cri.type}">
                      			<input type="hidden" name="keyword" value="${cri.keyword}">
                      			<sec:authentication property="principal" var="pinfo"/>
                                <sec:authorize access="isAuthenticated()">
                                <c:if test="${pinfo.username==board.writer }">
                              	<sec:csrfInput/>
                      			<button class="btn btn-warning" formaction="modify" id="btnSubmit">Modify</button>
                      			<button class="btn btn-danger" formaction="remove">Remove</button>
                      			</c:if>
                      			</sec:authorize>
                      			
                      			<a class="btn btn-primary" href="list">List</a>
                    	   </form>
                        </div>
                    </div>
                    <h1></h1>
			 		<div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">File Attach</h6>
                        </div>
                        <div class="card-body">
                        	<div class="form-group uploadDiv">
                       			<label for="files" class="btn btn-primary px-4"><i class='far fa-file mr-2'></i> File</label>
                       			<input type="file" class="form-control d-none" id="files" name="files" multiple>
                     		</div>
                   			<div class="uploadResult">
								<ul class="list-group">
								</ul>
                      	    </div>
						</div>
                     </div>
                </div>
                <!-- /.container-fluid -->
			
            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
  	<jsp:include page="../includes/footer.jsp"/>     
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
    <script>
  		//get방식을 제외한 비동기처리에 필요함
	    var csrfHeader=$("meta[name='_csrf_header']").attr("content");
		var csrfToken=$("meta[name='_csrf']").attr("content");
		
		$(document).ajaxSend(function (e,xhr) {
			xhr.setRequestHeader(csrfHeader,csrfToken);
		})
    
   		function showImage(fileCallPath) {
   			$("#pictureModal")
   			.find("img").attr("src","/display?fileName="+fileCallPath)
   			.end().modal("show");
		}
   	 $(function() {
         var bno = '${board.bno}';
         var cloneObj = $(".uploadDiv").clone();
         
         
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
                //이미지 업로드 후 콘솔창 확인
                for(var i in resultArr) {
                   str += "<li class='list-group-item' "
                   str += "data-uuid='" + resultArr[i].uuid + "' ";
                   str += "data-path='" + resultArr[i].path + "' ";
                   str += "data-origin='"+ resultArr[i].origin + "' ";
                   str += "data-size='"+ resultArr[i].size + "' "
                   str += "data-image='"+ resultArr[i].image + "' "
                   str += "data-mime='"+ resultArr[i].mime + "' "
                   str += "data-ext='"+ resultArr[i].ext + "' "
                   str += "data-ext='"+ resultArr[i].ext + "' "
                   str += ">"
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
        	if(confirm("Remove this file?")){
        		$li.remove();
        	}
        	/* $.ajax("/deleteFile", {
        		type : "post",
        		data : {fileName:$(this).data("file"),image:$(this).data("image")}, //data("image")->true ,false들어가있음
        		success : function(result){
						$li.remove();
        		}
        	}) */
        });
        //글작성 이밴트
        $("#btnSubmit").click(function() {
        	 event.preventDefault();
        	 var str="";
        	var datas=["uuid","path","origin","ext","mime","size","image"];
        	//datas[0]
        	$(".uploadResult li").each(function(i) {//(function(i) li반복문의 i
        		//여기디스.uploadResult li
        		for(var j in datas)//7번돌음
        		//for(var j =0;datas.length;j++)
				str+="<input type='hidden' name='attachs["+i+"]." + datas[j] + "' value='"+$(this).data(datas[j])+"'>";
			});
        	//여기디스 btnSubmit
        	$(this).closest("form").append(str).submit(); 
        	//console.log($(this).closest("form").append(str).html());
 	     })
 	     //첨부파일 불러오기
         $.getJSON("/board/getAttachs/"+bno).done(function(data) {
        	 console.log(data);
        	 showUploadedFile(data);
		})
      })
   </script>
    <jsp:include page="../includes/foot.jsp"/>     
</body>

</html>