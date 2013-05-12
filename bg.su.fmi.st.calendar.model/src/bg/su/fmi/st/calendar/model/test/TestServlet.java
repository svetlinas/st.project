package bg.su.fmi.st.calendar.model.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.UserDAO;

public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UserDAO userDAO;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		userDAO.addUser(new User("ivan.ivanov", "passwd",
				"ivan@ivanov@gmail.com", "Ivan Ivanov", null));
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head><title>Hello World</title></title>");
		pw.println("<body>");
		pw.println("<h1>Hello World</h1>");
		pw.println("Users count: " + userDAO.getUsers().size());
		pw.println("<hr/>");
		for (User user : userDAO.getUsers()) {
			pw.println(String.format(
					"User id: %s; Username: %s; Name: %s; e-mail: %s",
					user.getId(), user.getUsername(), user.getName(),
					user.getEmail()));
		}
		pw.println("</body></html>");
	}
}
