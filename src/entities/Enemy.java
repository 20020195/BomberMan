package entities;

import common.*;
import sound.SoundEffect;

public class Enemy extends Bomber {
    private int steps = 0;
    private int random = 0;

    public Enemy(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        sound_foot = new SoundEffect("res/audio/sound_foot.wav");
    }

    public void update() {
        if (isDie()) {
            interval = 8;
            if (indexAnim < 3) {
                image = common_view.sprite.enemyAnimDead[indexAnim];
                frame++;
                if (frame > interval) {
                    frame = 0;
                    indexAnim++;
                }
            } else {
                common_view.enemies.remove(this);
            }
        } else {
            this.moving = false;
            if (steps <= 0) {
                random = (int) (Math.random() * 5);
                steps = 64;
            }
            if (random == 0) {
                right = true;
                image = common_view.sprite.enemyAnimRight[this.indexAnim];
                if (isFreeR(x, y)) {
                    this.x += 1;
                    this.moving = true;
                    steps -= speed;
                }
                if (!isFreeR(x, y)) {
                    this.moving = false;
                    steps = 0;
                }
            } else if (random == 1) {
                left = true;
                image = common_view.sprite.enemyAnimLeft[this.indexAnim];
                if (isFreeL(x, y)) {
                    this.x -= 1;
                    this.moving = true;
                    steps -= speed;
                }
                if (!isFreeL(x, y)) {
                    this.moving = false;
                    steps = 0;
                }
            } else if (random == 2) {
                up = true;
                image = common_view.sprite.enemyAnimUp[this.indexAnim];
                if (isFreeU(x, y)) {
                    this.y -= 1;
                    this.moving = true;
                    steps -= speed;
                }
                if (!isFreeU(x, y)) {
                    this.moving = false;
                    steps = 0;
                }
            } else if (random == 3) {
                down = true;
                image = common_view.sprite.enemyAnimDown[this.indexAnim];
                if (isFreeD(x, y)) {
                    this.y += 1;
                    this.moving = true;
                    steps -= speed;
                }
                if (!isFreeD(x, y)) {
                    this.moving = false;
                    steps = 0;
                }
            } else if (random == 4) {
                this.moving = false;
                steps = 0;
                image = common_view.sprite.enemyAnimDown[0];
                random = (int) (Math.random() * 5);
            }

            if (this.moving) {
                this.frame++;
                if (this.frame > this.interval) {
                    this.frame = 0;
                    this.indexAnim++;
                    if (this.indexAnim > 2) {
                        this.indexAnim = 0;
                    }
                }
            }
        }
    }
}
