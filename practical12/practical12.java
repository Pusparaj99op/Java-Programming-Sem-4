// Aim: Write a program on Date, Date-time, Calendar class in Java.

// Topics covered:
// 1. java.util.Date           — current date and time
// 2. java.text.SimpleDateFormat — formatting and parsing dates
// 3. java.util.Calendar       — field-level manipulation
// 4. java.time API (Java 8+)  — LocalDate, LocalTime, LocalDateTime

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// =================================================================================================
// 1. java.util.Date

class DateDemo {
    static void demo() {
        System.out.println("===== java.util.Date =====");
        Date d = new Date();  // current date and time
        System.out.println("Default toString(): " + d);
        System.out.println("getTime() (ms since epoch): " + d.getTime());
    }
}

// =================================================================================================
// 2. java.text.SimpleDateFormat

class SimpleDateFormatDemo {
    static void demo() {
        System.out.println("\n===== java.text.SimpleDateFormat =====");
        Date now = new Date();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

        System.out.println("Format dd/MM/yyyy        : " + sdf1.format(now));
        System.out.println("Format dd-MMM-yyyy HH:mm : " + sdf2.format(now));
        System.out.println("Format Day, Month dd yyyy: " + sdf3.format(now));

        // Parsing a date string
        try {
            String dateStr = "15/08/1947";
            Date independence = sdf1.parse(dateStr);
            System.out.println("Parsed date              : " + independence);
        } catch (Exception e) {
            System.out.println("Parse error: " + e.getMessage());
        }
    }
}

// =================================================================================================
// 3. java.util.Calendar

class CalendarDemo {
    static void demo() {
        System.out.println("\n===== java.util.Calendar =====");
        Calendar cal = Calendar.getInstance();  // current date-time

        int year   = cal.get(Calendar.YEAR);
        int month  = cal.get(Calendar.MONTH) + 1;  // 0-indexed, so +1
        int day    = cal.get(Calendar.DAY_OF_MONTH);
        int hour   = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        int dow    = cal.get(Calendar.DAY_OF_WEEK);   // 1=Sun, 2=Mon ...
        int doy    = cal.get(Calendar.DAY_OF_YEAR);

        System.out.println("Year           : " + year);
        System.out.println("Month          : " + month);
        System.out.println("Day            : " + day);
        System.out.println("Hour (24h)     : " + hour);
        System.out.println("Minute         : " + minute);
        System.out.println("Second         : " + second);
        System.out.println("Day of Week    : " + dow + " (1=Sun, 7=Sat)");
        System.out.println("Day of Year    : " + doy);

        // Modify calendar
        cal.add(Calendar.DAY_OF_MONTH, 10);  // add 10 days
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Date after +10 days: " + sdf.format(cal.getTime()));
    }
}

// =================================================================================================
// 4. java.time (Java 8 API)

class JavaTimeDemo {
    static void demo() {
        System.out.println("\n===== java.time API (Java 8) =====");

        // LocalDate
        LocalDate today = LocalDate.now();
        System.out.println("LocalDate.now()     : " + today);
        System.out.println("Year                : " + today.getYear());
        System.out.println("Month               : " + today.getMonth());
        System.out.println("Day of month        : " + today.getDayOfMonth());
        System.out.println("Day of week         : " + today.getDayOfWeek());
        System.out.println("Is leap year?       : " + today.isLeapYear());

        // LocalTime
        LocalTime time = LocalTime.now();
        System.out.println("\nLocalTime.now()     : " + time);
        System.out.printf("Formatted (HH:mm:ss): %02d:%02d:%02d%n",
                time.getHour(), time.getMinute(), time.getSecond());

        // LocalDateTime
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("\nLocalDateTime.now() : " + dt);
        System.out.println("Formatted           : " + dt.format(dtf));

        // Date arithmetic
        LocalDate future = today.plusDays(30);
        LocalDate past   = today.minusMonths(3);
        System.out.println("\nDate + 30 days      : " + future);
        System.out.println("Date - 3 months     : " + past);
    }
}

// =================================================================================================
// Runner class — entry point

class Runner12 {
    public static void main(String[] args) {
        DateDemo.demo();
        SimpleDateFormatDemo.demo();
        CalendarDemo.demo();
        JavaTimeDemo.demo();
    }
}
