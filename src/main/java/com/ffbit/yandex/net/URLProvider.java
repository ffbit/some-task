package com.ffbit.yandex.net;

import java.net.MalformedURLException;
import java.net.URL;

import com.ffbit.yandex.args.Arguments;

public class URLProvider {
    public static final String KEY = "--url"; 

    public static URL provide(Arguments arguments) {
        if (!arguments.contains(KEY)) {
            throw new URLNotSpecifiedException();
        }
        
        try {
            return new URL(arguments.get(KEY));
        } catch (MalformedURLException e) {
            throw new URLMalformedException(arguments.get(KEY), e);
        }
    }

}
