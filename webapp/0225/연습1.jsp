<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		border : 1px solid blue;
		margin : 30px auto;
		width : 400px;	
		
	 }
	 
	 td{
	 	height : 30px;
	 	text-align: center;
	 
	 }
	 
	

</style>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("name");
		String mail = request.getParameter("mail");
		String area = request.getParameter("area");

	%>
	<table border="1">
	<tr>
		<td>이름</td>
		<td><%=id %></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><%=mail%></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><%=area %></td>
	</tr>

</table>
	
</body>
</html>