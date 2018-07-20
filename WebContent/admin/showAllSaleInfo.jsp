<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*, cn.edu.lingnan.dto.CartDTO, java.util.*,cn.edu.lingnan.dto.CartItemDTO"%>
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
	function allcheck1() {
		var checkObj = document.getElementsByName("check1");
		for (var i = 0; i < checkObj.length; i++)
			checkObj[i].checked = true;
	}

	function allnotcheck1() {
		var checkObj = document.getElementsByName("check1");
		for (var i = 0; i < checkObj.length; i++)
			checkObj[i].checked = false;
	}

	function backcheck1() {
		var checkObj = document.getElementsByName("check1");
		for (var i = 0; i < checkObj.length; i++)
			if (checkObj[i].checked == true)
				checkObj[i].checked = false;
			else
				checkObj[i].checked = true;
	}
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
				location.href = "deleteCheckCartInfoServlet?arr=" + arr;
		} else
			alert("您至少选择一条记录，再进行删除");
	}

	function deleteCheck1() {
		var checkObj = document.getElementsByName("check1");
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
				location.href = "deleteCheckCartItemInfoServlet?arr=" + arr;
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
						class="fa fa-qrcode"></i> 商品管理</a></li>

				<li><a href="#" class="active-menu"><i class="fa fa-sitemap"></i>预销售管理<span
						class="fa arrow"></span></a>
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
				                        <h1 class="page-header"><strong>用户预购买情况</strong></h1>
				                    </div>
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th></th>
												<th>销售ID</th>
												<th>用户ID</th>
												<th>消费总金额</th>
												<th></th>
											</tr>
										</thead>
										<%
											Vector<CartDTO> v = new Vector<CartDTO>();
											v = (Vector<CartDTO>) session.getAttribute("allCart");
											Iterator it = v.iterator();
											CartDTO cd = null;
											while (it.hasNext()) {
												cd = (CartDTO) it.next();
										%>
										<tbody>
											<tr>
												<td><input type="checkbox" name="check" value=<%=cd.getCid()%> /></td>
												<td><%=cd.getCid() %></td>
												<td><%=cd.getUid()%></td>
												<td><%=cd.getTotalPrice()%></td>
												<td><a
													href="deleteCartInfoServlet?cid=<%=cd.getCid()%>">删除</a> <a
													href="updateCartInfo.jsp?cid=<%=cd.getCid()%>">修改</a></td>
											</tr>
										</tbody>
										<%
											}
										%>
									</table>

									<input type="button" name="btn1" value="全选" onClick="allcheck();" /> 
									<input type="button" name="btn2" value="不选" onClick="allnotcheck();" /> 
									<input type="button" name="btn3" value="反选" onClick="backcheck();" /> 
									<input type="button" value="删除" onClick="deleteCheck();" />
									<pre>  </pre>
									<pre>  </pre>
									<pre>  </pre>

									<div class="col-md-12">
                        				<h1 class="page-header"><strong>商品预销售情况</strong></h1>
                    				</div>
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th></th>
												<th>销售ID</th>
												<th>商品ID</th>
												<th>购买商品数量</th>
												<th></th>
											</tr>
										</thead>
										<%
											Vector<CartItemDTO> v1 = new Vector<CartItemDTO>();
											v1 = (Vector<CartItemDTO>) session.getAttribute("allCartItem");
											Iterator it1 = v1.iterator();
											CartItemDTO idto = null;
											while (it1.hasNext()) {
												idto = (CartItemDTO) it1.next();
										%>
										<tbody>
											<tr>
												<td><input type="checkbox" name="check1" value="<%=idto.getCid()%>,<%=idto.getGid()%>" /></td>
												<td><%=idto.getCid()%></td>
												<td><%=idto.getGid()%></td>
												<td><%=idto.getNum()%></td>
												<td><a
													href="deleteCartItemInfoServlet?cid=<%=idto.getCid()%>&gid=<%=idto.getGid()%>">删除</a>
													<a
													href="updateCartItemInfo.jsp?cid=<%=idto.getCid()%>&gid=<%=idto.getGid()%>">修改</a>
												</td>
											</tr>
										</tbody>
										<%
											}
										%>
									</table>

									<input type="button" name="btn4" value="全选"
										onClick="allcheck1()" /> <input type="button" name="btn5"
										value="不选" onClick="allnotcheck1();" /> <input type="button"
										name="btn6" value="反选" onClick="backcheck1();" /> <input
										type="button" value="删除" onClick="deleteCheck1();" />
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