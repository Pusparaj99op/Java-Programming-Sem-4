import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ExpenseTracker {
    private final List<Transaction> transactions;
    private final Map<String, Double> monthlyBudgets;
    private final Path dataDirectory;
    private final Path transactionFile;
    private final Path budgetFile;

    public ExpenseTracker() {
        this.transactions = new ArrayList<>();
        this.monthlyBudgets = new HashMap<>();
        this.dataDirectory = Paths.get("data");
        this.transactionFile = dataDirectory.resolve("transactions.csv");
        this.budgetFile = dataDirectory.resolve("budgets.csv");
    }

    public void loadData() {
        try {
            Files.createDirectories(dataDirectory);
            loadTransactions();
            loadBudgets();
        } catch (IOException e) {
            System.out.println("Could not prepare data directory: " + e.getMessage());
        }
    }

    public void saveData() {
        try {
            saveTransactions();
            saveBudgets();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error while saving data: " + e.getMessage());
        }
    }

    public boolean addTransaction(String dateText, String type, String category, double amount, String note) {
        LocalDate date;
        try {
            date = LocalDate.parse(dateText);
        } catch (DateTimeParseException e) {
            return false;
        }

        if (amount <= 0) {
            return false;
        }

        String normalizedType = type.trim().toUpperCase();
        if (!normalizedType.equals("INCOME") && !normalizedType.equals("EXPENSE")) {
            return false;
        }

        Transaction transaction = new Transaction(date, normalizedType, category.trim(), amount, note.trim());
        transactions.add(transaction);
        return true;
    }

    public boolean setMonthlyBudget(String monthText, double amount) {
        if (amount <= 0) {
            return false;
        }
        try {
            YearMonth month = YearMonth.parse(monthText);
            monthlyBudgets.put(month.toString(), amount);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void printMonthlySummary(String monthText) {
        YearMonth month;
        try {
            month = YearMonth.parse(monthText);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid month format. Use yyyy-MM");
            return;
        }

        double income = 0;
        double expense = 0;
        Map<String, Double> categorySpend = new HashMap<>();

        for (Transaction t : transactions) {
            if (YearMonth.from(t.getDate()).equals(month)) {
                if (t.isIncome()) {
                    income += t.getAmount();
                } else {
                    expense += t.getAmount();
                    categorySpend.put(t.getCategory(), categorySpend.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
                }
            }
        }

        double balance = income - expense;
        Double budget = monthlyBudgets.get(month.toString());

        System.out.println("\n===== Monthly Summary: " + month + " =====");
        System.out.printf("Total Income : %.2f%n", income);
        System.out.printf("Total Expense: %.2f%n", expense);
        System.out.printf("Net Balance  : %.2f%n", balance);

        if (budget != null) {
            System.out.printf("Budget Limit : %.2f%n", budget);
            double budgetLeft = budget - expense;
            System.out.printf("Budget Left  : %.2f%n", budgetLeft);

            if (expense > budget) {
                System.out.println("ALERT: You crossed your budget this month.");
            } else if (expense > budget * 0.9) {
                System.out.println("WARNING: You are close to crossing your budget.");
            }
        } else {
            System.out.println("No budget set for this month.");
        }

        String topCategory = findTopCategory(categorySpend);
        if (!topCategory.isEmpty()) {
            System.out.println("Top spend category: " + topCategory + " (" + String.format("%.2f", categorySpend.get(topCategory)) + ")");
        }
    }

    public void printCategoryExpenseReport(String monthText) {
        YearMonth month;
        try {
            month = YearMonth.parse(monthText);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid month format. Use yyyy-MM");
            return;
        }

        Map<String, Double> categorySpend = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.isExpense() && YearMonth.from(t.getDate()).equals(month)) {
                categorySpend.put(t.getCategory(), categorySpend.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
            }
        }

        if (categorySpend.isEmpty()) {
            System.out.println("No expense data for " + month);
            return;
        }

        List<Map.Entry<String, Double>> sorted = new ArrayList<>(categorySpend.entrySet());
        sorted.sort(Collections.reverseOrder(Comparator.comparingDouble(Map.Entry::getValue)));

        System.out.println("\n===== Category Expense Report: " + month + " =====");
        for (Map.Entry<String, Double> entry : sorted) {
            System.out.printf("%-15s : %.2f%n", entry.getKey(), entry.getValue());
        }
    }

    public void searchByKeyword(String keyword) {
        String key = keyword.toLowerCase().trim();
        if (key.isEmpty()) {
            System.out.println("Keyword cannot be empty.");
            return;
        }

        boolean found = false;
        System.out.println("\n===== Search Results =====");
        for (Transaction t : transactions) {
            if (t.getCategory().toLowerCase().contains(key) || t.getNote().toLowerCase().contains(key)) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found for keyword: " + keyword);
        }
    }

    public void printFinancialHealthInsights(String monthText) {
        YearMonth month;
        try {
            month = YearMonth.parse(monthText);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid month format. Use yyyy-MM");
            return;
        }

        double income = 0;
        double expense = 0;
        int expenseCount = 0;
        Map<String, Double> categorySpend = new HashMap<>();

        for (Transaction t : transactions) {
            if (YearMonth.from(t.getDate()).equals(month)) {
                if (t.isIncome()) {
                    income += t.getAmount();
                } else {
                    expense += t.getAmount();
                    expenseCount++;
                    categorySpend.put(t.getCategory(), categorySpend.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
                }
            }
        }

        System.out.println("\n===== Financial Health Insights: " + month + " =====");
        if (income == 0 && expense == 0) {
            System.out.println("No transactions found for this month.");
            return;
        }

        double savingsRate = income == 0 ? 0 : ((income - expense) / income) * 100.0;
        double essentials = categorySpend.getOrDefault("Food", 0.0)
                + categorySpend.getOrDefault("Transport", 0.0)
                + categorySpend.getOrDefault("Bills", 0.0)
                + categorySpend.getOrDefault("Rent", 0.0);
        double essentialsRatio = expense == 0 ? 0 : (essentials / expense) * 100.0;

        int score = 50;
        if (savingsRate >= 30) {
            score += 35;
        } else if (savingsRate >= 15) {
            score += 20;
        } else if (savingsRate > 0) {
            score += 8;
        } else {
            score -= 15;
        }

        if (expenseCount > 0 && categorySpend.size() <= 5) {
            score += 10;
        } else if (categorySpend.size() > 8) {
            score -= 5;
        }

        Double budget = monthlyBudgets.get(month.toString());
        if (budget != null && expense <= budget) {
            score += 10;
        } else if (budget != null) {
            score -= 10;
        }

        score = Math.max(0, Math.min(100, score));

        System.out.printf("Savings Rate          : %.2f%%%n", savingsRate);
        System.out.printf("Essential Spend Ratio : %.2f%%%n", essentialsRatio);
        System.out.println("Financial Health Score: " + score + "/100");

        if (savingsRate < 10) {
            System.out.println("Tip: Try a weekly spending limit for non-essential categories.");
        }
        if (essentialsRatio > 75) {
            System.out.println("Tip: Compare utility plans or transport options to reduce fixed costs.");
        }
        if (budget == null) {
            System.out.println("Tip: Set a monthly budget to receive overspending alerts.");
        }
    }

    public void printRecentTransactions(int count) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        List<Transaction> copy = new ArrayList<>(transactions);
        copy.sort(Comparator.comparing(Transaction::getDate).reversed());

        System.out.println("\n===== Recent Transactions =====");
        int limit = Math.min(count, copy.size());
        for (int i = 0; i < limit; i++) {
            System.out.println(copy.get(i));
        }
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    private String findTopCategory(Map<String, Double> categorySpend) {
        String topCategory = "";
        double max = -1;
        for (Map.Entry<String, Double> entry : categorySpend.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                topCategory = entry.getKey();
            }
        }
        return topCategory;
    }

    private void loadTransactions() throws IOException {
        if (!Files.exists(transactionFile)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(transactionFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = splitCsvLine(line);
                if (parts.length < 5) {
                    continue;
                }

                try {
                    LocalDate date = LocalDate.parse(parts[0]);
                    String type = parts[1];
                    String category = parts[2];
                    double amount = Double.parseDouble(parts[3]);
                    String note = parts[4];
                    transactions.add(new Transaction(date, type, category, amount, note));
                } catch (Exception ignored) {
                    // Skip malformed rows and continue loading valid data.
                }
            }
        }
    }

    private void saveTransactions() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(transactionFile)) {
            for (Transaction t : transactions) {
                writer.write(String.join(",",
                        t.getDate().toString(),
                        escapeCsv(t.getType()),
                        escapeCsv(t.getCategory()),
                        String.format("%.2f", t.getAmount()),
                        escapeCsv(t.getNote())));
                writer.newLine();
            }
        }
    }

    private void loadBudgets() throws IOException {
        if (!Files.exists(budgetFile)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(budgetFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = splitCsvLine(line);
                if (parts.length < 2) {
                    continue;
                }

                try {
                    monthlyBudgets.put(parts[0], Double.parseDouble(parts[1]));
                } catch (NumberFormatException ignored) {
                    // Skip malformed budget rows.
                }
            }
        }
    }

    private void saveBudgets() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(budgetFile)) {
            for (Map.Entry<String, Double> entry : monthlyBudgets.entrySet()) {
                writer.write(entry.getKey() + "," + String.format("%.2f", entry.getValue()));
                writer.newLine();
            }
        }
    }

    private String escapeCsv(String value) {
        String escaped = value.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }

    private String[] splitCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        fields.add(current.toString());
        return fields.toArray(new String[0]);
    }
}
