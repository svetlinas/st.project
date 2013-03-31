package bg.su.fmi.st.project.ics.ical4j.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * Stub event for testing(until real implementation of events is done
 *
 * @author Leni Kirilov
 */
public class StubEvent implements IEvent {

    private TimeZone getTimezone() {
//        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
//        TimeZone timezone = registry.getTimeZone("America/Mexico_City");
        return TimeZone.getTimeZone("UTC");
    }

    @Override
    public Calendar getStartDate() {
        // Start Date is on: April 1, 2008, 9:00 am
        Calendar startDate = new GregorianCalendar();
        startDate.setTimeZone(getTimezone());
        startDate.set(Calendar.MONTH, Calendar.APRIL);
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        startDate.set(Calendar.YEAR, 2013);
        startDate.set(Calendar.HOUR_OF_DAY, 9);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);

        return startDate;
    }

    @Override
    public Calendar getEndDate() {
        // End Date is on: April 1, 2008, 13:00
        Calendar endDate = new GregorianCalendar();
        endDate.setTimeZone(getTimezone());
        endDate.set(Calendar.MONTH, Calendar.APRIL);
        endDate.set(Calendar.DAY_OF_MONTH, 1);
        endDate.set(Calendar.YEAR, 2013);
        endDate.set(Calendar.HOUR_OF_DAY, 13);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);

        return endDate;
    }

    @Override
    public String getTitle() {
        return "Test event";
    }
}
