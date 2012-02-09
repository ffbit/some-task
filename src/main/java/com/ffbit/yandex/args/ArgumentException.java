package com.ffbit.yandex.args;

public class ArgumentException extends RuntimeException {
    private static final long serialVersionUID = -8305472636222381893L;
    
    public ArgumentException() {
        super();
    }
    
    public ArgumentException(String message) {
        super(message);
    }

    public ArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgumentException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n"
                + "For usage information see here - https://github.com/ffbit/yandex-task";
    }

}
