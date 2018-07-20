<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,cn.edu.lingnan.dto.UserDTO"%>
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
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

<link rel="stylesheet"  type="text/css"  href="../css/table.css"/>
<script  type="text/javascript" src="../js/show.js"></script>

</head>
<body>
    <div id="wrapper"> 
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="updateUserInfo.jsp"><i class="fa fa-comments"></i> <strong>MASTER </strong></a>
            </div>
			<ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a href="../logoutServlet">
                        <i class="fa fa-sign-out fa-fw"></i>注销&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </a>
                </li>
            </ul>
		</nav>
        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <a href="findAllUserInfoServlet" class="active-menu"><i class="fa fa-user fa-fw"></i></i> 用户管理</a>
                    </li>

                    <li>
                        <a href="findAllGoodsInfoServlet"><i class="fa fa-qrcode"></i> 商品管理</a>
                    </li>
		    
		    <li>
                        <a href="#"><i class="fa fa-sitemap"></i>预销售管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="findAllSaleInfoServlet">预销售记录管理</a>
                            </li>
                            <li>
                                <a href="findAllSaleStatInfoServlet">预销售情况统计</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
				<div class="row">
	                <div class="col-md-12">
	                    <!-- Advanced Tables -->
	                    <div class="panel panel-default">
	                        <div class="panel-body">
	                            <div class="table-responsive">
	                            	<form action="updateUserInfoServlet">
		                           	 	<table class="table table-striped table-bordered table-hover" id="dataTables-example">
		                           	 		<thead>
		                           	 			<tr>
		                           	 				<th>ID</th>
													<th>姓名</th>
													<th>密码</th>
													<th>权限</th>
													<th></th>		                           	 			
		                           	 			</tr>
		                           	 		</thead>
		                           	 		<%
												String uid = request.getParameter("uid"); //前面一个页面传过来的参数
												Vector<UserDTO> v = new Vector<UserDTO>();
												v = (Vector<UserDTO>)session.getAttribute("alluser");
												Iterator it = v.iterator();
												UserDTO ud = null;
												while(it.hasNext()){
													ud = (UserDTO)it.next();
													if((ud.getUid()).equals(uid)){																	
											%>
											<tbody>
												<tr>
													<td><input type="text" name="uid" value=<%=ud.getUid() %>  readonly></td>
													<td><input type="text" name="uname" value=<%=ud.getUname() %>></td>
													<td><input type="text" name="password" value=<%=ud.getPassword()%>></td>
													<td><input type="text" name="superuser" value=<%=ud.getSuperuser()%>></td>
													<td><input type="submit" value="确认修改"></td>
												</tr>
											</tbody>	
											<% 
												}} 	
											%>
		                           	 	</table>
	                           	 	</form>
	                           	 	<pre>    </pre>
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