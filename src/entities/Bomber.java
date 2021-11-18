package entities;

import common.*;

public class Bomber extends Entity {
    private int speed = 4;

    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }


    public void update(int tileSize, int SCALE, char[][] scene) {
        if (common_view.right && isFree(x + speed, y, scene)) {
            x += speed;
        }
        if (common_view.left && isFree(x - speed, y, scene)) {
            x -= speed;
        }
        if (common_view.up && isFree(x, y - speed, scene)) {
            y -= speed;
        }
        if (common_view.down && isFree(x, y + speed, scene)) {
            y += speed;
        }
    }

    public boolean isFree(int x, int y, char[][] scene) {
        int x1 = x / (common_view.TILESIZE * common_view.SCALE);
        int y1 = y / (common_view.TILESIZE * common_view.SCALE);

        if (y % (common_view.TILESIZE * common_view.SCALE) == 0 && x % (common_view.TILESIZE * common_view.SCALE) == 0) {
            if (scene[y1][x1] == 32) {
                return true;
            } else {
                return false;
            }
        }
        else if (y % (common_view.TILESIZE * common_view.SCALE) == 0) {
            if (scene[y1][x1] == 32 && scene[y1][x1 + 1] == 32) {
                return true;
            }
            else {
                return false;
            }
        } else if (x % (common_view.TILESIZE * common_view.SCALE) == 0) {
            if (scene[y1][x1] == 32 && scene[y1 + 1][x1] == 32) {
                return true;
            }
            else {
                return false;
            }
        } else {
            if (scene[y1][x1] == 32 && scene[y1 + 1][x1] == 32 && scene[y1][x1 + 1] == 32 && scene[y1 + 1][x1 + 1] == 32) {
                return true;
            }
            else {
                return false;
            }
        }
    }


}

