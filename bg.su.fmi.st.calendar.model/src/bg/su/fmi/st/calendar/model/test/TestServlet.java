package bg.su.fmi.st.calendar.model.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.EventDAO;
import bg.su.fmi.st.calendar.model.manager.UserDAO;

public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;

	@EJB
	private EventDAO eventsDAO;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		userDAO.addUser(new User("ivan.ivanov", "passwd", "passwd",
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
		
		pw.println("<hr/>");
		pw.println("</body></html>");
	}
	
	private void testRemoveEvents(PrintWriter pw) {
		//create event
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
		
		displayEvents(pw);
		
		Long id = testEvent.getId();
//		Long id = new Long(3);
		
		pw.printf("Removing test event with ID %d <br>", id);
		eventsDAO.deleteEvent(Arrays.asList(id));
		displayEvents(pw);
	}

	private void displayUsers(PrintWriter pw){
		for (User user : userDAO.getUsers()) {
			pw.println(user.toString());
			pw.print("<br>");
		}
	}
	
	private void displayEvents(PrintWriter pw){
		pw.print("<br> Displaying all events<br>");
		for (Event event : eventsDAO.getEvents()) {
			pw.println(event.toString());
			pw.print("<br>");
		}
	}
	
}
