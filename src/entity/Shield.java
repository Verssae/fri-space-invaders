package entity;


import engine.DrawManager;

import java.awt.Color;

public class Shield extends Entity {

    private Ship ship;

    public Shield(final int positionX, final int positionY, Ship ship) {
        super(positionX + 2, positionY, 5 * 2, 3 * 2, Color.WHITE);
        this.spriteType = DrawManager.SpriteType.Shield;
        this.ship = ship;
    }

    public void moveRight(){ this.positionX += this.ship.getSpeed();}

    public void moveLeft(){ this.positionX -= this.ship.getSpeed();}

}
