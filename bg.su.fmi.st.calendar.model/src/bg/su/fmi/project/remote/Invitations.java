package bg.su.fmi.project.remote;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import bg.su.fmi.st.calendar.mail.MailData;
import bg.su.fmi.st.calendar.mail.MailSender;
import bg.su.fmi.st.calendar.model.entities.Event;
import bg.su.fmi.st.calendar.model.entities.EventInvitation;
import bg.su.fmi.st.calendar.model.manager.EventDAO;
import bg.su.fmi.st.calendar.model.manager.EventInvitationDAO;

/**
 * REST resource responsible for interactions with invitations.
 * 
 * @author Serafim Karparov
 * 
 */
@Path("/invitations")
public class Invitations {

	@EJB
	EventInvitationDAO invitationDao;

	private static final String MAIL_INVITATION_SUBJECT = "Invitation for registration in Sport Events Organizer.";

	private static final String MAIL_INVITATION_CONTENT = "Hello! Your friend {0} is sending you an invitation for registration in the Sport Events Organizer. Please use the following link {1} . Thank You!";

	private static final String HOST = "smtp.gmail.com";

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void createEvent(@Context UriInfo uriInfo,
			@FormParam("toAddress") String toAddress) {
		String baseUri = uriInfo.getBaseUri().toString();
		String mailText = MessageFormat.format(MAIL_INVITATION_CONTENT,
				"DEFAULT_USER", baseUri + "/signup.html"); // TODO: Valid user
															// and URL
		MailData mailData = new MailData();
		mailData.setHost(HOST);
		mailData.setSubject(MAIL_INVITATION_SUBJECT);
		mailData.setText(mailText);
		mailData.setToAddress(toAddress);
		MailSender mailSender = new MailSender();
		mailSender.send(mailData);
	}

}
