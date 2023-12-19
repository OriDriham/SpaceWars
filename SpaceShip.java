import java.awt.Image;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 */
public abstract class SpaceShip {

    /**
     * The SpaceShipPhysics object of the spaceship, that represents the position, direction and velocity
     * of the ship.
     */
    protected SpaceShipPhysics shipPhysicsObject = new SpaceShipPhysics();

    /**
     * The shield Status of the ship. true if the shields are on, false otherwise. Default is false.
     */
    protected boolean shieldStatus = false;

    /**
     * Represents a "no turn" command for the ship.
     */
    protected static final int NO_TURN = 0;

    /**
     Represents a right turn command for the ship.
     */
    protected static final int RIGHT_TURN = -1;

    /**
     Represents a left turn command for the ship.
     */
    protected static final int LEFT_TURN = 1;

    /*
    The maximal energy level default value.
     */
    private static final int MAXIMAL_ENERGY_DEFAULT_VALUE = 210;

    /*
    The current maximal energy level of the ship.
     */
    private int maximalEnergyLevel = MAXIMAL_ENERGY_DEFAULT_VALUE;

    /*
    The  current energy level default value.
     */
    private static final int CURRENT_ENERGY_DEFAULT_VALUE = 190;

    /*
    The current energy level of the ship.
     */
    private int currentEnergyLevel = CURRENT_ENERGY_DEFAULT_VALUE;

    /*
    The health level default value.
     */
    private static final int HEALTH_DEFAULT_VALUE = 22;

    /*
    The health level of the ship.
     */
    private int healthLevel = HEALTH_DEFAULT_VALUE;

    /*
    The amount of energy consumed per round the shield is used.
     */
    private static final int SHIELD_ENERGY_CONSUME = 3;

    /*
    The amount of energy required to perform a teleport.
     */
    private static final int TELEPORT_ENERGY_CONSUME = 140;

    /*
    The amount of energy required to fire a shot.
     */
    private static final int SHOT_ENERGY_CONSUME = 19;

    /*
    The amount of energy that goes up every round, up to the ship's current maximal energy level.
    */
    private static final int ENERGY_CHARGING_PER_ROUND = 1;

    /*
    The amount of energy units the maximal and current energy levels will go up by, when "bashing"
    with another ship.
     */
    private static final int BASHING_WITH_SHIELD_BONUS = 18;

    /*
    The amount of energy units that will be reduced from the ship's maximal energy, when getting hit.
     */
    private static final int GETTING_HIT_PENALTY = 10;

    /*
    The health level will be reduced by this amount after a hit.
     */
    private static final int HEALTH_REDUCE = 1;

    /*
    A non negative value to verify the energy level of the ships stays non-negative.
     */
    private static final int NON_NEGATIVE_ENERGY_LEVEL = 0;

    /*
    The health level that indicates the ship is dead.
     */
    private static final int DEATH_INDICATOR = 0;

    /*
    The counter for the cooldown period a ship need to wait before it can fire again.
     */
    private int SHOTS_COOLDOWN_PERIOD_COUNTER = 0;

    /*
    The maximal value "SHOTS_COOLDOWN_PERIOD_COUNTER" allowed to reach.
     */
    private static final int SHOTS_MAX_COOLDOWN_COUNT = 7;

    /*
    The minimal value of "SHOTS_COOLDOWN_PERIOD_COUNTER".
     */
    private static final int SHOTS_MIN_COOLDOWN_COUNT = 0;

    /*
    A representation of a negative direction.
    */
    private static final int NEGATIVE_DIRECTION = -1;

    /*
    A representation of a non negative number.
    */
    private static final int NON_NEGATIVE = 0;

    /**
     * Turn off the shields, if they are on.
     */
    protected void deactivateShields() {
        if(shieldStatus) {
            shieldStatus = false;  // cancel the shield activation from the previous round, if it existed
        }
    }

    /**
     * Add ENERGY_CHARGING_PER_ROUND energy units to the currentEnergyLevel.
     */
    protected void chargeEnergyPerRound() {
        if(currentEnergyLevel < maximalEnergyLevel) {
            currentEnergyLevel += ENERGY_CHARGING_PER_ROUND;
        }
    }

    /**
     * The ship will always accelerate and turn to toward the nearest ship.
     *
     * @param game The game object to which this ship belongs.
     * @return the nearest space ship this ship go toward.
     */
    protected SpaceShip goTowardNearestShip(SpaceWars game) {
        int turnStatus = NO_TURN;
        SpaceShip nearestShip = game.getClosestShipTo(this);
        double angleToOtherShip = shipPhysicsObject.angleTo(nearestShip.shipPhysicsObject);
        if(angleToOtherShip > NON_NEGATIVE) {
            turnStatus = LEFT_TURN;
        }
        else if(angleToOtherShip < NON_NEGATIVE) {
            turnStatus = RIGHT_TURN;
        }
        shipPhysicsObject.move(true, turnStatus);  // always accelerate
        return nearestShip;
    }

