package com.ffbit.yandex.xml;

import java.math.BigInteger;

public class ItemHandlerFactory {
    private String pseudoXpath;
    private String attribute;
    private BigInteger minValue;
    
    public ItemHandlerFactory(String pseudoXpath, String attribute,
            BigInteger minValue) {
        this.pseudoXpath = pseudoXpath;
        this.attribute = attribute;
        this.minValue = minValue;
    }

    public ItemHandler newHandler() {
        return new ItemHandler(pseudoXpath, attribute, minValue);
    }

    
}
