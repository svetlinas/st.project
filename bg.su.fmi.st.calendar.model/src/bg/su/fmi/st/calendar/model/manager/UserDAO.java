package bg.su.fmi.st.calendar.model.manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.su.fmi.st.calendar.model.entities.User;

@Stateless
public class UserDAO {

	@PersistenceContext(unitName = "sport-events-organizer-unit")
	private EntityManager entityManager;

	public void addUser(User user) {
		entityManager.persist(user);
	}

	public void deleteUser(User user) {
		entityManager.remove(user);
	}

	//TODO get user by name
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Query query = entityManager.createQuery("SELECT u from User as u");
		return query.getResultList();
	}
}
