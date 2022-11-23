package entity;

import engine.DrawManager;
import engine.I_State;

import java.awt.*;
/**
 * Implements a item.
 */
public class InGameItem extends Entity {
    /** Item drop speed */
    private int speed;
    /** Item types. */

    private I_State i_state;
    private boolean isget;

    /**
     * Constructor, establishes the item's properties.
     *
     * @param positionX
     *            Initial position of the item in the X axis.
     * @param positionY
     *            Initial position of the item in the Y axis.
     * @param speed
     *            Initial set of the item's drop-speed
     * @param i_state
     *            set the item's type.
     */
    public InGameItem(final int positionX, final int positionY, final int speed, final I_State i_state) {
        super(positionX, positionY, 9 * 2, 9 * 2, Color.ORANGE);
        this.setPositionX(positionX -this.getWidth()/2);
        this.speed = speed;
        this.i_state = i_state;
        this.isget = false;
    }

    /**
     * Updates the item's position.
     */
    public void update () {
        this.positionY += this.speed;
    }

    /**
     * Sets correct sprite for the item, based on for player to Obtained item.
     */
    public void setSprite () {
        if (!this.isget)
            this.spriteType = DrawManager.SpriteType.ItemDrop;
        else
            this.spriteType = DrawManager.SpriteType.ItemGet;
    }

    /**
     * decision to player's obtaining item.
     * @param bool
     */
    public void isGet(boolean bool){
        this.isget = bool;
    }

    /**
     * get information that isget.
     */
    public boolean getIsget(){
        return this.isget;
    }

    /**
     * Returns item's itemtype
     *
     * @return itemtype that dropped item has.
     */
    public I_State getItemType(){
        return this.i_state;
    }
}
