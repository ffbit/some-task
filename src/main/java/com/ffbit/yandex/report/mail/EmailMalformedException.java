package com.ffbit.yandex.report.mail;

import com.ffbit.yandex.args.ArgumentException;

public class EmailMalformedException extends ArgumentException {
    private static final long serialVersionUID = -3290472912059778146L;

    public EmailMalformedException(String email) {
        super("Malformed email address: `" + email + "`");
    }

}
