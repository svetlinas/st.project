<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!doctype html>

<%@ page import="bg.su.fmi.st.calendar.model.entities.*"%>
<%@ page import="bg.su.fmi.st.calendar.servlet.events.*"%>
<%@ page import="java.util.*"%>

<html>
<%
	Event e = (Event) request.getAttribute(EventUtils.ATTRIBUTE_EVENT);
%>

<head>
<title>Edit Event</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/pagestyle.css" rel="stylesheet" />
</head>

<body>
	<div class="events">
		<h1>Edit event "<%=e.getTitle()%>"</h1>
		<form name="edit_event" action="events" method="put">
			<div class="c1">
				<table>
					<tr align="right">
						Event Title:
						<br>
						<input type="text" size=30 name="title" value=<%=e.getTitle()%>>
						<br> Event Place:
						<br>
						<input type="text" size=30 name="place" value=<%=e.getPlace()%>>
						<br> Event Starts:
						<br>
						<input type="datetime-local" size=30 name="startDate" value=<%=e.getHtmlDateStartDate()%>>
						<br> Event Ends:
						<br>
						<input type=datetime-local size=30 name="endDate" value=<%=e.getHtmlDateEndDate()%>>
						<br> Event Type:
						<br>
						<input type="radio" name="type" value="private" 
						<%
							if(e.getType().equals("private")){
						%>
							checked="checked"
						<%} %>
						>Private
						
						<input type="radio" name="type" value="public"
						<%
							if(e.getType().equals("public")){
						%>
							checked="checked"
						<%} %>>Public
						<br> Event Details:
						<br>
						<textarea name="details" rows="4" cols="50"><%=e.getDetails()%></textarea>
						<br>
						<input type="submit" value="Submit">
					</tr>
				</table>
			</div>
		</form>
		<br>
	</div>
</body>
</html>