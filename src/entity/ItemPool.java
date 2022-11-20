package entity;
/**
 * Pools of player's items.
 *
 */
public class ItemPool {
    /** Array of obtained items. */
    private static Item[] item = new Item[1];
    /** Attributes of obtained items. */
    private static Item.ItemType itemtype;
    /** Initialize item array. */
    static {
        for(int i =0 ; i < 1 ; i++){
            item[i] = null;
        }
    }

    /**
     * constructor.
     * has player's obtained item.
     */
    public ItemPool() {
        this.item = item;
    }

    /**
     * Getter for the item of itempool.
     *
     * @return item ,in itempool.
     */
    public Item getItem(){
        return item[0];
    }

    /**
     * Setter for the item of itempool.
     *
     * @param item
     *        add obtained item in itempool
     */
    public void add(Item item){
        this.item[0] = item;
    }

}
