package com.ffbit.yandex.report.mail;

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
    
    DefaultSendMail(Properties properties, String user, String pass) {
        this.properties = new Properties(properties);
        this.user = user;
        this.pass = pass;
    }
    
    public static SendMail newInstace() {
        return new HardCodedGmailSMPTSendMail();
    }

    @Override
    public void send(String to, String subject, String text) {
        Session session = Session.getDefaultInstance(properties, null);
        
        // TODO: Should be refactored.
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
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
