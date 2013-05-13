package bg.su.fmi.st.calendar.model.manager;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import bg.su.fmi.st.calendar.model.entities.EventInvitation;

@Stateful
public class EventInvitationDAO {

	@PersistenceContext(unitName = "sport-events-organizer-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public void addEventInvitation(EventInvitation eventInvitation) {
		entityManager.persist(eventInvitation);
	}

	public void deleteEvent(EventInvitation eventInvitation) {
		entityManager.remove(eventInvitation);
	}

	@SuppressWarnings("unchecked")
	public List<EventInvitation> getEventInvitations() {
		Query query = entityManager.createQuery("SELECT e from EventInvitation as e");
		return query.getResultList();
	}
}
