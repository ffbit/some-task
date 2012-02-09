package com.ffbit.yandex.xml;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Before;
import org.junit.Test;

import com.ffbit.testhelper.URLTestHelper;

public class ItemHandlerTest {
    private String pseudoXpath;
    private String attribute;
    private int minValue;
    private SAXParser parser;
    private URL url;
    
    @Before
    public void setUp() throws Exception {
        url = URLTestHelper.makeURLFromPathname("src/test/resources/source.xml");
        
        pseudoXpath = "/root/records/item";
        attribute = "count";
        minValue = 7;
        
        SAXParserFactory factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
    }
    
    @Test
    public void testUrl() throws Exception {
        ItemHandler handler = new ItemHandler(pseudoXpath, attribute, minValue);
        parser.parse(url.toString(), handler);
        
        assertEquals(BigInteger.valueOf(3), handler.getQuantity());
    }
    
}
