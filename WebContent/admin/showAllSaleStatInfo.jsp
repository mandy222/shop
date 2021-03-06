<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*, cn.edu.lingnan.dto.SaleStatDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Insert title here</title>
<!-- Bootstrap Styles-->
<link href="../css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="../css/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->
<link href="../js/morris/morris-0.4.3.min.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="../css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />

<link rel="stylesheet" type="text/css" href="../css/table.css" />
<script type="text/javascript" src="../js/show.js"></script>
<script>
	function deleteCheck() {
		var checkObj = document.getElementsByName("check");
		var arr = [];
		var flag = false;
		for (var i = 0; i < checkObj.length; i++) {
			if (checkObj[i].checked == true) {
				arr.push(checkObj[i].value);
				flag = true;
			}
		}
		if (flag == true) {
			if (confirm("您确定要删除这些记录吗?"))
				location.href = "deleteCheckSaleStatInfoServlet?arr=" + arr;
		} else
			alert("您至少选择一条记录，再进行删除");
	}
</script>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="showAllUserInfo.jsp"><i
				class="fa fa-comments"></i> <strong>MASTER </strong></a>
		</div>

		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a href="../logoutServlet"> <i
					class="fa fa-sign-out fa-fw"></i>注销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</a></li>
		</ul>
		</nav>

		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">
				<li><a href="showAllUserInfo.jsp"><i
						class="fa fa-user fa-fw"></i></i> 用户管理</a></li>

				<li><a href="findAllGoodsInfoServlet"><i
						class="fa fa-qrcode"></i>商品管理</a></li>

				<li><a href="#" class="active-menu"><i class="fa fa-sitemap"></i>预销售管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="findAllSaleInfoServlet">预销售记录管理</a></li>
						<li><a href="findAllSaleStatInfoServlet">预销售情况统计</a></li>
					</ul></li>
			</ul>
		</div>
		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<!-- Advanced Tables -->
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="table-responsive">	
									<div class="col-md-12">
                        				<h1 class="page-header"><strong>预销售情况统计</strong></h1>
                    				</div>							
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th></th>
												<th>商品ID</th>
												<th>销售数量</th>
												<th></th>
											</tr>
										</thead>
										<%
											Vector<SaleStatDTO> v = new Vector<SaleStatDTO>();
											v = (Vector<SaleStatDTO>) session.getAttribute("allSaleStat");
											Iterator it = v.iterator();
											SaleStatDTO sd = null;
											while (it.hasNext()) {
												sd = (SaleStatDTO) it.next();
										%>
										<tbody>
											<tr>
												<td><input type="checkbox" name="check"
													value="<%=sd.getGid()%>" /></td>
												<td><%=sd.getGid()%></td>
												<td><%=sd.getSaleNum()%></td>
												<td><a
													href="deleteSaleStatInfoServlet?gid=<%=sd.getGid()%>">删除</a>
												</td>
											</tr>
										</tbody>
										<%
											}
										%>
									</table>

									<input type="button" name="btn1" value="全选"
										onClick="allcheck();" /> <input type="button" name="btn2"
										value="不选" onClick="allnotcheck();" /> <input type="button"
										name="btn3" value="反选" onClick="backcheck();" /> <input
										type="button" value="删除" onClick="deleteCheck();" />
									<pre>  </pre>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- JS Scripts-->
	<!-- jQuery Js -->
	<script src="../js/jquery-1.10.2.js"></script>
	<!-- Bootstrap Js -->
	<script src="../js/bootstrap1.min.js"></script>

	<!-- Metis Menu Js -->
	<script src="../js/jquery.metisMenu.js"></script>
	<!-- Morris Chart Js -->
	<script src="../js/morris/raphael-2.1.0.min.js"></script>
	<script src="../js/morris/morris.js"></script>

	<script src="../js/easypiechart.js"></script>
	<script src="../js/easypiechart-data.js"></script>

	<!-- Custom Js -->
	<script src="../js/custom-scripts.js"></script>
</body>
</html>