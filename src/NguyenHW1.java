/**
 * Created by Andrew Nguyen; Professor Barbara Goldner's CSC 142, Fall 2015
 * Wednesday, October 7, 2015
 *
 * PROGRAM DESCRIPTION
 * ===================
 * This program is written to simply display the values of e^x and sin(x).
 * The values are calculated using the Taylor series for each.
 * e^x = 1 + (x^1)/(1!) + (x^2)/(2!) ...
 * sin(x) = x - (x^3)/3! + (x^5)/5! - (x^7)/7! ...
 *
 *
 */


public class NguyenHW1 {

    /**
     * Returns a value of n!; where n is a method parameter
     * n! is calculated using a multiplicative accumulator, nFinal.
     */
    public static double factorial(int n) {
        double nFinal = 1;
        for (int i = 1; i <= n; i++) {
            nFinal *= i;        //Looping to calculate factorial of n.
        }
        return nFinal;
    }

    /**
     * This method calculates for e^x and returns the results to the main method with each iteration
     * of the for loop. Nested for loops; the first loop iterates for the term up to 30, the second
     * loop calculates Taylor Series up to n = 30. Can be changed for more or less accuracy.
     */
    public static double eX() {
        double result = 1;      //Result has a 1 added to it because Taylor series for e^x starts with 1
        double nFinal;
        System.out.println("x values                         e^x values");
        System.out.println("===========================================");
        for (int term = 1; term <= 30; term++) {
            result = 1;

            for (int n = 1; n <= 30; n++) {     //How many times the Taylor Series is calculated to. eg: x^30/30! or higher or lower.
                nFinal = factorial(n);
                result += (Math.pow(term, n)) / (nFinal);
            }
            System.out.printf("%-20.30s  %-20.30s%n", "x = " + term, result);
        }
        System.out.println("\n===========================================\n===========================================\n===========================================\n");
        return result;
    }

    /**
     * This method calculates for sin(x) and returns the results to the main method with each iteration
     * of the for loop. Nested for loops; the first loop iterates for the term up to 30, the second
     * loop calculates Taylor Series up to n = 59. Can be changed for more or less accuracy.
     */
    public static double sinX() {
        double result = 0;
        double nFinal;
        System.out.println("pi values                     sin(x) values");
        System.out.println("===========================================");

        for (int term = 1; term <= 30; term++) {    //How many terms to calculate to.
            result = 0;

            /**
             * 59 is used in the following for loops instead of 30 because the Taylor Series for sin(x)
             * iterates by 2s. And 30 terms with that interval = 59 if starting from 1. Two for loops were written;
             * one adds the positive values of the Taylor Series (+x^5/5!, +x^9/9!, etc.) while the other subtracts
             * the negative values.
             */

            for (int n = 1; n <= 59; n+=4) {        //How many times to iterate the Taylor Series. If this is changed, then change the one below as well
                nFinal = factorial(n);
                result += (Math.pow((Math.PI/2*term), n)) / (nFinal);
            }

            for (int n = 3; n <= 59; n+=4) {        //How many times to iterate the Taylor Series. If this is changed, then change the one above as well
                nFinal = factorial(n);
                result -= (Math.pow((Math.PI/2*term), n)) / (nFinal);
            }
            System.out.printf("%-20.30s  %-20.30s%n", term + "π/2", result);
        }
        System.out.println("\n===========================================\n");
        return result;
    }

    /**
     * Main method that rallies all the others.
     */
    public static void main(String[] args) {
        eX();
        sinX();
//        factorial()
        /**
         * leftover from testing the factorial() method. Am I allowed to leave code from testing here?
         * I tend to leave code from testing sometimes, so that I know what I did exactly should I
         * ever have to come back and look at it. If you don't like it, I'll be sure to remove it all
         * in the future.
         * -Andrew
         */

    }
}