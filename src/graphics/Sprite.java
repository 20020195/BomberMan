package graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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

    public BufferedImage[] bombAnim;
    public BufferedImage[] fontExplosion;
    public BufferedImage[] rightExplosion;
    public BufferedImage[] leftExplosion;
    public BufferedImage[] upExplosion;
    public BufferedImage[] downExplosion;

    public BufferedImage[] item1;


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
            common_view.view_menu = ImageIO.read(getClass().getResource("/menu.png"));
            common_view.view_how_to_play = ImageIO.read(getClass().getResource("/how.png"));
            common_view.view_choose_map = ImageIO.read(getClass().getResource("/chooseLevel.png"));
            common_view.view_pause = ImageIO.read(getClass().getResource("/pause.png"));
            common_view.view_game_over = ImageIO.read(getClass().getResource("/lose.png"));
            spriteSheet = ImageIO.read(getClass().getResource("/classic1.png"));

            playerAnimUp = new BufferedImage[8];
            playerAnimDown = new BufferedImage[8];
            playerAnimRight = new BufferedImage[8];
            playerAnimLeft = new BufferedImage[8];
            bombAnim = new BufferedImage[3];

            fontExplosion = new BufferedImage[3];
            upExplosion = new BufferedImage[3];
            downExplosion = new BufferedImage[3];
            leftExplosion = new BufferedImage[3];
            rightExplosion = new BufferedImage[3];

            item1 = new BufferedImage[6];

            for (int i = 0; i < 3; i++) {
                item1[i] = spriteSheet.getSubimage(9 * 16, i * 16, 16, 16);
                item1[i + 3] = spriteSheet.getSubimage(10 * 16, i * 16, 16, 16);
            }

            for (int i = 0; i < 8; i++) {
                playerAnimLeft[i] = spriteSheet.getSubimage((i + 8) * 16, 13 * 16, 16, 16);
                playerAnimRight[i] = spriteSheet.getSubimage((i + 8) * 16, 12 * 16, 16, 16);
                playerAnimUp[i] = spriteSheet.getSubimage((i + 8) * 16, 10 * 16, 16, 16);
                playerAnimDown[i] = spriteSheet.getSubimage((i + 8) * 16, 11 * 16, 16, 16);
            }


            for (int i = 0; i < 3; i++) {
                bombAnim[i] = spriteSheet.getSubimage(i * 16, 3 * 16, 16, 16);
                fontExplosion[i] = spriteSheet.getSubimage(0, (i + 4) * 16, 16, 16);
                upExplosion[i] = spriteSheet.getSubimage((i + 1) * 16, 4 * 16, 16, 16);
                downExplosion[i] = spriteSheet.getSubimage((i + 1) * 16, 6 * 16, 16, 16);
                leftExplosion[i] = spriteSheet.getSubimage(0, (i + 7) * 16, 16, 16);
                rightExplosion[i] = spriteSheet.getSubimage(2 * 16, (i + 7) * 16, 16, 16);

            }


            blockTile = spriteSheet.getSubimage(5 * 16, 14 * 16, 16, 16);
            grass = spriteSheet.getSubimage(6 * 16, 1 * 16, 16, 16);
            brick = spriteSheet.getSubimage(7 * 16, 0 * 16, 16, 16);
            wall = spriteSheet.getSubimage(5 * 16, 0 * 16, 16, 16);
            coconut = spriteSheet.getSubimage(5 * 16, 14 * 16, 16, 16);
            coconut_water = spriteSheet.getSubimage(6 * 16, 14 * 16, 16, 16);
            strawberry = spriteSheet.getSubimage(3 * 16, 14 * 16, 16, 16);
            banana = spriteSheet.getSubimage(4 * 16, 14 * 16, 16, 16);
            soil = spriteSheet.getSubimage(5 * 16, 1 * 16, 16, 16);
            mound = spriteSheet.getSubimage(1 * 16, 13 * 16, 16, 16);
            player = spriteSheet.getSubimage(8 * 16, 11 * 16, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
