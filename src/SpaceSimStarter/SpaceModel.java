
/**
 *Class SpaceModel keeps track of the state of the SpaceItem simulation.
 *
 *Stuart Reges and Marty Stepp
 * Modified by Andrew Nguyen (line 198)
 */

package SpaceSimStarter;
import java.util.*;
import java.awt.Point;
import java.awt.Color;
import java.lang.reflect.*;

public class SpaceModel {
    private int height;
    private int width;
    private SpaceItem[][] grid;
    private Map<SpaceItem, PrivateData> info;
    private SortedMap<String, Integer>thingCount;
    private boolean debugView;
    private int simulationCount;
    private static boolean created;
    
    public SpaceModel(int width, int height) {
        // this prevents someone from trying to create their own copy of
        // the GUI components
        if (created)
            throw new RuntimeException("Only one world allowed");
        created = true;

        this.width = width;
        this.height = height;
        grid = new SpaceItem[width][height];
        info = new HashMap<SpaceItem, PrivateData>();
        thingCount = new TreeMap<String, Integer>();
        this.debugView = false;
    }

    public Iterator<SpaceItem> iterator() {
        return info.keySet().iterator();
    }

    public Point getPoint(SpaceItem c) {
        return info.get(c).p;
    }

    public Color getColor(SpaceItem c) {
        return info.get(c).color;
    }

    public String getString(SpaceItem c) {
        return info.get(c).string;
    }

    public void add(int number, Class<? extends SpaceItem> thing) {
        Random r = new Random();
        SpaceItem.Direction[] directions = SpaceItem.Direction.values();
        if (info.size() + number > width * height)
            throw new RuntimeException("adding too many critters");
        for (int i = 0; i < number; i++) {
            SpaceItem next;
            try {
                next = makeSpaceItem(thing);
            } catch (Exception e) {
                System.out.println("ERROR: " + thing + " does not have" +
                                   " the appropriate constructor.");
                System.exit(1);
                return;
            }
            int x, y;
            do {
                x = r.nextInt(width);
                y = r.nextInt(height);
            } while (grid[x][y] != null);
            grid[x][y] = next;
            
            SpaceItem.Direction d = directions[r.nextInt(directions.length)];
            info.put(next, new PrivateData(new Point(x, y), d, 0,
                                           next.getColor(), next.toString()));
        }
        String name = thing.getName();
        if (!thingCount.containsKey(name))
            thingCount.put(name, number);
        else
            thingCount.put(name, thingCount.get(name) + number);
    }

