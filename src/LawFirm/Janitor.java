package LawFirm;

/**
 * @author ADKN
 * @version 20 Nov 2015, 10:26 PM
 */
public class Janitor extends Employee {
    private int baseHours = 80;
    private double baseSalary = 30000.0;
    private int baseVacationDays = 5;

    @Override
    public double getSalary() {
        return super.getSalary() - 10000.0;
    }
    @Override
    public int getHours() {
        return super.getHours() * 2;
    }
    @Override
    public int getVacationDays() {
        return super.getVacationDays()/2;
    }
    public void clean() {
        System.out.println("Workin' for the man.");
    }
}
