package OOPS.Multithreading.Basics;

/**
 * Multithreading in Java:
 *
 * Theory:
 * - Multithreading allows concurrent execution of multiple threads within the same process,
 *   enabling efficient utilization of CPU resources and responsiveness in applications.
 *
 * Thread Basics:
 * - A thread is the smallest unit of execution in a program.
 * - Threads can be created by extending the Thread class, implementing the Runnable interface,
 *   using anonymous classes, or lambda expressions.
 *
 * Thread States:
 * - New: When a thread instance is created but not yet started.
 * - Ready-To-Run : When the thread is ready tot run.
 * - Runnable: The thread is running.
 * - Blocked: Waiting for a monitor lock to enter a synchronized block or method.
 * - Waiting: Waiting indefinitely for another thread to perform a particular action.
 * - Timed Waiting: Waiting for a specified amount of time.
 * - Terminated: The thread has exited its run method.
 *
 * Thread Lifecycle:
 * - Threads transition through states such as new, runnable, blocked, waiting, timed waiting, and terminated.
 * - Thread methods include start() to begin execution, run() to define the task, sleep(ms) for pausing, and join() for synchronization.
 *
 * Daemon Threads:
 * - Daemon threads are background threads that do not prevent the JVM from exiting when all non-daemon threads have finished.
 * - Set a thread as daemon using setDaemon(true).
 *
 * Example Explanation:
 * - The example demonstrates four ways to create and start threads:
 *   1. By extending the Thread class (MyThread).
 *   2. By implementing the Runnable interface (MyRunnable).
 *   3. Using anonymous classes.
 *   4. Using lambda expressions.
 * - Each thread simulates a time-consuming task by calculating the sum of numbers in a range.
 * - The main thread continues executing after starting the other threads and prints a message.
 */

// Extend Thread class to create a custom thread
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread using Thread class is Started.");
        long sum = 0;
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread using Thread class is running.");
            sum += i;
        }
        System.out.println("Thread using Thread class finished. Sum: " + sum);
    }
}

// Implement Runnable interface to create a custom thread
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread using Runnable interface is started.");
        long sum = 0;
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread using Runnable interface is running.");
            sum += i;
        }
        System.out.println("Thread using Runnable interface finished. Sum: " + sum);
    }
}

public class Example {
    public static void main(String[] args) {
        // Creating and starting a thread using Thread class
        MyThread thread1 = new MyThread();
        thread1.start();
//
//        // Creating and starting a thread using Runnable interface
        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();

       //  Creating and starting a thread using an anonymous class
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread using anonymous class is started.");
                long sum = 0;
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread using anonymous class is running.");
                    sum += i;
                }
                System.out.println("Thread using anonymous class finished. Sum: " + sum);
            }
        });
        thread3.start();

        // Creating and starting a thread using a lambda expression
        Thread thread4 = new Thread(() -> {
            System.out.println("Thread using lambda expression is started.");
            long sum = 0;
            for (int i = 0; i < 1000000; i++) {
                System.out.println("Thread using lambda expression is running.");
                sum += i;
            }
            System.out.println("Thread using lambda expression finished. Sum: " + sum);
        });
        thread4.start();

        // Main thread continues execution
        System.out.println("Main thread finished.");
    }
}
