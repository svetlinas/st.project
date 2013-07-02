package bg.su.fmi.st.calendar.model.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.EventInvitation;
import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.EventDAO;
import bg.su.fmi.st.calendar.model.manager.EventInvitationDAO;
import bg.su.fmi.st.calendar.model.manager.UserDAO;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;

	@EJB
	private EventDAO eventsDAO;

	@EJB
	private EventInvitationDAO invitationsDAO;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 userDAO.addUser(new User("ivan.ivanov", "passwd",
		 "ivan@ivanov@gmail.com", "Ivan Ivanov", null));

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head><title>Hello World</title></title>");
		pw.println("<body>");
		pw.println("<h1>Hello World</h1>");

		pw.println("Users count: " + userDAO.getUsers().size());
		displayUsers(pw);

		displayEvents(pw);
		testRemoveEvents(pw);
		testEditEvents(pw);

		displaySomeEvents(pw);
		
		testEventInvitations(pw);

		pw.println("<hr/>");
		pw.println("</body></html>");
	}

	private void testEditEvents(PrintWriter pw) {
		Event testEvent = new Event(
				null, 
				"eventTitle5", 
				"eventPlace 5", 
				"2013-06-12T12:00:00", 
				"2013-07-12T12:00:00", 
				"public", 
				"eventDetails5");

		pw.printf("Creating test event %s <br>", testEvent.toString());
		eventsDAO.addEvent(testEvent);

		testEvent.setStartDate("2014-06-12T12:00:00");
		testEvent.setEndDate("2014-07-12T12:00:00");
		testEvent.setPlace("changed" + testEvent.getPlace());
		testEvent.setTitle("changed" + testEvent.getTitle());
		testEvent.setType("changed" + testEvent.getType());
		testEvent.setDetails("changed" + testEvent.getDetails());

		 inviteUserToEvent(testEvent);

		pw.printf("Editing event ... <br>");
		eventsDAO.updateEvent(testEvent);
		
		invitationsDAO.invite(testEvent);

		pw.printf("Check changed events...<br>");
		displayEvents(pw);

		Long id = testEvent.getId();
		pw.printf("Removing test event with ID %d <br>", id);
//		eventsDAO.deleteEvent(id);
	}
	
	private void testEventInvitations(PrintWriter pw) {
		
		Event firstEvent = new Event(null, "Sport1", "Mladost1",
				"2013-06-12T12:00:00", "2013-07-12T12:00:00", "private",
				"details");
		eventsDAO.addEvent(firstEvent);
		
		User firstUser = new User("kkuncheva", "passwd", "kunka.kuncheva@gmail.com",
				"Kunka Kuncheva", null);
		userDAO.addUser(firstUser);
		
		EventInvitation firstInvitation = new EventInvitation(firstEvent, firstUser);
		invitationsDAO.addEventInvitation(firstInvitation);
		
		Event secondEvent = new Event(null, "Sport1", "Mladost1",
				"2013-06-12T12:00:00", "2013-07-12T12:00:00", "private",
				"details");
		eventsDAO.addEvent(secondEvent);
		
		User secondUser = new User("kkuncheva", "passwd", "kunka.kuncheva@gmail.com",
				"Kunka Kuncheva", null);
		userDAO.addUser(secondUser);
		
		EventInvitation secondInvitation = new EventInvitation(secondEvent, secondUser);
		invitationsDAO.addEventInvitation(secondInvitation);

		EventInvitation ei = invitationsDAO.getEventInvitation(secondInvitation.getId());
		if (ei.getId() == secondInvitation.getId()) {
			pw.printf("Welldone!!! YOu have the same ids!!");
		}
		invitationsDAO.acceptInvitation(firstInvitation);
		
		invitationsDAO.declineInvitation(secondInvitation, "Cant Come!!!");
		
	}

	private void inviteUserToEvent(Event testEvent) {
		User user = new User("kkuncheva", "passwd", "kunka.kuncheva@gmail.com",
				"Kunka Kuncheva", null);
		userDAO.addUser(user);
		invitationsDAO.addEventInvitation(new EventInvitation(testEvent, user));
	}

	private void testRemoveEvents(PrintWriter pw) {
		Event testEvent = new Event(null, "eventTitle5", "eventPlace 5",
				"2013-06-12T12:00:00", "2013-07-12T12:00:00", "public",
				"eventDetails5");

		pw.printf("Creating test event %s <br>", testEvent.toString());
		eventsDAO.addEvent(testEvent);

		displayEvents(pw);

		Long id = testEvent.getId();
		// Long id = new Long(3);

		pw.printf("Removing test event with ID %d <br>", id);
		eventsDAO.deleteEvent(id);
		displayEvents(pw);
	}

	private void displayUsers(PrintWriter pw) {
		for (User user : userDAO.getUsers()) {
			pw.println(user.toString());
			pw.print("<br>");
		}
	}

	private void displayEvents(PrintWriter pw) {
		pw.print("<br> Displaying all events<br>");
		for (Event event : eventsDAO.getEvents()) {
			pw.println(event.toString());
			pw.print("<br>");
		}
	}

	private void displaySomeEvents(PrintWriter pw) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(new Long(2));

		pw.print("<br> Displaying events<br>");
		for (Event event : eventsDAO.getEvents(ids)) {
			pw.println(event.toString());
			pw.print("<br>");
		}
	}
}
