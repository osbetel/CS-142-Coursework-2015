package NguyenHW4;


/**
 * Top-level client -- controls the application.  This GUI program prompts
 * the user for a date in the form mm/dd/yyyy and then determines the date
 * 30 days in the future.
 * <br>
 * This is the file you need to modify to complete the assignment. The majority
 * of your work will be in the method respondToUser(), though you may decide
 * to add code in other places as well
 * <br>
 * To start the program, call the main() method.
 * 
 * @author CSC 142, Modified by Andrew Nguyen
 * @version 1-23-07
 */
public class FindADate {
    /**
     * DAY_SKIP class constant allows specification of how many days to skip via the daysFromDate()
     * method (from the Date class) in any future uses.
     */
    private final int DAY_SKIP = 30;
    private UserWindow window;

    /**
     * Create a new FindADate object which gets input from the actions done to the UserWindow GUI.
     */
    public FindADate( ) {
        window = new UserWindow(this);
    }
    
    /**
     * Perform the task of determining 30 days from the given day and output to the gui.
     */
    public void respondToUser() {
//       System.out.println("You've downloaded and compiled correctly");

        String monthString = window.getMonthInput();    //Get month from the gui as a string...
        int month = stringToMonth(monthString);         //Which is converted to an int for the creation of a Date object.
        int day = window.getDayInput();                 //The day is gotten as an int; no need for conversions here.
        int year = window.getYearInput();               //The year is gotten in the same way and data type as the day.


        //Needs method to convert month from string to int; Date class only has int to String
        //UserWindow doesn't have it either. Will write one. See below as stringToMonth().

        /**
         * Creates a new Date object called futureDate. The constructor itself runs verification on the
         * validity of the Date object, causing an unhandled exception if it's not valid.
         * It is caught to display the error message here.
         * Date constructor takes parameters in month,day,year format; all integers.
         * futureDateString is the final string, after the next date 30 days away is calculated.
         */
        try {
            Date futureDate = new Date(month, day, year);        //Constructs object. Object is verified on construction for validity.
            String futureDateString = (futureDate.daysFromDate(DAY_SKIP).toString());   //If exception not thrown, calculates future date
            window.displayText(futureDateString);           //Displays date to gui.
        } catch (Exception err) {
            window.displayErrorWindow(err.toString());      //Displays error messages to the gui if exception is thrown.
        }

    }

    /**
     * Converts the string form of a month to the integer form, and sets the Date obj's month value to the int form.
     * @param m A string form of a month, eg: "November," which is converted to the number of that month.
     * @return Returns the integer of the month. eg: "November" as String m will return "11."
     */
    public int stringToMonth(String m) {
        int month = 0;
        switch (m) {
            case "January":
                month = 1;
                break;
            case "February":
                month = 2;
                break;
            case "March":
                month = 3;
                break;
            case "April":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "June":
                month = 6;
                break;
            case "July":
                month = 7;
                break;
            case "August":
                month = 8;
                break;
            case "September":
                month = 9;
                break;
            case "October":
                month = 10;
                break;
            case "November":
                month = 11;
                break;
            case "December":
                month = 12;
                break;
        }
        return month;
    }
    
// NO NEED TO CHANGE ANY CODE BELOW THIS LINE.
     /**
     * Allows this application to be run as a stand-alone program, and
     * also starts it up in a 'thread-safe" way.
     */
    public static void main( String[] args ) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FindADate pa = new FindADate( );
            }
        });
    }   
}