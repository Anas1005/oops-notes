package OOPS.Interfaces;


/**
 * Interface in Java:
 * An interface in Java is a reference type, similar to a class, that can contain only constants, method signatures,
 * default methods, static methods, and nested types. Interfaces cannot contain instance fields.
 *
 * Interfaces are used to specify a set of methods that a class must implement. They establish a contract for what
 * a class can do, without specifying how it does it.
 */


// Interface defining behavior for an account
interface Account {
    // Abstract method for depositing money (to be implemented by subclasses)
    void deposit(double amount);

    // Abstract method for withdrawing money (to be implemented by subclasses)
    void withdraw(double amount);

    // Abstract method for checking balance (to be implemented by subclasses)
    double checkBalance();

    // Abstract method for displaying account information (to be implemented by subclasses)
    void displayAccountInfo();
}

// Abstract class for AccountBase providing common functionality for all accounts
 abstract class AccountBase implements Account {
    protected String accountNumber;
    protected double balance;

    // Constructor
    public AccountBase(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getter method for account number
    public String getAccountNumber() {
        return accountNumber;
    }

}

// Concrete class for SavingsAccount implementing Account interface
class SavingsAccount extends AccountBase {
    private double interestRate;

    // Constructor
    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    // Implementing deposit method from Account interface
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.printf("Deposited %.2f. Current Balance: %.2f%n", amount, balance);
    }

    // Implementing withdraw method from Account interface
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.printf("Withdrawn %.2f. Current Balance: %.2f%n", amount, balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    // Implementing checkBalance method from Account interface
    @Override
    public double checkBalance() {
        return balance;
    }

    // Implementing displayAccountInfo method from AccountBase abstract class
    @Override
    public void displayAccountInfo() {
        System.out.printf("Savings Account %s - Balance: %.2f - Interest Rate: %.2f%%%n", accountNumber, balance, interestRate);
    }
}

// Concrete class for CheckingAccount implementing Account interface
class CheckingAccount extends AccountBase {
    private double overdraftLimit;

    // Constructor
    public CheckingAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    // Implementing deposit method from Account interface
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.printf("Deposited %.2f. Current Balance: %.2f%n", amount, balance);
    }

    // Implementing withdraw method from Account interface
    @Override
    public void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.printf("Withdrawn %.2f. Current Balance: %.2f%n", amount, balance);
        } else {
            System.out.println("Exceeded overdraft limit!");
        }
    }

    // Implementing checkBalance method from Account interface
    @Override
    public double checkBalance() {
        return balance;
    }

    // Implementing displayAccountInfo method from AccountBase abstract class
    @Override
    public void displayAccountInfo() {
        System.out.printf("Checking Account %s - Balance: %.2f - Overdraft Limit: %.2f%n", accountNumber, balance, overdraftLimit);
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        // Practical Interface Example: Banking System

        // Creating instances of SavingsAccount and CheckingAccount
        Account savingsAccount = new SavingsAccount("SAV-001", 5000.00, 3.5);
        Account checkingAccount = new CheckingAccount("CHK-001", 3000.00, 1000.00);

        // Depositing and withdrawing money from savings account
        savingsAccount.displayAccountInfo();
        savingsAccount.deposit(1000.00);
        savingsAccount.withdraw(200.00);
        System.out.printf("Updated Balance: %.2f%n", savingsAccount.checkBalance());

        // Depositing and withdrawing money from checking account
        checkingAccount.displayAccountInfo();
        checkingAccount.deposit(500.00);
        checkingAccount.withdraw(4000.00);
        System.out.printf("Updated Balance: %.2f%n", checkingAccount.checkBalance());
    }
}
