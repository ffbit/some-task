package com.ffbit.yandex.report.jdbc;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.DummyReporter;
import com.ffbit.yandex.report.Reporter;

public class JDBCReportersFactoryTest {
    private Arguments arguments;

    @Before
    public void setUp() {
        arguments = mock(Arguments.class);
    }
    
    @Test
    public void itShouldProduceDummyReporter() {
        when(arguments.contains("--db-type")).thenReturn(false);
        
        Reporter reporter = JDBCReportersFactory.newInstance(arguments);
        
        verify(arguments).contains("--db-type");
        assertThat(reporter, instanceOf(DummyReporter.class));
    }
    
    @Test
    public void itShouldProduceJDBCReporter() {
        arguments = Arguments.parse("--db-type mysql --db-schema reports --db-user user".split(" "));
        arguments = spy(arguments);
        
        Reporter reporter = JDBCReportersFactory.newInstance(arguments);
        
        verify(arguments).get("--db-type");
        verify(arguments).get("--db-port", "3306");
        verify(arguments).get("--db-host", "localhost");
        verify(arguments).get("--db-password", "");
        assertThat(reporter, instanceOf(JDBCReporter.class));
    }
    
    @Test(expected = UnsupportedRDBMSException.class)
    public void itShouldReactToUnsupportedDatabase() {
        arguments = Arguments.parse("--db-type", "unsupported-db");
        
        JDBCReportersFactory.newInstance(arguments);
    }
    
}
