package entity;
import java.awt.Color;
import java.util.Set;

import engine.Cooldown;
import engine.Core;
import engine.DrawManager;
import engine.DrawManager.SpriteType;
/**
 * Implements a enemy ship, to be destroyed by the player.
 *
 * @author <a href="mailto:RobertoIA1987@gmail.com">Roberto Izquierdo Amo</a>
 *
 */
public class dangerousShip extends Entity {
    //추가
    private Item.ItemType itemtype;

    private static int hasItem ;
    /** Point value of a type A enemy. */

    /** Point value of a bonus enemy. */
    private static final int BONUS_TYPE_POINTS = 100;
    /** Cooldown between sprite changes. */
    private Cooldown animationCooldown;
    /** Checks if the ship has been hit by a bullet. */
    private boolean isDestroyed;
    /** Values of the ship, in points, when destroyed. */
    private int pointValue;

    private final int ITEM_SPEED = 2;
    /**
     * Constructor, establishes the ship's properties.
     *
     * @param positionX
     *            Initial position of the ship in the X axis.
     * @param positionY
     *            Initial position of the ship in the Y axis.
     * @param spriteType
     *            Sprite type, image corresponding to the ship.
     */


    //추가


    /**
     * Constructor, establishes the ship's properties for a dangerous ship, with
     * known starting properties.
     */
    public dangerousShip() {
        super(-32, 60, 16 * 2, 7 * 2, Color.BLUE);

        this.spriteType = DrawManager.SpriteType.EnemyShipdangerous;
        this.isDestroyed = false;
        this.pointValue = BONUS_TYPE_POINTS;

    }

    /**
     * Getter for the score bonus if this ship is destroyed.
     *
     * @return Value of the ship.
     */
    public final int getPointValue() {
        return this.pointValue;
    }

    public void setPointValue(int setpointvalue){
        this.pointValue = setpointvalue;
    }

    /**
     * Moves the ship the specified distance.
     *
     * @param distanceX
     *            Distance to move in the X axis.
     * @param distanceY
     *            Distance to move in the Y axis.
     */
    public final void move(final int distanceX, final int distanceY) {
        this.positionX += distanceX;
        this.positionY += distanceY;
    }

    /**
     * Updates attributes, mainly used for animation purposes.
     */
    public final void update() {
        if (this.animationCooldown.checkFinished()) {
            this.animationCooldown.reset();
            this.spriteType = spriteType;
            this.animationCooldown = Core.getCooldown(500);
            this.isDestroyed = false;
        }
    }

    /**
     * Destroys the ship, causing an explosion.
     */
    public final void destroy() {
        this.isDestroyed = true;
        this.spriteType = SpriteType.Explosion;
    }

    /**
     * Checks if the ship has been destroyed.
     *
     * @return True if the ship has been destroyed.
     */
    public final boolean isDestroyed() {
        return this.isDestroyed;
    }

}

