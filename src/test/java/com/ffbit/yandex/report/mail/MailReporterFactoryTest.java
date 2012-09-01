package com.ffbit.yandex.report.mail;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.DummyReporter;
import com.ffbit.yandex.report.Reporter;

public class MailReporterFactoryTest {
    private Arguments arguments;

    @Before
    public void setUp() {
        arguments = mock(Arguments.class);
    }

    @Test
    public void itShouldProduceDummyReporter() {
        when(arguments.contains("--email")).thenReturn(false);
        Reporter reporter = MailReporter.newInstance(arguments);

        verify(arguments).contains("--email");
        assertThat(reporter, is(DummyReporter.class));
    }

    @Test
    public void itShouldProduceMailReporter() {
        when(arguments.contains("--email")).thenReturn(true);
        when(arguments.get("--email")).thenReturn("good@email.com");

        Reporter reporter = MailReporter.newInstance(arguments);

        verify(arguments).get("--email");
        assertThat(reporter, is(MailReporter.class));
    }

    @Test(expected = EmailMalformedException.class)
    public void itMayThrowMailReporterException() {
        arguments = Arguments.parse("--email", "bad@email");
        MailReporter.newInstance(arguments);
    }

    @Test
    public void itShouldSendReport() {
        arguments = Arguments.parse("--email", "some@example.com");
        Reporter reporter = MailReporter.newInstance(arguments);

        reporter.report(BigInteger.TEN);
    }

}
