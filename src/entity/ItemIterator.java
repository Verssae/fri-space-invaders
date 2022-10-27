package entity;

import java.util.HashSet;
import java.util.Set;
/**
 * Implements a iterator of items.
 *
 */
public class ItemIterator {
    /** Set of already created items. */
    private static Set<Item> iterator = new HashSet<Item>();

    /**
     * Constructor, not called.
     */
    private ItemIterator(){

    }
    /**
     * Returns a item from the destroyed enemyship that already assigned hasItem.
     *
     * @param positionX
     *            Requested position of the item in the X axis.
     * @param positionY
     *            Requested position of the item in the Y axis.
     * @param itemtype
     *            Requested itemtype of item, for calling constructor of Item class.
     * @return Requested item.
     */
    public static Item drop(final int positionX,
                               final int positionY,  Item.ItemType itemtype) {
        Item item;
        if (!iterator.isEmpty()) {
            item = iterator.iterator().next();
            iterator.remove(item);
            item.setPositionX(positionX - item.getWidth() / 2);
            item.setPositionY(positionY);

        } else {
            item = new Item(positionX,positionY, 2 ,itemtype);
            item.setPositionX(positionX - item.getWidth() / 2);
        }
        return item;
    }


}
