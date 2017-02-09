/**
 * Created by Andrew Nguyen; Professor Barbara Goldner's CSC 142, Fall 2015
 * Tuesday, October 20, 2015
 *
 * PROGRAM DESCRIPTION
 * ===================
 * This is the class file that is referenced by BaseConversionsApp.java.
 * It contains 3 methods to convert a base-10 number to binary, octal, or hexadecimal.
 * Each method has a precondition check to ensure that the number is a positive integer.
 * I also chose to use the protected access modifier so that only these two classes could access them.
 * Just so I can keep my homework folder more organized.
 *
 */


package NguyenHW3;

public class BaseConversions {
    /**
     * Method to convert to binary. I used a loop to take a modulus of 2. If the number divided evenly,
     * it would be 0, if not, 1. Binary is simple. Uses a for loop to reverse the final string of converted numbers.
     */
    protected static String tenToBinary(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("You cannot use a negative value.");
        }


        String convertedNum = "";   //Will output opposite to what is the true output
        String finalString = "";    //Will be used to reverse output from convertedNum
        do {
            if (num % 2 == 0) {
                convertedNum += 0;
            } else if (num % 2 > 0) {
                convertedNum += 1;
            }
            num /= 2;
        } while (num != 0);
        //Take note, the output is opposite from what it should be. Eg: 25 in binary will show up as 10011
        //But it should really be 11001. Need to find a way to reverse the order of the string...

        /**
         * StringBuilder class allows for the manipulation of strings. Ordinarily, the reversal of a string
         * would be written with a for loop iterating through it character by character and concatenating it
         * to a string. Done using a for loop this time for assignment.
         */
        for (int i = convertedNum.length(); i > 0; i--) {
            char c = convertedNum.charAt(i-1);
            /**
             * (i-1) because .length() returns the number of characters. eg: "11001" would return as 5.
             * But charAt() counts strings starting at 0. "11001" starts at char#0 and ends on char#4.
             */
            finalString += c;
        }
//        System.out.println(finalString);
        return finalString;
    }

    /**
     * Structure of the octal conversion method is near identical, with only two changes to the do...while loop.
     * It takes a modulus of 8 instead, and adds that remainder to the string. The for loop for reversing the string
     * is unchanged.
     */
    protected static String tenToOctal(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("You cannot use a negative value.");
        }


        String convertedNum = "";
        String finalString = "";

        do {
            if (num % 8 == 0) {
                convertedNum += 0;
            } else if (num % 8 > 0) {
                convertedNum += (num % 8);
            }
            num /= 8;
        } while (num != 0);

        for (int i = convertedNum.length(); i > 0; i--) {
            char c = convertedNum.charAt(i-1);
            finalString += c;
        }
//        System.out.println(finalString);
        return finalString;
    }

    /**
     * Again, the for loop to reverse the final string is unchanged. But this time, I implemented a switch to activate
     * if the value of num % 16 was greater than 9, and mapped those values to the proper letters in hexadecimal.
     */
    protected static String tenToHex(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("You cannot use a negative value.");
        }


        String convertedNum = "";
        String finalString = "";

        do {
            if (num % 16 == 0) {
                convertedNum += 0;
            } else if (num % 16 > 0 && num % 16 <= 9) {
                convertedNum += (num % 16);
            } else if (num % 16 >= 10) {
                int val = num % 16;
                String valString = "";
                switch (val) {
                    case 10:
                        valString = "A";
                        break;
                    case 11:
                        valString = "B";
                        break;
                    case 12:
                        valString = "C";
                        break;
                    case 13:
                        valString = "D";
                        break;
                    case 14:
                        valString = "E";
                        break;
                    case 15:
                        valString = "F";
                        break;
                }
                convertedNum += valString;
            }
            num /= 16;
        } while (num != 0);

        for (int i = convertedNum.length(); i > 0; i--) {
            char c = convertedNum.charAt(i-1);
            finalString += c;
        }
//        System.out.println(finalString);
        return finalString;
    }
}
