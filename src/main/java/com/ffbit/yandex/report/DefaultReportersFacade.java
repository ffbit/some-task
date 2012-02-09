package com.ffbit.yandex.report;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import com.ffbit.yandex.args.Arguments;
import com.ffbit.yandex.report.io.FileReporter;
import com.ffbit.yandex.report.jdbc.JDBCReportersFactory;
import com.ffbit.yandex.report.mail.MailReporter;

public class DefaultReportersFacade implements ReportersFacade {
    private Collection<Reporter> reporters;
    
    public DefaultReportersFacade(Collection<Reporter> reporters) {
        setReporters(reporters);
    }

    public static ReportersFacade newInstance(Arguments arguments) {
        Collection<Reporter> reporters = new ArrayList<Reporter>();
        
        reporters.add(FileReporter.newInstance(arguments));
        reporters.add(MailReporter.newInstance(arguments));
        reporters.add(JDBCReportersFactory.newInstance(arguments));
        
        return new DefaultReportersFacade(reporters);
    }
    
    @Override
    public void report(BigInteger quantity) {
        for (Reporter reporter : reporters) {
            safeReport(quantity, reporter);
        }
    }

    private void safeReport(BigInteger quantity, Reporter reporter) {
        try {
            reporter.report(quantity);
        } catch (ReporterException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public Collection<Reporter> getReporters() {
        return new ArrayList<Reporter>(reporters);
    }
    
    @Override
    public void setReporters(Collection<Reporter> reporters) {
        this.reporters = new ArrayList<Reporter>(reporters);
    }

}
