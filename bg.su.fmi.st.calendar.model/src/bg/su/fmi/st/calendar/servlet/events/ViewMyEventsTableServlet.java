package bg.su.fmi.st.calendar.servlet.events;

import java.util.ArrayList;
import java.util.List;

import bg.su.fmi.st.calendar.model.entities.Event;

@SuppressWarnings("serial")
public class ViewMyEventsTableServlet extends AbstractEventsTableServlet {

	@Override
	protected List<Event> getEventsForDisplay() {
		List<Event> allEvents = eventsDAO.getEvents();
		String currentUsername = this.request.getRemoteUser();
		
		List<Event> myEvents = new ArrayList<Event>();
		for(Event e: allEvents){
			if(e.getOrganizer().getUsername().equals(currentUsername)){
				myEvents.add(e);
			}
		}
		return myEvents;
	}

	@Override
	public String getTitle() {
		return "My Events";
	}
}