package com.ffbit.yandex.report.mail;

import java.util.Properties;

public class HardCodedGmailSMPTSendMail implements SendMail {

    private SendMail actualSendMail;
    
    public HardCodedGmailSMPTSendMail() {
        initSendMail();
    }

    private void initSendMail() {
        String user = "some.example1@gmail.com";
        String pass = "1qaz12345";
        
        actualSendMail = new DefaultSendMail(getProperties(), user, pass);
    }
    
    private Properties getProperties() {
        Properties properties = System.getProperties();
        
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        
        return properties;
    }
    
    @Override
    public void send(String to, String subject, String body) {
        actualSendMail.send(to, subject, body);
    }
    
}
    
