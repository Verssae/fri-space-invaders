package entity;

import com.sun.jdi.VoidType;
import engine.DrawManager;

import java.awt.Color;

public class Shield extends Entity {

    private int cnt;
    private Ship ship;

    public Shield(final int positionX, final int positionY, final int cnt) {
        super(positionX + 2, positionY, 5 * 2, 3 * 2, Color.BLUE);
        this.setPositionX(ship.getPositionX());
        this.setPositionY(ship.getPositionY());
        this.cnt = cnt;
    }

    public void moveRight(){ this.positionX += ship.getSpeed();}
    public void moveLeft(){ this.positionX += ship.getSpeed();}

    public void setSprite(){
        if(this.cnt > 0)
            this.spriteType = DrawManager.SpriteType.Shield;
    }

    public final void setCnt(final int cnt) {this.cnt = cnt;}
    public final int getCnt() {return this.cnt;}


}
