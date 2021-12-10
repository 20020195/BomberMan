package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import common.*;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
    public BufferedImage player;
    public BufferedImage[] playerAnimUp;
    public BufferedImage[] playerAnimDown;
    public BufferedImage[] playerAnimRight;
    public BufferedImage[] playerAnimLeft;
    public BufferedImage[] playerAnimDead;

    public BufferedImage enemy;
    public BufferedImage[] enemyAnimUp;
    public BufferedImage[] enemyAnimDown;
    public BufferedImage[] enemyAnimRight;
    public BufferedImage[] enemyAnimLeft;
    public BufferedImage[] enemyAnimDead;

    public BufferedImage[] bombAnim;
    public BufferedImage[] fontExplosion;
    public BufferedImage[] rightExplosion;
    public BufferedImage[] leftExplosion;
    public BufferedImage[] upExplosion;
    public BufferedImage[] downExplosion;

    public BufferedImage[] item_speed;
    public BufferedImage[] item_deadth;

    public BufferedImage blockTile;
    public BufferedImage spriteSheet;
    public BufferedImage grass;
    public BufferedImage brick;
    public BufferedImage wall;
    public BufferedImage coconut;
    public BufferedImage coconut_water;
    public BufferedImage strawberry;
    public BufferedImage banana;
    public BufferedImage soil;
    public BufferedImage mound;
    public BufferedImage portal;

    public void load()
    {
        try {
            common_view.view_menu = ImageIO.read(new File("res/textures/menu.png"));
            common_view.view_how_to_play = ImageIO.read(new File("res/textures/how_to_play.png"));
            common_view.view_choose_map = ImageIO.read(new File("res/textures/choose_level.png"));
            common_view.view_pause = ImageIO.read(new File("res/textures/pause.png"));
            common_view.view_game_over = ImageIO.read(new File("res/textures/game_over.png"));
            spriteSheet = ImageIO.read(new File("res/textures/classic.png"));

            int size = common_view.TILESIZE;

            player = spriteSheet.getSubimage(8 * size, 11 * size, size, size);
            playerAnimUp = new BufferedImage[3];
            playerAnimDown = new BufferedImage[3];
            playerAnimRight = new BufferedImage[3];
            playerAnimLeft = new BufferedImage[3];
            playerAnimDead = new BufferedImage[3];

            enemy = spriteSheet.getSubimage(2 * size, 0, size, size);
            enemyAnimUp = new BufferedImage[3];
            enemyAnimDown = new BufferedImage[3];
            enemyAnimRight = new BufferedImage[3];
            enemyAnimLeft = new BufferedImage[3];
            enemyAnimDead = new BufferedImage[3];

            bombAnim = new BufferedImage[3];
            fontExplosion = new BufferedImage[3];
            upExplosion = new BufferedImage[3];
            downExplosion = new BufferedImage[3];
            leftExplosion = new BufferedImage[3];
            rightExplosion = new BufferedImage[3];

            item_speed = new BufferedImage[2];
            item_deadth = new BufferedImage[6];

            for (int i = 0; i < 3; i++) {
                playerAnimLeft[i] = spriteSheet.getSubimage((i + 12) * size, 7 * size, size, size);
                playerAnimRight[i] = spriteSheet.getSubimage(15 * size, (i + 5) * size, size, size);
                playerAnimUp[i] = spriteSheet.getSubimage((i + 12) * size, 6 * size, size, size);
                playerAnimDown[i] = spriteSheet.getSubimage((i + 12) * size, 5 * size, size, size);
                playerAnimDead[i] = spriteSheet.getSubimage((i + 12) * size, 8 * size, size, size);

                enemyAnimLeft[i] = spriteSheet.getSubimage(3 * size, i * size, size, size);
                enemyAnimRight[i] = spriteSheet.getSubimage(1 * size, i * size, size, size);
                enemyAnimUp[i] = spriteSheet.getSubimage(0 * size, i * size, size, size);
                enemyAnimDown[i] = spriteSheet.getSubimage(2 * size, i * size, size, size);
                enemyAnimDead[i] = spriteSheet.getSubimage((i + 4) * size, 2 * size, size, size);

                bombAnim[i] = spriteSheet.getSubimage(i * size, 3 * size, size, size);
                fontExplosion[i] = spriteSheet.getSubimage(0, (i + 4) * size, size, size);
                upExplosion[i] = spriteSheet.getSubimage((i + 1) * size, 4 * size, size, size);
                downExplosion[i] = spriteSheet.getSubimage((i + 1) * size, 6 * size, size, size);
                leftExplosion[i] = spriteSheet.getSubimage(0, (i + 7) * size, size, size);
                rightExplosion[i] = spriteSheet.getSubimage(2 * size, (i + 7) * size, size, size);

                item_deadth[i] = spriteSheet.getSubimage(10 * size, (i + 5) * size, size, size);
                item_deadth[i + 3] = spriteSheet.getSubimage(11 * size, (i + 5) * size, size, size);
            }

            item_speed[0] = spriteSheet.getSubimage(0 * size, 15 * size, size, size);
            item_speed[1] = spriteSheet.getSubimage(1 * size, 15 * size, size, size);

            blockTile = spriteSheet.getSubimage(5 * size, 14 * size, size, size);
            grass = spriteSheet.getSubimage(6 * size, size, size, size);
            brick = spriteSheet.getSubimage(7 * size, 0, size, size);
            wall = spriteSheet.getSubimage(5 * size, 0, size, size);
            coconut = spriteSheet.getSubimage(5 * size, 14 * size, size, size);
            coconut_water = spriteSheet.getSubimage(6 * size, 14 * size, size, size);
            strawberry = spriteSheet.getSubimage(3 * size, 14 * size, size, size);
            banana = spriteSheet.getSubimage(4 * size, 14 * size, size, size);
            soil = spriteSheet.getSubimage(4 * size, size, size, size);
            mound = spriteSheet.getSubimage(size, 13 * size, size, size);
            portal = spriteSheet.getSubimage( 4 * size, 0, size, size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
