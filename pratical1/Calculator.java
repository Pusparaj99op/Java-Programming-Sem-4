import java.util.*;
class Calculator{
    public static void main(String []agrs)
    {   Scanner scanner = new Scanner(System.in);
        System.out.println("Pranay is trying to make a Calculator!!");
        System.out.println("========JAVA CALCULATOR INTERFACE========");
        System.out.println("Enter First Digit");
        int FirstDigit = scanner.nextInt();
        System.out.println("Enter Second Digit");
        int SecondDigit = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please Broh!! Enter YOur Name");
        String Name = scanner.nextLine();
        int  sum = FirstDigit + SecondDigit;
        System.out.println("hellow, " + Name + " the Sum of "  + FirstDigit + " And " + SecondDigit + " is " + sum);
        scanner.close();
    }
}
