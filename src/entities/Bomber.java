package entities;

import common.*;
import sound.SoundEffect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomber extends Entity {
    protected int speed = 2;

    protected boolean moving = false;
    protected boolean right = false;
    protected boolean left = false;
    protected boolean up = false;
    protected boolean down = false;
    protected boolean die = false;

    protected boolean no_dead = false;
    protected long time_start_boost;
    protected long time_start_no_dead;

    protected SoundEffect sound_foot;
    protected SoundEffect sound_item_get;

    public Bomber(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        sound_foot = new SoundEffect("res/audio/sound_foot.wav");
        sound_item_get = new SoundEffect("res/audio/item_get.wav");
        interval = 5;
    }

    public void update() {
        if (collisionEnemy()) {
            common_view.bomber.die = true;
            common_view.menu = false;
            common_view.bomber.setDie(true);
            common_view.bomber.moving = false;
            common_view.sound_game.stop_sound();
            common_view.sound_game_over.is_play_music = false;
            common_view.sound_game_over.play_sound();
        } else {
            moving = false;
            if (right && isFreeR(x, y)) {
                this.x += speed;
                moving = true;
            }
            if (left && isFreeL(x, y)) {
                this.x -= speed;
                moving = true;
            }
            if (up && isFreeU(x, y)) {
                this.y -= speed;
                moving = true;
            }
            if (down && isFreeD(x, y)) {
                this.y += speed;
                moving = true;
            }

            if (moving) {
                sound_foot.play_sound();
                sound_foot.loop();
                frame++;
                if (frame > interval) {
                    frame = 0;
                    indexAnim++;
                    if (indexAnim > 2) {
                        indexAnim = 0;
                    }
                }

                if (right) {
                    image = common_view.sprite.playerAnimRight[indexAnim];
                } else if (left) {
                    image = common_view.sprite.playerAnimLeft[indexAnim];
                } else if (up) {
                    image = common_view.sprite.playerAnimUp[indexAnim];
                } else if (down) {
                    image = common_view.sprite.playerAnimDown[indexAnim];
                }
            } else {
                image = common_view.sprite.playerAnimDown[0];
                sound_foot.stop_sound();
            }

            if (System.currentTimeMillis() - common_view.bomber.time_start_boost > 4000) {
                common_view.bomber.speed = 2;
            }
            if (System.currentTimeMillis() - common_view.bomber.time_start_no_dead > 12000000) {
                common_view.bomber.no_dead = false;
            }

            if (isDie()) {
                interval = 12;
                if (indexAnim < 3) {
                    image = common_view.sprite.playerAnimDead[indexAnim];
                    frame++;
                    if (frame > interval) {
                        frame = 0;
                        indexAnim++;
                    }
                } else {
                    common_view.is_playing = false;
                    common_view.game_over = true;
                }
            }
        }
        if (common_view.enemies.isEmpty()) {
            if (common_view.bomber.getX() == 20 * common_view.size) {
                if (common_view.bomber.getY() == 14 * common_view.size) {
                    common_view.win_game = true;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, BufferedImage image, int x, int y) {
        g2.drawImage(this.getImage(), this.getX(), this.getY(), common_view.size, common_view.size, null);
    }

    public void handle_eat_item(int x, int y) {
        char[][] scene = common_view.scene;
        if (scene[y][x] == '1') {
            speed = 4;
            time_start_boost = System.currentTimeMillis();
        }
        if (scene[y][x] == '2') {
            no_dead = true;
            time_start_no_dead = System.currentTimeMillis();
        }

        scene[y][x] = ' ';
        common_view.has_item[y][x] = 0;
        for (int i = 0; i < common_view.items.size(); i++) {
            if (x * common_view.size == common_view.items.get(i).getX()) {
                if (y * common_view.size == common_view.items.get(i).getY()) {
                    common_view.items.remove(i);
                    break;
                }
            }
        }
    }

    public boolean collisionEnemy() {
        int size = common_view.TILESIZE * common_view.SCALE;
        int x1 = common_view.bomber.getX();
        int y1 = common_view.bomber.getY();
        for (int i = 0; i < common_view.enemies.size(); i++) {
            int x2 = common_view.enemies.get(i).getX();
            int y2 = common_view.enemies.get(i).getY();
            if ((x1 + size > x2) && (x2 + size > x1) && (y1 + size > y2) && (y2 + size > y1)) {
                if (common_view.bomber.isNo_dead()) {
                    common_view.enemies.get(i).setDie(true);
                    return false;
                } else {
                    common_view.bomber.moving = false;
                    common_view.enemies.get(i).setDie(true);
                }
                return true;
            }
        }
        return false;
    }

    public boolean isFreeR(int x, int y) {
        char[][] scene = common_view.scene;
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        for (int i = 0; i < common_view.bombs.size(); i++) {
            if (x + size + speed > common_view.bombs.get(i).getX() && x < common_view.bombs.get(i).getX() &&
                    y > common_view.bombs.get(i).getY() - size && y > common_view.bombs.get(i).getY() + size) {
                return false;
            }
        }
        if (x + speed > scene[0].length * size) {
            return false;
        } else {
            if (x % size != 0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;

                if (scene[y1][x1 + 1] == ' '  || scene[y1][x1 + 1] == '1' || scene[y1][x1 + 1] == '2') {
                    handle_eat_item(x1 + 1, y1);
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size != 0 && x % size == 0) {
                int y1 = y / size;
                int x1 = x / size;
                if ((scene[y1][x1 + 1] == '1' || scene[y1][x1 + 1] == '2') && scene[y1 + 1][x1 + 1] == ' ') {
                    handle_eat_item(x1 + 1, y1);
                    a = true;
                } else if (scene[y1][x1 + 1] == ' ' && (scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2')) {
                    handle_eat_item(x1 + 1, y1 + 1);
                    a = true;
                } else if (scene[y1][x1 + 1] == '1' || scene[y1][x1 + 1] == '2') {
                    handle_eat_item(x1 + 1, y1);
                    a = true;
                } else if (scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2') {
                    handle_eat_item(x1 + 1, y1 + 1);
                    a = true;
                } else if ((scene[y1][x1 + 1] == '1' || scene[y1][x1 + 1] == '2') && (scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2')) {
                    handle_eat_item(x1 + 1, y1 + 1);
                    handle_eat_item(x1 + 1, y1);
                    a = true;
                } else if (scene[y1][x1 + 1] == ' ' && scene[y1 + 1][x1 + 1] == ' ') {
                    a = true;
                }
            }
        } return a;
    }

    public boolean isFreeL(int x, int y) {
        char[][] scene = common_view.scene;
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        for (int i = 0; i < common_view.bombs.size(); i++) {
            if (x - speed < common_view.bombs.get(i).getX()+size && x > common_view.bombs.get(i).getX() &&
                    y > common_view.bombs.get(i).getY() - size && y > common_view.bombs.get(i).getY() + size) {
                return false;
            }
        }
        if (x - speed < size) {
            return false;
        } else {
            if (x % size != 0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;
                if (scene[y1][x1-1] == ' ' || scene[y1][x1-1] == '1' || scene[y1][x1-1] == '2') {
                    handle_eat_item(x1 - 1, y1);
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size != 0 && x % size == 0) {
                int y1 = y / size;
                int x1 = x / size;
                if ((scene[y1][x1 - 1] == '1' || scene[y1][x1 - 1] == '2') && scene[y1 + 1][x1 - 1] == ' ') {
                    handle_eat_item(x1 - 1, y1);
                    a = true;
                }
                if (scene[y1][x1 - 1] == ' ' && (scene[y1 + 1][x1 - 1] == '1' || scene[y1 + 1][x1 - 1] == '2')) {
                    handle_eat_item(x1 - 1, y1 + 1);
                    a = true;
                }
                if (scene[y1][x1 - 1] == '1' || scene[y1][x1 - 1] == '2') {
                    handle_eat_item(x1 - 1, y1);
                    a = true;
                }
                if (scene[y1 + 1][x1 - 1] == '1' || scene[y1 + 1][x1 - 1] == '2') {
                    handle_eat_item(x1 - 1, y1 + 1);
                    a = true;
                }
                if ((scene[y1][x1 - 1] == '1' || scene[y1][x1 - 1] == '2') && (scene[y1 + 1][x1 - 1] == '1' || scene[y1][x1 - 1] == '2')) {
                    handle_eat_item(x1 - 1, y1);
                    handle_eat_item(x1 - 1, y1 + 1);
                    a = true;
                }
                if (scene[y1][x1 - 1] == ' ' && scene[y1 + 1][x1 - 1] == ' ') {
                    a = true;
                }
            }
        } return a;
    }

    public boolean isFreeU(int x, int y) {
        char[][] scene = common_view.scene;
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        for (int i = 0; i < common_view.bombs.size(); i++) {
            if (y - speed < common_view.bombs.get(i).getY() + size && y > common_view.bombs.get(i).getY() &&
                    x > common_view.bombs.get(i).getX() - size && x > common_view.bombs.get(i).getX() + size) {
                return false;
            }
        }
        if (y - speed < size) {
            return false;
        } else {
            if (y % size != 0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;
                if (scene[y1 - 1][x1] == ' ' || scene[y1 - 1][x1] == '1' || scene[y1 - 1][x1] == '2') {
                    handle_eat_item(x1, y1 - 1);
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size == 0 && x % size != 0) {
                int y1 = y / size;
                int x1 = x / size;
                if ((scene[y1 - 1][x1 + 1] == '1' || scene[y1 - 1][x1 + 1] == '2') && scene[y1 - 1][x1] == ' ') {
                    handle_eat_item(x1 + 1, y1 - 1);
                    a = true;
                }
                if (scene[y1 - 1][x1 + 1] == ' ' && (scene[y1 - 1][x1] == '1' || scene[y1 - 1][x1] == '2')) {
                    handle_eat_item(x1, y1 - 1);
                    a = true;
                }
                if (scene[y1 - 1][x1 + 1] == '1' || scene[y1 - 1][x1 + 1] == '2') {
                    handle_eat_item(x1, y1 - 1);
                    a = true;
                }
                if (scene[y1 - 1][x1] == '1' || scene[y1 - 1][x1] == '2') {
                    handle_eat_item(x1, y1 - 1);
                    a = true;
                }
                if ((scene[y1 - 1][x1 + 1] == '1' || scene[y1 - 1][x1 + 1] == '2') && (scene[y1 - 1][x1] == '1' || scene[y1 - 1][x1] == '2') ) {
                    handle_eat_item(x1 + 1, y1 - 1);
                    handle_eat_item(x1, y1 - 1);
                    a = true;
                }
                if (scene[y1 - 1][x1 + 1] == ' ' && scene[y1 - 1][x1] == ' ' ) {
                    a = true;
                }
            }
        } return a;
    }

    public boolean isFreeD(int x, int y) {
        char[][] scene = common_view.scene;
        boolean a = false;
        int size = common_view.TILESIZE * common_view.SCALE;
        for (int i = 0; i < common_view.bombs.size(); i++) {
            if (y + speed > common_view.bombs.get(i).getY() + size && y < common_view.bombs.get(i).getY() &&
                    x > common_view.bombs.get(i).getX() - size && x > common_view.bombs.get(i).getX() + size) {
                return false;
            }
        }
        if (y + speed > scene.length * size) {
            return false;
        } else {
            if (y % size != 0) {
                a = true;
            } else if (x % size == 0 && y % size == 0) {
                int x1 = x / size;
                int y1 = y / size;
                if (scene[y1 + 1][x1] == ' ' || scene[y1 + 1][x1] == '1' || scene[y1 + 1][x1] == '2') {
                    handle_eat_item(x1, y1 + 1);
                    a = true;
                } else {
                    a = false;
                }
            } else if (y % size == 0 && x % size != 0) {
                int y1 = y / size;
                int x1 = x / size;
                if ((scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2') && scene[y1 + 1][x1] == ' ') {
                    handle_eat_item(x1 + 1, y1 + 1);
                    a = true;
                }
                if (scene[y1 + 1][x1 + 1] == ' ' && (scene[y1 + 1][x1] == '1' || scene[y1 + 1][x1] == '2')) {
                    handle_eat_item(x1, y1 + 1);
                    a = true;
                }
                if (scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2') {
                    handle_eat_item(x1 + 1, y1 + 1);
                    a = true;
                }
                if (scene[y1 + 1][x1] == '1' || scene[y1 + 1][x1] == '2') {
                    handle_eat_item(x1, y1 + 1);
                    a = true;
                }
                if ((scene[y1 + 1][x1 + 1] == '1' || scene[y1 + 1][x1 + 1] == '2') && (scene[y1 + 1][x1] == '1' || scene[y1 + 1][x1] == '2')) {
                    handle_eat_item(x1 + 1, y1 + 1);
                    handle_eat_item(x1, y1 + 1);
                    a = true;
                }
                if (scene[y1 + 1][x1 + 1] == ' ' && scene[y1 + 1][x1] == ' ' ) {
                    a = true;
                }
            }
        } return a;
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

    public boolean isDie() {
        return die;
    }

    public void setDie(boolean die) {
        this.die = die;
    }
}

