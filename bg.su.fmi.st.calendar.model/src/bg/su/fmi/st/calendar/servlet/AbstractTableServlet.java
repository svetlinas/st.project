package bg.su.fmi.st.calendar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractTableServlet extends HttpServlet {
	private static final long serialVersionUID = 7600259213069049846L;

	protected HttpServletRequest request;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.request = request;
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		pw.println("<html>");
		pw.println("<head>");
		pw.println("<link href=\"css/pagestyle.css\" rel=\"stylesheet\" />");
		pw.print("<title>");
		pw.print(getTitle());
		pw.print("</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.print("<h1>");
		pw.print(getTitle());
		pw.print("</h1>");
		pw.println("<img id=\"sports\" src=\"images/sports.png\"/>");
		pw.println("<br>");

		displayDataInTable(pw);

		pw.println("</body></html>");
	}

	public abstract String getTitle();

	protected abstract void displayDataInTable(PrintWriter pw);

}
