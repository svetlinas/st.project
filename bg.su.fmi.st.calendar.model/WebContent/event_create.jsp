<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="bg.su.fmi.st.calendar.model.entities.*"%>
<%@ page import="bg.su.fmi.st.calendar.servlet.events.*"%>
<%@ page import="java.util.*"%>

<html>

<head>
<title>Create Event</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/pagestyle.css" rel="stylesheet" />
</head>

<body>
	<div class="events">
		<h1>Create Event</h1>
		<form name="createevent" action="createEventController" method="GET">
			<div class="c1">
				<table>
					<tr align="right">
						Event Title:
                        <br>
                        <input type="text" size=30 name=<%=EventUtils.PARAMETER_EVENT_TITLE %> >
                        <br> Event Place:
                        <br>
                        <input type="text" size=30 name=<%=EventUtils.PARAMETER_EVENT_PLACE %>>
                        <br> Event Starts:
                        <br>
                        <input type="datetime-local" size=30 name=<%=EventUtils.PARAMETER_EVENT_START_DATE %>>
                        <br> Event Ends:
                        <br>
                        <input type=datetime-local size=30 name=<%=EventUtils.PARAMETER_EVENT_END_DATE %> >
                        <br> Event Type:
                        <br>
                        <input type="radio" name=<%=EventUtils.PARAMETER_EVENT_TYPE %> value="private" 
                        >Private
                        <input type="radio" name=<%=EventUtils.PARAMETER_EVENT_TYPE %> value="public"
                        >Public
                        <br> Event Details:
                        <br>
                        <textarea name=<%=EventUtils.PARAMETER_EVENT_DETAILS %> rows="4" cols="50"></textarea>
                        <br>
                        <input type="submit" name="submitButton" value="Submit">
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>