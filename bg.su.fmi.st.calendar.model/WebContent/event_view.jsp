<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="bg.su.fmi.st.calendar.model.entities.*"%>
<%@ page import="java.util.*"%>
<%@ page import="bg.su.fmi.st.calendar.servlet.events.*"%>

<html>
<%
	Event e = (Event) request.getAttribute(EventUtils.ATTRIBUTE_EVENT);
	List invitationList = (List) request
	      .getAttribute(EventUtils.ATTRIBUTE_EVENT_INVITATION_LIST);
%>
<head>
<title>An Event Event</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/pagestyle.css" rel="stylesheet" />
</head>

<body>
	<div>
		<h1><%=e.getTitle()%></h1>
		<form name="viewEvent">
			<div class="c1">
				<table border="1">
					<tr>
						<td><b>Event Owner</b></td>
						<td><%=e.getOrganizer().getName()%></td>
					</tr>
					<tr>
						<td><b>Event Place</b></td>
						<td><%=e.getPlace()%></td>
					</tr>
					<tr>
						<td><b>Event StartDate</b></td>
						<td><%=e.getStartDate()%></td>
					</tr>
					<tr>
						<td><b>Event EndDate</b></td>
						<td><%=e.getEndDate()%></td>
					</tr>
					<tr>
						<td><b>Event Details</b></td>
						<td><%=e.getDetails()%></td>
					</tr>
				</table>
			</div>
		</form>

		<br>
			<%
				if(invitationList.size() == 0){%>
					<h3 align="center">No invitations found</h3>
				<%} else {%>

			<table border="1" id="InvitationsTable">
				<tr>
					<td><b>Invited user</b></td>
					<td><b>Response</b></td>
					<td><b>Comment</b></td>
					</b>
				</tr>
			
			<% for (Object o : invitationList) {
					EventInvitation invitation = (EventInvitation) o;
			%>
			<tr>
				<td><%=invitation.getInvitedUser().getName()%></td>
				<td><%=invitation.getResponse()%></td>
				<td><%=invitation.getComment()%></td>
			</tr>
			<%
					}//end of else
				}
			%>
		</table>

		<!-- Display Delete/Edit buttons for owner -->

		<%
			Boolean isOwner = (Boolean) request.getAttribute(EventUtils.ATTRIBUTE_IS_OWNER);
			if (isOwner) {
		%>
		<br>
		<form method="GET" action="deleteEventController">
			<div class="c1">
				<input type="hidden" name=<%=EventUtils.PARAMETER_EVENT_ID%>
					value=<%=e.getId()%>> 
				<input type="submit"
					value="Delete event">
			</div>
		</form>

		<br>
		<form method="GET" action="event_edit">
			<div class="c1">
				<input type="hidden" name=<%=EventUtils.PARAMETER_EVENT_ID%> value="Edit event">
				<input type="submit" value="Edit event">
			</div>
		</form>

		<%
			}
		%>

		<br>
		<form name="newInvitation" action="inviteEventController" method="get">
			<div class="c1">
				Username: <br> 
				<input type="text" size=30 name=<%=EventUtils.PARAMETER_USERNAME%>> 
				<input type="hidden" name=<%=EventUtils.PARAMETER_EVENT_ID%> value=<%=e.getId()%>> 
				<input type="submit" value="Send Invitation">
			</div>
		</form>
		<br>
	</div>
</body>
</html>