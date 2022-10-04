package entity;

import java.util.HashSet;
import java.util.Set;

public class ItemIterator {

    private static Set<Item> iterator = new HashSet<Item>();

    private ItemIterator(){

    }

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
