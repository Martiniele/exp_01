<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:useBean id="mybean" class="com.jdbcconn.JdbcConnect"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>显示所有用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<%
		ResultSet rs = mybean.getAllUser();
	%>
	<table border="1" align="center">
		<tr>
			<td><h3>ID</h3></td>
			<td><h3>用户名</h3></td>
			<td><h3>密码</h3></td>
			<td><h3>操作1</h3></td>
			<td><h3>操作2</h3></td>
		</tr>
		<%
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("username");
				String pwd = rs.getString("password");
		%>
		<tr>
			<td><h5><%=id%></h5></td>
			<td><h5><%=name%></h5></td>
			<td><h5><%=pwd%></h5></td>
			<td><h5>
					<form action="edit.jsp" method="post">
						<input type="hidden" name="e_id" value=<%=id%>> <input
							type="submit" value="编辑">
					</form>
				</h5></td>
			<td><h5>
					<form action="del.action" method="post">
						<input type="hidden" name="id" value=<%=id%>> <input
							type="submit" value="删除">
					</form>
				</h5></td>
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<br>
	<br>

	<div align="center">
		<form action="adduser.action" method="post">
			账号<input type="text" name="username"><br> 
			密码<input type="text" name=password><br> 
			<input type="submit" value="添加">
		</form>
		<a href="index.jsp">退出系统</a>
	</div>

</body>
</html>
