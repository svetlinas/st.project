package bg.su.fmi.project.remote;

import java.text.MessageFormat;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import bg.su.fmi.st.calendar.mail.MailData;
import bg.su.fmi.st.calendar.mail.MailSender;
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
			@FormParam("toAddress") String toAddress,
			@FormParam("fromName") String fromName) {
		String baseUri = uriInfo.getBaseUri().toString();
		String mailText = MessageFormat.format(MAIL_INVITATION_CONTENT,
				fromName, baseUri + "/signup.html");
		MailData mailData = new MailData();
		mailData.setHost(HOST);
		mailData.setSubject(MAIL_INVITATION_SUBJECT);
		mailData.setText(mailText);
		mailData.setToAddress(toAddress);
		MailSender mailSender = new MailSender();
		mailSender.send(mailData);
	}

}
