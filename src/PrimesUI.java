import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author ADKN
 * @version 10 Dec 2015, 9:14 PM
 */
public class PrimesUI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String bound;
        String continueChoice;

        do {//This is the big loop that determines whether a user will compute another list or exit.
            //Reassign these values to a default state each loop.
            bound = "retry";
            continueChoice = "";

            int listBound = getChoice(bound,sc);
            display(listBound);

            //Determines whether or not to compute another list.
            while (!(continueChoice.equalsIgnoreCase("n") || continueChoice.equalsIgnoreCase("y"))) {
                System.out.println("Would you like to compute another list of prime numbers? Y/N?");
                continueChoice = sc.next();
                if (continueChoice.equalsIgnoreCase("n") || continueChoice.equalsIgnoreCase("y")) {
                    break;
                } else {
                    System.out.println("Please enter a valid choice: \"Y\" or \"N.\"");
                    System.out.println("**************************************");
                }
            }

            //Things to make the UI look pretty.
            if (continueChoice.equalsIgnoreCase("y")) {
                System.out.println("**************************************");
                System.out.println("*********      NEXT LIST     *********");
                System.out.println("**************************************");
            } else {
                System.out.println("**************************************");
                System.out.println("*******     PROGRAM EXITING    *******");
                System.out.println("**************************************");
            }

        } while (continueChoice.equalsIgnoreCase("y"));
    }


    /**
     * Methond to get the inputted integer from the user, complete with loops and try-catch blocks in the event
     * that the input is not an integer. Will continually ask until a valid, integer input is given.
     * @param bound The variable meant to catch scanner input so it can be parsed. Also serves to continue the loop
     *              if the input is not a valid integer.
     * @param sc Scanner object configured as new Scanner(System.in).
     * @return Returns the inputted integer value if it is deemed legitimate.
     */
    private static int getChoice(String bound, Scanner sc) {
        //This section of the UI gets the integer value from the user and also creates the list.
        int temp = -1;
        while (bound.equals("retry")) {
            try {
                System.out.println("Please type in the upper bound of the prime numbers you wish to find.");
                bound = sc.next();
                temp = Integer.parseInt(bound);    //Just here to throw ex if inputted bound is not actually an integer.
                if (temp < 2) throw new IllegalArgumentException();

            } catch (NumberFormatException err) {   //If user doesn't input an int/it can't be parsed.
                System.out.println("Letters, decimal numbers, and other symbols are not acceptable.");
                System.out.println("You must input only integers, and it must be greater or equal to 2.");
                bound = "retry";
                System.out.println("**************************************");

            } catch (Exception err) {   //If user does input an int, but it is less than 2. Also if a double is inputted.
                System.out.println("You must input an integer greater or equal to 2!");
                bound = "retry";
                System.out.println("**************************************");
            }
        }
        return temp;
    }

    /**
     * Displays all the results of computing the list of prime numbers with 12 numbers per line. Includes percentage prime.
     * @param listUpperBound An integer to be the upper bound of the prime number list to compute.
     */
    private static void display(int listUpperBound) {
        System.out.println("**************************************");
        System.out.println("The prime numbers up to " + listUpperBound + " are:");

        //Chose to make Primes.findPrime() a static method.
        //No need to instantiate a Primes obj if it's just a class to compute things and not store data.
        ArrayList<Integer> list = Primes.findPrimes(listUpperBound);

        int tally = 0;
        for (int x : list) {    //Prints out each prime number in the list.
            System.out.print(x);
            System.out.print(" ");
            tally += 1;
            if (tally >= 12) {
                tally = 0;
                System.out.println();
            } //12 numbers per line.
        }

        if (list.size() % 12 != 0)
            System.out.println();    //Guarantees the percentage below is always printed one line away
        //Really it's just to "look pretty" and have consistent spacing.

        System.out.println();
        System.out.printf("Total %% Prime: " + "%-2.1f" + "%%", 100 * (list.size() / (double)listUpperBound));
        System.out.println();
        System.out.println("**************************************");
    }
}