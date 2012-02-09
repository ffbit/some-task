package com.ffbit.yandex.net;

import com.ffbit.yandex.args.ArgumentException;

public class URLNotSpecifiedException extends ArgumentException {
    private static final long serialVersionUID = -1911057267701453442L;

    public URLNotSpecifiedException() {
        super("Source URL has not been specified.");
    }

}
