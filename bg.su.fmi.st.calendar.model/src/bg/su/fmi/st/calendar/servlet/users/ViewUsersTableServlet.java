package bg.su.fmi.st.calendar.servlet.users;

import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;

import bg.su.fmi.st.calendar.model.entities.User;
import bg.su.fmi.st.calendar.model.manager.UserDAO;
import bg.su.fmi.st.calendar.servlet.AbstractTableServlet;
import bg.su.fmi.st.calendar.servlet.HtmlTableUtil;

public class ViewUsersTableServlet extends AbstractTableServlet {
	private static final long serialVersionUID = 7600259213069049846L;

	private static final String[] COLUMNS = new String[] { "Username", "Name" };

	@EJB
	protected UserDAO userDAO;

	@Override
	public String getTitle() {
		return "All Users";
	}

	@Override
	protected void displayDataInTable(PrintWriter pw) {
		List<User> users = getUsersForDisplay();
		displayUsersInTable(pw, users);
	}

	private List<User> getUsersForDisplay() {
		return userDAO.getUsers();
	}

	private void displayUsersInTable(PrintWriter pw, List<User> users) {
		pw.print("<table border=\"1\">");

		HtmlTableUtil.displayColumnLabels(pw, COLUMNS);

		for (User u : users) {
			displayUserInRow(pw, u);
		}

		pw.print("</table>");
	}

	private void displayUserInRow(PrintWriter pw, User user) {
		pw.printf("<tr>");

		HtmlTableUtil.displayCell(pw, user.getUsername(), true);
		HtmlTableUtil.displayCell(pw, user.getName(), false);

		pw.printf("</tr>");
	}
}
