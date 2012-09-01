package com.ffbit.yandex.report.mail;

public interface SendMail {

    public void send(String to, String subject, String body);

}
