package com.ffbit.yandex.report.jdbc;

import com.ffbit.yandex.report.ReporterException;

public class JDBCReporterException extends ReporterException {
    private static final long serialVersionUID = 5149201787567480655L;

    public JDBCReporterException(String reason) {
        super("Could not persist report to the database.\nReason: " + reason);
    }

    public JDBCReporterException(Throwable cause) {
        this(cause.getMessage());
    }

}
