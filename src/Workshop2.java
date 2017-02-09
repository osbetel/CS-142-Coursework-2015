










public class Workshop2 {
    public static void main(String[] args) {

        class Complex {
            private double r;
            private double i;
            String number;

            public Complex(double real, double imaginary) {
                r = real;
                i = imaginary;
            }

            public Complex() {
                r = 0;
                i = 0;
            }

            public void setReal(double real) {
                r = real;
            }

            public void setImaginary(double imaginary) {
                i = imaginary;
            }

            public double getReal() {
                return r;
            }

            public double getImaginary() {
                return i;
            }

            public String toString() {
                String fullString;
                if (i > 0) {
                    fullString = r + "+" + i + "i";
                } else if (i < 0) {
                    fullString = Double.toString(r) + Double.toString(i) + "i";
                } else {    //imaginary component = 0
                    fullString = Double.toString(r);
                }
                return fullString;
            }
        }
        Complex obj = new Complex();
        obj.setReal(666.86);
        obj.setImaginary(-21.86);
        System.out.println(obj.getReal());
        System.out.println(obj.getImaginary());
        System.out.println(obj.toString());
    }
}