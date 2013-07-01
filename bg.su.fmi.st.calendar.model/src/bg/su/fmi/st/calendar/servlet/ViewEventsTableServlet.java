package bg.su.fmi.st.calendar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.EventDAO;

//TODO extract more common stuff for table of Users as well
public class ViewEventsTableServlet extends HttpServlet {

	private static final long serialVersionUID = 7600259213069049846L;

	@EJB
	private EventDAO eventsDAO;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<html>");
		pw.println("<head><title>All Events</title></title>");
		pw.println("<body>");

		displayEventsInTable(pw);

		pw.println("<hr/>");
		pw.println("</body></html>");
	}

	private void displayEventsInTable(PrintWriter pw) {
		List<Event> events = eventsDAO.getEvents();
		displayEventsInTable(pw, events);
	}

	private void displayEventsInTable(PrintWriter pw, List<Event> events) {
		pw.print("<table border=\"1\">");

		String[] columnContent = new String[] { "Name", "Start Date",
				"Event owner" };
		HtmlTableUtil.displayColumnLabels(pw, columnContent);

		for (Event e : events) {
			displayEventInRow(pw, e);
		}

		pw.print("</table>");
	}

	private void displayEventInRow(PrintWriter pw, Event event) {
		pw.printf("<tr>");

		// TODO fix links to viewEvet page
		HtmlTableUtil.displayCellWithLink(pw, event.getTitle(), "link");

		HtmlTableUtil.displayCell(pw, Event.df.format(event.getStartDate()), false);

		User organizer = event.getOrganizer();
		if (organizer == null) {
			System.err.println("CHECK YOUR DB for EVENTS with NULL organizer value. Remove/edit them and try again :)");
		}
		HtmlTableUtil.displayCell(pw, organizer.getName(), false);

		pw.printf("</tr>");
	}
}
