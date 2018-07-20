<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,cn.edu.lingnan.dto.GoodsDTO,cn.edu.lingnan.dto.CartItemDTO,cn.edu.lingnan.dao.GoodsDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/table.css" />
<script  type="text/javascript" src="js/show.js"></script>
<script>
	function deleteCheck(){
		var checkObj = document.getElementsByName("check");	
		var arr = [];
		var flag = false;
		for(var i=0; i<checkObj.length; i++){
			if(checkObj[i].checked == true){
				arr.push(checkObj[i].value);
				flag = true;
			}
		}
		if(flag == true){
			if(confirm("您确定要删除这些记录吗?"))
				location.href="deleteCheckWatchCartInfoServlet?arr="+arr;			
		}else
			alert("您至少选择一条记录，再进行删除");					
	}
	
	function updateNum(){
		var x=document.getElementsByName("newNum");
		var arr = [];
		for(var i=0; i<x.length; i++)
			arr.push(x[i].value);
		location.href="updateWatchCartNumServlet?arr="+arr;
	}
</script>
</head>
<body>
	<div id="updateText">
		<div id="logout1">
			<a href="customer.jsp">返回购物大厅</a>&emsp;
			<a href="logoutServlet">注销</a>
		</div>
		<pre> </pre>
			<table border="1">
				<tr>
					<th></th>
					<th>商品名称</th>
					<th>单价</th>
					<th>数量</th>
					<th>操作</th>
				</tr>
				<%
						GoodsDAO g = new GoodsDAO();
						String gname = null;
						int price = 0;
						
						int totalPrice = (Integer)session.getAttribute("totalPrice");
						
						Vector<CartItemDTO> v = new Vector<CartItemDTO>();
						v = (Vector<CartItemDTO>)session.getAttribute("allWatchCartInfo");
						Iterator it = v.iterator();
						CartItemDTO idto = null;
						while(it.hasNext()){
							idto = (CartItemDTO)it.next();
							String gid = idto.getGid();						
							gname = g.findGnameByGid(gid);
							price = g.findPriceByGid(gid);
				%>
				<tr>
					<td><input type="checkbox" name="check" value="<%=idto.getCid()%>,<%=idto.getGid()%>" /></td>
					<td><%=gname%></td>
					<td><%=price%></td>
					<td>
						<input type=hidden name="newNum" value="<%=idto.getCid() %>,<%=idto.getGid() %>"/>
						<input type="text" name="newNum" value="<%=idto.getNum()%>" onFocus="this.value ='';"/>
					</td>
					<td>
						<a href="deleteWatchCartInfoServlet?cid=<%=idto.getCid() %>&gid=<%=idto.getGid() %>">移除购物车</a>
					</td>
				</tr>
				<%
					}
				%>
				<tr>
					<td>总金额:<%=totalPrice %></td>
				</tr>
			</table>
			<pre> </pre>
			<input type="button" name="btn1" value="全选" onClick="allcheck();"/>
			<input type="button" name="btn2" value="不选" onClick="allnotcheck();"/>
			<input type="button" name="btn3" value="反选" onClick="backcheck();"/>
			<input type="button" value="批量移除购物车" onClick="deleteCheck();"/>
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			<input type="submit" value="更新数量" onClick="updateNum()"/>
	</div>

</body>
</html>