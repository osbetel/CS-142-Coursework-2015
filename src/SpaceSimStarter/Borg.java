/**
 * @author ADKN
 * @version 30 Nov 2015
 */

package SpaceSimStarter;
import java.awt.*;

/**
 * Borg inherits from Alien. It functions similarly with a few key differences.
 * Borg moves differently, with different patterns, as well as having different colors.
 * Other than that, it does still infect others. It's random movement pattern gives it an advantage
 * over Aliens though.
 */
public class Borg extends Alien {

    /**
     * Constructor for a Borg object. Simply calls a constructor for an Alien, and then modifies initial color and
     * String values. Other things like methods are also inherited.
     */
    public Borg() {
        super();
        setColor(Color.ORANGE);
        setDisplay("B");
    }

    /**
     * Method to determine the next move for a Borg object.
     * @param info  information about this SpaceItem in the simulation
     * @return  Returns an Action from SpaceItem's inner enum.
     */
    @Override
    public Action getMove(SpaceItemInfo info) {

        //Changes color randomly every two turns
        if (colorTally == 2) {
            colorTally = 0;
            colorTracker();
        } else {
            colorTally += 1;
        }

        if (info.getFront() == Neighbor.WALL) { //If facing a wall, will execute a U-turn like a Cruiser object.
            nextMove = Action.UTURN;
            moveTally = random.nextInt(16);     //Skip to a random move in the action "lineup" so to speak.
            return nextMove;                    //Ensures that it doesn't get stuck on the outside edges of the simulation boundaries.
        } else {

            //Move 1, turn left, move 2, turn left, move 4, turn right, move 1, turn right, move 4, ad infinitum.
            switch (moveTally) {
                case 1:
                    nextMove = Action.LEFT;
                    moveTally += 1;
                    break;
                case 4:
                    nextMove = Action.LEFT;
                    moveTally += 1;
                    break;
                case 9:
                    nextMove = Action.RIGHT;
                    moveTally += 1;
                    break;
                case 11:
                    nextMove = Action.RIGHT;
                    moveTally += 1;
                    break;
                case 15:
                    nextMove = Action.MOVE;
                    moveTally = 0;      //Resets the moveTally and repeats the sequence.
                    break;
                default:
                    nextMove = Action.MOVE;
                    moveTally += 1;
                    break;
            }
        }
        checkInfect(info);  //Check if the next move should be an infection.
        return nextMove;
    }

    /**
     * Determines which color the Borg should switch to next. Entirely random; no guarantee it will always alternate colors.
     */
    @Override
    protected void colorTracker() {
        int randInt = random.nextInt(2);
        if (randInt == 0) {
            setColor(Color.ORANGE);
        } else if (randInt == 1) {
            setColor(Color.BLACK);
        }
    }
}
