package bg.su.fmi.st.calendar.servlet.events;

import java.util.List;

import bg.su.fmi.st.calendar.model.entities.Event;

@SuppressWarnings("serial")
public class ViewAllEventsTableServlet extends AbstractEventsTableServlet {

	@Override
	protected List<Event> getEventsForDisplay() {
		return eventsDAO.getEvents();
	}

	@Override
	public String getTitle() {
		return "All Events";
	}
}