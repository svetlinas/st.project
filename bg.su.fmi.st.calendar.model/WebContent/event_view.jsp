<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "bg.su.fmi.st.calendar.model.entities.*" %>

<html>
	<% Event e = (Event) request.getAttribute("event"); %>
<head>
<title>An Event Event</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/pagestyle.css" rel="stylesheet" />
</head>

<body>
	<div class="events">
		<h1><%= e.getTitle() %></h1>
		<form name="viewEvent" action="events" method="post">
			<div class="c1">

				<table border="1">
					<tr>
						<td>Event Owner</td>
						<td><%= e.getOrganizer().getName() %></td>
					</tr>
					<tr>
						<td>Event Place</td>
						<td><%= e.getPlace() %></td>
					</tr>
					<tr>
						<td>Event StartDate</td>
						<td><%= e.getStartDate() %></td>
					</tr>
					<tr>
						<td>Event EndDate</td>
						<td><%= e.getEndDate() %></td>
					</tr>
					<tr>
						<td>Event Details</td>
						<td><%= e.getDetails() %></td>
					</tr>
				</table>

				<form name="newInvitation" action="index.html" method="get">
					<div class="c1">
						User Id: <br> <input type="text" size=30 name="title">
						<input type="submit" value="Send Invitation">
					</div>
				</form>
			</div>
		</form>
		<br>
	</div>
</body>
</html>