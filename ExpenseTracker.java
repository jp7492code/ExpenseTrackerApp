import java.sql.*;
import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager dbManager = new DatabaseManager();
        
        while (true) {
            System.out.println("Expense Tracker:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Generate Report");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Expense Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    dbManager.addExpense(new Expense(name, amount));
                    break;
                case 2:
                    dbManager.viewExpenses();
                    break;
                case 3:
                    dbManager.generateReport();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
