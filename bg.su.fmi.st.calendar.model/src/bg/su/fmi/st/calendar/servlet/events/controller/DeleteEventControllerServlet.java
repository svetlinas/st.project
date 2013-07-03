package bg.su.fmi.st.calendar.servlet.events.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.servlet.events.EventUtils;

@SuppressWarnings("serial")
public class DeleteEventControllerServlet extends AbstractEventControllerServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {

		Event event = getEvent(req, eventDao);

		if (isOwner(req, event)) {
			eventDao.deleteEvent(event.getId());
		} else {
			throw new RuntimeException("Unauthorized delete attempt by User "
					+ getOwnerName(req) + " for event " + event.getTitle());
		}

		// we display the MyEvents page
		RequestDispatcher view = req.getRequestDispatcher(EventUtils.URL_VIEW_MY_EVENTS_URL);
		view.forward(req, resp);
	}
}
