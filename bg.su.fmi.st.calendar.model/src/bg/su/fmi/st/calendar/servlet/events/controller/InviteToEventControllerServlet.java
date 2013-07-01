package bg.su.fmi.st.calendar.servlet.events.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.EventInvitation;
import bg.su.fmi.st.calendar.model.entities.EventInvitation.InvitationResponse;
import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.EventInvitationDAO;
import bg.su.fmi.st.calendar.model.manager.UserDAO;
import bg.su.fmi.st.calendar.servlet.events.EventUtils;

@SuppressWarnings("serial")
public class InviteToEventControllerServlet extends AbstractEventControllerServlet {
	
	@EJB
	private UserDAO userDao;

	@EJB
	private EventInvitationDAO invitationDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = getUser(req, userDao);
		Event event = getEvent(req, eventDao);
		
		EventInvitation eventInvitation = new EventInvitation(event, user);
		eventInvitation.setResponse(InvitationResponse.Waiting);
		eventInvitation.setComment("");
		
		invitationDao.addEventInvitation(eventInvitation);
		
		//we display the ViewEvent page again
		RequestDispatcher view = req.getRequestDispatcher(EventUtils.URL_VIEW_EVENT_CONTROLLER);
		view.forward(req, resp);
	}
}
