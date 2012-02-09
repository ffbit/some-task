package com.ffbit.yandex.report.mail;

import java.math.BigInteger;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.DummyReporter;
import com.ffbit.yandex.report.Reporter;

public class MailReporter implements Reporter {
    public static final String KEY = "--email";
    private static EmailValidator validator = new EmailValidator();
    
    private String email;
    
    private MailReporter(String email) {
        this.email = email;
    }

    public static Reporter newInstance(Arguments arguments) {
        if (!arguments.contains(KEY)) {
            return new DummyReporter();
        }
        
        String email = arguments.get(KEY);
        
        if (validator.validate(email)) {
            return new MailReporter(email);
        }
        
        throw new EmailMalformedException(email);
    }

    @Override
    public void report(BigInteger quantity) {
        System.out.println("MailReporter has been invoked.");
        
        SendMail sendMail = DefaultSendMail.newInstace();
        sendMail.send(email, "XML parsing report", quantity.toString());
    }

}
