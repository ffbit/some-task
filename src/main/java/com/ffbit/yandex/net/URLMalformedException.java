package com.ffbit.yandex.net;

import com.ffbit.yandex.args.ArgumentException;

public class URLMalformedException extends ArgumentException {
    private static final long serialVersionUID = 1L;

    public URLMalformedException(String url, Throwable cause) {
        super("Malformed URL `" + url + "`.\nReason: " + cause.getMessage());
    }

}
