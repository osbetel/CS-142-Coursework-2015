package NguyenHW4;


/**
 * @author Andrew Nguyen
 * @version 23 Oct 2015, 8:27 PM
 *
 * This is the class file referenced and called by the FindADate class. It includes constructors for a Date
 * object, as well as the appropriate accessor and mutator methods for it.
 */

public final class Date {
    int month;
    int day;
    int year;
    String monthString;
    boolean leapYear;
    boolean DAYS_FROM_DATE_RESTRICTION = true;      //Restricts daysFromDate() to a range of 1-31 days.
                                                    // Remove to calculate further dates.

    /**
     * Constructor for a Date object. Requires 3 parameters. The exception it throws is intentionally left uncaught,
     * requiring a client to catch it, should they wish to execute actions afterwards like displaying an error message,
     * in the event of an invalid date.
     * @param m     Represents the month in integer format; 1 to 12.
     * @param d     Represents the day in integer format; 1 to 31. February and leap years accounted for later.
     * @param y     Represents the year in integer format.
     * @throws      IllegalArgumentException, to be caught by any class wishing to create a Date obj.
     */
    public Date(int m,int d,int y) throws IllegalArgumentException {
        month = m;
        day = d;
        year = y;
        monthString = monthToString(m);

        if (!isLegal(m,d,y)) {      //Verification upon object creation.
            throw new IllegalArgumentException("An error occurred in the construction of the object." +
                    "Your date values must have been invalid.");
        }
    }

    /**
     * This method is used directly in the constructor to verify that the date is a valid one. Returns as boolean.
     * Parameters used are directly from the ones put into the constructor when it is first instantiated.
     * Method is made private and can only be accessed when constructing a Date object, and only by the object itself.
     * This ensures that any Date object created has to be of a valid date, or it simply won't initialize.
     *
     * @param m     Represents the month in integer format; 1 to 12.
     * @param d     Represents the day in integer format; 1 to 31, February and leap years are accounted for.
     * @param y     Represents the year in integer format.
     * @return      The method returns a boolean value; if true, the object is created, if false, an Exception is thrown.
     * @throws      IllegalArgumentException if m, d, or y are invalid dates or not given at all.
     */
    private boolean isLegal(int m,int d,int y) throws IllegalArgumentException {
        /**
         * Try block with a catch for illegal args at the end. First checks that month, day, and year are in a valid
         * range. Then checks if it is a leap year; finally followed by a check on the months with less than 31 days
         * to ensure that their day value is in the proper range. Accounts for February and leap years.
         */
        try {
            //Three separate if statements for 3 separate Exceptions. Instead of a bunch of &&s and ||s.
            if (y < 1600) {
                throw new IllegalArgumentException("The year must be a number greater than 1600.");
            } if (d > 31 || d < 1) {
                throw new IllegalArgumentException("The day must be a number between 1 and 31.");
            } if (m > 12 || m < 1) {
                throw new IllegalArgumentException("The month must be a number from 1 to 12.");
            }

            //Verifies whether it is a leap year or not.
            if (y % 4 == 0 && y % 100 != 0 || y % 400 ==0) { //If the year is divisible by 4 but not 100, or divisible by 400
                leapYear = true;
            } else {
                leapYear = false;
            }

            /*Verifies that the months with less than 31 days have the proper amount of days
            * Months w/ 31 days are handled by the decision structure above. This is just extra for the other months.*/
            switch (m) {
                case 2:                             //February is the only special case. only 28 days, 29 on a leap year.
                    //Leap year verification comes into play here.
                    if (!leapYear && d > 28) {
                        throw new IllegalArgumentException("It is not a leap year, February has 28 days.");
                    }
                    else if (leapYear && d > 29) {
                        throw new IllegalArgumentException("February only has 29 days on leap years.");
                    }

                    break;
                case 4:                      //These are the months that only have 30 days. 31 day months not included
                case 6:                      //because the maximum day count is set to 31 anyway.
                case 9:
                case 11:

                    //If cases 4,6,9,11 trigger, then they are checked for 30 days, and throw an exception if days == 31.
                    if (d > 30) {
                        throw new IllegalArgumentException(monthString + " only has 30 days. Try again.");
                    }
                    break;
            }

        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException(err);        //Throws an exception which is caught by the constructor itself. A chained exception.
        }

        //If the date passes all these tests and raises no exceptions, then it returns true and the object is constructed.
//        System.out.println(a +"/"+ b +"/"+ c);
//        System.out.println("Date is valid");
//        System.out.println("Is leap year? " + leapYear);
        return true;
    }

    /**
     * Allows verification of any Date object at any time. Would be used in situations where client needed to verify
     * date but did not want to create a new equal object to compare two objects for equality, or did not want to
     * modify any of the object's internal data values. I used it mainly for testing purposes.
     * @return boolean true/false
     */
    public boolean isLegalAsObj() {
        int m = this.month;
        int d = this.day;
        int y = this.year;
        return isLegal(m,d,y);
    }

    public void isLeapYear() {
        int y = this.year;
        //taken directly from isLegal().
        if (y % 4 == 0 && y % 100 != 0 || y % 400 ==0) {
            leapYear = true;
        } else {
            leapYear = false;
        }
    }

