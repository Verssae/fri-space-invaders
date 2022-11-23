package entity;

/**
 * Pools of player's items.
 *
 */
public class ItemPool {
    /** Array of obtained items. */
    private static InGameItem[] inGameItems = new InGameItem[1];
    /** Attributes of obtained items. */
    private static ItemState itemState;
    /** Initialize item array. */
    static {
        for(int i =0 ; i < 1 ; i++){
            inGameItems[i] = null;
        }
    }

    /**
     * constructor.
     * has player's obtained item.
     */
    public ItemPool() {
        this.inGameItems = inGameItems;
    }

    /**
     * Getter for the item of itempool.
     *
     * @return item ,in itempool.
     */
    public InGameItem getItem(){
        return inGameItems[0];
    }

    /**
     * Setter for the item of itempool.
     *
     * @param inGameItem
     *        add obtained item in itempool
     */
    public void add(InGameItem inGameItem){
        this.inGameItems[0] = inGameItem;
    }

}
