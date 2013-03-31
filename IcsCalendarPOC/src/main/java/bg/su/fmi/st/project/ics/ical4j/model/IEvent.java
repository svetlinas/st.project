package bg.su.fmi.st.project.ics.ical4j.model;

import java.util.Calendar;

/**
 * @author Leni Kirilov
 */
public interface IEvent {

    Calendar getStartDate();

    Calendar getEndDate();

    String getTitle();
}
