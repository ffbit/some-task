package com.ffbit.yandex.report.io;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.DummyReporter;
import com.ffbit.yandex.report.Reporter;

public class FileReporterFactoryTest {
    private Arguments arguments;
    
    @Before
    public void setUp() {
        arguments = mock(Arguments.class);
    }

    @Test
    public void itShouldProduceFileReporter() {
        when(arguments.contains("--output-file")).thenReturn(true);
        when(arguments.get("--output-file")).thenReturn("filepath");
        
        Reporter reporter = FileReporter.newInstance(arguments);
        
        verify(arguments).get("--output-file");
        assertThat(reporter, is(FileReporter.class));
    }
    
    @Test
    public void itShouldProduceDummyReporter() {
        when(arguments.contains("--output-file")).thenReturn(false);
        
        Reporter reporter = FileReporter.newInstance(arguments);
        
        verify(arguments).contains("--output-file");
        assertThat(reporter, is(DummyReporter.class));
    }
    
}
