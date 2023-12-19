import oop.ex2.*;

/**
 * This class has a single static method. It is used by the supplied driver to create all the spaceship
 * objects according to the command line arguments.
 */
public class SpaceShipFactory {
    /**
     * The function create spaceship objects according to the command line arguments.
     *
     * @param args Program arguments.
     * @return Array filled with spaceships.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip [] ships = new SpaceShip[args.length];
        int i = 0;  // index in the array
        for(String arg : args) {
            switch (arg) {
                case "h":   // Human
                    ships[i] = new HumanControlledShip();
                    break;
                case "d":   // Drunkard
                    ships[i] = new DrunkardShip();
                    break;
                case "r":   // Runner
                    ships[i] = new RunnerShip();
                    break;
                case "a":   // Aggressive
                    ships[i] = new AggressiveShip();
                    break;
                case "b":   // Basher
                    ships[i] = new BasherShip();
                    break;
                case "s":   // Special
                    ships[i] = new SpecialShip();
                    break;
                default:   // not one of the options above
                    return null;
            }
            i++;
        }
        return ships;
    }
}
