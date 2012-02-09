package com.ffbit.yandex.report.jdbc;

import com.ffbit.yandex.args.ArgumentException;

public class UnsupportedRDBMSException extends ArgumentException {
    private static final long serialVersionUID = -2209262413785167346L;

    public UnsupportedRDBMSException() {
        super();
    }

    public UnsupportedRDBMSException(String dbtype) {
        super("Unsupported RDBMS `" + dbtype + "`.");
    }

    public UnsupportedRDBMSException(Throwable cause) {
        super(cause);
    }

}
