package OOPS.Multithreading.Semaphores;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int maxSize;
    private final Semaphore empty;
    private final Semaphore full;
    private final Semaphore mutex;

    public Buffer(int size) {
        this.maxSize = size;
        this.empty = new Semaphore(size);
        this.full = new Semaphore(0);
        this.mutex = new Semaphore(1);
    }

    public void produce(int item) throws InterruptedException {
        empty.acquire(); // Wait for an empty slot
        mutex.acquire(); // Enter critical section

        queue.add(item);

        System.out.println("Produced: " + item);

        mutex.release(); // Exit critical section
        full.release();  // Signal that a new item is available
    }

    public void consume() throws InterruptedException {
        full.acquire(); // Wait for a filled slot
        mutex.acquire(); // Enter critical section

        int item = queue.poll();
        System.out.println("Consumed: " + item);

        mutex.release(); // Exit critical section
        empty.release(); // Signal that an empty slot is available
    }
}

 class ProducerConsumer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    buffer.produce(i);
                    Thread.sleep(1000); // Simulate time taken to produce an item
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    buffer.consume();
                    Thread.sleep(2000); // Simulate time taken to consume an item
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All items produced and consumed.");
    }
}
