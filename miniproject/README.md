# Mini Project: Smart Expense Tracker and Budget Assistant

## Aim
To develop a useful Java mini project that helps users manage personal finance by tracking income and expenses, setting monthly budgets, and generating insights from spending data.

## Problem Statement
Students and beginners often track money manually in notes, which makes it hard to know:
- how much they spent in each category,
- whether they crossed monthly budget,
- how healthy their savings are.

This project solves that by providing a simple command-line finance assistant with persistent data storage.

## Key Features
1. Add income transaction
2. Add expense transaction
3. Set monthly budget (`yyyy-MM`)
4. Monthly summary with budget alert
5. Category-wise expense report
6. Search transactions by keyword
7. Financial health score with tips
8. Recent transaction history
9. Save data and exit

## Project Structure
- `ExpenseTrackerApp.java` - Main menu and user interaction
- `ExpenseTracker.java` - Business logic, reports, and CSV persistence
- `Transaction.java` - Transaction model class
- `data/transactions.csv` - Auto-created transaction data file
- `data/budgets.csv` - Auto-created budget data file

## Concepts Used
- OOP (encapsulation, class design)
- Collections (`ArrayList`, `HashMap`)
- Java Time API (`LocalDate`, `YearMonth`)
- File handling (`BufferedReader`, `BufferedWriter`, `java.nio.file`)
- Exception handling and validation
- Sorting with comparators

## Algorithm (High Level)
1. Load previous transaction and budget data from CSV files.
2. Show menu and accept user choice.
3. Validate inputs (date, month format, amount).
4. Store transaction/budget in memory.
5. Generate requested reports (summary, category report, search, insights).
6. Save all updated data to CSV on exit.

## Code Snippets

### 1) Transaction Model (`Transaction.java`)
```java
class Transaction {
	private final LocalDate date;
	private final String type;
	private final String category;
	private final double amount;
	private final String note;

	public boolean isExpense() {
		return "EXPENSE".equalsIgnoreCase(type);
	}

	public boolean isIncome() {
		return "INCOME".equalsIgnoreCase(type);
	}
}
```

### 2) Menu Handling (`ExpenseTrackerApp.java`)
```java
switch (choice) {
	case "1":
		handleAddTransaction(scanner, tracker, "INCOME");
		break;
	case "2":
		handleAddTransaction(scanner, tracker, "EXPENSE");
		break;
	case "4":
		System.out.print("Enter month (yyyy-MM): ");
		tracker.printMonthlySummary(scanner.nextLine().trim());
		break;
	case "9":
		tracker.saveData();
		running = false;
		break;
	default:
		System.out.println("Invalid choice. Please try again.");
}
```

### 3) Budget Check Logic (`ExpenseTracker.java`)
```java
if (budget != null) {
	double budgetLeft = budget - expense;
	System.out.printf("Budget Left  : %.2f%n", budgetLeft);

	if (expense > budget) {
		System.out.println("ALERT: You crossed your budget this month.");
	} else if (expense > budget * 0.9) {
		System.out.println("WARNING: You are close to crossing your budget.");
	}
}
```

## How to Compile and Run
Run these commands from the `miniproject/` folder:

```bash
javac *.java
java ExpenseTrackerApp
```

## Sample Input Flow
1. Set budget for `2026-03` as `20000`
2. Add income: `Salary`, amount `30000`
3. Add expenses: `Food 2500`, `Transport 1200`
4. View monthly summary and category report
5. View financial health insights
6. Save and exit

## Sample Output (Example)
```text
===== Monthly Summary: 2026-03 =====
Total Income : 30000.00
Total Expense: 3700.00
Net Balance  : 26300.00
Budget Limit : 20000.00
Budget Left  : 16300.00
Top spend category: Food (2500.00)
```

## Result
The mini project is unique, practical, and extendable. It demonstrates real-world Java programming with persistence, reporting, and analytics. It can be further enhanced with GUI, charts, login system, and cloud backup.
