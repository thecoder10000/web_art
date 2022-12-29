<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form name="loginForm" method="get" action="index.html" target="_top">
	<center>
				<%
				String strID=request.getParameter("id_");					
				out.println(strID + "님!! 반갑습니다.<br/>");
				%>
				<p align ="center"> <input type="submit" value="로그아웃"> </p>
	</center>
	</form>
</body>
</html>