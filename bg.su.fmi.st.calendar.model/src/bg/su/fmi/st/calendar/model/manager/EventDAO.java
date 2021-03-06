package bg.su.fmi.st.calendar.model.manager;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.utils.NotificationService;

@Stateless
public class EventDAO {

	@PersistenceContext(unitName = "sport-events-organizer-unit")
	private EntityManager entityManager;

	@EJB
	private NotificationService notificationService;

	public Event addEvent(Event event) {
		entityManager.persist(event);
		entityManager.flush();
		return event;
	}

	public void deleteEvent(Long id) {
		List<Event> eventsToBeRemoved = getEvents(Arrays.asList(id));
		for (Event event : eventsToBeRemoved) {
			entityManager.remove(event);
			entityManager.flush();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Event> getEvents() {
		Query query = entityManager.createQuery("SELECT e from Event as e");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Event> getEvents(User organizer) {
		Query query = entityManager.createQuery("SELECT e from Event as e where e.organizer=?1");
		query.setParameter(1, organizer);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Event> getEvents(List<Long> ids) {
		String idSQL = "";
		if(ids.size() == 0 || ids.get(0) == null){
			idSQL = "";
		}else{
			idSQL = "WHERE ID in " + convertToSqlString(ids);
		}
		Query query = entityManager.createQuery("SELECT e from Event as e " + idSQL);
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

	public Event updateEvent(Event changedEvent) {

		Event event = entityManager.find(Event.class, changedEvent.getId());

		event.setDetails(changedEvent.getDetails());
		event.setEndDate(changedEvent.getEndDate());
		event.setOrganizer(changedEvent.getOrganizer());
		event.setPlace(changedEvent.getPlace());
		event.setStartDate(changedEvent.getStartDate());
		event.setTitle(changedEvent.getTitle());
		event.setType(changedEvent.getType());

		entityManager.persist(event);
		entityManager.flush();

		notificationService.notifyUsersForChangedEvent(event);
		return event;
	}

	//	public static void main(String[] args) {
	//		List<Long> ids = new ArrayList<Long>();
	//		ids.add(new Long(10));
	//		ids.add(new Long(12));
	//
	//		String r = new EventDAO().convertToSqlString(ids);
	//		System.out.println(r);
	//	}
}
