package NguyenHW5;
import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * @author ADKN
 * @version 8 Nov 2015, 3:47 PM
 */

/**
 * This is the user interface of the SensorApp, tying together Sensor and SensorSet classes, and
 * deriving methods and data from them. This data is gotten using an inputted text file of prior
 * gathered data. Must be formatted with first line as the date, and all subsequent lines as:
 * sensorName redValue blueValue greenValue time
 * Must have 20 Sensors per text file.
 */
public class SensorApp {
    public static final int X_POSITION = 75;    //Constants for drawing panel.
    public static final int Y_POSITION = 40;
    public static final int SIDE_LENGTH = 20;


    /**
     * The user interface of the SensorApp. Contains several do...while loops asking to try again after an exception,
     * or to process another file at the end. Also contains a try-catch block in case the file specified in the user input
     * portion is not found. Admittedly, I could've done more procedural decomp, but the code here is mostly System.out's.
     * @throws FileNotFoundException If the file or file path specified by the user cannot be found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        //Initialize variables
        Scanner sc = new Scanner(System.in);        //to capture input
        String inputFile = "", outputFile = "";      //must equal something or I get a "may not yet be initialized" error.
        SensorSet dataSet = null;
        String tryAgain;                            //Control var for the do...while loops.


        //Intro message
        System.out.println("Hello valued customer of BrightLights Inc!");
        System.out.println("==========================================");


        //DETERMINE FILE IN/OUT SECTION
        do {//This outer loop goes over all the below blocks of code. Asks to process another file at the end.
            do {//This loop is only for the upper portion that determines input and output file, as well as constructing the SensorSet obj.
                try {//To catch the FileNotFoundException if an input file doesn't exist.
                    tryAgain = "n";
                    System.out.println("Please enter the name of your input file.");
                    inputFile = sc.next();  //I had to concatenate "./src/NguyenHW5/" to the front in testing. Likely an issue with my IDE's default directory.

                    System.out.println("Please enter the name of your output file.");
                    outputFile = sc.next(); //"./src/NguyenHW5/"

                    dataSet = new SensorSet(new Scanner(new File(inputFile)));
                } catch (FileNotFoundException err) {
                    System.out.println("******************************************");
                    System.out.println(err);
                    System.out.println("Your input file was not found. Did you remember the extension and proper file path?");
                    System.out.println("******************************************");
                    System.out.println();
                    do {//Asks to try again after a FileNotFoundException.
                        System.out.println("Would you like to try again? Enter Y/N for yes or no.");
                        System.out.println("==========================================");
                        tryAgain = sc.next();
                        if (tryAgain.equalsIgnoreCase("n")) {
                            System.exit(0);
                        }
                    } while (!tryAgain.equalsIgnoreCase("y") && !tryAgain.equalsIgnoreCase("n"));
                }
            } while (tryAgain.equalsIgnoreCase("y"));


            //DISPLAY TO CONSOLE SECTION
            System.out.println("==========================================");
            System.out.println("The total brightness change from that set of sensors is: " + dataSet.brightChange());
            System.out.println("==========================================");
            System.out.println("The brightness values of each sensor from the set are as follows: ");

            //Prints brightness values to the console; one per line.
            for (int i = 0; i < dataSet.getSensorArray().length; i++) {
                System.out.println("Sensor " + (i + 1) + ": " + dataSet.getSensorArray()[i].getBrightness());
            }


            //WRITE TO FILE SECTION
            //Initializing to write to the output file.
            PrintStream output = new PrintStream(new File(outputFile));

            //Writes the input file where data was taken from, date, shortest timed sensor, and longest non decreasing sequence.
            output.println(inputFile);
            output.println(dataSet.getDate());
            output.println(dataSet.shortest());
            output.println(Arrays.toString(dataSet.getNonDecSeq()));


            //DRAWING PANEL SECTION FOR EC
            DrawingPanel panel = new DrawingPanel(300,600);
            Graphics g = panel.getGraphics();
            Sensor[] toDraw = dataSet.getNonDecSeq();   //Extract the NonDecSeq from the dataSet.

            for (int i = 0; i < toDraw.length; i++) {
                g.setColor(toDraw[i].getColor());
                g.fillRect(X_POSITION,Y_POSITION+i*60,SIDE_LENGTH,SIDE_LENGTH);     //Side lengths and distance between boxes are constant
                g.drawString(toDraw[i].getName(),X_POSITION+22,Y_POSITION+i*60+7);
                g.drawString("Time: " + toDraw[i].getSeconds(),X_POSITION+22,Y_POSITION+i*60+21);
            }

            do {//Asks to process another file
                System.out.println("==========================================");
                System.out.println("Would you like to process another file? Enter Y/N for yes or no.");
                System.out.println("==========================================");
                tryAgain = sc.next();

            } while (!tryAgain.equalsIgnoreCase("y") && !tryAgain.equalsIgnoreCase("n"));
        } while (tryAgain.equalsIgnoreCase("y"));
        if (tryAgain.equalsIgnoreCase("n")) {//This is probably redundant.
            System.exit(0);
        }
    }
}
