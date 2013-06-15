<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/demo-web-app/css/style.css"
	type="text/css" />
<title>Logout Page</title>
</head>
<body>
	<div id="wrap">
		<div id="content">
			<%@ page session="true"%>
			<br /> User '<%=request.getRemoteUser()%>' has been logged out.
			<%
				session.invalidate();
			%>
			<br /> Click <a href="login.html">here</a> to login. <br />
		</div>
	</div>
</body>
</html>

