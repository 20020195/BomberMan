package entities;

import common.common_view;

public class Item extends Entity {
    public int frameItem = 0;
    public int intervalItem = 12;
    public int indexAnimItem = 0;

    public Item(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    public void animItem(Item item) {
        item.frameItem++;
        if (item.frameItem == item.intervalItem) {
            item.frameItem = 0;
            item.indexAnimItem++;

            if (item.indexAnimItem > 5) {
                item.indexAnimItem = 0;
            }
        }
    }

    public void update() {
        if (common_view.items.size() != 0) {
            for (int i = 0; i < common_view.items.size(); i++) {
                animItem(common_view.items.get(i));
            }
        }
    }
}
