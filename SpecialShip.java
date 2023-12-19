/**
 * This ship is special. It pursues other ships and tries to fire at them. It also trys to teleport
 * if it feels threatened, or activates it's shield if other ship is getting too close.
 * It will always accelerate, and turn towards the nearest ship.
 */
public class SpecialShip extends SpaceShip {
    /*
    The maximal distance this ship will keep from other ships, before attempting to turn on its shields
    or teleport.
    */
    private static final double NEAREST_SHIP_MAX_DISTANCE = 0.19;

    /*
    The maximal angle of this ship to the nearest ship, before it tries to fire or teleport.
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
        SpaceShip closestShip = game.getClosestShipTo(this);
        if(shipPhysicsObject.distanceFrom(closestShip.shipPhysicsObject) < NEAREST_SHIP_MAX_DISTANCE) {
            if(closestShip.shipPhysicsObject.angleTo(shipPhysicsObject) < MAX_ANGLE_NEAR_SHIP) {
                teleport();  // feels threatened
            }
        }
        goTowardNearestShip(game);
        if(shipPhysicsObject.distanceFrom(closestShip.shipPhysicsObject) <= NEAREST_SHIP_MAX_DISTANCE) {
            shieldOn();
        }
        if(shipPhysicsObject.angleTo(closestShip.shipPhysicsObject) < MAX_ANGLE_NEAR_SHIP) {
            fire(game);
        }
        chargeEnergyPerRound();
    }
}
