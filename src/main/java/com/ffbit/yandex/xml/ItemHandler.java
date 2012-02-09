package com.ffbit.yandex.xml;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ItemHandler extends DefaultHandler {
    private String pseudoXpath;
    private String attribute;
    private BigInteger minValue;
    private BigInteger quantity;
    private List<String> targetDepth;
    private Deque<String> currentDepth;
    
    public ItemHandler(String pseudoXpath, String attribute,
            BigInteger minValue) {
        this.pseudoXpath = pseudoXpath;
        this.attribute = attribute;
        this.minValue = minValue;
        this.quantity = BigInteger.ZERO;
        
        currentDepth = new LinkedList<String>();
        initTargetStack();
    }
    
    public ItemHandler(String pseudoXpath, String attribute, long minValue) {
        this(pseudoXpath, attribute, BigInteger.valueOf(minValue));
    }

    private void initTargetStack() {
        SimplestXpathSplitter splitter = new SimplestXpathSplitter();
        targetDepth = Collections.unmodifiableList(splitter.split(pseudoXpath));
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        currentDepth.addLast(qName);
        
        if (isRightDepth()) {
            checkAttribute(attributes);
        }
    }
    
    private boolean isRightDepth() {
        return currentDepth.equals(targetDepth);
    }

    private void checkAttribute(Attributes attributes) {
        String attributeValue = attributes.getValue(attribute);
        
        if (isNotANumber(attributeValue)) {
            return;
        }
        
        BigInteger value = new BigInteger(attributeValue);
        
        if (isEqualToOrGreaterThanMinValue(value)) {
            incrementQuantity();
        }
    }

    private boolean isNotANumber(String value) {
        return value == null || !value.matches("^\\d+$");
    }
    
    private boolean isEqualToOrGreaterThanMinValue(BigInteger value) {
        return value.compareTo(minValue) >= 0;
    }

    private void incrementQuantity() {
        quantity = quantity.add(BigInteger.ONE);
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currentDepth.removeLast();
    }

    public BigInteger getQuantity() {
        return quantity;
    }
    
}
