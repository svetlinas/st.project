package bg.su.fmi.project.remote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.manager.EventDAO;
import bg.su.fmi.st.calendar.utils.DateParser;

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
	public void createEvent(@FormParam("title") String title,
			@FormParam("place") String place,
			@FormParam("startDate") String startDate,
			@FormParam("endDate") String endDate,
			@FormParam("type") String type, @FormParam("details") String details)
			throws ParseException {
		Date eventStartDate = DateParser.parse(startDate);
		Date eventEndDate = DateParser.parse(endDate);
		Event newEvent = new Event(null, title, place, eventStartDate,
				eventEndDate, type, details); // TODO organizer is null.
		eventDAO.addEvent(newEvent);
	}
	
	@GET
	public List<Event> getEvents() {
		return eventDAO.getEvents();
	}
}
