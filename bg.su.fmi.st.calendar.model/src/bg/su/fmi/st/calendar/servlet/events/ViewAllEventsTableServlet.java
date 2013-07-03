package bg.su.fmi.st.calendar.servlet.events;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bg.su.fmi.st.calendar.model.entities.Event;

@SuppressWarnings("serial")
public class ViewAllEventsTableServlet extends ViewMyEventsTableServlet {

	@Override
	protected List<Event> getEventsForDisplay() {
		List<Event> allEvents = eventsDAO.getEvents();

		List<Event> publicEvents = new ArrayList<Event>();
		for (Event e : allEvents) {
			if (e.getType().equals("public")) {
				publicEvents.add(e);
			}
		}

		List<Event> myEvents = super.getEventsForDisplay();

		Set<Event> uniqueEvents = new HashSet<Event>();
		uniqueEvents.addAll(publicEvents);
		uniqueEvents.addAll(myEvents);

		return new ArrayList<Event>(uniqueEvents);
	}

	@Override
	public String getTitle() {
		return "All Events";
	}
}