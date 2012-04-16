package fr.devoxx.lepresidentest;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.devoxx.lepresidentest.entity.Comment;

@SuppressWarnings("serial")
public class MailHandlerServlet extends HttpServlet { 
    public void doPost(HttpServletRequest req, 
                       HttpServletResponse resp) 
            throws IOException, ServletException { 
        Properties props = new Properties(); 
        Session session = Session.getDefaultInstance(props, null); 
        try {
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			Comment.store(getContent(message), getUser(message));
			Message reply = message.reply(false);
			reply.setFrom(new InternetAddress("commentaires@electionfr2012.appspotmail.com"));
			reply.setText("Merci pour votre commentaire\nRetouvez votre commentaire en ligne : http://electionfr2012.appspot.com/comments.jsp");
			Transport.send(reply);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
    }

	public String getContent(MimeMessage message) throws IOException, MessagingException {
		return (String)((Multipart)message.getContent()).getBodyPart(0).getContent();
	}

	public String getUser(MimeMessage message) throws MessagingException {
		InternetAddress email = (InternetAddress)message.getFrom()[0];
		return email.getPersonal() != null ? email.getPersonal() : email.getAddress();
	}
}