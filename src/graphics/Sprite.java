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

    public BufferedImage enemy;
    public BufferedImage[] enemyAnimUp;
    public BufferedImage[] enemyAnimDown;
    public BufferedImage[] enemyAnimRight;
    public BufferedImage[] enemyAnimLeft;

    public BufferedImage[] bombAnim;
    public BufferedImage[] fontExplosion;
    public BufferedImage[] rightExplosion;
    public BufferedImage[] leftExplosion;
    public BufferedImage[] upExplosion;
    public BufferedImage[] downExplosion;
    public BufferedImage[] rightExplosion_;
    public BufferedImage[] leftExplosion_;
    public BufferedImage[] upExplosion_;
    public BufferedImage[] downExplosion_;

    public BufferedImage[] item1;
    public BufferedImage[] item2;


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
            playerAnimUp = new BufferedImage[8];
            playerAnimDown = new BufferedImage[8];
            playerAnimRight = new BufferedImage[8];
            playerAnimLeft = new BufferedImage[8];

            enemy = spriteSheet.getSubimage(2 * size, 0, size, size);
            enemyAnimUp = new BufferedImage[3];
            enemyAnimDown = new BufferedImage[3];
            enemyAnimRight = new BufferedImage[3];
            enemyAnimLeft = new BufferedImage[3];

            bombAnim = new BufferedImage[3];
            fontExplosion = new BufferedImage[3];
            upExplosion = new BufferedImage[3];
            downExplosion = new BufferedImage[3];
            leftExplosion = new BufferedImage[3];
            rightExplosion = new BufferedImage[3];

            upExplosion_ = new BufferedImage[3];
            downExplosion_ = new BufferedImage[3];
            leftExplosion_ = new BufferedImage[3];
            rightExplosion_ = new BufferedImage[3];

            item1 = new BufferedImage[6];
            item2 = new BufferedImage[6];

            for (int i = 0; i < 8; i++) {
                playerAnimLeft[i] = spriteSheet.getSubimage((i + 8) * size, 13 * size, size, size);
                playerAnimRight[i] = spriteSheet.getSubimage((i + 8) * size, 12 * size, size, size);
                playerAnimUp[i] = spriteSheet.getSubimage((i + 8) * size, 10 * size, size, size);
                playerAnimDown[i] = spriteSheet.getSubimage((i + 8) * size, 11 * size, size, size);
            }

            for (int i = 0; i < 3; i++) {
                enemyAnimLeft[i] = spriteSheet.getSubimage(3 * size, i * size, size, size);
                enemyAnimRight[i] = spriteSheet.getSubimage(1 * size, i * size, size, size);
                enemyAnimUp[i] = spriteSheet.getSubimage(0 * size, i * size, size, size);
                enemyAnimDown[i] = spriteSheet.getSubimage(2 * size, i * size, size, size);

                bombAnim[i] = spriteSheet.getSubimage(i * size, 3 * size, size, size);
                fontExplosion[i] = spriteSheet.getSubimage(0, (i + 4) * size, size, size);
                upExplosion[i] = spriteSheet.getSubimage((i + 1) * size, 4 * size, size, size);
                downExplosion[i] = spriteSheet.getSubimage((i + 1) * size, 6 * size, size, size);
                leftExplosion[i] = spriteSheet.getSubimage(0, (i + 7) * size, size, size);
                rightExplosion[i] = spriteSheet.getSubimage(2 * size, (i + 7) * size, size, size);
                upExplosion_[i] = spriteSheet.getSubimage((i + 1) * size, 4 * size, size, size);
                downExplosion_[i] = spriteSheet.getSubimage((i + 1) * size, 6 * size, size, size);
                leftExplosion_[i] = spriteSheet.getSubimage(0, (i + 7) * size, size, size);
                rightExplosion_[i] = spriteSheet.getSubimage(2 * size, (i + 7) * size, size, size);

                item1[i] = spriteSheet.getSubimage(11 * size, i * size, size, size);
                item1[i + 3] = spriteSheet.getSubimage(12 * size, i * size, size, size);
                item2[i] = spriteSheet.getSubimage(10 * size, (i + 5) * size, size, size);
                item2[i + 3] = spriteSheet.getSubimage(11 * size, (i + 5) * size, size, size);
            }

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
