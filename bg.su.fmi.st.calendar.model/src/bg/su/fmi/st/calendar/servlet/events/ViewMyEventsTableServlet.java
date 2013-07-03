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
		User currentUser = userDao.getUser(currentUsername);

		return eventsDAO.getEvents(currentUser);
	}

	@Override
	public String getTitle() {
		return "My Events";
	}
}