package com.ffbit.yandex.args;


public class EmptyArgumentsException extends ArgumentException {
    private static final long serialVersionUID = 2091870320356422847L;

    public EmptyArgumentsException() {
        super("Arguments list is empty.");
    }
    
}
