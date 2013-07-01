package bg.su.fmi.st.calendar.servlet.events.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
import bg.su.fmi.st.calendar.servlet.events.EventUtils;

@SuppressWarnings("serial")
public class InviteToEventControllerServlet extends HttpServlet {

	@EJB
	private EventDAO eventDao;
	
	@EJB
	private UserDAO userDao;

	@EJB
	private EventInvitationDAO invitationDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = EventUtils.getUser(req, userDao);
		Event event = EventUtils.getEvent(req, eventDao);		
		
		invitationDao.addEventInvitation(new EventInvitation(event, user));

		//we display the ViewEvent page again
		RequestDispatcher view = req.getRequestDispatcher(EventUtils.EVENT_VIEW_CONTROLLER_URL);
		view.forward(req, resp);
	}
}
