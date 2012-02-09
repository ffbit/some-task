package com.ffbit.yandex.report;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class DefaultReportersFacadeTest {
    private Collection<Reporter> reporters;
    
    @Before
    public void setUp() {
        reporters = new ArrayList<Reporter>();
    }
    
    @Test
    public void itShouldFollowIncapsulationRules() {
        ReportersFacade facade = new DefaultReportersFacade(reporters);
        reporters.add(new DummyReporter());
        
        assertThat(facade.getReporters(), not(equalTo(reporters)));
    }
    
    @Test
    public void itShouldReport() {
        Reporter mockReporter = mock(DummyReporter.class);
        reporters.add(mockReporter);
        
        ReportersFacade facade = new DefaultReportersFacade(reporters);
        facade.report(BigInteger.TEN);
        
        verify(mockReporter).report(BigInteger.TEN);
    }

}
