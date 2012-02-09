package com.ffbit.yandex.args;


public class DuplicatedKeyArgumentException extends ArgumentException {
    private static final long serialVersionUID = -6786613853408835442L;
    
    public DuplicatedKeyArgumentException(String key) {
        super(String.format("Duplicated key `%s`", key));
    }
    
}
