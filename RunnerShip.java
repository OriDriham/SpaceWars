/**
 * This spaceship attempts to run away from the fight. It will always accelerate, and
 * will constantly turn away from the closest ship.
 */
public class RunnerShip extends SpaceShip {
    /*
    The maximal distance this ship will keep from other ships, before it might 'feels' threatened.
     */
    private static final double NEAREST_SHIP_MAX_DISTANCE = 0.25;

    /*
    The maximal nearest ship's angle to the runner, before it might 'feels' threatened.
    */
    private static final double MAX_ANGLE_NEAR_SHIP = 0.23;

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game The game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        deactivateShields();
        SpaceShip closestShip = game.getClosestShipTo(this);
        if(shipPhysicsObject.distanceFrom(closestShip.shipPhysicsObject) < NEAREST_SHIP_MAX_DISTANCE) {
            if(closestShip.shipPhysicsObject.angleTo(shipPhysicsObject) < MAX_ANGLE_NEAR_SHIP) {
                teleport();  // feels threatened
            }
        }
        goAwayFromNearestShip(closestShip);
        chargeEnergyPerRound();
    }
}
