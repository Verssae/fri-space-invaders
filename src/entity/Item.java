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

        ExtraLifeItem

    };

    private ItemType itemtype;

    private boolean isget;


    public Item(final int positionX, final int positionY, final int speed, final ItemType itemtype) {
            super(positionX, positionY, 3 * 2, 5 * 2, Color.WHITE);
            this.speed = speed;
            this.itemtype = itemtype;
            this.isget = false;
        }



        public void update () {

        }



        public void setSprite () {

        }

        public void isGet(boolean bool){


        }


        public void drop(){

        }



        public ItemType getItemType(){

        return this.itemtype;

        }


    }