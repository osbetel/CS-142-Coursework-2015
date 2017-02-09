/**
 * Calculates only the coefficients of polynomials. eg: 3x^2 + 6x + 18
 * would return as "6 6," showing the coefficient of d/dx of 3x^2 and d/dx of 6x.
 * Very misleading. Not a true derivative calculator.
 */



import java.io.*;
import java.text.*;
import java.util.Scanner;

public class Derivative_Calculator
{
    public static void main (String args[]) {
        String choice = "n";
        Scanner sc = new Scanner(System.in);
        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                DecimalFormat df = new DecimalFormat("#.####");
                System.out.print("Enter the highest degree of the polynomial: ");
                int a = Integer.parseInt(br.readLine());
                double coefficient[] = new double[a + 1];
                double dxdy[] = new double[a];
                for (int i = 0; i <= a; i++) {
                    System.out.print("Enter number: ");
                    coefficient[i] = Double.parseDouble(br.readLine());
                }
                int ce = a;
                for (int j = 0; j < a; j++) {
                    dxdy[j] = coefficient[j] * ce;
                    ce--;
                }
                System.out.print("Derivative of Function: ");
                if (a != 0) {
                    for (int k = 0; k < a; k++) {
                        System.out.print(df.format(dxdy[k]) + " ");
                    }
                } else {
                    System.out.print(0);
                }
                System.out.println();
                System.out.println("Would you like to derive another number? Y/N?");
                choice = sc.next();
            } catch (Exception err) {
                System.out.println(err);
                System.exit(0);
            }
        } while ("y".equalsIgnoreCase(choice));
	}
}
