package com.ffbit.yandex.processor;

public class ProcessorException extends RuntimeException {
    private static final long serialVersionUID = -4319153474799878265L;

    public ProcessorException() {
        this("");
    }

    public ProcessorException(String reason) {
        super("An exception occured during processing.\nReason: " + reason);
    }

    public ProcessorException(String message, Throwable cause) {
        this(message + ": " + cause.getLocalizedMessage());
    }

    public ProcessorException(Throwable cause) {
        this(cause.getMessage());
    }

}
