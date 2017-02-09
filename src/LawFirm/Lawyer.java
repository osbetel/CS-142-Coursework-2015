package LawFirm;

/**
 * @author ADKN
 * @version 20 Nov 2015, 10:39 PM
 */
// A class to represent lawyers.
public class Lawyer extends Employee {
    // overrides getVacationDays from Employee class
    @Override
    public int getVacationDays() {
        return super.getVacationDays() + 5;
    }

    // overrides getVacationDays from Employee class
    @Override
    public String getVacationForm() {
        return lawyerVacationForm;
    }

    // this is the Lawyer's added behavior
    public void sue() {
        System.out.println("I'll see you in court!");
    }


    // these are so that test cases can change the Lawyer values
    // and make sure that subclasses also change
    private String lawyerVacationForm = "pink";

    public final void setVacationForm(String form) {
        lawyerVacationForm = form;
    }
}