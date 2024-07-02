package OOPS.Multithreading.Semaphores;

import java.util.concurrent.Semaphore;

class Book {

    private final Semaphore mutex; // Semaphore to protect the reader count...
    private final Semaphore rwLock ; // Semaphore to provide mutual exclusion for writers
    private int readerCount = 0; // Count of readers currently reading
    private int sharedData = 0; // Shared resource

    public Book() {
        this.mutex = new Semaphore(1);
        this.rwLock = new Semaphore(1);
    }

    /**
     * Method for readers to read the shared resource.
     *
     * @param readerId the ID of the reader
     * @throws InterruptedException
     */
    public void read(int readerId) throws InterruptedException {
        mutex.acquire(); // Acquire mutex to modify readerCount
        readerCount++;
        if (readerCount == 1) {
            rwLock.acquire(); // First reader locks the shared resource
        }
        mutex.release(); // Release mutex after modifying readerCount

        // Reading section
        System.out.println("Reader " + readerId + " reads: " + sharedData);
        Thread.sleep(2000);

        mutex.acquire(); // Acquire mutex to modify readerCount
        readerCount--;
        if (readerCount == 0) {
            rwLock.release(); // Last reader releases the shared resource
        }
        mutex.release(); // Release mutex after modifying readerCount
    }

    /**
     * Method for writers to write to the shared resource.
     *
     * @param writerId the ID of the writer
     * @param value    the value to be written to the shared resource
     * @throws InterruptedException
     */
    public void write(int writerId, int value) throws InterruptedException {
        rwLock.acquire(); // Writer locks the shared resource

        // Writing section
        sharedData = value;
        Thread.sleep(3000);
        System.out.println("Writer " + writerId + " writes: " + sharedData);

        rwLock.release(); // Writer releases the shared resource
    }
}

    class ReaderWriter {
        public static void main(String[] args) {
            Book book = new Book();

            int MAX_READERS = 6;

            // Creating and starting writer threads
            for (int i = 1; i <= 1; i++) {
                final int writerId = i;
                final int value = i * 42;
                new Thread(() -> {
                    try {
                        book.write(writerId, value);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }

            // Creating and starting reader threads
            for (int i = 1; i <= MAX_READERS; i++) {
                final int readerId = i;
                new Thread(() -> {
                    try {
                        book.read(readerId);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }

        }

}

/*
 * Explanation of synchronization techniques:
 *
 * mutex (Semaphore):
 * - The mutex semaphore ensures that the readerCount is updated in a thread-safe manner.
 * - Only one thread can access or modify the readerCount at a time, ensuring atomicity and preventing race conditions.
 *
 * rwLock (Semaphore):
 * - The rwLock semaphore ensures mutual exclusion for writers.
 * - Only one writer can access the shared resource at a time, and it prevents readers from accessing it while writing.
 *
 * read() method:
 * - Readers first acquire the mutex to safely increment the readerCount.
 * - The first reader acquires the rwLock to lock the shared resource preventing any other writer to write.
 * - After reading, readers decrement the readerCount while holding the mutex.
 * - The last reader releases the rwLock, allowing writers to access the shared resource.
 *
 * write() method:
 * - Writers acquire the rwLock before writing to the shared resource.
 * - This ensures that only one writer can write at a time and prevents readers from accessing the resource during writing.
 *
 * Notes on Atomicity and Thread Safety:
 * - The mutex semaphore is used to ensure atomicity when modifying the readerCount.
 * - The rwLock semaphore ensures that writers have exclusive access to the shared resource, preventing simultaneous access by readers.
 *
 * Usage Scenario:
 * - Multiple reader threads can read from the shared resource simultaneously.
 * - Writer threads need exclusive access to write to the shared resource.
 *

 */
