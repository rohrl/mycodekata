package concurrency;

import java.util.concurrent.*;

public class DeadLockExample {

    static final CountDownLatch starter = new CountDownLatch(2);

    public static class Job implements Runnable {

        final Object resource1;
        final Object resource2;

        public Job(Object resource1, Object resource2) {
            this.resource1 = resource1;
            this.resource2 = resource2;
        }

        @Override
        public void run() {
            try {
                starter.countDown();
                starter.await();
                for (int i = 0; i < 100; i++) {
                    synchronized (resource1) {
                        System.out.println(i + ": " + Thread.currentThread().getId() + " passed 1st lock");
                        synchronized (resource2) {
                            System.out.println(i + ": " + Thread.currentThread().getId() + " passed BOTH locks");
                        }
                    }
                }
            } catch (InterruptedException ignored) {
            }
        }
    }

    public static void main(String[] args) {

        final Object o1 = new Object();
        final Object o2 = new Object();

        // TODO change order to prevent from deadlock
        Job j1 = new Job(o1, o2);
        Job j2 = new Job(o2, o1);

        new Thread(j1).start();
        new Thread(j2).start();
    }

}
