package com.ffbit.yandex.report.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DefaultSendMail implements SendMail {
    private Properties properties;
    private String user;
    private String pass;

    DefaultSendMail() {
        initProperties();

        this.user = properties.getProperty("mail.username");
        this.pass = properties.getProperty("mail.password");
    }

    private void initProperties() {
        properties = new Properties();

        try {
            properties.load(DefaultSendMail.class.getClassLoader()
                    .getResourceAsStream("java.mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(String to, String subject, String text) {
        Session session = Session.getDefaultInstance(properties, null);

        // TODO: Should be refactored.
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject(subject);
            message.setText("Quantity: " + text);

            Transport transport = session.getTransport();
            transport.connect(user, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            throw new MailReporterException(e.getMessage());
        }
    }

}
