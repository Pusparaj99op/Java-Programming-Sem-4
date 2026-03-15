import java.time.LocalDate;

class Transaction {
    private final LocalDate date;
    private final String type;
    private final String category;
    private final double amount;
    private final String note;

    public Transaction(LocalDate date, String type, String category, double amount, String note) {
        this.date = date;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public boolean isExpense() {
        return "EXPENSE".equalsIgnoreCase(type);
    }

    public boolean isIncome() {
        return "INCOME".equalsIgnoreCase(type);
    }

    @Override
    public String toString() {
        return String.format("%s | %-7s | %-12s | %8.2f | %s", date, type, category, amount, note);
    }
}
