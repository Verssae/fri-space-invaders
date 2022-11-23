package engine;

import entity.EnemyShipFormation;
import entity.I_State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages assigning items to enemyship.
 *
 */
public class ItemManager {

    /** item list for shuffle items randomly. */
    private ArrayList<I_State> item_list = new ArrayList<I_State>();
    /** randomly shuffled queue that has items, for assigning items to enemyship. */
    private Queue<I_State> item_queue = new LinkedList<I_State>();
    /** pointUpItem itemtype  */
    private I_State pointupitem = I_State.PointUpItem;
    /** speedUpItem itemtype  */
    private I_State speedupitem = I_State.SpeedUpItem;
    /** shield itemtype  */
    private I_State shielditem = I_State.ShieldItem;
    /** extralifeitem itemtype  */
    private I_State extralifeitem = I_State.ExtraLifeItem;
    /** bulletspeed itemtype  */
    private I_State bulletspeeditem = I_State.BulletSpeedItem;
    /** machinegun itemtype  */
    private I_State machinegun = I_State.MachineGun;
    /** enemyshipspeeditem itemtype, not used */

    private I_State  enemyshipspeeditem = I_State.EnemyShipSpeedItem;
    /** information that has the num of enemyship, init state of gamescreen  */
    private int enemyshipssize;

    /**
     * constructor.
     * randomly shuffling items
     */
    public ItemManager() {

        this.addlist();
        Collections.shuffle(item_list);
        this.addqueue();

    }


    /**
     * For assigning items to enemyship, when init state of gamescreen set.
     * @param  enemyshipformation
     */
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
                        this. assignItem(enemyshipformation, i , j);
                    }
                }
            }
        }

    /**
     * For assigning items to enemyship, when init state of gamescreen set.
     * @param  enemyshipformation
     */
        public void assignItem (EnemyShipFormation enemyshipformation, int i, int j){
            //노은솔
            /*enemyshipformation의 enemyship의 hasItem값이 1이면 item_queue에서 아이템타입 할당
             */
            I_State tmpitem = null;
            tmpitem = item_queue.remove();
            enemyshipformation.getEnemyShip().get(i).get(j).setItemType(tmpitem);
            item_queue.add(tmpitem);
        }


        public void enenmyhasitem () {
            // 임시 토글메서드
            
        }

    /**
     * used for shuffling items.
     */
        private void addlist () {
            /*
            아이템타입의 리스트
             */
            item_list.add(pointupitem);
            item_list.add(speedupitem);
            item_list.add(shielditem);
            item_list.add(extralifeitem);
            item_list.add(bulletspeeditem);
            item_list.add(machinegun);
        }

    /**
     * used for assigning items.
     */
        private void addqueue () {
            //박용수
            /*
            아이템타입들이 셔플된 큐
             */
            for(int i = 0; i < 6 ; i ++) {
                item_queue.add(this.item_list.get(i));
            }
        }
    }
