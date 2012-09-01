package com.ffbit.yandex;

import java.util.concurrent.TimeUnit;

import com.ffbit.yandex.worker.DefaultWorker;
import com.ffbit.yandex.worker.Worker;
import com.ffbit.yandex.xml.DefaultXMLProcessor;

public class Main {

    public static void main(String... args) {
        String pseudoXpath = "/root/records/item";
        String attribute = "count";
        long minValue = 7;

        Runnable task = createTask(pseudoXpath, attribute, minValue, args);

        long period = 10;
        Worker worker = new DefaultWorker(task, period, TimeUnit.MINUTES);

        worker.run();
    }

    private static Runnable createTask(String pseudoXpath, String attribute,
            long minValue, String... args) {
        return DefaultXMLProcessor.newInstance(args, pseudoXpath, attribute,
                minValue);
    }

}
