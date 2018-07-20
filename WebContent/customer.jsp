<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*,cn.edu.lingnan.dto.GoodsDTO,cn.edu.lingnan.dto.CartItemDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet"  type="text/css"  href="css/table.css"/>
</head>
<body>
<div id="updateText">
	<div><h1><strong>欢迎进入购物大厅！</strong></h1></div>
	<div id="logout1"><a href="logoutServlet">注销</a></div>
	<pre> </pre>
		<table border="1">
		    <tr><th>商品名称</th><th>单价</th><th>操作</th></tr>
		    <% 	
		    	int cid = (Integer)session.getAttribute("cid");	
		    
		    	int flag = (Integer)session.getAttribute("flag"); //控制只插入一条记录到cart表
		    	System.out.println("customer flag:"+flag); 
				Vector<GoodsDTO> v = new Vector<GoodsDTO>();
				v = (Vector<GoodsDTO>)session.getAttribute("allGoods");
				Iterator it = v.iterator();
				GoodsDTO gd = null;
				CartItemDTO idto = new CartItemDTO();
				String uid = (String)session.getAttribute("userUid");
				System.out.println("userUid:"+uid);
				while(it.hasNext()){
					gd = (GoodsDTO)it.next();			
		   %>
		   <tr>
				<td><%=gd.getGname() %></td>
				<td><%=gd.getPrice() %></td>
				<td>  
					<a href="addCartServlet?uid=<%=uid %>&gid=<%=gd.getGid() %>&flag=<%=flag %>">加入购物车</a>
				</td>
			</tr>	         	            	            	            
		    <%
		        }
		    %>
		</table>
		<pre> </pre>
		<input type="button" value="查看购物车" onclick="watchCart()" />
		<pre id="logout1">注意：如需修改商品购买数量，请移步购物车</pre>
	</div>
	
<script>
	function watchCart(){
		location.href="findWatchCartInfoServlet?cid=<%=cid %>";
	}	
</script>
</body>
</html>