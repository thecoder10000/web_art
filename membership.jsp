<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style>
			h1 {
				border-color: orange plum lightyellow lime;
				border-style: solid;
				border-width: 3px;
				background-color: lavender;
				width:600px;
			}
		</style>
</head>
<body>
<br><br><br>
		<center>
		<h1>회원가입이 완료되었습니다.</h1>
		
		<%
		String strID=request.getParameter("ID");
		out.println(strID + "님!! 반갑습니다.<br/>저희 홈페이지를 이용해주셔서 감사합니다." );
		%>
		
		</center>
</body>
</html>