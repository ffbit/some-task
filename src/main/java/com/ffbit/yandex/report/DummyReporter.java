package com.ffbit.yandex.report;

import java.math.BigInteger;

public class DummyReporter implements Reporter {

    @Override
    public void report(BigInteger quantity) {
        System.out.println("DummyReporter has been invoked.");
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DummyReporter;
    }

}
