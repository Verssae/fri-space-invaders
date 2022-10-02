package engine;

import entity.EnemyShipFormation;
import entity.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class ItemManager {

    private ArrayList<Item.ItemType> item_list = new ArrayList<Item.ItemType>();

    private Queue<Item.ItemType> item_queue = new LinkedList<Item.ItemType>();

    private Item.ItemType pointupitem = Item.ItemType.PointUpItem;

    private Item.ItemType speedupitem = Item.ItemType.SpeedUpItem;

    private Item.ItemType shielditem = Item.ItemType.ShieldItem;

    private Item.ItemType extralifeitem = Item.ItemType.ExtraLifeItem;

    private Item.ItemType bulletspeeditem = Item.ItemType.BulletSpeedItem;

    private int enemyshipssize;

    //private List<List<>> randomlist;

    public ItemManager() {

        this.addlist();
        Collections.shuffle(item_list);
        this.addqueue();

    }

        public void assignHasItem(EnemyShipFormation enemyshipformation) {


        }

        public void assignItem (EnemyShipFormation enemyshipformation, int i, int j){

        }




        public void enenmyhasitem () {
            // 임시 토글메서드
        }


        private void addlist () {


        }

        private void addqueue () {


        }


    }
