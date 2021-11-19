/*
package entities;

import common.*;
import graphics.Sprite;

public class Bomber extends Entity {
    public int speed = 4;
    public int framePlayer = 0;
    public int intervalPlayer = 5;
    public int indexAnimPlayer = 0;
    public boolean moving = false;


    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }


    public void update(int tileSize, int SCALE, char[][] scene) {
        moving = false;
        if (common_view.right && isFree(x + speed, y, scene)) {
            this.x += speed;
            moving = true;
        }
        if (common_view.left && isFree(x - speed, y, scene)) {
            this.x -= speed;
            moving = true;
        }
        if (common_view.up && isFree(x, y - speed, scene)) {
            this.y -= speed;
            moving = true;
        }
        if (common_view.down && isFree(x, y + speed, scene)) {
            this.y += speed;
            moving = true;
        }

        if (moving) {
            framePlayer++;
            if (framePlayer > intervalPlayer) {
                framePlayer = 0;
                indexAnimPlayer++;
                if (indexAnimPlayer > 2) {
                    indexAnimPlayer = 0;
                }
            }

            if (common_view.right) {
                common_view.sprite.player = common_view.sprite.playerAnimRight[indexAnimPlayer];
            } else if (common_view.left) {
                common_view.sprite.player = common_view.sprite.playerAnimLeft[indexAnimPlayer];
            } else if (common_view.up) {
                common_view.sprite.player = common_view.sprite.playerAnimUp[indexAnimPlayer];
            } else if (common_view.down) {
                common_view.sprite.player = common_view.sprite.playerAnimDown[indexAnimPlayer];
            } else {
                common_view.sprite.player = common_view.sprite.playerAnimDown[1];
            }
        }

        System.out.println("(" + this.x + ", " + this.y + ")");

    }

    public boolean isFree(int x, int y, char[][] scene) {
        int x1 = x / (common_view.TILESIZE * common_view.SCALE);
        int y1 = y / (common_view.TILESIZE * common_view.SCALE);

        if (y % (common_view.TILESIZE * common_view.SCALE) == 0 && x % (common_view.TILESIZE * common_view.SCALE) == 0) {
            if (scene[y1][x1] == 32 || scene[y1][x1] == 57) {
                return true;
            } else {
                return false;
            }
        }
        else if (y % (common_view.TILESIZE * common_view.SCALE) == 0) {
            if ((scene[y1][x1] == 32 && scene[y1][x1 + 1] == 32) || (scene[y1][x1] == 57 || scene[y1][x1 + 1] == 57)) {
                return true;
            }
            else {
                return false;
            }
        } else if (x % (common_view.TILESIZE * common_view.SCALE) == 0) {
            if ((scene[y1][x1] == 32 && scene[y1 + 1][x1] == 32) || (scene[y1][x1] == 57 || scene[y1 + 1][x1] == 57)) {
                return true;
            }
            else {
                return false;
            }
        } else {
            if ((scene[y1][x1] == 32 && scene[y1 + 1][x1] == 32 && scene[y1][x1 + 1] == 32 && scene[y1 + 1][x1 + 1] == 32) || (scene[y1][x1] == 57 || scene[y1 + 1][x1] == 57 || scene[y1][x1 + 1] == 57 || scene[y1 + 1][x1 + 1] == 57)) {
                return true;
            }
            else {
                return false;
            }
        }
    }


}

*/


package entities;

import common.*;
import graphics.Sprite;

