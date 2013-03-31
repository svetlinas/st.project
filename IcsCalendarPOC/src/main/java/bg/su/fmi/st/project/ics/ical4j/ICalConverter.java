package bg.su.fmi.st.project.ics.ical4j;

import bg.su.fmi.st.project.ics.ical4j.model.IEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyFactoryRegistry;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;

/**
 *
 * Class for converting Events and Calendars to .ics format using iCal4j
 *
 * Check this page for usage examples:
 * http://wiki.modularity.net.au/ical4j/index.php?title=Examples#Creating_a_meeting_of_four_hour_duration
 *
 * @author Leni Kirilov
 */
public class ICalConverter {

    private ICalAdapter adapter = new ICalAdapter();

    public void convertEventToIcs(IEvent iEvent, String destinationFile) {
        convertEventToIcs(iEvent, new File(destinationFile));
    }

    //TODO not implemented
    public void convertEventToIcs(IEvent iEvent, File destination) {
        VEvent convertedEvent = adapter.convertEvent(iEvent);
        Calendar icsCalendar = createIcalCalendar(convertedEvent);
        writeIcsToFile(icsCalendar, destination);
    }

    private void writeIcsToFile(Calendar icsCalendar, File destination) {

        FileOutputStream fout = null;
        try {
            destination.createNewFile();
            fout = new FileOutputStream(destination);
            CalendarOutputter outputter = new CalendarOutputter(true);
            outputter.output(icsCalendar, fout);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ICalConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ICalConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidationException ex) {
            Logger.getLogger(ICalConverter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(ICalConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    public void printToScreen(){}
    private Calendar createIcalCalendar(VEvent meeting) {
        //TODO create/find proper ID generator
        // generate unique identifier..
//        UidGenerator ug = new UidGenerator("uidGen");
//        Uid uid = ug.generateUid();
        Uid uid = new Uid(Long.toString(System.currentTimeMillis()));
        meeting.getProperties().add(uid);

        Property version = PropertyFactoryRegistry.getInstance().createProperty(Property.VERSION);
        meeting.getProperties().add(version);

//        // add attendees..
//        Attendee dev1 = new Attendee(URI.create("mailto:dev1@mycompany.com"));
//        dev1.getParameters().add(Role.REQ_PARTICIPANT);
//        dev1.getParameters().add(new Cn("Developer 1"));
//        meeting.getProperties().add(dev1);
//
//        Attendee dev2 = new Attendee(URI.create("mailto:dev2@mycompany.com"));
//        dev2.getParameters().add(Role.OPT_PARTICIPANT);
//        dev2.getParameters().add(new Cn("Developer 2"));
//        meeting.getProperties().add(dev2);

        // Create a calendar
        Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
        icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
        icsCalendar.getProperties().add(Version.VERSION_2_0);
        icsCalendar.getProperties().add(CalScale.GREGORIAN);

        // Add the event and print
        icsCalendar.getComponents().add(meeting);

        return icsCalendar;
    }
}
