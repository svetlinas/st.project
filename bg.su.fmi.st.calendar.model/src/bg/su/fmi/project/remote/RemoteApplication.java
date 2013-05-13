package bg.su.fmi.project.remote;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RemoteApplication extends Application {

	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(Events.class);
		s.add(Users.class);
		return s;
	}

}
