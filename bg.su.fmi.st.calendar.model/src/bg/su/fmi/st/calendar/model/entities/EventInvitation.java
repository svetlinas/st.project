package bg.su.fmi.st.calendar.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EventInvitation {

	public enum InvitationResponse {
		YES, NO
	}

	public EventInvitation() {
	}

	public EventInvitation(Event event, User user) {
		this.event = event;
		this.invitedUser = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private User invitedUser;

	@ManyToOne
	private Event event;

	@Column(name = "INVITATION_RESPONSE")
	@Enumerated(EnumType.STRING)
	private InvitationResponse response;

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

}
