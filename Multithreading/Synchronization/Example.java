package OOPS.Multithreading.Synchronization;

/**
 * Practical Example Demonstrating Synchronization Methods in Java:
 *
 * Purpose:
 * This example demonstrates different synchronization techniques in Java to ensure thread safety
 * when multiple threads access shared resources or critical sections of code.
 *
 * Notes:
 * 1. Synchronized methods:
 *    - When a synchronized instance method is called, it acquires the lock on the current instance of the object (this).
 *    - While this lock is held, no other thread can call any other synchronized instance method on the same object instance.
 *
 * 2. Synchronized block on this:
 *    - When a synchronized block is used with synchronized (this), it also acquires the lock on the current instance.
 *    - While this lock is held, no other thread can enter any synchronized method or synchronized block that locks on this.
 *
 * 3. Static synchronized methods:
 *    - When a synchronized static method is called, it acquires the lock on the Class object associated with the class.
 *    - While this lock is held, no other thread can call any other synchronized static method on the same class.
 *
 * 4. Synchronized block on a Class object:
 *    - When a synchronized block is used with synchronized (BankAccount.class), it acquires the lock on the Class object
 *      associated with the BankAccount class.
 *    - While this lock is held, no other thread can enter any synchronized static method or synchronized block that locks on BankAccount.class.
 */

class BankAccount {
    private int balance;
    private final Object lock = new Object(); // Private lock object for this instance

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    // Synchronized method to deposit money into the account
    public synchronized void deposit(int amount) {
        System.out.println(Thread.currentThread().getName() + " is depositing " + amount);
        balance += amount;
    }

    // Synchronized method to withdraw money from the account
    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    // Static synchronized method to demonstrate class-level synchronization
    public synchronized static void globalDeposit(BankAccount account, int amount) {
        System.out.println(Thread.currentThread().getName() + " is globally depositing " + amount);
        account.deposit(amount);
    }

    // Method using synchronized block to perform transfer between accounts
    public void transfer(BankAccount destination, int amount) {
        // Lock both source and destination accounts to avoid deadlock
        BankAccount first = this;
        BankAccount second = destination;

        // Ensure a consistent locking order to avoid deadlock
        if (System.identityHashCode(first) > System.identityHashCode(second)) {
            first = destination;
            second = this;
        }

        synchronized (first.lock) {
            synchronized (second.lock) {
                System.out.println(Thread.currentThread().getName() + " is transferring " + amount + " to " + destination);
                if (balance >= amount) {
                    this.withdraw(amount);
                    destination.deposit(amount);

                } else {
                    System.out.println("Transfer failed: Insufficient funds.");
                }
            }
        }
    }

    // Getter method for balance
    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount@" + Integer.toHexString(hashCode());
    }
}

public class Example {
    public static void main(String[] args) {

        /*
         * Explanation of synchronization techniques:
         *
         * withDraw()/deposit() : (Synchronized method)
         * When these methods are called, the current instance (account) applies a lock on itself,
         * ensuring that no other thread can concurrently execute synchronized methods like withdraw or deposit
         * on the same account. This prevents simultaneous withdrawals or deposits that could lead to inconsistencies.
         *
         * globalDeposit() : (Static synchronized method)
         * Applies a lock at the level of static synchronized methods of the BankAccount class,
         * ensuring that no other thread can execute globalDeposit or globalWithdraw on any instance of
         * BankAccount concurrently. This prevents simultaneous global deposits or withdrawals across accounts,
         * maintaining consistent and accurate financial operations.
         *
         * transfer() : (Synchronized block on class.object)
         * Uses synchronized (BankAccount.class) to apply a lock at the class level,
         * ensuring that no other thread can simultaneously execute any synchronized block or static synchronized methods
         * (like globalDeposit) on any instance of BankAccount. This prevents concurrent transfers
         * between accounts from different instances of BankAccount, maintaining transaction integrity.
         *
         * Note: While using synchronized (BankAccount.class) ensures mutual exclusion across all instances of BankAccount,
         * it becomes less efficient as the number of instances increases. This is because all instances would contend for
         * the same class-level lock, potentially leading to increased contention and decreased concurrency.
         *
         * Private Lock Object:
         * In scenarios involving more than two instances of BankAccount, using a private lock object for each instance
         * allows for finer-grained control over synchronization. Each instance manages its own lock independently,
         * ensuring that concurrent operations on different instances do not unnecessarily block each other.
         *
         * Synchronized Block on BankAccount.class:
         * While using synchronized (BankAccount.class) ensures mutual exclusion across all instances of BankAccount,
         * it becomes less efficient as the number of instances increases. This is because all instances would contend for
         * the same class-level lock, potentially leading to increased contention and decreased concurrency.
         * In contrast, using instance-level locks (private lock objects) allows threads to operate concurrently on
         * different instances, thus improving throughput and performance in scenarios with multiple accounts.
         *
         * Conclusion:
         * For managing concurrent operations on multiple instances of BankAccount, using private lock objects provides
         * a more scalable and efficient solution compared to using synchronized blocks on class objects.
         *
         * Importance of Overriding hashCode and equals:
         * When using a private lock object, it’s crucial to ensure that the lock object is unique to each instance.
         * Overriding hashCode and equals methods in the BankAccount class helps maintain the uniqueness of instances,
         * thus ensuring the correct synchronization behavior.
         */


        // Shared resources
        BankAccount account1 = new BankAccount(4000); // Initial balance of account1 is 4000
        BankAccount account2 = new BankAccount(4000); // Initial balance of account2 is 4000
        BankAccount account3 = new BankAccount(3000); // Initial balance of account3 is 3000
        BankAccount account4 = new BankAccount(3000); // Initial balance of account4 is 3000
        BankAccount account5 = new BankAccount(3000); // Initial balance of account5 is 3000

        // Example of synchronized method usage
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account1.deposit(10); // Each thread deposits 10 into account1
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account1.withdraw(5); // Each thread withdraws 5 from account1
            }
        }, "Thread2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account2.deposit(10); // Each thread deposits 10 into account2
            }
        }, "Thread3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account2.withdraw(5); // Each thread withdraws 5 from account2
            }
        }, "Thread4");

        // Example of synchronized block usage for transfer
        Thread thread5 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account1.transfer(account2, 2); // Each thread transfers 2 from account1 to account2
            }
        }, "Thread5");

        Thread thread6 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account2.transfer(account1, 1); // Each thread transfers 1 from account2 to account1
            }
        }, "Thread6");

        // Example of multiple transfers between different accounts
        Thread thread7 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account3.transfer(account4, 5); // Each thread transfers 5 from account3 to account4
            }
        }, "Thread7");

        Thread thread8 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                account4.transfer(account5, 3); // Each thread transfers 3 from account4 to account5
            }
        }, "Thread8");

        // Example of static synchronized method usage
        Thread thread9 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                BankAccount.globalDeposit(account5, 10); // Each thread statically deposits 10 into account5
            }
        }, "Thread9");

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
//        thread9.start();

        try {
            // Wait for all threads to complete
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
//            thread9.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Output final balances after operations
        System.out.println("Final Balance for account1: " + account1.getBalance());
        System.out.println("Final Balance for account2: " + account2.getBalance());
        System.out.println("Final Balance for account3: " + account3.getBalance());
        System.out.println("Final Balance for account4: " + account4.getBalance());
        System.out.println("Final Balance for account5: " + account5.getBalance());
    }
}
