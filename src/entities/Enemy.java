package entities;

import common.*;
import sound.SoundEffect;

import java.awt.image.BufferedImage;

public class Enemy extends Bomber {
    private BufferedImage img_enemy;

    private int steps = 0;
    private int random = 0;

    public Enemy(int xUnit, int yUnit) {
        super(xUnit, yUnit);
        sound_foot = new SoundEffect("res/audio/sound_foot.wav");
    }

    public void update1(int tileSize, int SCALE, char[][] scene) {
        this.moving = false;

        if (steps <= 0) {
            random = (int) (Math.random() * 5);
            steps = 64;
        }
        if (random == 0) {
            right = true;
            img_enemy = common_view.sprite.enemyAnimRight[this.indexAnimPlayer];
            if(isFreeR(x, y, scene)) {
                this.x += 1;
                this.moving = true;
                steps -= speed;
            }
            if (!isFreeR(x, y, scene)) {
                this.moving = false;
                steps = 0;
            }
        }
        else if (random == 1) {
            left = true;
            img_enemy = common_view.sprite.enemyAnimLeft[this.indexAnimPlayer];
            if (isFreeL(x, y, scene)) {
                this.x -= 1;
                this.moving = true;
                steps -= speed;
            }
            if (!isFreeL(x, y, scene)) {
                this.moving = false;
                steps = 0;
            }
        }
        if (random == 2) {
            up = true;
            img_enemy = common_view.sprite.enemyAnimUp[this.indexAnimPlayer];
            if (isFreeU(x, y, scene)) {
                this.y -= 1;
                this.moving = true;
                steps -= speed;
            }
            if (!isFreeU(x, y, scene)) {
                this.moving = false;
                steps =0;
            }
        } else if (random == 3) {
            down = true;
            img_enemy = common_view.sprite.enemyAnimDown[this.indexAnimPlayer];
            if (isFreeD(x, y, scene)) {
                this.y += 1;
                this.moving = true;
                steps -= speed;
            }
            if (!isFreeD(x, y, scene)) {
                this.moving = false;
                steps =0;
            }
        } else if (random == 4) {
            this.moving = false;
            steps = 0;
            img_enemy = common_view.sprite.enemyAnimDown[0];
            random = (int) (Math.random() * 5);
        }

        if (this.moving) {
            this.framePlayer++;
            if (this.framePlayer > this.intervalPlayer) {
                this.framePlayer = 0;
                this.indexAnimPlayer++;
                if (this.indexAnimPlayer > 2) {
                    this.indexAnimPlayer = 0;
                }
            }
        }
    }

    public BufferedImage getImg_enemy() {
        return img_enemy;
    }
}
