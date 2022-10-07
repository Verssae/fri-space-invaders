package entity;

import com.sun.jdi.VoidType;
import engine.DrawManager;

import java.awt.Color;

public class Shield extends Entity {

    private int cnt;
    private Ship ship;

    public Shield(final int positionX, final int positionY, final int cnt, Ship ship) {
        super(positionX + 2, positionY, 5 * 2, 3 * 2, Color.WHITE);
        //this.setPositionX(ship.getPositionX());
       // this.setPositionY(ship.getPositionY());
        this.spriteType = DrawManager.SpriteType.Shield;
        this.cnt = cnt;
        this.ship = ship;
    }

    public void moveRight(){ this.positionX += this.ship.getSpeed();}
    public void moveLeft(){ this.positionX -= this.ship.getSpeed();}

    public void setSprite(){
            this.spriteType = DrawManager.SpriteType.Shield;
    }

    public final void setCnt(final int cnt) {this.cnt = cnt;}
    public final int getCnt() {return this.cnt;}


}
