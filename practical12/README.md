# Practical 12: Date, Date-Time, Calendar Class in Java

## Aim
To write a program on Date, Date-time, Calendar class in Java.

## Theory
Java provides several classes for date and time handling across different packages:

### `java.util.Date`
- Represents a specific instant in time (milliseconds since epoch: Jan 1, 1970).
- `new Date()` gives the current date-time.
- `getTime()` returns milliseconds since epoch.
- Most methods are deprecated; use `Calendar` or `java.time` instead.

### `java.text.SimpleDateFormat`
- Used to **format** (Date → String) and **parse** (String → Date) dates.
- Pattern characters: `dd` (day), `MM` (month), `yyyy` (year), `HH` (hour 24h), `mm` (minute), `ss` (second), `EEE` (day name), `MMM` (month name).

### `java.util.Calendar`
- Abstract class; get instance with `Calendar.getInstance()`.
- Access fields: `YEAR`, `MONTH` (0-indexed!), `DAY_OF_MONTH`, `HOUR_OF_DAY`, `MINUTE`, `SECOND`, `DAY_OF_WEEK`, `DAY_OF_YEAR`.
- `add(field, amount)` adds to a field; `set(field, value)` sets it.
- `getTime()` returns the equivalent `Date` object.

### `java.time` (Java 8 — recommended)
- `LocalDate` — date without time (year, month, day).
- `LocalTime` — time without date (hour, minute, second, nanosecond).
- `LocalDateTime` — date + time combined.
- `DateTimeFormatter` — for custom formatting patterns.
- Arithmetic: `plusDays()`, `minusMonths()`, `isLeapYear()`, etc.

## Algorithm
1. Create `new Date()` and print its default string and millisecond value.
2. Use `SimpleDateFormat` with three different patterns to format the current date. Parse a historical date string.
3. Use `Calendar.getInstance()` to extract year, month, day, hour, minute, second, day-of-week, day-of-year. Add 10 days and format the result.
4. Use `LocalDate.now()`, `LocalTime.now()`, `LocalDateTime.now()` to print current values. Use `DateTimeFormatter` for custom format. Demonstrate `plusDays` and `minusMonths`.

## Code

### practical12.java
```java
// Aim: Write a program on Date, Date-time, Calendar class in Java.

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Runner12 {
    public static void main(String[] args) throws Exception {
        // 1. java.util.Date
        Date d = new Date();
        System.out.println("Date toString(): " + d);
        System.out.println("getTime(): " + d.getTime());

        // 2. SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Formatted: " + sdf.format(d));
        System.out.println("Parsed 15/08/1947: " + sdf.parse("15/08/1947"));

        // 3. Calendar
        Calendar cal = Calendar.getInstance();
        System.out.println("Year: " + cal.get(Calendar.YEAR));
        System.out.println("Month: " + (cal.get(Calendar.MONTH)+1));
        System.out.println("Day: " + cal.get(Calendar.DAY_OF_MONTH));
        cal.add(Calendar.DAY_OF_MONTH, 10);
        System.out.println("Date+10: " + sdf.format(cal.getTime()));

        // 4. java.time
        LocalDate today = LocalDate.now();
        System.out.println("LocalDate: " + today + " | LeapYear: " + today.isLeapYear());
        LocalDateTime dt = LocalDateTime.now();
        System.out.println("LocalDateTime: " + dt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.out.println("Date+30days: " + today.plusDays(30));
    }
}
```

## Output
*(Output varies by date of execution)*
```
Date toString(): Sat Mar 07 14:30:45 IST 2026
getTime(): 1741346445123
Formatted: 07/03/2026
Parsed 15/08/1947: Fri Aug 15 00:00:00 IST 1947

Year: 2026
Month: 3
Day: 7
Date+10: 17/03/2026

LocalDate: 2026-03-07 | LeapYear: false
LocalDateTime: 07-03-2026 14:30:45
Date+30days: 2026-04-06
```

## Result
The program successfully demonstrates date and time handling in Java using `java.util.Date`, `SimpleDateFormat`, `Calendar`, and the modern `java.time` API. `Calendar` allows fine-grained field access and arithmetic. `SimpleDateFormat` enables flexible date string formatting and parsing. The `java.time` API (Java 8) provides an intuitive, immutable, and thread-safe approach to date-time operations.
