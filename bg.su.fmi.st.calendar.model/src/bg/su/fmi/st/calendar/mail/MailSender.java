package bg.su.fmi.st.calendar.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public static final String SMTP = "smtp";

	public boolean send(MailData mailData) {
		Session session = Session.getDefaultInstance(getProperties(mailData),
				null);
		MimeMessage message = new MimeMessage(session);
		Transport transport = null;
		try {
			message.setFrom(new InternetAddress(mailData.getUsername()));
			InternetAddress toInternetAddress = new InternetAddress(
					mailData.getToAddress());
			message.addRecipient(Message.RecipientType.TO, toInternetAddress);

			message.setSubject(mailData.getSubject());
			message.setText(mailData.getText());
			transport = session.getTransport(SMTP);
			transport.connect(mailData.getHost(), mailData.getUsername(),
					mailData.getPassword());
			transport.sendMessage(message, message.getAllRecipients());
		} catch (AddressException e) {
			// TODO: Log
			return false;
		} catch (MessagingException e) {
			// TODO: Log
			return false;
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					// TODO: Log: cannot close transport:
					return false;
				}
			}
		}
		return true;
	}

	private Properties getProperties(MailData mailData) {
		Properties props = System.getProperties(); // TODO: Just new properties?
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", mailData.getHost());
		props.put("mail.smtp.user", mailData.getUsername());
		props.put("mail.smtp.password", mailData.getPassword());
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		return props;
	}

	public static void main(String[] args) throws Exception {
	}

}
