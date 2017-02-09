import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author ADKN
 * @version 08 Dec 2015, 6:50 PM
 */
public class Primes {
//    private ArrayList<Integer> primeList = new ArrayList<>();
//    private ArrayList<Integer> consecList = new ArrayList<>();

    /**
     * Originally I made the two lists members of the class, and the methods were non-static,
     * but later decided it would be easier if one didn't have to instantiate the class just to find prime
     * numbers. So I chose to make the lists members of the findPrimes() method instead,
     * and for all the methods to be static.
     * Arguably, it may be more efficient in terms of memory that way,
     * as there's no need to instantiate the class. So after finding the prime numbers, the lists would be
     * garbage collected.
     */


    /**
     * Specified algorithm (Sieve of Eratosthenes):
     * 1) Create 2 lists; one of all ints from 2 - N, and one that is empty.
     * 2) Get the next prime # by removing it from List 1 and put it in List 2.
     * 3) Eliminate all multiples of that prime # from List 1.
     * 4) Repeat, unless the most recent prime is >= Sqrt(N)
     *
     * @param upperBound The upper bound of all possible prime numbers to be found.
     * @return Returns an ArrayList of all the prime numbers from 2 to the specified upperBound
     * @throws IllegalArgumentException If the upperBound specified is less than 2.
     */
    public static ArrayList<Integer> findPrimes(int upperBound) throws IllegalArgumentException {
        ArrayList<Integer> primeList = new ArrayList<>();
        ArrayList<Integer> consecList = new ArrayList<>();

        if (upperBound < 2) {
            throw new IllegalArgumentException("The upper bound must be an integer of at least 2 or greater.");
        }

        //Populates consecList with all #'s from 2 - upperBound.
        for (int i = 2; i <= upperBound; i++) {
            consecList.add(i);
        }

        //This right here serves as the "post" for the fencepost loop.
        //This fencepost was needed so that primeList would not be empty at the start of the while loop.
        //Otherwise mostRecentDivisor could not be assigned in the loop.
        primeList.add(consecList.get(0));                               //Adds 2 to the primeList.
        consecList.remove(0);                                           //Removes 2 from consecList.
        int mostRecentDivisor = primeList.get(primeList.size() - 1);    //defines mostRecentDivisor as the last int in primeList
        removeDivisible(consecList, mostRecentDivisor);                 //Removes all divisble by 2 in consec list

        while (!consecList.isEmpty()) {

            mostRecentDivisor = primeList.get(primeList.size()-1);  //Would throw IdxOutOfBounds Ex if primeList is empty.
            if (mostRecentDivisor >= (int)Math.sqrt(upperBound)) {
                primeList.addAll(consecList);   //adds all consecList values to primeList if we've passed the sqrt of upperBound
                consecList.clear();
                return primeList;       //Exits method if consecList's values have been added to primeList.
            } else {
                primeList.add(consecList.get(0));   //No need to confirm if next int is prime, eratosthenes algorithm guarantees it is.
                consecList.remove(0);
            }

            mostRecentDivisor = primeList.get(primeList.size() - 1);    //Defines most recent prime # every loop execution.
            removeDivisible(consecList, mostRecentDivisor);    //Removes the divisible numbers of the current prime from consecList
        }
        return primeList;
    }

    /**
     * Method to remove any number from an ArrayList that is divisible by a specified integer.
     * @param list  The ArrayList to process for divisible numbers.
     * @param divideBy  An integer that acts as the divisor. All #'s divisible by it are removed.
     */
    private static void removeDivisible(ArrayList<Integer> list, int divideBy) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int temp = it.next();
            if (temp % divideBy == 0) it.remove();  //Removes value from the list if it is divisible by the specified int.
        }
    }

    /**
     * Determines whether or not a number is prime by dividing it by all previous numbers up to sqrt(n)
     * (See "Sieve of Eratosthenes")
     * @param n The number to be tested for prime status.
     * @return Returns true if prime, false if not.
     */
    public static boolean isPrime(int n) {
        if (n <= 1) return false;   //negatives and 1 do not qualify as prime.

        for (int i = 2; i <= (int)Math.sqrt(n); i++) {  //if n is divisible by all ints up to its sqrt, then it is prime.
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
