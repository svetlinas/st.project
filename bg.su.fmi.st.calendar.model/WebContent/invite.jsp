<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Email Invitation</title>
<link href="css/pagestyle.css" rel="stylesheet" />
<script>
function showMessage()
{
	alert("Invitation sent to " + document.forms["sendinvitation"]["toAddress"].value + ".");
}
</script>
</head>
<body>
	<div class="events">
		<h1>Send Invitation</h1>
		<img id="invitationimg" src="images/email.png"/>
		<%@ page session="true"%>
		<form name="sendinvitation" action="invitations" method="post" onsubmit="showMessage()">
			<div class="c1">
				<p>&nbsp;</p>
				<table>
					<tr align="right">
						E-mail:
						<input type="email" name="toAddress">
						<br>
						<input type="hidden" name="fromName" value="<%=request.getRemoteUser()%>">
						<input type="submit" value="Send">
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>

