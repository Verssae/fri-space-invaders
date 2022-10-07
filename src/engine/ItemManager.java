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
            enemyshipssize = enemyshipformation.getEnemyShip().size();
            final int nShipsWide = enemyshipformation.getnshipsWide();
            final int nShipsHigh = enemyshipformation.getnShipsHigh();

            Integer[][] random = new Integer[nShipsWide][nShipsHigh];


            for (int i = 0; i < nShipsWide; i++) {
                for (int j = 0; j < nShipsHigh; j++) {
                    if (Math.random()*10 + 1 < 2.1) {
                        random[i][j] = 1;
                    } else random[i][j] = 0;
                }
            }

            for (int i = 0; i < nShipsWide; i++) {
                for (int j = 0; j < nShipsHigh; j++) {
                    if (random[i][j] == 1) {
                        // System.out.println("랜덤 1인 경우"); -> 디버그용
                        enemyshipformation.getEnemyShip().get(i).get(j).setHasItem(1);
                        this. assignItem(enemyshipformation, i , j);
                    }
                    else enemyshipformation.getEnemyShip().get(i).get(j).setHasItem(0);
                    //System.out.println(enemyshipformation.getEnemyShip().get(i).get(j).getHasItem()); -> 로그추가하기
                }

            }

        }


        public void assignItem (EnemyShipFormation enemyshipformation, int i, int j){
            //노은솔
            /*enemyshipformation의 enemyship의 hasItem값이 1이면 item_queue에서 아이템타입 할당
             */
            Item.ItemType tmpitem = null;
            tmpitem = item_queue.remove();
            enemyshipformation.getEnemyShip().get(i).get(j).setItemType(tmpitem);
            item_queue.add(tmpitem);
        }


        public void enenmyhasitem () {
            // 임시 토글메서드
        }


        private void addlist () {
            //박용수
            /*
            아이템타입의 리스트
             */
            item_list.add(pointupitem);
            item_list.add(speedupitem);
            item_list.add(shielditem);
            item_list.add(extralifeitem);
            item_list.add(bulletspeeditem);

        }


        private void addqueue () {
            //박용수
            /*
            아이템타입들이 셔플된 큐
             */
            item_queue.add(this.item_list.get(0));
            item_queue.add(this.item_list.get(1));
            item_queue.add(this.item_list.get(2));
            item_queue.add(this.item_list.get(3));
            item_queue.add(this.item_list.get(4));
        }
    }
