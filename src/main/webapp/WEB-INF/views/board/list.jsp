<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
 <jsp:include page="../includes/head.jsp"/>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
       
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
					
              
             

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary float-left mt-2">Board List Page</h6>
                            <a href="editor" class="btn btn-sm btn-primary float-right ">Editor new Board</a>
                            <a href="register" class="btn btn-sm btn-primary float-right ">Register new Board</a>
                        </div>
                        <div class="card-body">
                            <div class="table dataTables_wrapper">
                            	<div class="row"><div class="col-sm-12 col-md-6"><div class="dataTables_length" id="dataTable_length">
                            	<label>Show <select name="dataTable_length" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm">
                            	<option value="10">10</option>
                            		<option value="25">25</option>
                            		<option value="50">50</option>
                            		<option value="100">100</option>
                            		</select> entries
                            		</label>
                            		</div>
                            		</div>
                            		<div class="col-sm-12 col-md-6 d-flex justify-content-end">
                            		<form class="form-inline">
									<select name="type" class="custom-select custom-selet-sm form-control form-control-sm ">
										<option value="T">??????</option>
										<option value="C">??????</option>
										<option value="W">?????????</option>
									</select>
                            		<div id="dataTable_filter" class="input-group">
                            		<input name= "keyword" type="search" class="form-control form-control-sm" placeholder="search.." aria-controls="dataTable">
                            		<div class="input-group-append">
		                               <button class="btn btn-primary btn-sm">
		                                   <i class="fas fa-search fa-sm"></i>
		                               </button>
		                           </div>
                            		</div>
                            		<input type="hidden" name="pageNum" value="${page.cri.pageNum}">
                            		<input type="hidden" name="pageNum" value="${page.cri.amount}">
                            		</form>
                            		</div>
                            		</div>
                            	<div class="row">
                            	<div class="col-sm-12">
                                 <table class="table table-bordered" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>??????</th>
                                            <th>??????</th>
                                            <th>?????????</th>
                                            <th>?????????</th>
                                            <th>?????????</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${list}" var= "board">
                                       <tr>
                                          <td><c:out value="${board.bno}"/></td>
                                          <td><a href="get${page.cri.params}&bno=${board.bno}"><c:out value="${board.title}"/><b>[${board.replyCnt}]</b></a></td>
                                          <td><c:out value="${board.writer}"/></td>
                                          <td><fmt:formatDate value="${board.regDate}" pattern="yy-MM-dd"/></td>
                                          <td><fmt:formatDate value="${board.updateDate}" pattern="yy-MM-dd"/></td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                </div>
                                </div>
                             	<div class="row">
                             		<div class="col-sm-12 col-md-5">
                             		</div>
                             		<div class="col-sm-12 col-md-7">
                             			<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                             				<ul class="pagination">
                             					 <li class="paginate_button page-item previous ${page.prev ? '' : 'disabled'}" id="dataTable_previous">
                                            <a href="list?pageNum=${page.startPage-1}&amount=${page.cri.amount}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
                                           </li>
                                            <c:forEach begin="${page.startPage}" end="${page.endPage}" var="p">
                                           <li class="paginate_button page-item ${p == page.cri.pageNum ? 'active' : ''}">
                                              <a href="list?pageNum=${p}&amount=${page.cri.amount}" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">${p}</a>
                                        </li>
                                        </c:forEach>
                                        <li class="paginate_button page-item ${page.next ? '' : 'disabled' }" id="dataTable_next">
                                           <a href="list?pageNum=${page.endPage+1}&amount=${page.cri.amount}" aria-controls="dataTable" data-dt-idx="3" tabindex="0" class="page-link">Next</a>
                                          </li>
                             				</ul>
                             			</div>
                             		</div>
                             	</div>
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
    $(function(){
    	var result = '${result}';
    	checkModal(result);
    	history.replaceState({},null,null);
    	function checkModal(result){
    		if(!result || history.state){
    			return;
    		}
    		var text = result == 'success' ? "????????? ?????????????????????.":"?????????" + result + "?????? ?????????????????????.";
   			$("#myModal .modal-body").text(text);
   			$("#myModal").modal("show");
    	}
    });
    
    </script>
     <!-- List Modal-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Message</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">??</span>
                    </button>
                </div>
                <div class="modal-body"></div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../includes/foot.jsp"/>     
</body>

</html>