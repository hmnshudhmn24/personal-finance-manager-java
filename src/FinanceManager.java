import java.util.*;

class Transaction {
    String type;
    double amount;
    String description;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return type.toUpperCase() + " | Amount: $" + amount + " | " + description;
    }
}

public class FinanceManager {
    private static final List<Transaction> transactions = new ArrayList<>();
    private static double balance = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nPersonal Finance Manager");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTransaction(scanner, "income");
                case 2 -> addTransaction(scanner, "expense");
                case 3 -> viewTransactions();
                case 4 -> System.out.println("Current Balance: $" + balance);
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void addTransaction(Scanner scanner, String type) {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        if (type.equals("expense") && amount > balance) {
            System.out.println("Insufficient funds!");
            return;
        }

        transactions.add(new Transaction(type, amount, description));
        balance += type.equals("income") ? amount : -amount;
        System.out.println("Transaction added successfully!");
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            System.out.println("\nTransaction History:");
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }
}
