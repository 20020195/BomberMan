package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
    public static final int SCALED_SIZE = 3;

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
            spriteSheet = ImageIO.read(getClass().getResource("/classic1.png"));
            playerAnimUp = new BufferedImage[3];
            playerAnimDown = new BufferedImage[3];
            playerAnimRight = new BufferedImage[3];
            playerAnimLeft = new BufferedImage[3];
            bombAnim = new BufferedImage[3];

            fontExplosion = new BufferedImage[3];
            upExplosion = new BufferedImage[3];
            downExplosion = new BufferedImage[3];
            leftExplosion = new BufferedImage[3];
            rightExplosion = new BufferedImage[3];


            for (int i = 0; i < 3; i++) {
                playerAnimLeft[i] = spriteSheet.getSubimage(3 * 16, i * 16, 16, 16);
                playerAnimRight[i] = spriteSheet.getSubimage(1 * 16, i * 16, 16, 16);
                playerAnimUp[i] = spriteSheet.getSubimage(0, i * 16, 16, 16);
                playerAnimDown[i] = spriteSheet.getSubimage(2 * 16, i * 16, 16, 16);
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
            coconut = spriteSheet.getSubimage(5 * 16, 14 * 16, 14, 14);
            coconut_water = spriteSheet.getSubimage(6 * 16, 14 * 16, 14, 14);
            strawberry = spriteSheet.getSubimage(3 * 16, 14 * 16, 14, 14);
            banana = spriteSheet.getSubimage(4 * 16, 14 * 16, 14, 14);
            soil = spriteSheet.getSubimage(5 * 16, 1 * 16, 14, 14);
            mound = spriteSheet.getSubimage(1 * 16, 13 * 16, 14, 14);
            player = spriteSheet.getSubimage(2 * 16, 0 * 16, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
