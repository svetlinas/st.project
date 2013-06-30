<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout Page</title>
<style>
body {
	background: darkgray;
	text-align: center;
	font-size: 25px;
}

h1 {
	font-size: 50px;
	text-shadow: 0px 0px 5px black;
	color: #D8D8D8;
	margin-left:auto;
	margin-right:auto;
	float: center;
}

a {
	text-decoration: none;
	color: white;
	font-weight: bold;
}
a:hover
{ 
	text-decoration: underline;
}
a:active
{ 
	color: grey;
}
</style>
</head>
<body>
	<h1>Sport Events Organizer</h1>
	<div id="wrap">
		<div id="content">
			<%@ page session="true"%>
			<br /> User '<%=request.getRemoteUser()%>' has been logged out.
			<%
				session.invalidate();
			%>
			<br /> Click <a href="index.html">here</a> to login again. <br />
		</div>
	</div>
</body>
</html>