    /**
     * Sets the value for monthString based on the integer value of the month.
     * @param m The number of a month.
     * @return Returns the string form of a month of number m. eg: if m is "11," returns "November."
     */
    private String monthToString(int m) {
        switch (m) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
        }
        return monthString;
    }

//    /**
//     * Sets value of the month based on the String value of the monthString
//     * Added as an optional method in the case of future use. Not part of HW4 assignment.
//     * @param m The month in String form. eg: "November"
//     * @return Returns the month as an integer.*/
//
//    public int stringToMonth(String m) {
//        int month = 0;
//        switch (m) {
//            case "January":
//                month = 1;
//                break;
//            case "February":
//                month = 2;
//                break;
//            case "March":
//                month = 3;
//                break;
//            case "April":
//                month = 4;
//                break;
//            case "May":
//                month = 5;
//                break;
//            case "June":
//                month = 6;
//                break;
//            case "July":
//                month = 7;
//                break;
//            case "August":
//                month = 8;
//                break;
//            case "September":
//                month = 9;
//                break;
//            case "October":
//                month = 10;
//                break;
//            case "November":
//                month = 11;
//                break;
//            case "December":
//                month = 12;
//                break;
//        }
//        return month;
//    }

    //BEGIN ACCESSOR METHODS

    /**
     * Gets the day value of the Date object
     * @return Object's day value as an int.
     */
    public int getDay() {
        return day;
    }

    /**
     * Get the month value of the Date object
     * @return Object's month value as an int.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Get the year value of the Date object.
     * @return Object's year value as an int.
     */
    public int getYear() {
        return year;
    }

    /**
     * Get the month value of the Date object as a string.
     * @return Specifically returns the month value as a string. eg: month = 11 would return "November"
     */
    public String getMonthName() {
        return monthString;
    }

    /**
     * Method to convert the Date object's month, day, year values to a mm/dd/yyyy format for an end user.
     * @return Returns the mm/dd/yyyy form of the Date object as a String.
     */
    @Override
    public String toString() {      //Note, must be public or it can't override java.lang.Object.toString()
        String s;
        if (month < 10) {       //This is for adding a 0 to the front of single digit months.
            s = ("0" + month + "/" + day + "/" + year);
        } else {
            s = (month + "/" + day + "/" + year);
        }
        return s;
    }

    /**
     * Method for calculating a date d days away from the current Date object's date values.
     * Does fine for any number of days d, even numbers outside of the assignment range of 31.
     * To keep in line with the assignment, a class constant has been added to restrict input
     * from 1 to 31. The restriction interferes with calculation though, so it is set to false after the
     * parameter is verified as valid, and set back to true when calculations are done.
     *
     * @param d The number of days away of the date that you wish to calculate.
     * @return Returns the Date object itself, after it's month, day, year values have been modified.
     * @throws IllegalArgumentException
     */
    public Date daysFromDate(int d) throws IllegalArgumentException{
        if (DAYS_FROM_DATE_RESTRICTION) {
            try {
                if (d < 1 || d > 31) {
                    throw new IllegalArgumentException("You must choose a date 1 to 31 days away to calculate.");
                }
                DAYS_FROM_DATE_RESTRICTION = false;         //Shut off restriction for calculation. Verification is only needed once.
            } catch (IllegalArgumentException err) {
                throw new IllegalArgumentException(err); //Throws the exception to a higher level catcher,
            }                                            //Handled in the FindADate class.
        }

        day += d;                                   //Adds the # of days you want to calculate to the day value.


        isLeapYear();
        if (month > 12) {                           //Handles the changing of year values.
            month -= 12;
            year += 1;
            daysFromDate(0);                        //Each of these resets to the top of the method when a value is changed. Day values stay changed though.
        }
        if (month == 2 && this.leapYear) {          //intended to handle february on leap years
            if (day > 29) {
                day -= 29;
                month += 1;
                daysFromDate(0);
            }
        } else if (month == 2) {                    //handles regular february.
            if (day > 28) {
                day -= 28;
                month += 1;
                daysFromDate(0);
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {         //Handles months with 30 days.
            if (day > 30) {
                day -= 30;
                month += 1;
                daysFromDate(0);
            }
        } else {                                    //Handles all other months.
            if (day > 31) {
                day -= 31;
                month += 1;
                daysFromDate(0);
            }
        }
        this.setDay(day);       //This portion sets the day, month, year values to the new calculated values.
        this.setMonth(month);   //This is a bit redundant though, but serves as a double check before isLegalAsObj() is called.
        this.setYear(year);
        isLegalAsObj();         //Final verification of the object after date is calculated.
        DAYS_FROM_DATE_RESTRICTION = true;          //Turns verification back on.
        return this;
    }
    //END ACCESSOR METHODS

    //BEGIN MUTATOR METHODS
    /**
     * Method to set the day value of a Date object.
     * @param d Which day d to set the day value as.
     */
    public void setDay(int d) {
        day = d;
        isLegalAsObj();
    }

    /**
     * Method to set the month value of a Date object.
     * @param m Which month (integer only) to set the month value to.
     */
    public void setMonth(int m) {
        month = m;
        isLegalAsObj();
    }

    /**
     * Method to set the year value of a Date object.
     * @param y Which year to set the year value to.
     */
    public void setYear(int y) {
        year = y;
        isLegalAsObj();
    }
    //END MUTATOR METHODS
}
