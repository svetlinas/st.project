package bg.su.fmi.project.remote;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.UserDAO;

/**
 * REST resource responsible for interactions with users.
 * 
 * @author kkuncheva
 * 
 */

@Path("/users")
public class Users {
	
	@EJB
	private UserDAO userDAO;

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void addUser(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("cpassword") String cpassword,
			@FormParam("email") String email,
			@FormParam("name") String name,
			@FormParam("picture") byte[] picture) {
		if (!password.equals(cpassword)) {
			//TODO print error message
			return;
		}
		User newUser = new User(username, password, email, name, picture);
		newUser.setGroups(Arrays.asList(User.Group.USER));
		userDAO.addUser(newUser);
	}
	
	@GET
	public List<User> getUsers() { 
		return userDAO.getUsers();
	}

}
