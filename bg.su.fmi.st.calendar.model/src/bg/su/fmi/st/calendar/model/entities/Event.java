package bg.su.fmi.st.calendar.model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "organizer_id")
	private User organizer;

	private String title;

	private String place;

	private Date startDate;

	private Date endDate;

	private String type;
	
	private String details;

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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	
	public Event() {
	}

}
