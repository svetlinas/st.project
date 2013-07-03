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

	public boolean addUser(User user) {
		for (User existingUser: getUsers()) {
			if (existingUser.getUsername().equals(user.getUsername())) {
				return false;
			}
		}
		entityManager.persist(user);
		return true;
	}

	public void deleteUser(User user) {
		entityManager.remove(user);
	}

	//TODO add get user by Username or ID functionality
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Query query = entityManager.createQuery("SELECT u from User as u");
		return query.getResultList();
	}
}
