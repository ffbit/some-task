package com.ffbit.yandex.xml;

import static org.junit.Assert.assertArrayEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ffbit.yandex.xml.SimplestXpathSplitter;

public class SimplestXpathSplitterTest {
    private SimplestXpathSplitter splitter;
    
    @Before
    public void setUp() {
        splitter = new SimplestXpathSplitter();
    }
    
    @Test
    public void testSplit() {
        String xpath = "/root/records/item";
        List<String> actuals = splitter.split(xpath);
        
        assertArrayEquals(new String[] {"root", "records", "item"}, actuals.toArray());
    }
    
}
