package com.application.ejb;

import static com.application.lib.constants.PropertiesEmailUtil.*;

import java.util.Date;
import java.util.Properties;

import com.application.dao.UserDao;
import com.application.lib.constants.MessageSend;
import com.application.lib.dto.ChangePasswordDto;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
@LocalBean
public class SendEmailBean {

    @PersistenceContext(unitName = "DataSourceUN")
    private EntityManager em;

    private Properties getPropertiesMail() {
        Properties props = new Properties();
        props.put("mail.smtp.host", MAIL_HOST);
        props.put("mail.smtp.port", MAIL_PORT);
        props.put("mail.smtp.auth", MAIL_AUTH ? "true" : "false");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }

    private Authenticator getAuthenticatorMail() {
        Authenticator authenticator = null;
        if (MAIL_AUTH) {
            authenticator = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME_EMAIL, PASSWORD_EMAIL);
                }
            };
        }
        return authenticator;
    }

    private MimeMessage getMimeMessage(Session session, String correo) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM));
        InternetAddress[] address = { new InternetAddress(correo) };
        message.setRecipients(Message.RecipientType.TO, address);
        message.setSubject("subject");
        message.setSentDate(new Date());
        message.setText("hello world");
        return message;
    }

    public String sendMail(ChangePasswordDto change) {
        UserDao userDao = new UserDao();
        boolean userFoundWithEmail = userDao.countUserNameAndOther(em, change);

        if (getAuthenticatorMail() != null && userFoundWithEmail) {
            Session session = Session.getInstance(getPropertiesMail(), getAuthenticatorMail());
            session.setDebug(MAIL_DEBUG);
            try {
                Transport.send(getMimeMessage(session, change.getEmailOrPhoneNumber()));
                return MessageSend.SEND;
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
        return MessageSend.ERROR;
    }
}
