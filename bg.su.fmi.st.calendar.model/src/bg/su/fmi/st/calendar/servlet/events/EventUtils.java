package bg.su.fmi.st.calendar.servlet.events;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.EventDAO;
import bg.su.fmi.st.calendar.model.manager.UserDAO;

public class EventUtils {
	public static final String PARAMETER_EVENT_ID = "event_id";
	public static final String PARAMETER_USERNAME = "username";
	public static final String ATTRIBUTE_EVENT = "event";
	public static final String ATTRIBUTE_EVENT_INVITATION_LIST = "eventInvitationList";
	
	public static final String EVENT_VIEW_JSP = "event_view.jsp";
	public static final String EVENT_VIEW_CONTROLLER_URL = "viewEventController";
	
	public static String buildViewEventString(Long eventId){
		return String.format("%s?%s=%d",
				EventUtils.EVENT_VIEW_CONTROLLER_URL,
				EventUtils.PARAMETER_EVENT_ID, eventId);
	}
	
	public static Event getEvent(HttpServletRequest req, EventDAO eventDao){
		Long id = Long.valueOf(req.getParameter(EventUtils.PARAMETER_EVENT_ID));
		List<Event> events = eventDao.getEvents(
				Arrays.asList(new Long[] { id }));
		Event resultEvent = events.get(0);
		return resultEvent;
	}
	
	public static User getUser(HttpServletRequest req, UserDAO userDao){
		String username = req.getParameter(EventUtils.PARAMETER_USERNAME);
		List<User> users = userDao.getUsers();
		User resultUser = null;
		
		for(User u: users){
			if(u.getUsername().equals(username)){
				resultUser = u;
				break;
			}
		}
		
		return resultUser;
	}
}
