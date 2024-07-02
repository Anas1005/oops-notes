package OOPS.Multithreading.Methods;

/**
 * Exploring Thread Methods in Java:
 *
 * - Demonstrates the use of various thread methods.
 *
 * Explanation within main function:
 *
 * - start(): Begins execution of the thread.
 * - run(): Contains the code that constitutes the new thread.
 * - sleep(): Causes the currently executing thread to sleep for a specified amount of time.
 * - join(): Waits for a thread to die.
 * - yield(): Causes the currently executing thread object to temporarily pause and allow other threads to execute
 *            suggests the Thread Scheduler to throw the currently running thread to the Ready_To_Run State (Ready State)
 * - interrupt(): Interrupts a thread.
 */

public class Example {
    public static void main(String[] args) {
        // Creating a thread using a lambda expression
        Thread thread1 = new Thread(() -> {
            // Thread's run method: Contains the code executed by the thread
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread 1 is running, Count: " + i);
                try {
                    Thread.sleep(20); // Simulate some work by sleeping
                } catch (InterruptedException e) {
                    // Handle interrupt exceptions
                    System.out.println("Thread interrupted: " + Thread.currentThread().getName());
                }
            }
        });

        // Start the thread
        thread1.start(); // Start the thread's execution

        // Main thread continues execution
        System.out.println("Main thread is running.");

        // Yield the main thread (not guaranteed to have any effect)
        Thread.yield(); // Hint scheduler that the current thread is willing to yield

        // Creating another thread using a lambda expression
        Thread thread2 = new Thread(() -> {
            // Thread's run method: Contains the code executed by the thread
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread 2 is running, Count: " + i);
                try {
                    Thread.sleep(20); // Simulate some work by sleeping
                } catch (InterruptedException e) {
                    // Handle interrupt exceptions
                    System.out.println("Thread interrupted: " + Thread.currentThread().getName());
                }
            }
        });

        // Start the second thread
        thread2.start(); // Start the thread's execution

        // Join thread1 to wait until it finishes
        try {
            thread1.join(); // Wait for thread1 to complete before proceeding
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Main thread continues
        System.out.println("Main thread finished.");
    }
}
