package bg.su.fmi.st.calendar.servlet.events.controller;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.EventDAO;
import bg.su.fmi.st.calendar.model.manager.UserDAO;
import bg.su.fmi.st.calendar.servlet.events.EventUtils;

@SuppressWarnings("serial")
public class AbstractEventControllerServlet extends HttpServlet {

	@EJB
	protected EventDAO eventDao;
	
	protected Event getEvent(HttpServletRequest req, EventDAO eventDao) {
		Long id = Long.valueOf(req.getParameter(EventUtils.PARAMETER_EVENT_ID));
		List<Event> events = eventDao.getEvents(Arrays.asList(new Long[] { id }));
		Event resultEvent = events.get(0);
		return resultEvent;
	}

	protected User getUser(HttpServletRequest req, UserDAO userDao) {
		String username = req.getParameter(EventUtils.PARAMETER_USERNAME);
		List<User> users = userDao.getUsers();
		User resultUser = null;
	
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				resultUser = u;
				break;
			}
		}
	
		return resultUser;
	}

	protected boolean isOwner(HttpServletRequest request, Event event) {
		String currentUser = request.getUserPrincipal().getName();
		return event.getOrganizer().getUsername().equals(currentUser);
	}
}
