package com.ffbit.yandex.report;

public class ReporterException extends RuntimeException {
    private static final long serialVersionUID = -1984144328253806128L;

    public ReporterException() {
        super();
    }
    
    public ReporterException(String message) {
        super(message);
    }

    public ReporterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReporterException(Throwable cause) {
        super(cause);
    }
    
}
