package bg.su.fmi.st.calendar.servlet.eventinvitations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.EventInvitation;
import bg.su.fmi.st.calendar.model.entities.EventInvitation.InvitationResponse;
import bg.su.fmi.st.calendar.model.manager.EventInvitationDAO;
import bg.su.fmi.st.calendar.servlet.HtmlTableUtil;
import bg.su.fmi.st.calendar.servlet.events.EventUtils;

public class ViewEventInvitationsTableServlet extends HttpServlet {

	private static final long serialVersionUID = 5289774875930198329L;

	private static final String[] COLUMNS = new String[]{"Event", "Status", "My Comments"};

	@EJB
	private EventInvitationDAO eventInvitationsDAO;

	private HttpServletRequest request;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.request = request;
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<html>");
		pw.println("<head>");
		pw.println("<link href=\"css/pagestyle.css\" rel=\"stylesheet\" />");
		pw.println("<title>Invitations</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.print("<h1>");
		pw.print(getTitle());
		pw.print("</h1>");
		pw.println("<br>");

		displayEventInvitationsInTable(pw);

		pw.println("</body></html>");
	}

	public List<EventInvitation> getEventInvitationsForDisplay() {
		List<EventInvitation> allEventInvitations = eventInvitationsDAO.getAllEventInvitations();
		String currentUsername = this.request.getRemoteUser();

		List<EventInvitation> myEventInvitations = new ArrayList<EventInvitation>();
		for(EventInvitation e: allEventInvitations) {
			if(e.getInvitedUser().getUsername().equals(currentUsername)){
				myEventInvitations.add(e);
			}
		}
		return myEventInvitations;
	}

	public String getTitle() {
		return "Invitations";
	}

	private void displayEventInvitationsInTable(PrintWriter pw) {
		List<EventInvitation> eventInvitations = getEventInvitationsForDisplay();
		displayEventInvitationsInTable(pw, eventInvitations);
	}

	private void displayEventInvitationsInTable(PrintWriter pw, List<EventInvitation> eventInvitations) {
		pw.print("<table border=\"1\">");

		HtmlTableUtil.displayColumnLabels(pw, COLUMNS);

		for (EventInvitation ei : eventInvitations) {
			displayEventInvitationInRow(pw, ei);
		}

		pw.print("</table>");
	}

	private void displayEventInvitationInRow(PrintWriter pw, EventInvitation eventInvitation) {
		pw.printf("<tr>");
		String viewEventControllerUrl = EventUtils.buildViewEventString(eventInvitation.getEvent().getId());
		HtmlTableUtil.displayCellWithLink(pw, eventInvitation.getEvent().getTitle(), viewEventControllerUrl);

		InvitationResponse response = eventInvitation.getResponse();
		displayColoredResponse(pw, response);

		HtmlTableUtil.displayCell(pw, eventInvitation.getComment(), false);
		pw.printf("</tr>");
	}

	private void displayColoredResponse(PrintWriter pw, InvitationResponse response) {
		String color = HtmlTableUtil.COLOR_YELLOW;
		if(response == InvitationResponse.YES) {
			color = HtmlTableUtil.COLOR_GREEN;
		}else if(response == InvitationResponse.NO) {
			color = HtmlTableUtil.COLOR_RED;
		}

		HtmlTableUtil.displayCellWithColor(pw, response.toString(), color);
	}
}
