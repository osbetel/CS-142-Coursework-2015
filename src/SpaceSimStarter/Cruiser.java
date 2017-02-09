/**
 * @author ADKN
 * @version 30 Nov 2015
 */

package SpaceSimStarter;
import java.awt.*;

/**
 * Cruiser inherits from SpaceItem, it has a zig zag movement pattern, and the ability to execute a U-turn when
 * Coming into contact with a wall.
 */
public class Cruiser extends SpaceItem {

    /**
     * Constructor for the Cruiser object.
     */
    public Cruiser() {
        super("C", Color.DARK_GRAY);
        moveTally = 0;  //To keep track of moves in the zig zag sequence.
    }

    /**
     * Method to get the next move of the Cruiser object.
     * @param info information about this SpaceItem in the simulation
     * @return  Returns an Action from SpaceItem's inner enum.
     */
    @Override
    public Action getMove(SpaceItemInfo info) {

        //Created new enum value in SpaceItem "UTURN" and added one line of code in SpaceModel accordingly.
        //Utilizes the rotate() method in SpaceModel to turn 180 degrees
        if (info.getFront() == Neighbor.WALL) {
            nextMove = Action.UTURN;
            moveTally = 0;  //Restarts sequence upon hitting a wall.
            return nextMove;
        }


        //moveTally starts at 0.
        //Moves in a zig zag pattern.
        if (moveTally == 0) {
            nextMove = Action.MOVE;
            moveTally += 1;
        } else if (moveTally == 1) {
            nextMove = Action.RIGHT;
            moveTally += 1;
        } else if (moveTally == 2) {
            nextMove = Action.MOVE;
            moveTally += 1;
        } else if (moveTally == 3) {
            nextMove = Action.LEFT;
            moveTally = 0;      //Resets the sequence back to the beginning.
        }
        checkCloak(info);   //This will only happen if the cruiser can't move anyway, so movement is irrelevant.
        return nextMove;
    }

    /**
     * Method to check whether or not a Cruiser should activate it's "cloaking" device.
     * @param info A SpaceItemObject. Meant to be used inside of a SpaceItem subclass's getMove()
     */
    private void checkCloak(SpaceItemInfo info) {
        //If the Cruiser is surrounded by other objects (not walls)
        if (info.getFront() == Neighbor.OTHER && info.getBack() == Neighbor.OTHER &&
                info.getRight() == Neighbor.OTHER && info.getLeft() == Neighbor.OTHER) {
            setDisplay("_");
        } else {
            setDisplay("C");
        }
    }

}
