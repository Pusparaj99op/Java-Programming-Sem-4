import java.util.Scanner;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.loadData();

        System.out.println("===============================================");
        System.out.println(" Smart Expense Tracker and Budget Assistant");
        System.out.println("===============================================");
        System.out.println("Loaded transactions: " + tracker.getTransactionCount());

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleAddTransaction(scanner, tracker, "INCOME");
                    break;
                case "2":
                    handleAddTransaction(scanner, tracker, "EXPENSE");
                    break;
                case "3":
                    handleSetBudget(scanner, tracker);
                    break;
                case "4":
                    System.out.print("Enter month (yyyy-MM): ");
                    tracker.printMonthlySummary(scanner.nextLine().trim());
                    break;
                case "5":
                    System.out.print("Enter month (yyyy-MM): ");
                    tracker.printCategoryExpenseReport(scanner.nextLine().trim());
                    break;
                case "6":
                    System.out.print("Enter keyword to search: ");
                    tracker.searchByKeyword(scanner.nextLine());
                    break;
                case "7":
                    System.out.print("Enter month (yyyy-MM): ");
                    tracker.printFinancialHealthInsights(scanner.nextLine().trim());
                    break;
                case "8":
                    tracker.printRecentTransactions(10);
                    break;
                case "9":
                    tracker.saveData();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Thank you for using the tracker.");
    }

    private static void printMenu() {
        System.out.println("\n------------- MENU -------------");
        System.out.println("1. Add Income");
        System.out.println("2. Add Expense");
        System.out.println("3. Set Monthly Budget");
        System.out.println("4. View Monthly Summary");
        System.out.println("5. View Category Expense Report");
        System.out.println("6. Search Transactions");
        System.out.println("7. Financial Health Insights");
        System.out.println("8. View Recent Transactions");
        System.out.println("9. Save and Exit");
    }

    private static void handleAddTransaction(Scanner scanner, ExpenseTracker tracker, String type) {
        System.out.print("Enter date (yyyy-MM-dd): ");
        String date = scanner.nextLine().trim();

        System.out.print("Enter category (Food, Rent, Travel, etc.): ");
        String category = scanner.nextLine().trim();

        System.out.print("Enter amount: ");
        String amountText = scanner.nextLine().trim();

        System.out.print("Enter note: ");
        String note = scanner.nextLine().trim();

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount.");
            return;
        }

        boolean added = tracker.addTransaction(date, type, category, amount, note);
        if (added) {
            System.out.println(type + " transaction added.");
        } else {
            System.out.println("Failed to add transaction. Check date/type/amount.");
        }
    }

    private static void handleSetBudget(Scanner scanner, ExpenseTracker tracker) {
        System.out.print("Enter month (yyyy-MM): ");
        String month = scanner.nextLine().trim();

        System.out.print("Enter budget amount: ");
        String amountText = scanner.nextLine().trim();

        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid budget amount.");
            return;
        }

        if (tracker.setMonthlyBudget(month, amount)) {
            System.out.println("Budget saved for " + month);
        } else {
            System.out.println("Could not set budget. Check month format and amount.");
        }
    }
}
