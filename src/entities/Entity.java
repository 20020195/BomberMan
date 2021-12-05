package entities;

import common.*;

public abstract class Entity {
    protected int x;
    protected int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Entity( int xUnit, int yUnit) {
        this.x = xUnit * common_view.SCALE;
        this.y = yUnit * common_view.SCALE;
    }
}
