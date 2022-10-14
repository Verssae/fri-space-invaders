package entity;

public class ItemPool {
    private static Item[] item = new Item[1];

    private static Item.ItemType itemtype;

    static {
        for(int i =0 ; i < 1 ; i++){
            item[i] = null;
        }
    }

    public ItemPool() {
        this.item = item;
    }


    public Item getItem(){
        return item[0];
    }


    public void add(Item item){
        this.item[0] = item;
    }

    /*
    public ItemPool setNullItem(){
        return this.getInstance();
        // 쿨타임 이후 아이템객체 초기화
    }
    */

}
