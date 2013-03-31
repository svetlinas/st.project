package bg.su.fmi.st.calendar.model.entities;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class EventInvitation {

	public enum InvitationResponse {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private User invitee;

	@ManyToOne
	private Event event;

	@Column(name = "INITATION_RESPONSE")
	@Enumerated(EnumType.ORDINAL)
	private InvitationResponse response;

	private String comment;

}
