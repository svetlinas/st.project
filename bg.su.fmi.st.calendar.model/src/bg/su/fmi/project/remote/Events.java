package bg.su.fmi.project.remote;

import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.manager.EventDAO;

/**
 * REST resource responsible for interactions with events.
 * 
 * @author Serafim Karparov
 * 
 */
@Path("/events")
public class Events {

	@EJB
	private EventDAO eventDAO;

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void createEvent(
			@FormParam("title") String title,
			@FormParam("place") String place,
			@FormParam("startDate") String startDate,
			@FormParam("endDate") String endDate,
			@FormParam("type") String type, @FormParam("details") String details)
			throws ParseException {
		
		// TODO organizer is null.
		Event newEvent = new Event(null, title, place, startDate, endDate, type, details); 
		eventDAO.addEvent(newEvent);
	}

	@GET
	public List<Event> getEvents() {
		return eventDAO.getEvents();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void removeEvents(@FormParam("ids") List<Long> ids) {
		eventDAO.deleteEvent(ids);
	}
	
}
