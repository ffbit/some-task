package com.ffbit.yandex.report.jdbc.mysql;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.Reporter;
import com.ffbit.yandex.report.jdbc.JDBCReporter;
import com.ffbit.yandex.report.jdbc.JDBCReporterException;

public class DefaultMySQLReporter implements JDBCReporter {
    private String connectionUrl;
    private String user;
    private String password;
    
    public DefaultMySQLReporter(String connectionString, String user,
            String password) {
        this.connectionUrl = connectionString;
        this.user = user;
        this.password = password;
    }

    public static Reporter newInstance(Arguments arguments) {
        String connectionString = prepareConnectionUrl(arguments);
        String user = arguments.get("--db-user");
        String password = arguments.get("--db-password", "");
        
        return new DefaultMySQLReporter(connectionString, user, password);
    }
    
    private static String prepareConnectionUrl(Arguments arguments) {
        String host = arguments.get("--db-host", "localhost");
        String port = arguments.get("--db-port", "3306");
        String schema = arguments.get("--db-schema");
        
        return String.format("jdbc:mysql://%s:%s/%s", host, port, schema);
    }
    
    @Override
    public void report(BigInteger quantity) {
        System.out.println("DefaultMySQLReporter has been invoked.");
        
        Connection connection = null;
        
        try {
            connection = openConnection();
            insert(quantity, connection);
        } catch (SQLException e) {
            throw new JDBCReporterException(e);
        }
        
        try {
            connection.close();
        } catch (SQLException e) {
            // Nothing to do.
        }
    }

    private void insert(BigInteger quantity, Connection connection)
            throws SQLException {
        PreparedStatement statement = getPreparedStatement(connection);
        
        statement.setBigDecimal(1, new BigDecimal(quantity));
        statement.execute();
        
        statement.close();
    }

    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, user, password);
    }
    
    private PreparedStatement getPreparedStatement(
            Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO `reports`(`quantity`) VALUES(?)");
    }

}
