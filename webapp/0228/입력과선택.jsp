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
	}
	td {
		width : 200px;
		height : 50px;
	    text-align : center;
	}
</style>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String area = request.getParameter("area");
	String car = request.getParameter("car");
	
	String[] foods = request.getParameterValues("food");
	
	String str = "";
	for(int i=0; i< foods.length; i++){
		str += foods[i] + "&nbsp;&nbsp;&nbsp;";
	}
	//area에는 enter기호가- \r\n- 삽입되어 있다.
	//브라우저에 출력할때는 <br>태그로 변경되어야 한다.
	area = area.replaceAll("\r","").replaceAll("\n", "<br>");
	
	/* out.print("<p>아이디 :" + id + "<p>");
	out.print("<p>비밀번호 :" + password + "<p>");
	out.print("<p>소개 : <br>" + area + "<p>"); */
	
	/* out.print("<table border='1'>");
	
	out.print("<tr><td>아이디</td>");
	out.print("<td>" + id + "</td></tr>");
	
	out.print("<tr><td>비밀번호</td>");
	out.print("<td>" + password + "</td></tr>");

	out.print("<tr><td>소개</td>");
	out.print("<td>" + area + "</td></tr>");
	
	out.print("</table>"); */
%>

<table border="1">
	<tr>
		<td>아이디</td>
		<td><%=id %></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><%=password%></td>
	</tr>
	<tr>
		<td>소개</td>
		<td><%=area %></td>
	</tr>
	<tr>
		<td>자동차</td>
		<td><%=car %></td>
	</tr>
	<tr>
		<td>좋아하는 음식</td>
		<td><%=str %></td>
	</tr>
</table>

</body>
</html>