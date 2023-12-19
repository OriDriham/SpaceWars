import oop.ex2.GameGUI;

import java.awt.Image;

/**
 * A human controlled ship, controlled by the user. The keys are: left-arrow and right-arrow to turn,
 * up-arrow to accelerate, 'd' to fire a shot, 's' to turn on the shield, 'a' to teleport.
 */
public class HumanControlledShip extends SpaceShip {
    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game The game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        deactivateShields();
        // setting the default 2 parameters to "shipPhysicsObject.move" method:
        boolean accelStatus = false;  // not accelerating
        int turnStatus = NO_TURN;
        GameGUI guiObject = game.getGUI();
        if(guiObject.isTeleportPressed()) {
            teleport();
        }
        if(guiObject.isUpPressed()) {
            accelStatus = true;
        }
        if(guiObject.isRightPressed()) {
            turnStatus = RIGHT_TURN;
        }
        if(guiObject.isLeftPressed()) {
            turnStatus = LEFT_TURN;
        }
        if(guiObject.isRightPressed() && guiObject.isLeftPressed()) {  // both are pressed
            turnStatus = NO_TURN;
        }
        shipPhysicsObject.move(accelStatus, turnStatus);  // happens anyway
        if(guiObject.isShieldsPressed()) {
            shieldOn();
        }
        if(guiObject.isShotPressed()) {
            fire(game);
        }
        chargeEnergyPerRound();
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return The image of this ship.
     */
    @Override
    public Image getImage() {
        if(shieldStatus) {
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.SPACESHIP_IMAGE;
    }
}
