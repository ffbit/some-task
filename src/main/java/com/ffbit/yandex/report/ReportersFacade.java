package com.ffbit.yandex.report;

import java.util.Collection;

import com.ffbit.yandex.report.Reporter;

public interface ReportersFacade extends Reporter {

    public Collection<Reporter> getReporters();
    
    public void setReporters(Collection<Reporter> reporters);

}
