package com.ffbit.testhelper;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class URLTestHelper {
    
    public static URL makeURLFromPathname(String pathname) throws IOException {
        File file = new File(pathname);
        return file.toURI().toURL();
    }
    
}