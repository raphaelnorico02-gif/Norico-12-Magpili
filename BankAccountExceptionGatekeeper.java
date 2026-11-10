import java.util.Scanner;
import java.util.InputMismatchException;

// STEP 1: Custom Exception Class
class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}

// STEP 2: Bank Account Class (Encapsulation)
class BankAccount {

    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Getter Methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // STEP 3: Deposit Method
    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Invalid Amount! Deposit must be greater than 0."
            );
        }

        balance += amount;

        System.out.printf("Deposit Successful! New Balance: $%.2f%n", balance);
    }

    // Withdraw Method
    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Invalid Amount! Withdrawal must be greater than 0."
            );
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                "Insufficient Funds! Required: $" +
                String.format("%.2f", amount) +
                ", Available: $" +
                String.format("%.2f", balance)
            );
        }

        balance -= amount;

        System.out.printf("Withdrawal Successful! New Balance: $%.2f%n", balance);
    }
}

// STEP 4: Main Application
public class BankAccountExceptionGatekeeper {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create Bank Account
        BankAccount account = new BankAccount(
            "322009",
            "Raphael Norico",
            1000
        );

        System.out.println("=== BANK ACCOUNT EXCEPTION GATEKEEPER ===");

        System.out.println(
            "Account Created: " +
            account.getAccountNumber() +
            " (Holder: " +
            account.getAccountHolder() +
            ", Balance: $" +
            String.format("%.2f", account.getBalance()) +
            ")"
        );

        try {

            // Withdrawal Transaction
            System.out.print("Enter withdrawal amount: ");
            double withdrawAmount = input.nextDouble();

            try {

                account.withdraw(withdrawAmount);

            } catch (InsufficientFundsException e) {

                System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());

            } catch (IllegalArgumentException e) {

                System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
            }

            // Deposit Transaction
            System.out.print("Enter deposit amount: ");
            double depositAmount = input.nextDouble();

            try {

                account.deposit(depositAmount);

            } catch (IllegalArgumentException e) {

                System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
            }

        } catch (InputMismatchException e) {

            System.out.println(
                "[INPUT ERROR]: Please enter a valid numeric amount."
            );

        } finally {

            System.out.println("[SESSION COMPLETE]: Transaction log updated Thank You.");

            System.out.printf(
                "Current Balance: $%.2f%n",
                account.getBalance()
            );

            input.close();
        }
    }
}