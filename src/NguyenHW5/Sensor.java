package NguyenHW5;
import java.awt.*;

/**
 * @author ADKN
 * @version 08 Nov 2015, 6:02 PM
 */

/**
 * Sensor class contains appropriate data types and a constructor for a Sensor object.
 */
public class Sensor {
    private Color color = new Color(0, 0, 0); //The RGB values. Imagine a slider interface. Range of 0-255 on each param
    private int redVal;             //R
    private int grnVal;             //G
    private int bluVal;             //B
    private int seconds;            //Time value of sensor
    private String name;            //Sensor name
    private double brightness;      //Calculated from RGB


    /**
     * Constructor for a Sensor object.
     * @param n Name of the sensor
     * @param c Color of the sensor, STRICTLY as a Color object, not RGB values; can be set later with RGB values.
     * @param time Time of the sensor in seconds.
     * @throws IllegalArgumentException Time cannot be negative nor equal to 0.
     */
    public Sensor(String n, Color c, int time) throws IllegalArgumentException {
        if (time <= 0) {//Times cannot be 0 or less.
            throw new IllegalArgumentException("Time must be a value greater than 0");
        }
        name = n;
        color = c;
        redVal = color.getRed();        //Dividing Color obj into RGB values for brightness calculation.
        grnVal = color.getGreen();
        bluVal = color.getBlue();
        seconds = time;
        brightness = Math.sqrt(.241*Math.pow(redVal,2) + .691*Math.pow(grnVal,2) + .068*Math.pow(bluVal,2));
    }

    /**
     * Retrieves Color value of the sensor.
     * @return Returns Color as an object.
     */
    public Color getColor() {return color;}

    /**
     * Retrieves the time value of the sensor.
     * @return Returns time value as an integer.
     */
    public int getSeconds() {return seconds;}

    /**
     * Retrieves the name value of the sensor.
     * @return Returns name value as an String.
     */
    public String getName() {return name;}

    /**
     * Retrieves the brightness value of the sensor.
     * @return Returns brightness value as a double.
     */
    public double getBrightness() {return brightness;}

    /**
     * Converts the Sensor object into a string.
     * @return Returns the Sensor object as a string in the format: Name (r, g, b):s
     */
    @Override
    public String toString() {return (name + " (" + redVal + "," + grnVal + "," + bluVal + "):" + seconds);}

    /**
     * Method to set the color value. Takes a Color object.
     * @param c Must be a Color object.
     */
    public void setColor(Color c) {this.color = c;}     //If color object is directly supplied

    /**
     * Method to set the color value. Takes integer RBG values.
     * @param r Red as an integer.
     * @param g Green as an integer.
     * @param b Blue as an integer.
     */
    public void setColor(int r, int g, int b) {this.color = new Color(r,g,b);}      //If user wants to manually select rgb values.

    /**
     * Sets the time value of a Sensor object.
     * @param time Time value in seconds as an integer.
     */
    public void setSeconds(int time) {this.seconds = time;}
}