    /**
     * The ship will always accelerate and turn the opposite direction of the nearest ship.
     *
     * @param closestShip The nearest ship to this one.
     */
    protected void goAwayFromNearestShip(SpaceShip closestShip) {
        int turnStatus = NO_TURN;
        double angleToOtherShip = shipPhysicsObject.angleTo(closestShip.shipPhysicsObject);
        // multiply by NEGATIVE_DIRECTION to get the opposite direction:
        if (angleToOtherShip * NEGATIVE_DIRECTION > NON_NEGATIVE) {
            turnStatus = LEFT_TURN;
        } else if (angleToOtherShip < NON_NEGATIVE) {
            turnStatus = RIGHT_TURN;
        }
        shipPhysicsObject.move(true, turnStatus);  // always accelerate
    }


    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game The game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        // Actually an abstract method but since I can't change it's signature it's up to me to make sure
        // I override it
    }

    /**
     * This method is called every time a collision with this ship occurs.
     */
    public void collidedWithAnotherShip() {
        reduceHealthAfterHit();
        if(shieldStatus) {  // if "bashing"
            maximalEnergyLevel += BASHING_WITH_SHIELD_BONUS;
            currentEnergyLevel += BASHING_WITH_SHIELD_BONUS;
        }
        changeMaximalEnergy();
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        shipPhysicsObject = new SpaceShipPhysics();
        currentEnergyLevel = CURRENT_ENERGY_DEFAULT_VALUE;
        maximalEnergyLevel = MAXIMAL_ENERGY_DEFAULT_VALUE;
        healthLevel = HEALTH_DEFAULT_VALUE;
        shieldStatus = false;
    }

    /**
     * Checks if this ship is dead.
     *
     * @return True if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return (healthLevel == DEATH_INDICATOR);
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return The physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return shipPhysicsObject;
    }

    /*
    If the shields are off and the health level of the ship in positive - reduce "HEALTH_REDUCE" units
    of health.
    */
    private void reduceHealthAfterHit() {
        if(healthLevel > DEATH_INDICATOR && !shieldStatus) {
            healthLevel -= HEALTH_REDUCE;
        }
    }

    /*
    Reduce the maximal energy level by "GETTING_HIT_PENALTY" energy units. If after the reduction, the new
    maximum is smaller than the current energy level, it also sets the current value to the max value.
     */
    private void changeMaximalEnergy() {
        maximalEnergyLevel -= GETTING_HIT_PENALTY;
        if(currentEnergyLevel > maximalEnergyLevel) {
            currentEnergyLevel = maximalEnergyLevel;
        }
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        reduceHealthAfterHit();
        changeMaximalEnergy();
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return The image of this ship.
     */
    public Image getImage() {
        // ATTENTION: "default" implementation is for an enemy ship, and it can be override by other classes.
        if(shieldStatus) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game The game object.
     */
    public void fire(SpaceWars game) {
        if(SHOTS_COOLDOWN_PERIOD_COUNTER == SHOTS_MAX_COOLDOWN_COUNT) {  // finished cooldown
            SHOTS_COOLDOWN_PERIOD_COUNTER = SHOTS_MIN_COOLDOWN_COUNT;  // reset counter
        }
        if(SHOTS_COOLDOWN_PERIOD_COUNTER == SHOTS_MIN_COOLDOWN_COUNT) {  // no cooldown period
            if(currentEnergyLevel - SHOT_ENERGY_CONSUME >= NON_NEGATIVE_ENERGY_LEVEL) {
                currentEnergyLevel -= SHOT_ENERGY_CONSUME;
                game.addShot(shipPhysicsObject);
                SHOTS_COOLDOWN_PERIOD_COUNTER++;  // start counting
            }
        }
        else {
            SHOTS_COOLDOWN_PERIOD_COUNTER++;  // continue counting
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if(currentEnergyLevel - SHIELD_ENERGY_CONSUME >= NON_NEGATIVE_ENERGY_LEVEL) {
            shieldStatus = true;
            currentEnergyLevel -= SHIELD_ENERGY_CONSUME;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if(currentEnergyLevel - TELEPORT_ENERGY_CONSUME >= NON_NEGATIVE_ENERGY_LEVEL) {
            currentEnergyLevel -= TELEPORT_ENERGY_CONSUME;
            shipPhysicsObject = null;  // delete the old object
            shipPhysicsObject = new SpaceShipPhysics();  // new object, new position
        }
    }
}