    @SuppressWarnings("unchecked")
    private SpaceItem makeSpaceItem(Class thing) throws Exception {
        Constructor c = thing.getConstructors()[0];
        return (SpaceItem) c.newInstance(); 
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getAppearance(SpaceItem c) {
        // Override specified toString if debug flag is true
        if (!debugView) 
            return info.get(c).string;
        else {
            PrivateData data = info.get(c);
            if (data.direction == SpaceItem.Direction.NORTH) return "^";
            else if (data.direction == SpaceItem.Direction.SOUTH) return "v";
            else if (data.direction == SpaceItem.Direction.EAST) return ">";
            else return "<";
        }
    }
    
    public void toggleDebug() {
        this.debugView = !this.debugView;
    }

    private boolean inBounds(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    private boolean inBounds(Point p) {
        return inBounds(p.x, p.y);
    }

    // returns the result of rotating the given direction clockwise
    private SpaceItem.Direction rotate(SpaceItem.Direction d) {
        if (d == SpaceItem.Direction.NORTH) return SpaceItem.Direction.EAST;
        else if (d == SpaceItem.Direction.SOUTH) return SpaceItem.Direction.WEST;
        else if (d == SpaceItem.Direction.EAST) return SpaceItem.Direction.SOUTH;
        else return SpaceItem.Direction.NORTH;
    }

    private Point pointAt(Point p, SpaceItem.Direction d) {
        if (d == SpaceItem.Direction.NORTH) return new Point(p.x, p.y - 1);
        else if (d == SpaceItem.Direction.SOUTH) return new Point(p.x, p.y + 1);
        else if (d == SpaceItem.Direction.EAST) return new Point(p.x + 1, p.y);
        else return new Point(p.x - 1, p.y);
    }

    private Info getInfo(PrivateData data, Class original) {
        SpaceItem.Neighbor[] neighbors = new SpaceItem.Neighbor[4];
        SpaceItem.Direction d = data.direction;
        for (int i = 0; i < 4; i++) {
            neighbors[i] = getStatus(pointAt(data.p, d), original);
            d = rotate(d);
        }
        return new Info(neighbors, data.direction, data.infectCount);
    }

    private SpaceItem.Neighbor getStatus(Point p, Class original) {
        if (!inBounds(p))
            return SpaceItem.Neighbor.WALL;
        else if (grid[p.x][p.y] == null)
            return SpaceItem.Neighbor.EMPTY;
        else if (grid[p.x][p.y].getClass() == original)
            return SpaceItem.Neighbor.SAME;
        else
            return SpaceItem.Neighbor.OTHER;
    }

    @SuppressWarnings("unchecked")
    public void update() {
        simulationCount++;
        Object[] list = info.keySet().toArray();
        Collections.shuffle(Arrays.asList(list));
        Arrays.sort(list, new Comparator() {
                public int compare(Object x, Object y) {
                    return Math.min(10, info.get(x).infectCount) -
                        Math.min(10, info.get(y).infectCount);
                }
            });
        for (int i = 0; i < list.length; i++) {
            SpaceItem next = (SpaceItem)list[i];
            PrivateData data = info.get(next);
            if (data == null) {
                // happens when creature was infected earlier in this round
                continue;
            }
            Point p = data.p;
            Point p2 = pointAt(p, data.direction);
            SpaceItem.Action move = next.getMove(getInfo(data, next.getClass()));
            data.color = next.getColor();
            data.string = next.toString();
            if (move == SpaceItem.Action.LEFT)
                data.direction = rotate(rotate(rotate(data.direction)));
            else if (move == SpaceItem.Action.RIGHT)
                data.direction = rotate(data.direction);
            else if (move == SpaceItem.Action.MOVE) {
                if (inBounds(p2) && grid[p2.x][p2.y] == null) {
                    grid[p2.x][p2.y] = grid[p.x][p.y];
                    grid[p.x][p.y] = null;
                    data.p = p2;
                }
            }
            // ============= BEGIN ADDED CODE =============
            else if (move == SpaceItem.Action.UTURN) {
                data.direction = rotate(rotate(data.direction));    //rotates twice, turning 180 degrees. Cruiser only.
            }
            // ============= END ADDED CODE =============
            else if (move == SpaceItem.Action.INFECT) {
                if (inBounds(p2) && grid[p2.x][p2.y] != null && grid[p2.x][p2.y].getClass() != next.getClass()) {
                    SpaceItem other = grid[p2.x][p2.y];
                    // remember the old SpaceItem's private data
                    PrivateData oldData = info.get(other);
                    // then remove that old SpaceItem
                    String c1 = other.getClass().getName();
                    thingCount.put(c1, thingCount.get(c1) - 1);
                    String c2 = next.getClass().getName();
                    thingCount.put(c2, thingCount.get(c2) + 1);
                    info.remove(other);
                    // and add a new one to the grid
                    try {
                        grid[p2.x][p2.y] = makeSpaceItem(next.getClass());
                    } catch (Exception e) {
                        throw new RuntimeException("" + e);
                    }
                    // and add to the map
                    info.put(grid[p2.x][p2.y], oldData);
                    // and update oldData for new critter's color/string
                    oldData.color = grid[p2.x][p2.y].getColor();
                    oldData.string = grid[p2.x][p2.y].toString();
                    // and remember that we infected a critter
                    data.infectCount++;
                }
            }
        }
    }

    public Set<Map.Entry<String, Integer>> getCounts() {
        return Collections.unmodifiableSet(thingCount.entrySet());
    }

    public int getSimulationCount() {
        return simulationCount;
    }

    private class PrivateData {
        public Point p;
        public SpaceItem.Direction direction;
        public int infectCount;
        public Color color;
        public String string;

        public PrivateData(Point p, SpaceItem.Direction d, int infectCount,
                           Color color, String string) {
            this.p = p;
            this.direction = d;
            this.infectCount = infectCount;
            this.color = color;
            this.string = string;
        }

        public String toString() {
            return p + " " + direction + " " + infectCount;
        }
    }

    // an object used to query a critter's state (neighbors, direction)
    private static class Info implements SpaceItemInfo {
        private SpaceItem.Neighbor[] neighbors;
        private SpaceItem.Direction direction;
        private int infectCount;

        public Info(SpaceItem.Neighbor[] neighbors, SpaceItem.Direction d,
                    int infectCount) {
            this.neighbors = neighbors;
            this.direction = d;
            this.infectCount = infectCount;
        }

        public SpaceItem.Neighbor getFront() {
            return neighbors[0];
        }

        public SpaceItem.Neighbor getBack() {
            return neighbors[2];
        }

        public SpaceItem.Neighbor getLeft() {
            return neighbors[3];
        }

        public SpaceItem.Neighbor getRight() {
            return neighbors[1];
        }

        public SpaceItem.Direction getDirection() {
            return direction;
        }

        public int getInfectCount() {
            return infectCount;
        }
    }
}
