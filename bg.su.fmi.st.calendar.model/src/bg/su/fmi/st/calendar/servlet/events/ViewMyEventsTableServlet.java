package bg.su.fmi.st.calendar.servlet.events;

import java.util.List;

import javax.ejb.EJB;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.UserDAO;

@SuppressWarnings("serial")
public class ViewMyEventsTableServlet extends AbstractEventsTableServlet {

	@EJB
	UserDAO userDao;

	@Override
	protected List<Event> getEventsForDisplay() {
		String currentUsername = this.request.getRemoteUser();
		User currentUser = getUserByUsername(currentUsername);

		return eventsDAO.getEvents(currentUser);
	}

	//TODO this is duplicate. To be used UserDAO method for better impl
	private User getUserByUsername(String username) {
		User user = null;
		for (User u : userDao.getUsers()) {
			if (u.getUsername().equals(username)) {
				user = u;
				break;
			}
		}
		return user;
	}

	@Override
	public String getTitle() {
		return "My Events";
	}
}