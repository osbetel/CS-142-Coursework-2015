package BankPackage;
import java.text.DecimalFormat;
import java.util.*;

/*  A Bank holds a list of BankAccounts. */

public class Bank {
    private TreeMap<String,BankAccount> list;
    private double totalBal;
    private int totalAccts;

    public Bank() {
        list = new TreeMap<>();
        totalBal = 0;
        totalAccts = 0;
    }

    public void addAccount(BankAccount acct) {
        list.put(acct.getID(),acct);    //Stores ID and pairs with BankAccount obj.
    }

    public int numOfAccts() {
        return list.size();
    }

    public double avgBal() {
        if (list.size() == 0) {
            return 0;
        }

        Set<String> set = list.keySet();
        for (String str : set) {
            totalBal += list.get(str).getBalance();
            totalAccts += 1;
        }
        return (totalBal/totalAccts);
    }

    public BankAccount getAcct(String id) {
        if (list.containsKey(id)) {
            return list.get(id);
        } else {
            return null;
        }
    }

    public BankAccount largestAcct() {
        if (list.isEmpty()) {
            return null;
        }

        Set<String> set = list.keySet();
        BankAccount largest = list.get(list.firstKey());       //gets first acct to compare to.
        for (String str : set) {
            if (list.get(str).getBalance() > largest.getBalance()) {
                largest = list.get(str);
            }
        }
        return largest;
    }
}

/**
 * Complete this class by implementing the following behaviors. A client should be able to:
 * 1. add a BankAccount to this Bank.
 * 2. query for the number of BankAccounts at this Bank
 * 3. query for the average balance of all BankAccounts at this Bank. Return 0 if there are no accounts.
 * 4. query for a BankAccount based on ID (that is a String).  Return the BankAccount, or null if it isn't found
 * 5. query for the BankAccount with the largest balance (return null if the Bank has no accounts).
 */
