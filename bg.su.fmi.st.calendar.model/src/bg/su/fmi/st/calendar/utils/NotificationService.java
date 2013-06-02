package bg.su.fmi.st.calendar.utils;

import java.text.MessageFormat;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import bg.su.fmi.st.calendar.mail.MailData;
import bg.su.fmi.st.calendar.mail.MailSender;
import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.EventInvitation;
import bg.su.fmi.st.calendar.model.manager.EventInvitationDAO;

@Stateless
public class NotificationService {

	private static final String MAIL_INVITATION_SUBJECT = "Changed event in Sport Events Organizer.";
	private static final String MAIL_INVITATION_CONTENT = "Hello! You are receiving this message, because an event that you are invited to - {0} is changed. To view the updated event details please use the following link {1} . Thank You!";
	private static final String HOST = "smtp.gmail.com";

	@Context
	private UriInfo uriInfo;

	@EJB
	private EventInvitationDAO invitationDao;

	public void notifyUsersForChangedEvent(Event event) {
		Collection<EventInvitation> eventInvitations = invitationDao
				.getEventInvitations(event);
		// TODO: provide a real link to the event
		String baseUri = "http://localhost:8080/bg.su.fmi.st.calendar.model/";

		for (EventInvitation invitation : eventInvitations) {
			String mailText = MessageFormat.format(MAIL_INVITATION_CONTENT,
					event.getTitle(), baseUri + "/events/" + event.getId());
			MailData mailData = new MailData();
			mailData.setHost(HOST);
			mailData.setSubject(MAIL_INVITATION_SUBJECT);
			mailData.setText(mailText);
			mailData.setToAddress(invitation.getInvitedUser().getEmail());
			MailSender mailSender = new MailSender();
			mailSender.send(mailData);
		}
	}
}
