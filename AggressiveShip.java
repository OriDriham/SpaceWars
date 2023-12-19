/**
 * This ship pursues other ships and tries to fire at them. It will always accelerate,
 * and turn towards the nearest ship.
 */
public class AggressiveShip extends SpaceShip {
    /*
    The maximal angle of this ship to the nearest ship, before it tries to fire.
    */
    private static final double MAX_ANGLE_NEAR_SHIP = 0.21;

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game The game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        deactivateShields();
        SpaceShip closestShip = goTowardNearestShip(game);
        if(shipPhysicsObject.angleTo(closestShip.shipPhysicsObject) < MAX_ANGLE_NEAR_SHIP) {
            fire(game);
        }
        chargeEnergyPerRound();
    }
}
