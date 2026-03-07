// Aim: Write a program on String Class and its methods in Java.

// Topics covered:
// 1. String class core methods
// 2. String comparison methods
// 3. StringBuffer — mutable string operations
// 4. StringBuilder — faster mutable string

// =================================================================================================
// String class methods demonstration

class StringMethodsDemo {
    static void demo() {
        System.out.println("===== String Class Methods =====");
        String s = "  Hello, Pranay Gajbhiye!  ";

        System.out.println("Original          : [" + s + "]");
        System.out.println("length()          : " + s.length());
        System.out.println("trim()            : [" + s.trim() + "]");

        String t = s.trim();
        System.out.println("charAt(7)         : " + t.charAt(7));
        System.out.println("indexOf('G')      : " + t.indexOf('G'));
        System.out.println("lastIndexOf('a')  : " + t.lastIndexOf('a'));
        System.out.println("substring(7)      : " + t.substring(7));
        System.out.println("substring(7,13)   : " + t.substring(7, 13));
        System.out.println("toUpperCase()     : " + t.toUpperCase());
        System.out.println("toLowerCase()     : " + t.toLowerCase());
        System.out.println("replace('a','@')  : " + t.replace('a', '@'));
        System.out.println("contains('Pranay'): " + t.contains("Pranay"));
        System.out.println("startsWith('Hello'): " + t.startsWith("Hello"));
        System.out.println("endsWith('!')     : " + t.endsWith("!"));

        // split
        String csv = "Mango,Banana,Orange,Apple";
        System.out.println("\nOriginal CSV: " + csv);
        String[] parts = csv.split(",");
        for (int i = 0; i < parts.length; i++) {
            System.out.println("  parts[" + i + "] = " + parts[i]);
        }

        // concat vs +
        String first = "Pranay";
        String last  = "Gajbhiye";
        System.out.println("\nconcat(): " + first.concat(" " + last));
        System.out.println("+ operator: " + (first + " " + last));

        // valueOf
        System.out.println("String.valueOf(42)   : " + String.valueOf(42));
        System.out.println("String.valueOf(3.14) : " + String.valueOf(3.14));
        System.out.println("String.valueOf(true) : " + String.valueOf(true));
    }
}

// =================================================================================================
// String comparison methods

class StringComparisonDemo {
    static void demo() {
        System.out.println("\n===== String Comparison =====");
        String s1 = "java";
        String s2 = "java";
        String s3 = new String("java");
        String s4 = "JAVA";

        System.out.println("s1 == s2          : " + (s1 == s2));         // true (string pool)
        System.out.println("s1 == s3          : " + (s1 == s3));         // false (heap object)
        System.out.println("s1.equals(s3)     : " + s1.equals(s3));     // true
        System.out.println("s1.equalsIgnoreCase(s4): " + s1.equalsIgnoreCase(s4));
        System.out.println("s1.compareTo(s4)  : " + s1.compareTo(s4));  // positive (lowercase > uppercase)
    }
}

// =================================================================================================
// StringBuffer demonstration

class StringBufferDemo {
    static void demo() {
        System.out.println("\n===== StringBuffer =====");
        StringBuffer sb = new StringBuffer("Hello");

        sb.append(" World");
        System.out.println("append()   : " + sb);

        sb.insert(5, ",");
        System.out.println("insert(5,',') : " + sb);

        sb.replace(7, 12, "Java");
        System.out.println("replace(7,12,'Java'): " + sb);

        sb.delete(5, 6);
        System.out.println("delete(5,6)  : " + sb);

        sb.reverse();
        System.out.println("reverse()    : " + sb);

        System.out.println("length()     : " + sb.length());
        System.out.println("capacity()   : " + sb.capacity());
    }
}

// =================================================================================================
// StringBuilder demonstration

class StringBuilderDemo {
    static void demo() {
        System.out.println("\n===== StringBuilder (faster, not thread-safe) =====");
        StringBuilder sb = new StringBuilder();
        sb.append("K.D.K. College");
        sb.append(" | B.Tech CSE");
        sb.append(" | Pranay Gajbhiye");
        System.out.println("Built string: " + sb);
        System.out.println("charAt(0)   : " + sb.charAt(0));
        sb.deleteCharAt(0);
        System.out.println("deleteCharAt(0): " + sb);
    }
}

// =================================================================================================
// Runner class — entry point

class Runner11 {
    public static void main(String[] args) {
        StringMethodsDemo.demo();
        StringComparisonDemo.demo();
        StringBufferDemo.demo();
        StringBuilderDemo.demo();
    }
}
