import java.util.Scanner;

/**
 * -~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-
 * Created by ADKN
 * 23 Oct 2015
 * 6:20 PM
 * -~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-
 */


public class Calculator {
    String Fx;
    String Gx;
    Scanner input = new Scanner(System.in);

    public Calculator() {
        System.out.println("Please enter the \"F(x)\" portion of the function.");
        Fx = input.next();
        System.out.println("Please enter the \"G(x)\" portion of the function.");
        Gx = input.next();
    }
    public Calculator(String a) {
        Fx = a;
    }
    public Calculator(String top, String bottom) {
        Fx = top;
        Gx = bottom;
    }
    public String calculateDX_powerRule() {
        String dX;
        dX = "potato";



        return dX;
    }
}
