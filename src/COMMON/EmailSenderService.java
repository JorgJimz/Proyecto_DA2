package COMMON;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderService {

    private final Properties properties = new Properties();

    private String password;

    private Session session;

    private void init() {

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.mail.sender", "jrg.jmnz.prd@gmail.com");
        properties.put("mail.smtp.user", "jrg.jmnz.prd@gmail.com");
        properties.put("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties);
    }

    public void sendEmail(int idOc) {
        init();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("jrg.jmnz.prd@gmail.com"));
            message.setSubject("Orden de Compra Generada");
            message.setText("Se generó la orden de compra N°" + String.format("%05d", idOc) + " para su atención a la brevedad posible." + "\n" + "Enviado desde: Sistema LAS DELICIAS");
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), "v3kuhg6gy0jxbqi6*");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }

    }

}
