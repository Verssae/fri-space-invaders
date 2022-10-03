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
            //이예나
            /*enemyshipformation의 enemyship에 0,1 값 할당
              1이면 hasItem
              30퍼센트의 적이 hasItem값 할당
             */

        }

        public void assignItem (EnemyShipFormation enemyshipformation, int i, int j){
            //노은솔
            /*enemyshipformation의 enemyship의 hasItem값이 1이면 item_queue에서 아이템타입 할당
             */
            //
          //  ggggg
        }




        public void enenmyhasitem () {
            // 임시 토글메서드
        }


        private void addlist () {
            //박용수
            /*
            아이템타입의 리스트
             */
        }

        private void addqueue () {
            //박용수
            /*
            아이템타입들이 셔플된 큐
             */
        }


    }
