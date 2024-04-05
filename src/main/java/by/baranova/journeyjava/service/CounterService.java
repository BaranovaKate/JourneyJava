package by.baranova.journeyjava.service;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
@Data
public class CounterService {

    private CounterService() {}
    private static AtomicInteger requestCount = new AtomicInteger(0);

    public static synchronized void incrementRequestCount() {
        requestCount.incrementAndGet();
    }

    public static synchronized int getRequestCount() {
        return requestCount.get();
    }
}