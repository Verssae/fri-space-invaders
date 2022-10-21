package entity;

import engine.DrawManager;

import java.awt.*;

public class Item extends Entity {

    //private static int dropspeed;
    //private static int duration;
    private int speed;
    public static enum ItemType{

        BulletSpeedItem,

        PointUpItem,

        ShieldItem,

        SpeedUpItem,

        ExtraLifeItem,

        EnemyShipSpeedItem

    };

    private ItemType itemtype;

    private boolean isget;


    public Item(final int positionX, final int positionY, final int speed, final ItemType itemtype) {
            super(positionX, positionY, 3 * 2, 5 * 2, Color.ORANGE);
            this.setPositionX(positionX -this.getWidth()/2);
            this.speed = speed;
            this.itemtype = itemtype;
            this.isget = false;
        }


    public void update () {this.positionY += this.speed;}


    public void setSprite () {
        if (!this.isget)
            this.spriteType = DrawManager.SpriteType.ItemDrop;
        else
            this.spriteType = DrawManager.SpriteType.ItemGet;
    }


    public void isGet(boolean bool){
        this.isget = bool;
    }


    public boolean getIsget(){
        return this.isget;
    }


    public void setIsget(boolean bool){
        this.isget = bool;
    }


    public void drop(){}


    public ItemType getItemType(){
        return this.itemtype;
    }


    }
