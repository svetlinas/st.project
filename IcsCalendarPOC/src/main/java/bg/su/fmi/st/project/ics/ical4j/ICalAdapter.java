package bg.su.fmi.st.project.ics.ical4j;

import bg.su.fmi.st.project.ics.ical4j.model.IEvent;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.TzId;

/**
 *
 * Adapts Calendar++ model to that of iCal for usage by Converter.
 *
 * @author Leni Kirilov
 */
public class ICalAdapter {

    //TODO not implemented
    public VEvent convertEvent(IEvent event) {

        Date convertedStartDate = convertDate(event.getStartDate());
        Date convertedEndDate = convertDate(event.getEndDate());

        VEvent convertedEvent = new VEvent(convertedStartDate, convertedEndDate, event.getTitle());

        //add timezone info
        convertedEvent.getProperties().add(convertTimezone(event.getStartDate()));
        return convertedEvent;
    }

    private Date convertDate(java.util.Calendar date) {
        return new Date(date.getTime());
    }

    private TzId convertTimezone(java.util.Calendar date) {
        return convertTimezone(date.getTimeZone());
    }

    private TzId convertTimezone(java.util.TimeZone timezone) {
        String timezoneId = timezone.getID();
        TimeZone convertedTimezone = TimeZoneRegistryFactory.getInstance().createRegistry().getTimeZone(timezoneId);
        return convertedTimezone.getVTimeZone().getTimeZoneId();
    }
}
