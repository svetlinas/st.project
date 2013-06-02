package bg.su.fmi.st.calendar.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import bg.su.fmi.st.calendar.model.entities.Event;

@Stateful
public class EventDAO {

	@PersistenceContext(unitName = "sport-events-organizer-unit", type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	public void addEvent(Event event) {
		entityManager.persist(event);
	}

	public void deleteEvent(List<Long> ids) {
		List<Event> eventsToBeRemoved = getEvents(ids);
		for (Event event : eventsToBeRemoved) {
			entityManager.remove(event);
		}
	}

	public void deleteEvent(Event event) {
		entityManager.remove(event);
	}

	@SuppressWarnings("unchecked")
	public List<Event> getEvents(List<Long> ids) {
		Query query = entityManager.createQuery("SELECT e from Event as e WHERE ID in "
						+ convertToSqlString(ids));
		return query.getResultList();
	}

	private String convertToSqlString(List<Long> ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Long id : ids) {
			sb.append(String.format("'%d' ,", id));
		}
		String result = sb.toString();
		return result.substring(0, result.length() - 1) + ")";
	}

	public static void main(String[] args) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(new Long(10));
		ids.add(new Long(12));

		String r = new EventDAO().convertToSqlString(ids);
		System.out.println(r);
	}

	@SuppressWarnings("unchecked")
	public List<Event> getEvents() {
		Query query = entityManager.createQuery("SELECT e from Event as e");
		return query.getResultList();
	}

	public void editEvent(Event changedEvent) {

		Event event = entityManager.find(Event.class, changedEvent.getId());

		event.setDetails(changedEvent.getDetails());
		event.setEndDate(changedEvent.getEndDate());
		event.setOrganizer(changedEvent.getOrganizer());
		event.setPlace(changedEvent.getPlace());
		event.setStartDate(changedEvent.getStartDate());
		event.setTitle(changedEvent.getTitle());
		event.setType(changedEvent.getType());
	}
}
