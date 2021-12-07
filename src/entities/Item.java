package entities;

import common.common_view;

import java.awt.image.BufferedImage;

public class Item extends Entity {
    private BufferedImage img_item;

    private int frameItem = 0;
    private int intervalItem = 12;
    private int indexAnimItem = 0;

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
        animItem(this);
    }

    public BufferedImage getImg_item() {
        return img_item;
    }

    public void setImg_item(BufferedImage img_item) {
        this.img_item = img_item;
    }

    public int getIndexAnimItem() {
        return indexAnimItem;
    }

    public void setIndexAnimItem(int indexAnimItem) {
        this.indexAnimItem = indexAnimItem;
    }
}
