package it.interlogic.vimp.batch;

import it.interlogic.vimp.utils.LoggerUtility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMail
{
	public static void main(String[] args)
	{
		LoggerUtility.error("----------------- Send mail begin");
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtpmail.comune.genova.it");
			props.put("mail.smtp.port", "25");
			props.put("mail.smtp.auth", "false");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.debug", "true");

			Session session = Session.getInstance(props);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply@vimp.comune.genova.it"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("agostino.moronese@gmail.com"));
			message.setSubject("Vetrina imprese - Allineamento CCIAA-Registro");
			message.setText("Test test");
			
			
			// TODO SEND MAIL
			Transport.send(message);

		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
		LoggerUtility.error("----------------- Send mail end");
	}
}
