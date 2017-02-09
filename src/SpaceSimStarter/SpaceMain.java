
/**
 * SpaceMain provides the main method for a simple simulation program.  Alter
 * the number of each entity added to the simulation if you want to experiment
 * with different scenarios.  You can also alter the width and height passed to
 * the SpaceFrame constructor.

 * by Stuart Reges and Marty Stepp
 */
package SpaceSimStarter;
public class SpaceMain {
    public static int COUNT = 30;
    public static void main(String[] args) {
        SpaceFrame frame = new SpaceFrame(60, 40);

        // uncomment each of these lines as you complete these classes
        frame.add(COUNT, Asteroid.class);
        frame.add(COUNT, Cruiser.class);
        frame.add(COUNT, Alien.class);
        frame.add(COUNT, Borg.class);   //"You will be assimilated!"

        frame.start();
    }
}
