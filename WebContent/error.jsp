<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="refresh" content="0.5; url=index.html">  
<title>Insert title here</title>
	<script> 
		//取出传回来的参数error并与yes比较
		  var errori ='<%=request.getParameter("error")%>';
		  if(errori=='yes'){
		   		alert("登录失败!用户名或密码错误!");
		  }
	</script>
</head>
<body>

</body>
</html>
