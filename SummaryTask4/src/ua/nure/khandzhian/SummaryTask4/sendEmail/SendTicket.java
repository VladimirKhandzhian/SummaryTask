package ua.nure.khandzhian.SummaryTask4.sendEmail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import ua.nure.khandzhian.SummaryTask4.db.entity.Orders;

public class SendTicket {
	
	public void sendEmail(String email, Orders order, String ticketForSend) throws IOException, MessagingException {
		
		final Properties properties = new Properties();
		properties.load(SendTicket.class.getClassLoader().getResourceAsStream("mail.properties"));

		Session mailSession = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("volodymyr.khandzhian"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		message.setSubject("Ticket");
		message.setText("Thank you for you buying");
		
		File file = new File("C:\\Users\\Vova4\\Desktop\\Tickets\\Flight №" + 
				order.getId() + "Ticket ¹" + ticketForSend + ".pdf");
		
		MimeMultipart multipart = new MimeMultipart();
		MimeBodyPart part2 = new MimeBodyPart();
		part2.setFileName(MimeUtility.encodeWord(file.getName()));
		part2.setDataHandler(new DataHandler(new FileDataSource(file)));
		multipart.addBodyPart(part2);
		
		message.setContent(multipart);
	
		Transport tr = mailSession.getTransport();
		tr.connect(null, "Vova17082002");
		tr.sendMessage(message, message.getAllRecipients());
		tr.close();
	}
}
