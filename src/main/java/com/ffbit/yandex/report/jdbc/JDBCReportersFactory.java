package com.ffbit.yandex.report.jdbc;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.DummyReporter;
import com.ffbit.yandex.report.Reporter;
import com.ffbit.yandex.report.jdbc.mysql.DefaultMySQLReporter;

public abstract class JDBCReportersFactory {
    public static final String RDBMS_KEY = "--db-type";

    public static Reporter newInstance(Arguments arguments) {
        if (!arguments.contains(RDBMS_KEY)) {
            return new DummyReporter();
        }
        
        String rdbms = arguments.get(RDBMS_KEY).toLowerCase();
        
        if (rdbms.equals("mysql")) {
            return DefaultMySQLReporter.newInstance(arguments);
        }
        
        throw new UnsupportedRDBMSException(arguments.get(RDBMS_KEY));
    }

}
