package com.delivery.testDelivery.shared.utils.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {

    public void sendNotification(String subject, String message, String emailTo) throws EmailException {
        Email email = emailConfig();
        emailInformation(subject, message, email);
        email.addTo(emailTo);
        email.send();
    }

    public void sendNotificationGroup(String subject, String message, String[] emailTo) throws EmailException {
        Email email = emailConfig();
        emailInformation(subject, message, email);
        email.addTo(emailTo);
        email.send();
    }

    private void emailInformation(String subject, String message, Email email) {
        try {
            email.setFrom("dijetal.noreply@gmail.com");
            email.setSubject(subject);
            email.setMsg(message);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public Email emailConfig() {
        Email email = new SimpleEmail();
        email.setCharset("utf-8");
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("dijetal.noreply@gmail.com", "35Ereban"));
        email.setSSLOnConnect(true);
        return email;
    }


}
