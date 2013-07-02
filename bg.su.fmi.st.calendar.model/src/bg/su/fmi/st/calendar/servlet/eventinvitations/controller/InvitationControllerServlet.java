package bg.su.fmi.st.calendar.servlet.eventinvitations.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.EventInvitation;
import bg.su.fmi.st.calendar.model.manager.EventInvitationDAO;
import bg.su.fmi.st.calendar.servlet.events.EventUtils;
public class InvitationControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 162356820452759261L;
	
	@EJB
	EventInvitationDAO eventInvitationDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = Long.valueOf(req.getParameter(EventUtils.PARAMETER_INVITATION_ID));
		boolean isAccepted = Boolean.parseBoolean(req.getParameter(EventUtils.INVITATION_RESPONDED));
		
		EventInvitation eventInvitation = eventInvitationDAO.getEventInvitation(id);
		if (isAccepted) {
			eventInvitationDAO.acceptInvitation(eventInvitation);
		} else {
			eventInvitationDAO.declineInvitation(eventInvitation);
		}
		
		RequestDispatcher view = req.getRequestDispatcher(EventUtils
				.buildViewEventString(eventInvitation.getEvent().getId()));
		view.forward(req, resp);
	}

}
