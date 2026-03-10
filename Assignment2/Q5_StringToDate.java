// Assignment 2 – Q5
// Aim: Write a program to convert a String to a Date using the
//      SimpleDateFormat class.

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class Q5_StringToDate {
    public static void main(String[] args) {

        System.out.println("===== String to Date using SimpleDateFormat =====\n");

        // ------------------------------------------------------------------
        // Example 1: "dd/MM/yyyy" format
        // ------------------------------------------------------------------
        String dateStr1 = "15/08/1947";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date1 = sdf1.parse(dateStr1);          // parse() converts String → Date
            System.out.println("Input string  : " + dateStr1);
            System.out.println("Parsed Date   : " + date1);
            System.out.println("Re-formatted  : " + sdf1.format(date1));    // Date → String
        } catch (ParseException e) {
            System.out.println("Parse error for '" + dateStr1 + "': " + e.getMessage());
        }

        // ------------------------------------------------------------------
        // Example 2: "dd-MM-yyyy" format
        // ------------------------------------------------------------------
        System.out.println();
        String dateStr2 = "26-01-1950";
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date2 = sdf2.parse(dateStr2);
            System.out.println("Input string  : " + dateStr2);
            System.out.println("Parsed Date   : " + date2);
            System.out.println("Re-formatted  : " + sdf2.format(date2));
        } catch (ParseException e) {
            System.out.println("Parse error for '" + dateStr2 + "': " + e.getMessage());
        }

        // ------------------------------------------------------------------
        // Example 3: "yyyy-MM-dd HH:mm:ss" format (ISO-like)
        // ------------------------------------------------------------------
        System.out.println();
        String dateStr3 = "2026-03-10 14:30:00";
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date3 = sdf3.parse(dateStr3);
            System.out.println("Input string  : " + dateStr3);
            System.out.println("Parsed Date   : " + date3);
            // Display in a more readable format
            SimpleDateFormat prettyFmt = new SimpleDateFormat("EEEE, dd MMMM yyyy  hh:mm:ss a");
            System.out.println("Re-formatted  : " + prettyFmt.format(date3));
        } catch (ParseException e) {
            System.out.println("Parse error for '" + dateStr3 + "': " + e.getMessage());
        }

        // ------------------------------------------------------------------
        // Example 4: Invalid date string – shows how ParseException is handled
        // ------------------------------------------------------------------
        System.out.println();
        String dateStr4 = "31-02-2026";    // 31 Feb does not exist
        SimpleDateFormat sdf4 = new SimpleDateFormat("dd-MM-yyyy");
        sdf4.setLenient(false);             // strict parsing: rejects invalid dates
        try {
            Date date4 = sdf4.parse(dateStr4);
            System.out.println("Parsed Date   : " + date4);
        } catch (ParseException e) {
            System.out.println("Input string  : " + dateStr4 + "  (invalid date)");
            System.out.println("ParseException: " + e.getMessage());
        }

        System.out.println("\n--- Key Points ---");
        System.out.println("sdf.parse(String)  : converts a String to a java.util.Date object.");
        System.out.println("sdf.format(Date)   : converts a java.util.Date object back to a String.");
        System.out.println("setLenient(false)  : enforces strict date validation during parsing.");
    }
}
