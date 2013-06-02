package bg.su.fmi.st.calendar.model.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name="Event")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement(name="id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "organizer_id")
	@XmlElement(name="organizer")
	private User organizer;

	@XmlElement(name="title")
	private String title;

	@XmlElement(name="place")
	private String place;

	@XmlElement(name="startDate")
	private Date startDate;

	@XmlElement(name="endDate")
	private Date endDate;

	@XmlElement(name="type")
	private String type;
	
	@XmlElement(name="details")
	private String details;
	
	public static String EVENT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
	private static DateFormat df = new SimpleDateFormat(EVENT_DATE_FORMAT);
	
	@Override
	public String toString() {
		
		return String.format("title= %s; place= %s startDate= %s, endDate= %s; type= %s; details= %s",
				this.title, this.place, df.format(this.startDate), df.format(this.endDate), this.type, this.details);
	}
	
	public Event(User organizer, String title, String place, Date startDate,
			Date endDate, String type, String details) {
		super();
		this.organizer = organizer;
		this.title = title;
		this.place = place;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.details = details;
	}
	
	public Event(User organizer, String title, String place, String startDate,
			String endDate, String type, String details) {
		this(organizer, title, place, parseDate(startDate), parseDate(endDate), type, details);
	}	
	
	/**
	 * 
	 * @param dateString
	 *            - the date in format YYYY-MM-DDTHH:MM
	 * @return
	 */
	public static Date parseDate(String dateString) {
		String parsableDateString = dateString.replace("T", " ");
		SimpleDateFormat dateFormat = new SimpleDateFormat(Event.EVENT_DATE_FORMAT);
		try {
			return dateFormat.parse(parsableDateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String dateToString(Date date){
		return df.format(date);
	}

	public User getOrganizer() {
		return organizer;
	}

	public void setOrganizer(User organizer) {
		this.organizer = organizer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public void setStartDate(String startDateStr) {
		this.startDate = parseDate(startDateStr);
	}


	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void setEndDate(String endDateStr) {
		this.endDate = parseDate(endDateStr);
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public long getId() {
		return id;
	}
	
	public Event() {
	}
}
