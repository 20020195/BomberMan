package entities;

import common.common_view;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {
    private String type_item;
    public Item(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }

    public void animItem() {
        if (type_item.equals("item_deadth")) {
            image = common_view.sprite.item_deadth[this.getIndexAnim()];
        } else if (type_item.equals("item_speed")) {
            image = common_view.sprite.item_speed[this.getIndexAnim()];

        }
        frame++;
        if (frame == interval) {
            frame = 0;
            indexAnim++;

            if (indexAnim > 1) {
                indexAnim = 0;
            }
        }
    }

    public void update() {
        animItem();
    }

    @Override
    public void draw(Graphics2D g2, BufferedImage image, int x, int y) {
        g2.drawImage(this.getImage(), this.getX(), this.getY(), common_view.size, common_view.size, null);
    }

    public String getType_item() {
        return type_item;
    }

    public void setType_item(String type_item) {
        this.type_item = type_item;
    }
}
