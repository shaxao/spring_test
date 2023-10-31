package current;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j(topic = "c.TestPoolExecutors")
public class TestPoolExecutors {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<String> future = pool.submit(() -> {
            log.debug("running....");
            Thread.sleep(1000);
            return "ok";
        });

        log.debug("{}",future.get());
    }
}
