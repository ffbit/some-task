package com.ffbit.yandex.report;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DummyReporterTest {
    
    @Test
    public void itShouldBeEqualOther() {
        assertEquals(new DummyReporter(), new DummyReporter());
    }
    
    @Test
    public void itShoulHaveEqualHasCode() {
        assertEquals(new DummyReporter().hashCode(), new DummyReporter().hashCode());
    }
    
}
