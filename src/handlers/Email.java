package handlers;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class send a log message by email
 * @author Александр
 *
 */
public class Email implements Handler {
	/**
	 * send a log message by email
	 * @param message log message
	 * @throws UnsupportedEncodingException
	 */
	public void processMassage(String message) throws UnsupportedEncodingException{
        String to = "shakhrayas@gmail.com";
        String from = "shahryalex@mail.ru";
        String host = "smtp.mail.ru";
 
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.debug", "true");
        Session session = Session.getInstance(props);
 
        try {
            Message msg = new MimeMessage(session);
 
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Test E-Mail through Java");
            msg.setSentDate(new Date());
 
            msg.setText(message);
 
            Transport.send(msg);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
	}
}
