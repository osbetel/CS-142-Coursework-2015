import java.io.*;
import java.util.*;

/**
 * // {{{
 * -~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-
 * @author ${USER}
 * @version ${DAY} ${MONTH_NAME_SHORT} ${YEAR}
 * ${TIME}
 * -~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-
 * ${PACKAGE_NAME}
 * ===================
 * CLASS DESCRIPTION
 * ===================
 *
 * ===================
 * DESCRIPTION END
 * ===================
 * // }}}
 */





public class MethodLibrary {
    /**
     * Strips a text file of all extraneous spaces and tabs, and prints it to console.
     * @param input Accepts a Scanner object representing a File object.
     * @throws FileNotFoundException
     */
    public static void collapseSpaces(Scanner input) throws FileNotFoundException {
        while (input.hasNextLine()) {
            String tempLine = input.nextLine();
            tempLine = tempLine.replaceAll("\\s+", " ");
            tempLine = tempLine.trim();
            System.out.println(tempLine);
        }
    }

    /**
     * Returns a text file as a string object.
     * @param input Scanner object representing a File object.
     * @return String object with original file's contents.
     */
    public static String readEntireFile(Scanner input) {
        String tempFile = "";
        while (input.hasNextLine()) {
            tempFile += input.nextLine();
            tempFile += "\n";
        }
        return tempFile;
    }

    /**
     * Determines the range of an int[]. Range being the distance from the largest to the smallest, all inclusive.
     * @param array The integer array to be passed into the method.
     * @return Returns an integer denoting the range of the array. eg: {0,1,2,3,4,5,6,7} would return 8.
     */
    public static int arrayRange(int[] array) {
        int[] temp = new int[array.length];
        temp = array;
        Arrays.sort(temp);
//        System.out.println(temp);
        return temp[temp.length-1] - temp[0] + 1;
    }

    /**
     * Method to find the closest "bid" (in array param) to the price. Bid cannot be more than the price.
     * @param array an int[] of bids
     * @param price a price value as an int
     * @return returns the closest bid to the desired price
     */
    public static int priceIsRight(int[] array, int price) {
        int diffIndex = 0;
        int closePrice;
        int[] maxVal = new int[array.length];
        int[] difference = new int[array.length];

        //Create an array of the difference in price from original bids.
        for (int i = 0; i < array.length; i++) {
            difference[i] = price - array[i];
            maxVal[i] = array[i];
        }

        Arrays.sort(maxVal); //in order to get the max value of the array.
        int temp = maxVal[maxVal.length-1];

        //Find the index of the array with the smallest difference value (closest to the price)
        for (int i = 0; i < difference.length; i++) {
            if (difference[i] >= 0) {
                if (price - array[i] < temp) {
                    temp = difference[i];
                    diffIndex = i;
                }
            }
        }

        //Bid must be less or equal to price.
        if (array[diffIndex] <= price) {
            closePrice = array[diffIndex];
        } else {
            closePrice = -1;
        }
        return closePrice;
    }

    /**
     * Method to calculate standard deviation of an integer array.
     * @param array an int[]
     * @return returns a double value; the standard deviation of the array.
     */
    public static double stdev(int[] array) {
        double stDeviation;
        double sum = 0;
        double avg = 0;
        //steps: take sum of (all differences of values from the total average). get avg first.
        for (int i = 0; i < array.length; i++) {
            avg += array[i];
        } avg /= array.length;

        for (int i = 0; i < array.length; i++) {
            sum += Math.pow((array[i] - avg),2);
        }
        stDeviation = Math.sqrt(sum/(array.length-1));

        return stDeviation;
    }
    public static double stdev(ArrayList<Integer> list) {
        double stDeviation;
        double sum = 0;
        double avg = 0;
        //steps: take sum of (all differences of values from the total average). get avg first.
        for (int i = 0; i < list.size(); i++) {
            avg += list.get(i);
        } avg /= list.size();

        for (int i = 0; i < list.size(); i++) {
            sum += Math.pow((list.get(i) - avg),2);
        }
        stDeviation = Math.sqrt(sum/(list.size()-1));

        return stDeviation;
    }

