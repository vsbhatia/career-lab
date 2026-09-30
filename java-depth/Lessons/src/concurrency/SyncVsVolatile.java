package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

class Resource {
    // instance variable - PrintDemo is a shared resource
    int cnt = 0;
    volatile int volatileCnt = 0;
    int sync = 0;
    final AtomicInteger atomicCount = new AtomicInteger(0);

    public void work() {
        // normal - instance variable (Object state)
        System.out.println();
        printCount();

        // Volatile  ==  Publish (Broadcast)
        // volatile - breaks | memory visibility lies (doesn't fail exactly) while updating the counter.....
        System.out.println();
        printVolatileCnt();

        // Synchronized == Monitor  -- monitorEnter & monitorExit (OS Level)
        // synchronized - PASS
        System.out.println();
        printSyncCount();

        // AtomicInteger - PASS --->    Hardware-aware | Compare-And-Swap (CAS)
        System.out.println();
        printAtomicInt();
    }

    public void printCount() {
        System.out.println(" ====== printCount() ======= ");
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("(i: " + i + ", cnt: " + cnt +") IN [" + Thread.currentThread().getName() + "]");
                Thread.sleep(1000);
                cnt++;
            }
        } catch (Exception e) {
            System.out.println("Thread has been interrupted.");
        }
    }

    public void printVolatileCnt() {
        System.out.println(" ====== printVolatileCnt() ======= ");
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("(i: " + i + ", cnt: " + volatileCnt +") IN [" + Thread.currentThread().getName() + "]");
                Thread.sleep(1000);
                volatileCnt++;
            }
        } catch (Exception e) {
            System.out.println("Thread has been interrupted.");
        }
    }

    synchronized public void printSyncCount() {
        System.out.println(" ====== printVolatileCnt() ======= ");
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("(i: " + i + ", cnt: " + sync +") IN [" + Thread.currentThread().getName() + "]");
                Thread.sleep(1000);
                sync++;
            }
        } catch (Exception e) {
            System.out.println("Thread has been interrupted.");
        }
    }

    public void printAtomicInt() {
        System.out.println(" ====== printAtomicInt() ======= ");
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("(i: " + i + ", cnt: " + atomicCount.getAndIncrement() +") IN [" + Thread.currentThread().getName() + "]");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Thread has been interrupted.");
        }
    }
}

class ThreadDemo implements Runnable {
    private Thread thread;
    private String threadName;
    Resource resource;

    ThreadDemo(String threadName, Resource resource) {
        this.threadName = threadName;
        this.resource = resource;
    }

    public void run() {
        resource.work();
        System.out.println("Thread " + threadName + " finishing.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}

public class SyncVsVolatile {
    public static void main(String args[]) {

        Resource resource = new Resource();

        ThreadDemo firstThread = new ThreadDemo("Thread 1", resource);
        ThreadDemo secondThread = new ThreadDemo("Thread 2", resource);

        try {
            firstThread.start();
            secondThread.start();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}
