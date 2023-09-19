import java.util.Scanner;

public class Bank {
    public void deposit(double amount) {
        // Add code so that, if amount is not positive,
        // an Illegal Argument Exception is thrown
        // with the message "Non-positive deposit amount"
        
        // 1 for the if conditional (with or without { }
        // 1 for throw
        // 1 for IllegalArgumentException
        // 1 for the message as a parameter

        if (amount <= 0)
            throw new IllegalArgumentException("Non-positive deposit amount");
            
        balance += amount;
    }
    public static void main(String[] args) {
        // Add code so that, if the user enters a non-positive
        // deposit amount below, catch the exception
        // thrown in Bank.deposit(double) and print
        // its message to the console's error output stream
        Bank bank = new Bank();
        Scanner in = new Scanner(System.in);
        double d = in.nextDouble();
        
        // 1 for the try clause
        // 1 for the catch clause
        // 1 for the catch parameter (Exception also OK)
        // 1 for streaming e.getMessage() to System.err
        try {
            bank.deposit(d);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private int balance;
}
