package com.ffbit.yandex.args;


public class DuplicatedValueArgumentException extends ArgumentException {
    private static final long serialVersionUID = -6786613853408835442L;
    
    public DuplicatedValueArgumentException(String value) {
        super(String.format("Duplicated value `%s`", value));
    }
    
}
