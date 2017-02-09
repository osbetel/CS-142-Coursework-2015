
/**
 * The SpaceItemInfo interface defines a set of methods for querying the
 * state of a SpaceItem object in the simulation. 
 * 
 * Based on work by Stuart Reges and Marty Stepp
 */

package SpaceSimStarter;
public interface SpaceItemInfo {
    /**
     * returns the neighbor in front of a specific SpaceItem
     */
    public SpaceItem.Neighbor getFront();
    
    /**
     * returns the neighbor behind of a specific SpaceItem
     */
    public SpaceItem.Neighbor getBack();
    
    /**
     * returns the neighbor to the left of a specific SpaceItem
     */
    public SpaceItem.Neighbor getLeft();
    
    
    /**
     * returns the neighbor to the right of a specific SpaceItem
     */
    public SpaceItem.Neighbor getRight();
    
    
    /**
     * returns the direction a specific SpaceItem is facing in the simulation
     */
    public SpaceItem.Direction getDirection();
    
    /**
     * returns the number of entities a specific SpaceItem has infected
     */
    public int getInfectCount();
}
