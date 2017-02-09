
/** 
 * This is the superclass of all of the SpaceItem classes.  It keeps track of color,
 * String representation. Some methods must be overriden.
 * 
 * The class provides several kinds of constants:<p>
 * type Neighbor  : WALL, EMPTY, SAME, OTHER<br>
 * type Action    : HOP, LEFT, RIGHT, INFECT<br>
 * type Direction : NORTH, SOUTH, EAST, WEST<br><br> </p>
 * 
 * Based on work by Stuart Reges and Marty Stepp
 * Modified by Andrew Nguyen
 */

package SpaceSimStarter;
import java.awt.*;

public abstract class SpaceItem {
    protected int moveTally;
    protected Action nextMove;

    private String myDisplay;
    private Color myColor;
    
    /**
     * Initializes this SpaceItem's state
     * @param s this SpaceItem's initial String representation
     * @param c this SpaceItem's starting color
     */
    public SpaceItem(String s, Color c){
        myDisplay = s;
        myColor = c;

        //Potentially set the values for all 4 neighbors and direction?
        //Must account for already existing neighbors.
    }
    
    /**
     * This method answers this SpaceItem's color
     * @return the current color
     */
    public Color getColor(){
        return myColor;
    }

    /**
     * This method answers this SpaceItem's String representation
     * @return the current string
     */
    public String toString(){
        return myDisplay;
    }
    
    /**
     * This method allows subclasses only to change this SpaceItem's color
     * @param c the new color
     */
    protected void setColor(Color c){
        myColor = c;
    }
    
    /**
     * This method allows subclasses only to change this SpaceItem's String display
     * @param s the new display
     */
    protected void setDisplay(String s){
        myDisplay = s;
    }

    /**
     * This method answers this SpaceItem's Action
     * MUST BE OVERRIDDEN IN SUBCLASSES
     *  
     * @param info information about this SpaceItem in the simulation
     * @return the current Action
     */
    public abstract Action getMove(SpaceItemInfo info);

    /**
     * <div>
     * WALL: against the wall of the simulation world<br>
     * EMPTY: the neighboring spot is empty<br>
     * SAME: an SpaceItem of the same species<br>
     * OTHER: an SpaceItem of another species<br></div>
     */
    public static enum Neighbor {
        WALL, EMPTY, SAME, OTHER
    }

    /**
     * <div>
     * MOVE: move one space in the current direction<br>
     * LEFT: turn left by rotating 90 degrees counter-clockwise<br>
     * RIGHT: turn right by rotating 90 degrees clockwise<br>
     * INFECT: infect the SpaceItem in front of you</div>
     */
    public static enum Action {
        MOVE, LEFT, RIGHT, INFECT, UTURN    //ADDED UTURN FOR CRUISER'S 180 DEGREE TURN FUNCTION, MODIFIED SpaceModel AS WELL.
    }

    /**
     * <div>
     * NORTH<br>
     * SOUTH<br>
     * EAST<br>
     * WEST</div>
     */
    public static enum Direction {
        NORTH, SOUTH, EAST, WEST
    }
    
    // This prevents any SpaceItem from trying to redefine the definition of
    // object equality, which is important for the simulator to work properly.
    public final boolean equals(Object other) {
        return this == other;
    }

}