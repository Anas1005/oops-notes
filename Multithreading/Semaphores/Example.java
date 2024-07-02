package OOPS.Multithreading.Semaphores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Explanation of Semaphores:
 *
 * A semaphore is a synchronization aid that can be used to control access to a shared resource.
 * Semaphores maintain a set of permits, which are used to allow or block access to the resource.
 * - acquire(): Decreases the number of available permits. If no permits are available, the calling thread is blocked.
 * - release(): Increases the number of available permits, potentially releasing a blocked thread.
 *
 * Semaphores are commonly used to limit the number of concurrent accesses to a resource, ensuring controlled and safe usage.
 *
 * This implementation uses Java's built-in Semaphore class for managing access to a pool of printers.
 */

/*
 * Explanation of ReentrantLock and Condition for Semaphore:
 *
 * A custom semaphore can be implemented using ReentrantLock and Condition objects to handle waiting and signaling threads.
 * This approach provides more fine-grained control over locking and waiting conditions, improving concurrency and efficiency.
 *
 * The custom semaphore implementation would include:
 * - A ReentrantLock to manage the critical section where permits are acquired and released.
 * - A Condition object to handle the waiting and signaling of threads based on the availability of permits.
 *
 * This ensures that multiple threads can acquire and release permits concurrently without blocking each other unnecessarily,
 * thus improving the throughput and scalability of the system.
 */

class PrinterPool {
    private static final int MAX_AVAILABLE_PRINTERS = 2; // Total number of printers available
    private final Semaphore availablePrinters; // Semaphore to manage available printers

    public PrinterPool() {
        availablePrinters = new Semaphore(MAX_AVAILABLE_PRINTERS, true); // Using fair mode for fairness
    }

    public void printDocument(String document, int userId) throws InterruptedException {
        availablePrinters.acquire(); // Acquire a printer
        System.out.println("User " + userId + " is printing document: " + document);

        // Simulate printing process
        Thread.sleep(3000);

        System.out.println("User " + userId + " finished printing.");
        availablePrinters.release(); // Release the printer
    }
}

public class Example {
    public static void main(String[] args) {
        final PrinterPool printerPool = new PrinterPool();

        // Create threads representing users
        Thread[] users = new Thread[10]; // Five users
        for (int i = 0; i < 10; i++) {
            final int userId = i + 1;
            users[i] = new Thread(() -> {
                try {
                    String document = "Document-" + userId;
                    printerPool.printDocument(document, userId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            users[i].start();
        }

        // Wait for all threads to complete
        for (Thread user : users) {
            try {
                user.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All users have finished printing.");
    }
}

/*
 * Example of Custom Semaphore using ReentrantLock and Condition:
 */

class PrinterSemaphore {
    private int permits;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public PrinterSemaphore(int permits) {
        this.permits = permits;
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits == 0) {
                condition.await();
            }
            permits--;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            permits++;
            condition.signal(); // Notify one waiting thread
        } finally {
            lock.unlock();
        }
    }
}

/*
 * The CustomSemaphore class is a probable implementation of built-in Semaphore which
 * demonstrates how to use ReentrantLock and Condition
 * to implement a semaphore. This approach provides more control and better concurrency
 * compared to using simple synchronized methods, avoiding potential deadlocks
 * and improving performance by allowing multiple threads to acquire and release
 * permits concurrently.
 */
