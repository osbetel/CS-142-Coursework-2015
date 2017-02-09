package NguyenHW5;

import java.awt.*;
import java.util.*;
/**
 * @author ADKN
 * @version 08 Nov 2015, 6:02 PM
 */

/**
 * SensorSet class. This is essentially an object holding an array of Sensor objs and their respective data, in
 * addition to the date the data was recorded.
 *
 * EXTRA CREDIT:
 * Helper methods should be private because they often have no use when used individually. They're dependent on something
 * or return data that is used by another, public method. If you leave it public, a client programmer could access it and,
 * if they called it, it could potentially break their program since they'd have no idea what it was intended for.
 * As an example, I created a subclass to act as an object for returning two data values instead of 1 (under class IndexSequence)
 * and a method to get those data values. It was for the non descending sequence method. Any methods I use for testing
 * are usually made private too, when I am done coding.
 */
public class SensorSet {
    private String[] date;    //ONLY ARRAYS ALLOWED.
    private Sensor[] sensorArray;

    /**
     * Constructor for a SensorSet object. Requires a Scanner obj with a file object inside as input.
     * @param source A Scanner object with a File object inside. eg: SensorSet(new Scanner(new File("example.txt"))
     */
    public SensorSet(Scanner source) {  //take a txt file and produce a String[1] array for the date and a Sensor[20] array
        date = new String[1];           //Just the date value, as an ARRAY.
        date[0] = source.nextLine();    //Single date value from the first line of the txt file.
        sensorArray = new Sensor[20];   //Initializes the Array; note, future users may change the size of the array for more data.


        //For loop to read one line from the input file, and construct a Sensor object from it. One Sensor obj per line.
        for (int i = 0; i < 20; i++) {
            String name = source.next();
            int r = source.nextInt();
            int g = source.nextInt();
            int b = source.nextInt();
            int time = source.nextInt();
            Color c = new Color(r,g,b);
            Sensor s = new Sensor(name,c,time);
            sensorArray[i] = s;

        }
    }

    /**
     * Private method. Used during testing to manipulate arrays of Sensor objs from the SensorSet obj.
     * @return Returns only the sensor array from the SensorSet obj.
     */
    public Sensor[] getSensorArray() {return sensorArray;}

    /**
     * Retrieves the date from the SensorSet object, which is stored as a String[]. Converts to just a String, as per
     * program specification document (presumably from a customer).
     * @return Returns the date as a String.
     */
    public String getDate() {//Assignment stated that only arrays are allowed for SensorSet properties; convert to string.
        String dateStr = Arrays.toString(this.date);
        return dateStr;
    }

    /**
     * Method to find the Sensor with the smallest time value.
     * @return Returns the Sensor object with the smallest time value.
     */
    public Sensor shortest() {
        double smallestTime = this.sensorArray[0].getSeconds();
        int arrayIndex = 0;
        for (int i = 0; i < this.sensorArray.length; i++) {
            if (this.sensorArray[i].getSeconds() < smallestTime) {
                arrayIndex = i;
                smallestTime = this.sensorArray[i].getSeconds();
            }
        }
        return sensorArray[arrayIndex];
    }

    /**
     * Method to determine brightness of each Sensor obj, and put them all into an array.
     * @return Returns an array of all the brightness values.
     */
    public double[] brightness() {
        double[] brightArray = new double[this.sensorArray.length];

        for (int i = 0; i < this.sensorArray.length; i++) {
            brightArray[i] = this.sensorArray[i].getBrightness();
        }
        return brightArray;
    }

    /**
     * Method to determine the net number of increases and decreases in brightness. Magnitude of the
     * brightness is not considered, only the fact that it increased/decreased. One increase/decrease =
     * +1/-1 respectively, to a total tally.
     * @return Returns the final tally of net increases/decreases of the total sensor data.
     */
    public int brightChange() {
        int tally = 0;

        double brightVal = this.sensorArray[0].getBrightness();
        for (int i = 0; i < this.sensorArray.length; i++) {
            if (brightVal - this.sensorArray[i].getBrightness() > 0) {
                tally += 1;
                brightVal = this.sensorArray[i].getBrightness();
            } else if (brightVal - this.sensorArray[i].getBrightness() < 0) {
                tally -= 1;
                brightVal = this.sensorArray[i].getBrightness();
            }
        }
        return tally;
    }


    /**
     * Utilizes the IndexSequence class to get the starting index and sequence length of the longest non
     * decreasing sequence in the data set.
     * @return Returns an array of Sensor objects as long as the longest sequence of non decreasing values, according to time.
     */
    public Sensor[] getNonDecSeq() {
        IndexSequence k = lengthOfSequence();           //Both IndexSequence class and lengthOfSequence() are private!
        Sensor[] finalArray = new Sensor[k.length];

        if (k.length == 1 && k.index == 1){
            k.index = 0;
        }

        for (int i = 0; i < k.length; i++) {
            finalArray[i] = this.sensorArray[k.index+i];
        }
        return finalArray;
    }

    /**
     * A very short class to act as a data type for storing the array index and the the length of the longest sequence.
     * Written to make the getNonDecSeq() easier. This subclass works together with the lengthOfSequence() method.
     */
    private class IndexSequence {
        int index;
        int length;
        public IndexSequence(int i, int l) {
            index = i;
            length = l;
        }
    }

    /**
     * Determines the length of the longest sequence AND the array index that sequence starts at. I needed a method that
     * returned two values (array index, sequence length) for getNonDecSeq(), and the easiest way to do that was with
     * a small class to act as a data type holding these values.
     * @return An IndexSequence object, which holds both the index and sequence value.
     */
    private IndexSequence lengthOfSequence() {
        int startSeq = 1;
        int startIndex = 1;
        int longestSeq = 0;
        int longestIndex = 0;

        for (int i = startIndex; i < this.sensorArray.length; i++) {
            if (this.sensorArray[i].getSeconds() > this.sensorArray[i - 1].getSeconds()) {
                startSeq++;
            } else {
                startSeq = 1;
                startIndex = i;
            }
            if (startSeq > longestSeq) {
                longestSeq = startSeq;
                longestIndex = startIndex;
            }
        }
        return new IndexSequence(longestIndex,longestSeq);
    }


    /**
     * Method to erase the original data of the sensorSet object (the date and stored array), and
     * populates it with data from the new source.
     * @param source A Scanner object with a File object inside. eg:
     *               SensorSet(new Scanner(new File("example.txt")))
     */
    public void load(Scanner source) {
        SensorSet temp = new SensorSet(source);
        this.sensorArray = temp.sensorArray;
        this.date = temp.date;
    }

    /**
     * Method to find a sensor object from a sensorSet array with the matching sensor
     * name value.
     * @param name The name of the sensor object you want to retrieve.
     * @return The sensor object with a matching name. Null if no matching object found.
     */
    public Sensor findSensor(String name) {
        for (int i = 0; i < this.sensorArray.length; i++) {
            if (name.equalsIgnoreCase(this.sensorArray[i].getName())) {
                return this.sensorArray[i];
            }
        }
        return null;
    }
}
