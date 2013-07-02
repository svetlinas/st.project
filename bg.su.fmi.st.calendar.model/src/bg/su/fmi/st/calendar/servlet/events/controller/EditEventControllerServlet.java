package bg.su.fmi.st.calendar.servlet.events.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.servlet.events.EventUtils;

@SuppressWarnings("serial")
public class EditEventControllerServlet extends AbstractEventControllerServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	      IOException {
		
		Event event = getEvent(req, eventDao);
		req.setAttribute(EventUtils.ATTRIBUTE_EVENT, event);

		// we display the edit page for an event
		RequestDispatcher view = req.getRequestDispatcher(EventUtils.URL_EDIT_EVENT);
		view.forward(req, resp);
	}
}
