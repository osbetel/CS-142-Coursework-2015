/**
 * Created by Andrew Nguyen; Professor Barbara Goldner's CSC 142, Fall 2015
 * Wednesday, October 21, 2015
 *
 * PROGRAM DESCRIPTION
 * ===================
 * The user interface half of the BaseConversion classes. The only thing that this does is
 * take user input and deliver messages back to the console. It has a single major decision structure
 * to determine which method to use for converting to a new base.
 */


package NguyenHW3;
import java.util.Scanner;

public class BaseConversionsApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    //initialize a Scanner object to receive user input.

        System.out.println("Welcome to Andrew's Base Conversion Tool!");

        /**
         * A while loop, plus a sentinel, in case someone wanted to convert multiple numbers in a row.
         * There is also an exception catcher as well as a throw exception statement if the input is invalid.
         * eg: if the integer input is not an integer, or if the base value is not 2, 8, or 16.
         */
        //defaults "y" to start loop, and in the event
        String sentinel = "y";      // an exception is thrown and the user has no choice, will loop again.
        while (sentinel.equalsIgnoreCase("y")) {
            try {
                System.out.println("-~-~-~-~-~-~-~-~-~-~--~-~-~-~-~--~-~-~-~-~-");
                System.out.println("Please enter a number, followed by the base you wish to convert to.");
                System.out.println("eg: \"25 2\" to convert the number 25 to a base 2 number.");
                System.out.println("-~-~-~-~-~-~-~-~-~-~--~-~-~-~-~--~-~-~-~-~-");

                //Used Integer.parseInt instead of sc.nextInt because Integer.parseInt throws an exception automatically if
                //it is not an integer.
                int toConvert = Integer.parseInt(sc.next());
                int base = Integer.parseInt(sc.next());

                /**
                 * in testing, I found that typing an int and a string for number and base would return 1 exception,
                 *  eg: 15 r
                 *  and typing two strings would return two exceptions.
                 *  eg: a n
                 */

                //Only activates when the base is a valid number.
                if (toConvert >= 0 && base == 2 || base == 8 || base == 16) {
                    System.out.println("Your number, " + toConvert + ", was converted to base, " + base + ".");
                }

                //Generic decision structure determining which method to use in BaseConversion class.
                if (base == 2) {
                    System.out.println("The converted number is: " + BaseConversions.tenToBinary(toConvert));
                } else if (base == 8) {
                    System.out.println("The converted number is: " + BaseConversions.tenToOctal(toConvert));
                } else if (base == 16) {
                    System.out.println("The converted number is: " + BaseConversions.tenToHex(toConvert));
                } else {
                    throw new Exception();
                }

                //Small loop asking if user would like to keep converting. Valid input exits the loop.
                //Inputting any form of "n" exits the outside loop as well, exiting the program.
                do {
                    System.out.println("Would you like to convert another number? y/n?");
                    sentinel = sc.next();
                } while (!sentinel.equalsIgnoreCase("y") && !sentinel.equalsIgnoreCase("n"));

                /**
                 * Exception catcher for the main loop.
                 */
            } catch (Exception err) {
                System.out.println("-~-~-~-~-~-~-~-~-~-~--~-~-~-~-~--~-~-~-~-~-");
                System.out.println("You entered an invalid form of input. \nPlease enter two numbers separated by a single space, " +
                        "with the second number being a valid base to convert to (2, 8, or 16). \nNote, the converter does not" +
                        " accept decimal values at this time.");
                System.out.println("-~-~-~-~-~-~-~-~-~-~--~-~-~-~-~--~-~-~-~-~-");
            }
        }
        System.out.println("Thanks for using Andrew's Base Conversion Tool!");
    }
}