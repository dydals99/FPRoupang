<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="../../css/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>      
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>판매차단목록</title>

<!-- Custom fonts for this template-->
<link rel="stylesheet" href="../../css/all.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link rel="stylesheet" href="../../css/sb-admin-2.min.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
td, th {
	text-align: center;
	/* center checkbox horizontally */
	vertical-align: middle;
	/* center checkbox vertically */
}
</style>
<script>
	$(document).on('click', '#checkAll', function() {
		if ($('#checkAll').is(':checked')) {
			$('.checkbox_group').prop('checked', true);
		} else {
			$('.checkbox_group').prop('checked', false);
		}
	});

	$(function(){
		$("#approveSell").click(function(){
			var approve_val=[];
			$("[name=public1]").each(function(idx){
				if($(this).is(":checked")==true){
					approve_val.push($(this).val());
					console.log(approve_val);
				}
			});
			if(approve_val.length==0){
				alert("승인할 판매자 계정을 하나 이상 선택해주세요.");
				return false;
			}
			if(confirm("선택한 "+approve_val.length+"개의 계정을 승인하시겠습니까?")){
					location.href="/appOk.do?value="+approve_val;
					alert(approve_val);
			}
         
	
		});
	});
		
		
</script>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<%@include file="../sidebar.jsp"%>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->

		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<%@include file="../topbar.jsp"%>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				
				<div class="container-fluid">
					<h2>판매자차단목록</h2>
					<form id="block" method="post">
						<table class="table table-hover">
							<tr>
							<th>아이디</th>
							<th>상호</th>
							<th>이름</th>
							<th>신청일</th>
							<th></th>
							<th>전체선택 <input class="checkbox_group" type="checkbox"
								name="checkAll" id="checkAll" />
							</th>
							</tr>
							<c:forEach items="${lists }" var="row" varStatus="loop">
								<tr>
									<td>${row.email }</td>
	
									<td>${row.name }</td>
									<td>${row.company_name }</td>
									<td>${row.regidate }</td>
	
									<td><a href="blockView.do?member_idx=${row.member_idx }">상세보기</a>
									</td>
									<td><input style='zoom: 1.5;' class="checkbox_group" name="public1"
										type="checkbox" value="${row.member_idx }"
										id="flexCheckDefault"></td>
								</tr>
							</c:forEach>
							<tr style="border-bottom: hidden;">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<button class="btn btn-outline-secondary" id="approveSell" type="button">승인하기</button>
								</td>
							</tr>
						</table>
					</form>
		<%-- <table border="0">
		<tr>
			<th>아이디</th>
			<th>상호</th>
			<th>이름</th>
			<th>신청일</th>
			
			<th></th>
		</tr>
		<c:forEach items="${lists }" var="row" varStatus="loop">
		<tr>
			<td>${row.email }</td>
		
			<td>${row.name }</td>
			<td>${row.company_name }</td>
			<td>${row.regidate }</td>
		
			<td>
				<a href="blockView.do?member_idx=${row.member_idx }">상세보기</a>

				<a href="appOk.do?member_idx=${row.member_idx }">승인</a>
			</td>
		</tr>
		</c:forEach>
	</table> --%>
	<a href="/admin/index.do">관리자 홈으로가기</a>
				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">Ã</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="../../css/vendor/jquery/jquery.min.js"></script>
	<script src="../../css/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../../css/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../../js/sb-admin-2.min.js"></script>

</body>

</html>