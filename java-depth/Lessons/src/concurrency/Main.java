package concurrency;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Main {

    // Step 2: add volatile to this line and re-run.
     static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            System.out.println("worker: spinning - " + Date.from(Instant.now()));
            while (!stop) {
//                try {
////                    System.out.println("awake - " + Date.from(Instant.now()));
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                // deliberately empty — no print, no sleep, no method call
            }
            System.out.println("worker: saw stop=true, exited - " + Date.from(Instant.now()));
        }, "worker");

        worker.start();

        Thread.sleep(3000);       // let the JIT compile the loop
        stop = true;
        System.out.println("main: set stop = true  - " + Date.from(Instant.now()));

        worker.join(4000);        // wait up to 4s for it to notice
        System.out.println("main: worker still alive? " + worker.isAlive() + " - " + Date.from(Instant.now()));
        System.exit(0);           // needed — the spinning thread won't die on its own
    }
}