package bg.su.fmi.st.calendar.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name="EventInvitation")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventInvitation {

	public enum InvitationResponse {
		YES, NO, Waiting
	}

	public EventInvitation() {
	}

	public EventInvitation(Event event, User user) {
		this.event = event;
		this.invitedUser = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement(name="id")
	private long id;

	@ManyToOne
	@XmlElement(name="user")
	private User invitedUser;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@XmlElement(name="event")
	private Event event;

	@Column(name = "INVITATION_RESPONSE")
	@Enumerated(EnumType.STRING)
	@XmlElement(name="response")
	private InvitationResponse response;

	@XmlElement(name="comment")
	private String comment;

	public User getInvitedUser() {
		return invitedUser;
	}

	public void setInvitedUser(User invitedUser) {
		this.invitedUser = invitedUser;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public InvitationResponse getResponse() {
		return response;
	}

	public void setResponse(InvitationResponse response) {
		this.response = response;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("Id=%d, InvitedUser=%s, EventName=%s, Response=%s, Comment=%s",
				id, invitedUser.getName(), event.getTitle(), response, comment);
	}
}
