// Assignment 2 – Q4
// Aim: Write a program to retrieve specific date & time fields from the
//      current calendar using the Calendar class.

import java.util.Calendar;
import java.text.SimpleDateFormat;

class Q4_CalendarDemo {
    public static void main(String[] args) {

        // Obtain the current Calendar instance (uses default timezone & locale)
        Calendar cal = Calendar.getInstance();

        // ---- Retrieve individual date fields ----
        int year        = cal.get(Calendar.YEAR);
        int month       = cal.get(Calendar.MONTH) + 1;     // Calendar.MONTH is 0-indexed
        int day         = cal.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek   = cal.get(Calendar.DAY_OF_WEEK);   // 1 = Sunday ... 7 = Saturday
        int dayOfYear   = cal.get(Calendar.DAY_OF_YEAR);
        int weekOfYear  = cal.get(Calendar.WEEK_OF_YEAR);
        int weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);

        // ---- Retrieve individual time fields ----
        int hour12      = cal.get(Calendar.HOUR);          // 12-hour clock
        int hour24      = cal.get(Calendar.HOUR_OF_DAY);   // 24-hour clock
        int minute      = cal.get(Calendar.MINUTE);
        int second      = cal.get(Calendar.SECOND);
        int millisecond = cal.get(Calendar.MILLISECOND);
        int amPm        = cal.get(Calendar.AM_PM);         // 0 = AM, 1 = PM

        // ---- Display all fields ----
        System.out.println("===== Calendar – Current Date & Time Fields =====");
        System.out.println();

        System.out.println("--- Date Fields ---");
        System.out.println("Year            : " + year);
        System.out.println("Month           : " + month + " (1=Jan … 12=Dec)");
        System.out.println("Day of Month    : " + day);
        System.out.println("Day of Week     : " + dayOfWeek + " (1=Sun, 2=Mon, …, 7=Sat)");
        System.out.println("Day of Year     : " + dayOfYear);
        System.out.println("Week of Year    : " + weekOfYear);
        System.out.println("Week of Month   : " + weekOfMonth);

        System.out.println();
        System.out.println("--- Time Fields ---");
        System.out.println("Hour (12-hour)  : " + hour12);
        System.out.println("Hour (24-hour)  : " + hour24);
        System.out.println("Minute          : " + minute);
        System.out.println("Second          : " + second);
        System.out.println("Millisecond     : " + millisecond);
        System.out.println("AM / PM         : " + (amPm == Calendar.AM ? "AM" : "PM"));

        // ---- Display formatted date using SimpleDateFormat ----
        System.out.println();
        System.out.println("--- Formatted Date ---");
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy  HH:mm:ss");
        System.out.println("Full date-time  : " + sdf.format(cal.getTime()));

        // ---- Demonstrate field modification ----
        System.out.println();
        System.out.println("--- Date Arithmetic ---");
        SimpleDateFormat shortSdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Today           : " + shortSdf.format(cal.getTime()));

        cal.add(Calendar.DAY_OF_MONTH, 7);
        System.out.println("Today + 7 days  : " + shortSdf.format(cal.getTime()));

        cal.add(Calendar.MONTH, 1);
        System.out.println("     + 1 month  : " + shortSdf.format(cal.getTime()));

        cal.add(Calendar.YEAR, 1);
        System.out.println("     + 1 year   : " + shortSdf.format(cal.getTime()));
    }
}
