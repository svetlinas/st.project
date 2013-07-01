package bg.su.fmi.st.calendar.servlet.events;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.manager.EventDAO;

@SuppressWarnings("serial")
public class ViewEventControllerServlet extends HttpServlet {

	@EJB
	private EventDAO eventDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Long id = Long.valueOf(req.getParameter(EventUtils.PARAMETER_EVENT_ID));
		List<Event> events = eventDao.getEvents(
				Arrays.asList(new Long[] { id }));
		Event resultEvent = events.get(0);

		req.setAttribute(EventUtils.ATTRIBUTE_EVENT, resultEvent);
		RequestDispatcher view = req.getRequestDispatcher(EventUtils.EVENT_VIEW_JSP);
		view.forward(req, resp);
	}
}
