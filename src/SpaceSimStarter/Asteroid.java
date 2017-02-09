/**
 * @author ADKN
 * @version 30 Nov 2015
 */

package SpaceSimStarter;
import java.awt.*;
import java.util.Random;

/**
 * Asteroid inherits from SpaceItem; it moves in a completely random pattern. Sometimes simply rotating in place.
 */
public class Asteroid extends SpaceItem {
    private Random random;

    /**
     * Constructor for an Asteroid object.
     */
    public Asteroid() {
        super("A", Color.BLUE);
        random = new Random();
    }

    /**
     * Method to get the next move of an Asteroid object.
     * @param info information about this SpaceItem in the simulation
     * @return Returns an Action from SpaceItem's inner enum.
     */
    @Override
    public Action getMove(SpaceItemInfo info) {
        //Prevents moving forward if the asteroid is facing a wall
        if(info.getFront() == Neighbor.WALL) {
            int randInt = random.nextInt(2);    //Random between two choices
            if (randInt == 0) {
                nextMove = Action.LEFT;
            } else if (randInt == 1) {
                nextMove = Action.RIGHT;
            }
        }

        int randInt = random.nextInt(3);    //random between 3 choices
        if (randInt == 0) {
            nextMove = Action.MOVE;
        } else if (randInt == 1) {
            nextMove = Action.LEFT;
        } else if (randInt == 2) {
            nextMove = Action.RIGHT;
        }
        return nextMove;
    }
}
