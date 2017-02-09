/**
 * @author ADKN
 * @version 30 Nov 2015
 */

package SpaceSimStarter;
import java.awt.*;
import java.util.Random;

/**
 * Alien class inherits from SpaceItem. Aliens move in a 5x5 square pattern and "infect" any other objects
 * they come in contact with.
 */
public class Alien extends SpaceItem {
    protected int colorTally;   //To keep track of turns between color changes.
    protected Random random;

    /**
     * Constructor for an Alien object.
     */
    public Alien() {
        super("^∞^", Color.YELLOW); //Yellow used as default, changed randomly below.
        moveTally = 0;
        colorTally = 0;
        random = new Random();  //Use the .nextInt(bounds) to get a random integer.
        colorTracker();         //Sets the initial color to something random.
    }

    /**
     * Method to get the next move of the Alien object.
     * @param info information about this SpaceItem in the simulation
     * @return Returns an Action from SpaceItem's inner enum.
     */
    @Override
    public Action getMove(SpaceItemInfo info) {

        //Keeps track of color changes every 3 cycles
        if (colorTally == 3) {
            colorTally = 0;
            colorTracker();
        } else {
            colorTally += 1;
        }

        //Turn when facing a wall
        if (info.getFront() == Neighbor.WALL) {
            return nextMove = Action.RIGHT;
        }

        //Pattern to move 5 times, then turn right
        if (moveTally == 5) {
            moveTally = 0;
            return nextMove = Action.RIGHT;
        } else {
            nextMove = Action.MOVE;
            moveTally += 1;
            checkInfect(info);      //Check if nextMove should be an infection.
            return nextMove;
        }
    }

    /**
     * Method to determine if the nextMove should be an infection.
     * Turned into a short method for inheritance into the subclass Borg.
     * @param info A SpaceItemObject. Meant to be used inside of a SpaceItem subclass's getMove()
     */
    protected void checkInfect(SpaceItemInfo info) {
        if (info.getFront() == Neighbor.OTHER) {
            nextMove = Action.INFECT;
        }
    }

    /**
     * Determines a random color to change to.
     */
    protected void colorTracker() {
        int randInt = random.nextInt(3);
        if (randInt == 0) {
            setColor(Color.YELLOW);
        } else if (randInt == 1) {
            setColor(Color.GREEN);
        } else if (randInt == 2) {
            setColor(Color.RED);
        }
    }
}
