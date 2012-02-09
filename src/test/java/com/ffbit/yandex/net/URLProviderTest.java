package com.ffbit.yandex.net;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Test;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.net.URLMalformedException;
import com.ffbit.yandex.net.URLNotSpecifiedException;
import com.ffbit.yandex.net.URLProvider;

public class URLProviderTest {
    
    @Test
    public void itShouldProvideURL()  throws Exception {
        String[] args = {"--url", "http://yandex.ru"};
        URL url = URLProvider.provide(Arguments.parse(args));
        
        assertNotNull(url);
    }
    
    @Test(expected = URLNotSpecifiedException.class)
    public void itShouldReactToAbsenceOfUrlKey() {
        String[] args = {"--email", "email@ya.ru"};
        URLProvider.provide(Arguments.parse(args));
    }
    
    @Test(expected = URLMalformedException.class)
    public void itShouldReactToMalformedURL() {
        String[] args = {"--url", "ya.ru"};
        URLProvider.provide(Arguments.parse(args));
    }
    
}
