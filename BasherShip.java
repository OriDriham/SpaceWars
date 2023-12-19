/**
 * This ship attempts to collide with other ships. It will always accelerate, and will
 * constantly turn towards the closest ship.
 */
public class BasherShip extends SpaceShip {
    /*
    The maximal distance this ship will keep from other ships, before attempting to turn on its shields.
    */
    private static final double NEAREST_SHIP_MAX_DISTANCE = 0.19;

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
        if(shipPhysicsObject.distanceFrom(closestShip.shipPhysicsObject) <= NEAREST_SHIP_MAX_DISTANCE) {
            shieldOn();
        }
        chargeEnergyPerRound();
    }
}
