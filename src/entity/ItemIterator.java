package entity;

import java.util.HashSet;
import java.util.Set;
/**
 * Implements a iterator of items.
 *
 */
public class ItemIterator {
    /** Set of already created items. */
    private static Set<InGameItem> iterator = new HashSet<InGameItem>();

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
     * @param itemState
     *            Requested itemtype of item, for calling constructor of Item class.
     * @return Requested item.
     */
    public static InGameItem drop(final int positionX,
                               final int positionY,  ItemState itemState) {
        InGameItem inGameItem;
        if (!iterator.isEmpty()) {
            inGameItem = iterator.iterator().next();
            iterator.remove(inGameItem);
            inGameItem.setPositionX(positionX - inGameItem.getWidth() / 2);
            inGameItem.setPositionY(positionY);
        } else {
            inGameItem = new InGameItem(positionX, positionY, 2, itemState);
            inGameItem.setPositionX(positionX - inGameItem.getWidth() / 2);
        }
        return inGameItem;
    }


}
