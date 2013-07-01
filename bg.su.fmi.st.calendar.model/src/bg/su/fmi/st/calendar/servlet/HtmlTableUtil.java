package bg.su.fmi.st.calendar.servlet;

import java.io.PrintWriter;

/**
 * Utility class for displaying tables and rows in HTML
 * 
 * @author Leni Kirilov
 *
 */
public class HtmlTableUtil {

	static void displayColumnLabels(PrintWriter pw, String[] columnLabels) {
		for (String label : columnLabels) {
			displayCell(pw, label, true);
		}
	}

	static void displayCell(PrintWriter pw, String cellText, boolean isBold) {
		if (isBold) {
			pw.printf("<td><b>%s</b></td>", cellText);
		} else {
			pw.printf("<td>%s</td>", cellText);
		}
	}

	static void displayCellWithLink(PrintWriter pw, String columnLabel,
			String link) {
		pw.printf("<td><a href=%s> <b>%s</b> </a></td>", link, columnLabel);
	}
}
