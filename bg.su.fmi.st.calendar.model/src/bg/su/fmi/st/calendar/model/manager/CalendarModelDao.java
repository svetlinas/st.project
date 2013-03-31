package bg.su.fmi.st.calendar.model.manager;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful
public class CalendarModelDao {

	@PersistenceContext(unitName = "movie-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

}
