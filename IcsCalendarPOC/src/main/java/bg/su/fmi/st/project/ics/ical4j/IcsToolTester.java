package bg.su.fmi.st.project.ics.ical4j;

import bg.su.fmi.st.project.ics.ical4j.model.StubEvent;

/**
 *
 * Class for testing the iCal4J lib.
 *
 * @author Leni Kirilov
 */
public class IcsToolTester {

    public static void main(String[] args) {
        ICalConverter converter = new ICalConverter();
        converter.convertEventToIcs(new StubEvent(), "c:\\temp\\mycalendar.ics");
    }
}