    /**
     * Creates an array of prime numbers until the array length is reached.
     * Utilizes isPrime(); Prime numbers start from 2.
     * @param length How long do you want the array to be?
     * @return Will return an array of the first (length) prime numbers.
     */
    public static int[] primeNumArray(int length) {
        int[] primeArray = new int[length];
        int index = 0;

        do {
            for (int k = 2; index < length; k++) {

                if (isPrime(k)) {
                    primeArray[index] = k;
                    index +=1;
                }
            }
        } while (index < length);
        return primeArray;
    }

    /**
     * Determines whether or not a number is prime by dividing it by all previous numbers up to sqrt(n)
     * See "Sieve of Eratosthenes"
     * @param n The number to be tested for prime status.
     * @return Returns true if prime, false if not.
     */
    public static boolean isPrime(int n) {
        if (n <= 1) return false;   //negatives and 1 do not qualify as prime.

        for (int i = 2; i <= (int)Math.sqrt(n); i++) {   //Dividing the num by previous
            if (n % i == 0)
                return false;
        }
        return true;
    }

    /**
     * Creates an array of the fibonacci sequence until the array length is reached.
     * @param length How long do you want the array to be?
     * @return Returns an array of the first (length) fibonacci numbers.
     */
    public static int[] fibonacciArray(int length) {
        //Taken by adding the two numbers before it. eg: arr[2] = arr[1] + arr[0]
        int[] fibArray = new int[length];
        if (length == 0) {
            return fibArray;
        } if (length == 1) {
            fibArray[0] = 0;
            return fibArray;
        }

        fibArray[0] = 0;    //Default starting values.
        fibArray[1] = 1;

        for (int i = 2; i < length; i++) {
            fibArray[i] = fibArray[i-1] + fibArray[i-2];
        }
        return fibArray;
    }

    /**
     * method removeEvenLength that takes an ArrayList of Strings as a parameter and
     * that removes all of the strings of even length from the list.
     * @param list An ArrayList of Strings
     */
    public static void removeEvenLength(ArrayList<String> list) {
        Iterator<String> iter  = list.iterator();

        while (iter.hasNext()) {
            if (iter.next().length() % 2 == 0) { //If string length is even...
                iter.remove();
            }
        }
    }

    /**
     * method doubleList that takes an ArrayList of Strings as a parameter and that replaces every string with two of that string.
     * For example, if the list stores the values {"how", "are", "you?"} before the method is called,
     * it should store the values {"how", "how", "are", "are", "you?", "you?"} after the method finishes executing.
     * @param list An ArrayList of Strings
     */
    public static void doubleList(ArrayList<String> list) {
        Iterator<String> iter = list.iterator();
        ArrayList<String> oldList = new ArrayList<>(list.size());
        for (String item: list) {
            oldList.add(item);
        }
        iter = oldList.iterator();
        list.clear();

        while (iter.hasNext()) {
            String toDouble = iter.next();
            list.add(toDouble);
            list.add(toDouble);
        }
    }

    /**
     * Write a method minToFront that takes an ArrayList of integers as a parameter and that moves the minimum value in the
     * list to the front, otherwise preserving the order of the elements. For example, if a variable called list stores the
     * following values: {3, 8, 92, 4, 2, 17, 9} and you make this call: minToFront(list); it should store the following
     * values after the call: {2, 3, 8, 92, 4, 17, 9} You may assume that the list stores at least one value.
     */
    public static void minToFront(ArrayList<Integer> list) {
        Iterator<Integer> iter = list.iterator();
        int lowest = list.get(0);
        int index = 0;
        int tempIndex = -1;

        while (iter.hasNext()) {
            int testInt = iter.next();
            tempIndex += 1; //Starts at 0.

            if (testInt < lowest) {
                lowest = testInt;
                index = tempIndex;  //Saves index of the low number in the list.
            }
        }
        //var lowest is now the low integer and var index is the index of that integer

        list.remove(index); //Removes lowest one. Still saved in lowest.
        ArrayList<Integer> oldList = new ArrayList<>(list.size());
        for (Integer item: list) {
            oldList.add(item);
        }
        //Makes clone of the original list with the lowest number already removed.

        //Clears the original list and adds the low value to the front.
        list.clear();
        list.add(lowest);

        //add all the other values back, retaining order.
        for (int x : oldList) {
            list.add(x);
        }
    }
}
