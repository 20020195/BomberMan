package entities;

import common.*;
import sound.SoundEffect;

import java.awt.image.BufferedImage;

public class Bomber extends Entity {
    protected int speed = 2;
    private BufferedImage img_player;

    protected boolean moving = false;
    protected boolean right = false;
    protected boolean left = false;
    protected boolean up = false;
    protected boolean down = false;

    protected int framePlayer = 0;
    protected int intervalPlayer = 5;
    protected int indexAnimPlayer = 0;

    protected boolean no_dead = false;
    protected long time_start_boost;
    protected long time_start_no_dead;

    protected SoundEffect sound_foot;

    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        sound_foot = new SoundEffect("res/audio/sound_foot.wav");
    }

    public void eat_item_speed() {
        speed = 4;
        time_start_boost = System.currentTimeMillis();
    }

    public void eat_item_nodead() {
        no_dead = true;
        time_start_no_dead = System.currentTimeMillis();
    }

    public void update(int tileSize, int SCALE, char[][] scene) {
        moving = false;
        if (right && isFreeR(x , y, scene)) {
            this.x += speed;
            moving = true;
        }
        if (left && isFreeL(x , y, scene)) {
            this.x -= speed;
            moving = true;
        }
        if (up && isFreeU(x, y , scene)) {
            this.y -= speed;
            moving = true;
        }
        if (down && isFreeD(x, y , scene)) {
            this.y += speed;
            moving = true;
        }

        if (moving) {
            sound_foot.play_sound();
            framePlayer++;
            if (framePlayer > intervalPlayer) {
                framePlayer = 0;
                indexAnimPlayer++;
                if (indexAnimPlayer > 7) {
                    indexAnimPlayer = 0;
                }
            }

            if (right) {
                img_player = common_view.sprite.playerAnimRight[indexAnimPlayer];
            } else if (left) {
                img_player = common_view.sprite.playerAnimLeft[indexAnimPlayer];
            } else if (up) {
                img_player = common_view.sprite.playerAnimUp[indexAnimPlayer];
            } else if (down) {
                img_player = common_view.sprite.playerAnimDown[indexAnimPlayer];
            } else {
                img_player = common_view.sprite.playerAnimDown[1];
            }
        } else {
            img_player = common_view.sprite.playerAnimDown[0];
            sound_foot.stop_sound();
        }
        if (System.currentTimeMillis() - common_view.bomber.time_start_boost > 4000) {
            common_view.bomber.speed = 2;
        }
        if (System.currentTimeMillis() - common_view.bomber.time_start_no_dead > 12000) {
            common_view.bomber.no_dead = false;
        }
    }

    public boolean isFreeR(int x, int y, char[][] scene) {
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (x + speed > scene[0].length * size) {
            return false;
        } else {
            if (x % size != 0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;

                if (scene[y1][x1 + 1] == ' ' || scene[y1][x1 + 1] == '9' || scene[y1][x1 + 1] == '1' || scene[y1][x1 + 1] == '2') {
                    if (scene[y1][x1 + 1] == '1') {
                        scene[y1][x1 + 1] = ' ';
                        common_view.has_item[y1][x1 + 1] = 0;
                        this.eat_item_speed();
                    }
                    if (scene[y1][x1 + 1] == '2') {
                        scene[y1][x1 + 1] = ' ';
                        common_view.has_item[y1][x1 + 1] = 0;
                        this.eat_item_nodead();
                    }
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size != 0 && x % size == 0) {
                int y1 = y / size;
                int x1 = x / size;
                if ((scene[y1][x1 + 1] == '1' || scene[y1][x1 + 1] == '2') && scene[y1 + 1][x1 + 1] == ' ') {
                    if (scene[y1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;

                    scene[y1][x1 + 1] = ' ';
                    common_view.has_item[y1][x1 + 1] = 0;
                }
                else if (scene[y1][x1 + 1] == ' ' && (scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2')) {
                    if (scene[y1 + 1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;

                    scene[y1 + 1][x1 + 1] = ' ';
                    common_view.has_item[y1 + 1][x1 + 1] = 0;
                }
                else if ((scene[y1][x1 + 1] == '1' || scene[y1][x1 + 1] == '2') && scene[y1 + 1][x1 + 1] == '9') {
                    if (scene[y1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;

                    scene[y1][x1 + 1] = ' ';
                    common_view.has_item[y1][x1 + 1] = 0;
                }
                else if (scene[y1][x1 + 1] == '9' && (scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2')) {
                    if (scene[y1 + 1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;

                    scene[y1 + 1][x1 + 1] = ' ';
                    common_view.has_item[y1 + 1][x1 + 1] = 0;
                }
                else if ((scene[y1][x1 + 1] == '1' || scene[y1][x1 + 1] == '2') && (scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2')) {
                    if (scene[y1 + 1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    if (scene[y1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;


                    scene[y1][x1 + 1] = ' ';
                    common_view.has_item[y1][x1 + 1] = 0;
                    scene[y1 + 1][x1 + 1] = ' ';
                    common_view.has_item[y1 + 1][x1 + 1] = 0;
                } else if (scene[y1][x1 + 1] == ' ' && scene[y1 + 1][x1 + 1] == ' ') {
                    a = true;
                } else if (scene[y1][x1 + 1] == ' ' && scene[y1 + 1][x1 + 1] == '9') {
                    a = true;
                } else if (scene[y1][x1 + 1] == '9' && scene[y1 + 1][x1 + 1] == ' ') {
                    a = true;
                } else if (scene[y1][x1 + 1] == '9' && scene[y1 + 1][x1 + 1] == '9') {
                    a = true;
                }
            }
        } return a;
    }

    public boolean isFreeL(int x, int y, char[][] scene) {
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (x - speed < size) {
            return false;
        } else {
            if (x % size != 0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;
                if (scene[y1][x1-1] == ' ' || scene[y1][x1-1] == '9' || scene[y1][x1-1] == '1' || scene[y1][x1-1] == '2') {
                    if (scene[y1][x1-1] == '1') {
                        scene[y1][x1-1] = ' ';
                        common_view.has_item[y1][x1 - 1] = 0;
                        this.speed = 4;
                        this.time_start_boost = System.currentTimeMillis();
                    }
                    if (scene[y1][x1-1] == '2') {
                        scene[y1][x1-1] = ' ';
                        common_view.has_item[y1][x1 - 1] = 0;
                        this.no_dead = true;
                        this.time_start_no_dead = System.currentTimeMillis();
                    }

                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size != 0 && x % size == 0) {
                int y1 = y / size;
                int x1 = x / size;
                if ((scene[y1][x1 - 1] == '1' || scene[y1][x1 - 1] == '2') && scene[y1 + 1][x1 - 1] == ' ') {
                    if (scene[y1][x1 - 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1][x1 - 1] == '2') {
                        this.eat_item_nodead();
                    }

                    a = true;
                    scene[y1][x1 - 1] = ' ';
                    common_view.has_item[y1][x1 - 1] = 0;
                }
                if (scene[y1][x1 - 1] == ' ' && (scene[y1 + 1][x1 - 1] == '1' || scene[y1 + 1][x1 - 1] == '2')) {
                    if (scene[y1 + 1][x1 - 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1 - 1] == '2') {
                        this.eat_item_nodead();
                    }

                    a = true;
                    scene[y1 + 1][x1 - 1] = ' ';
                    common_view.has_item[y1 + 1][x1 - 1] = 0;
                }
                if ((scene[y1][x1 - 1] == '1' || scene[y1][x1 - 1] == '2') && scene[y1 + 1][x1 - 1] == '9') {
                    if (scene[y1][x1 - 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1][x1 - 1] == '2') {
                        this.eat_item_nodead();
                    }

                    a = true;
                    scene[y1][x1 - 1] = ' ';
                    common_view.has_item[y1][x1 - 1] = 0;
                }
                if (scene[y1][x1 - 1] == '9' && (scene[y1 + 1][x1 - 1] == '1' || scene[y1 + 1][x1 - 1] == '2')) {
                    if (scene[y1 + 1][x1 - 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1 - 1] == '2') {
                        this.eat_item_nodead();
                    }

                    a = true;
                    scene[y1 + 1][x1 - 1] = ' ';
                    common_view.has_item[y1 + 1][x1 - 1] = 0;
                }
                if ((scene[y1][x1 - 1] == '1' || scene[y1][x1 - 1] == '2') && (scene[y1 + 1][x1 - 1] == '1' || scene[y1][x1 - 1] == '2')) {
                    if (scene[y1][x1 - 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1][x1 - 1] == '2') {
                        this.eat_item_nodead();
                    }
                    if (scene[y1 + 1][x1 - 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1 - 1] == '2') {
                        this.eat_item_nodead();
                    }


                    a = true;
                    scene[y1][x1 - 1] = ' ';
                    common_view.has_item[y1][x1 - 1] = 0;
                    scene[y1 + 1][x1 - 1] = ' ';
                    common_view.has_item[y1 + 1][x1 - 1] = 0;
                }
                if (scene[y1][x1 - 1] == ' ' && scene[y1 + 1][x1 - 1] == ' ') a = true;
                if (scene[y1][x1 - 1] == ' ' && scene[y1 + 1][x1 - 1] == '9') a = true;
                if (scene[y1][x1 - 1] == '9' && scene[y1 + 1][x1 - 1] == ' ') a = true;
                if (scene[y1][x1 - 1] == '9' && scene[y1 + 1][x1 - 1] == '9') a = true;
            }
        } return a;
    }

    public boolean isFreeU(int x, int y, char[][] scene) {
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (y - speed < size) {
            return false;
        } else {
            if (y % size != 0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;
                if (scene[y1 - 1][x1] == ' ' || scene[y1 - 1][x1] == '9' || scene[y1 - 1][x1] == '1' || scene[y1 - 1][x1] == '2') {
                    if (scene[y1 - 1][x1] == '1') {
                        scene[y1 - 1][x1] = ' ';
                        common_view.has_item[y1 - 1][x1] = 0;
                        this.speed = 4;
                        this.time_start_boost = System.currentTimeMillis();
                    }
                    if (scene[y1 - 1][x1] == '2') {
                        scene[y1 - 1][x1] = ' ';
                        common_view.has_item[y1 - 1][x1] = 0;
                        this.no_dead = true;
                        this.time_start_no_dead = System.currentTimeMillis();
                    }

                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size == 0 && x % size != 0) {
                int y1 = y / size;
                int x1 = x / size;
                if ((scene[y1 - 1][x1 + 1] == '1' || scene[y1 - 1][x1 + 1] == '2') && scene[y1 - 1][x1] == ' ' ) {
                    if (scene[y1 - 1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 - 1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }

                    a = true;
                    scene[y1 - 1][x1 + 1] = ' ';
                    common_view.has_item[y1 - 1][x1 + 1] = 0;
                }
                if (scene[y1 - 1][x1 + 1] == ' ' && (scene[y1 - 1][x1] == '1' || scene[y1 - 1][x1] == '2') ) {
                    if (scene[y1 - 1][x1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 - 1][x1] == '2') {
                        this.eat_item_nodead();
                    }

                    a = true;
                    scene[y1 - 1][x] = ' ';
                    common_view.has_item[y1 - 1][x1] = 0;
                }
                if ((scene[y1 - 1][x1 + 1] == '1' || scene[y1 - 1][x1 + 1] == '2') && scene[y1 - 1][x1] == '9' ) {
                    if (scene[y1 - 1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 - 1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }

                    a = true;
                    scene[y1 - 1][x1 + 1] = ' ';
                    common_view.has_item[y1 - 1][x1 + 1] = 0;
                }
                if (scene[y1 - 1][x1 + 1] == '9' && (scene[y1 - 1][x1] == '1' || scene[y1 - 1][x1] == '2')) {
                    if (scene[y1 - 1][x1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 - 1][x1] == '2') {
                        this.eat_item_nodead();
                    }

                    a = true;
                    scene[y1 - 1][x1] = ' ';
                    common_view.has_item[y1 - 1][x1] = 0;
                }
                if ((scene[y1 - 1][x1 + 1] == '1' || scene[y1 - 1][x1 + 1] == '2') && (scene[y1 - 1][x1] == '1' || scene[y1 - 1][x1] == '2') ) {
                    if (scene[y1 - 1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 - 1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    if (scene[y1 - 1][x1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 - 1][x1] == '2') {
                        this.eat_item_nodead();
                    }

                    a = true;
                    scene[y1 - 1][x1 + 1] = ' ';
                    common_view.has_item[y1 - 1][x1 + 1] = 0;
                    scene[y1 - 1][x1] = ' ';
                    common_view.has_item[y1 - 1][x1] = 0;
                }
                if (scene[y1 - 1][x1 + 1] == ' ' && scene[y1 - 1][x1] == ' ' ) a = true;
                if (scene[y1 - 1][x1 + 1] == ' ' && scene[y1 - 1][x1] == '9' ) a = true;
                if (scene[y1 - 1][x1 + 1] == '9' && scene[y1 - 1][x1] == ' ' ) a = true;
                if (scene[y1 - 1][x1 + 1] == '9' && scene[y1 - 1][x1] == '9' ) a = true;
            }
        } return a;
    }

    public boolean isFreeD(int x, int y, char[][] scene) {
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        if (y + speed > scene.length * size) {
            return false;
        } else {
            if (y % size != 0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;
                if (scene[y1 + 1][x1] == ' ' || scene[y1 + 1][x1] == '9' || scene[y1 + 1][x1] == '1' || scene[y1 + 1][x1] == '2') {
                    if (scene[y1 + 1][x1] == '1') {
                        scene[y1 + 1][x1] = ' ';
                        common_view.has_item[y1 + 1][x1] = 0;

                        this.speed = 4;
                        this.time_start_boost = System.currentTimeMillis();
                    }
                    if (scene[y1 + 1][x1] == '2') {
                        scene[y1 + 1][x1] = ' ';
                        common_view.has_item[y1 + 1][x1] = 0;

                        this.no_dead = true;
                        this.time_start_no_dead = System.currentTimeMillis();
                    }
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size == 0 && x % size != 0) {
                int y1 = y / size;
                int x1 = x / size;
                if ((scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2') && scene[y1 + 1][x1] == ' ') {
                    if (scene[y1 + 1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;
                    scene[y1 + 1][x1 + 1] = ' ';

                    common_view.has_item[y1 + 1][x1 + 1] = 0;
                }
                if (scene[y1 + 1][x1 + 1] == ' ' && (scene[y1 + 1][x1] == '1' || scene[y1 + 1][x1] == '2')) {
                    if (scene[y1 + 1][x1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;
                    scene[y1 + 1][x1] = ' ';

                    common_view.has_item[y1 + 1][x1] = 0;
                }
                if ((scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2') && scene[y1 + 1][x1] == '9' ) {
                    if (scene[y1 + 1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;
                    scene[y1 + 1][x1 + 1] = ' ';

                    common_view.has_item[y1 + 1][x1 + 1] = 0;
                }
                if (scene[y1 + 1][x1 + 1] == '9' && (scene[y1 + 1][x1] == '1' || scene[y1 + 1][x1] == '2') ) {
                    if (scene[y1 + 1][x1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;
                    scene[y1 + 1][x1] = ' ';

                    common_view.has_item[y1 + 1][x1] = 0;
                }
                if ((scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2') && (scene[y1 + 1][x1] == '1' || scene[y1 + 1][x1] == '2') ) {
                    if (scene[y1 + 1][x1 + 1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1 + 1] == '2') {
                        this.eat_item_nodead();
                    }
                    if (scene[y1 + 1][x1] == '1') {
                        this.eat_item_speed();
                    }
                    if (scene[y1 + 1][x1] == '2') {
                        this.eat_item_nodead();
                    }
                    a = true;
                    scene[y1 + 1][x1] = ' ';

                    common_view.has_item[y1 + 1][x1] = 0;
                    scene[y1 + 1][x1 + 1] = ' ';

                    common_view.has_item[y1 + 1][x1 + 1] = 0;
                }
                if (scene[y1 + 1][x1 + 1] == ' ' && scene[y1 + 1][x1] == ' ' ) a = true;
                if (scene[y1 + 1][x1 + 1] == ' ' && scene[y1 + 1][x1] == '9' ) a = true;
                if (scene[y1 + 1][x1 + 1] == '9' && scene[y1 + 1][x1] == ' ' ) a = true;
                if (scene[y1 + 1][x1 + 1] == '9' && scene[y1 + 1][x1] == '9' ) a = true;
            }
        } return a;
    }

    public BufferedImage getImg_player() {
        return img_player;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isNo_dead() {
        return no_dead;
    }
}