public class Bomber extends Entity {
    public int speed = 4;
    public int framePlayer = 0;
    public int intervalPlayer = 5;
    public int indexAnimPlayer = 0;
    public boolean moving = false;

    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit);
    }


    public void update(int tileSize, int SCALE, char[][] scene) {
        moving = false;
        if (common_view.right && isFreeR(x , y, scene)) {
            this.x += speed;
            moving = true;
        }
        if (common_view.left && isFreeL(x , y, scene)) {
            this.x -= speed;
            moving = true;
        }
        if (common_view.up && isFreeU(x, y , scene)) {
            this.y -= speed;
            moving = true;
        }
        if (common_view.down && isFreeD(x, y , scene)) {
            this.y += speed;
            moving = true;
        }

        if (moving) {
            framePlayer++;
            if (framePlayer > intervalPlayer) {
                framePlayer = 0;
                indexAnimPlayer++;
                if (indexAnimPlayer > 2) {
                    indexAnimPlayer = 0;
                }
            }

            if (common_view.right) {
                common_view.sprite.player = common_view.sprite.playerAnimRight[indexAnimPlayer];
            } else if (common_view.left) {
                common_view.sprite.player = common_view.sprite.playerAnimLeft[indexAnimPlayer];
            } else if (common_view.up) {
                common_view.sprite.player = common_view.sprite.playerAnimUp[indexAnimPlayer];
            } else if (common_view.down) {
                common_view.sprite.player = common_view.sprite.playerAnimDown[indexAnimPlayer];
            } else {
                common_view.sprite.player = common_view.sprite.playerAnimDown[1];
            }
        }

    }
    public boolean isFreeR(int x, int y, char[][] scene) {
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if ( x + speed >21*size) {
            return false;
        } else {
            if (x % size !=0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x/48;
                int y1 = y/48;
                if (scene[y1][x1+1]==32 || scene[y1][x1+1]==57) {
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size != 0 && x % size == 0) {
                int y1 = y / size;
                int x1 = x / size;
                if (scene[y1][x1+1] == 32 && scene[y1+1][x1+1] == 32) a = true;
                if (scene[y1][x1+1] == 32 && scene[y1+1][x1+1] == 57) a = true;
                if (scene[y1][x1+1] == 57 && scene[y1+1][x1+1] == 32) a = true;
                if (scene[y1][x1+1] == 57 && scene[y1+1][x1+1] == 57) a = true;
            }
        } return a;
    }

    public boolean isFreeL(int x, int y, char[][] scene) {
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if ( x - speed < 48) {
            return false;
        } else {
            if (x % size !=0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x/48;
                int y1 = y/48;
                if (scene[y1][x1-1]==32 || scene[y1][x1-1]==57) {
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size != 0 && x % size == 0) {
                int y1 = y / size;
                int x1 = x / size;
                if (scene[y1][x1-1] == 32 && scene[y1+1][x1-1] == 32) a = true;
                if (scene[y1][x1-1] == 32 && scene[y1+1][x1-1] == 57) a = true;
                if (scene[y1][x1-1] == 57 && scene[y1+1][x1-1] == 32) a = true;
                if (scene[y1][x1-1] == 57 && scene[y1+1][x1-1] == 57) a = true;
            }
        } return a;
    }

    public boolean isFreeU(int x, int y, char[][] scene) {
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (y-speed<48) {
            return false;
        } else {
            if (y % size !=0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x/48;
                int y1 = y/48;
                if (scene[y1-1][x1]==32 || scene[y1-1][x1]==57) {
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size == 0 && x % size != 0) {
                int y1 = y / size;
                int x1 = x / size;
                if (scene[y1-1][x1+1] ==32 && scene[y1-1][x1] ==32 ) a=true;
                if (scene[y1-1][x1+1] ==32 && scene[y1-1][x1] ==57 ) a=true;
                if (scene[y1-1][x1+1] ==57 && scene[y1-1][x1] ==32 ) a=true;
                if (scene[y1-1][x1+1] ==57 && scene[y1-1][x1] ==57 ) a=true;
            }
        } return a;
    }

    public boolean isFreeD(int x, int y, char[][] scene) {
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (y+speed > 15*size) {
            return false;
        } else {
            if (y % size !=0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x/48;
                int y1 = y/48;
                if (scene[y1+1][x1]==32 || scene[y1+1][x1]==57) {
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size == 0 && x % size != 0) {
                int y1 = y / size;
                int x1 = x / size;
                if (scene[y1+1][x1+1] ==32 && scene[y1+1][x1] ==32 ) a=true;
                if (scene[y1+1][x1+1] ==32 && scene[y1+1][x1] ==57 ) a=true;
                if (scene[y1+1][x1+1] ==57 && scene[y1+1][x1] ==32 ) a=true;
                if (scene[y1+1][x1+1] ==57 && scene[y1+1][x1] ==57 ) a=true;
            }
        } return a;
    }
}

