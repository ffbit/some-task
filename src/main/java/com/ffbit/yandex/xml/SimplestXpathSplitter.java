package com.ffbit.yandex.xml;

import java.util.Arrays;
import java.util.List;

public class SimplestXpathSplitter {

    public List<String> split(final String xpath) {
        String result[] = xpath.replaceAll("^/*", "").split("/");
        return Arrays.asList(result);
    }

}
