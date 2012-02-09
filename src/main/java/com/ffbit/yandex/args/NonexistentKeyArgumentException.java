package com.ffbit.yandex.args;


public class NonexistentKeyArgumentException extends ArgumentException {
    private static final long serialVersionUID = -6786613853408835442L;
    
    public NonexistentKeyArgumentException(String key) {
        super(String.format("Nonexistent key `%s`", key));
    }
    
}
