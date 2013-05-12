package bg.su.fmi.st.calendar.model.manager;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import bg.su.fmi.st.calendar.model.entities.Event;

@Stateful
public class EventDAO {

	@PersistenceContext(unitName = "sport-events-organizer-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public void addEvent(Event event) {
		entityManager.persist(event);
	}

	public void deleteUser(Event event) {
		entityManager.remove(event);
	}

	@SuppressWarnings("unchecked")
	public List<Event> getEvents() {
		Query query = entityManager.createQuery("SELECT e from Event as e");
		return query.getResultList();
	}
}
