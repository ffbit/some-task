package com.ffbit.yandex.worker;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class DefaultWorkerTest {
    private Runnable task;
    private int period;
    private Worker worker;

    @Before
    public void setUp() throws Exception {
        task = mock(Runnable.class);
        period = 1;
        worker = new DefaultWorker(task, period, TimeUnit.SECONDS);
    }
    
    @Test
    public void itShouldUseTask() throws Exception {
        worker.run();
        
        TimeUnit.SECONDS.sleep(5 * period);

        verify(task, atLeast(2 * period)).run();
    }
    
}
