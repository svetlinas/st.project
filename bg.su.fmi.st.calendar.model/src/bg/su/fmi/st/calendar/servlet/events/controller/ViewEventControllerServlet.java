package bg.su.fmi.st.calendar.servlet.events.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.EventInvitation;
import bg.su.fmi.st.calendar.model.manager.EventInvitationDAO;
import bg.su.fmi.st.calendar.servlet.events.EventUtils;

@SuppressWarnings("serial")
public class ViewEventControllerServlet extends AbstractEventControllerServlet {

	@EJB
	private EventInvitationDAO invitationDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	      IOException {

		Event event = getEvent(req, eventDao);
		req.setAttribute(EventUtils.ATTRIBUTE_EVENT, event);

		List<EventInvitation> invitations = new ArrayList<EventInvitation>(
		      invitationDao.getEventInvitations(event));
		req.setAttribute(EventUtils.ATTRIBUTE_EVENT_INVITATION_LIST, invitations);

		req.setAttribute(EventUtils.ATTRIBUTE_IS_OWNER, isLoggedInUserOwner(req, event));

		RequestDispatcher view = req.getRequestDispatcher(EventUtils.URL_VIEW_EVENT_JSP);
		view.forward(req, resp);
	}
}
