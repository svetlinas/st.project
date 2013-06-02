package bg.su.fmi.project.remote;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.manager.EventDAO;

/**
 * REST resource responsible for interactions with events.
 * 
 * Great help here: http://coenraets.org/blog/2011/12/restful-services-with-jquery-and-java-using-jax-rs-and-jersey/
 * 
 * @author Serafim Karparov, Leni Kirilov
 * 
 */
@Path("/events")
public class Events {

	@EJB
	private EventDAO eventDAO;

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Event createEvent(
			@FormParam("title") String title,
			@FormParam("place") String place,
			@FormParam("startDate") String startDate,
			@FormParam("endDate") String endDate,
			@FormParam("type") String type, 
			@FormParam("details") String details)
			throws ParseException {
		
		// TODO organizer is null.
		Event newEvent = new Event(null, title, place, startDate, endDate, type, details); 
		return eventDAO.addEvent(newEvent);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Event> getAllEvents() {
		return eventDAO.getEvents();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Event> getEvents(
			@FormParam("id") 
			List<Long> id) {
		return eventDAO.getEvents(id);
	}
	
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void removeEvent(
			@FormParam("id")
			@PathParam("id") Long id) {
		eventDAO.deleteEvent(id);
	}

	@PUT 
	@Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_XHTML_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Event updateEvent(Event changedEvent) {
		return eventDAO.updateEvent(changedEvent);
    }
}
