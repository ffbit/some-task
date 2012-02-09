package com.ffbit.yandex.report;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.io.FileReporter;
import com.ffbit.yandex.report.jdbc.JDBCReporter;
import com.ffbit.yandex.report.mail.MailReporter;

@RunWith(Parameterized.class)
public class DefaultReportersFacadeFactoryTest {
    private String[] args;
    private Class<Reporter> reporterClazz;
    private Arguments arguments;
    private ReportersFacade facade;
    
    public DefaultReportersFacadeFactoryTest(String[] args, Class<Reporter> reporterClazz) {
        this.args = args;
        this.reporterClazz = reporterClazz;
    }

    @Parameters
    public static Collection<Object[]> init() {
        Object[][] parameters = {
                { "--output-file /tmp/out.txt".split(" "), FileReporter.class },
                { "--email good@email.com".split(" "), MailReporter.class},
                { "--db-type mysql --db-host localhost --db-port 3306 --db-schema reports --db-user user --db-password pass".split(" "), JDBCReporter.class }
        };
        
        return Arrays.asList(parameters);
    }
    
    @Before
    public void setUp() {
        arguments = Arguments.parse(args);
        facade = DefaultReportersFacade.newInstance(arguments);
    }
    
    @Test
    public void itShouldProduceReportersFacade() {
        assertNotNull(DefaultReportersFacade.newInstance(arguments));
    }
    
    @Test
    public void itShouldContainReporters() {
        assertFalse(facade.getReporters().isEmpty());
    }
    
    @Test
    public void itShouldFollowIncapsulationRulesWhenRetrievesReporters() {
        assertEquals(facade.getReporters(), facade.getReporters());
        assertNotSame(facade.getReporters(), facade.getReporters());
    }
    
    @Test
    public void itShouldFollowIncapsulationRulesWhenSetsReporters() {
        Collection<Reporter> reporters = facade.getReporters();
        reporters.add(new DummyReporter());
        
        assertThat(facade.getReporters(), not(equalTo(reporters)));
    }
    
    @Test
    public void itShouldContainAppropriateReporter() {
        boolean contains = false;
        
        // TODO: Should be refactored.
        for (Reporter reporter : facade.getReporters()) {
            if (reporterClazz.isInstance(reporter)) {
                contains = true;
            }
        }
        
        assertTrue(appropriateReportClassFailureMessage(), contains);
    }

    private String appropriateReportClassFailureMessage() {
        return String.format("It doesn't contain a report of %s class", reporterClazz);
    }
    
}
