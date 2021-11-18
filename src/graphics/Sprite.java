package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
    public static final int SCALED_SIZE = 3;

    private BufferedImage blockTile;
    private BufferedImage spriteSheet;
    private BufferedImage player;
    private BufferedImage grass;
    private BufferedImage brick;
    private BufferedImage wall;
    private BufferedImage coconut;
    private BufferedImage coconut_water;
    private BufferedImage strawberry;
    private BufferedImage banana;
    private BufferedImage soil;
    private BufferedImage mound;

    public BufferedImage getBlockTile() {
        return blockTile;
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public BufferedImage getPlayer() {
        return player;
    }

    public BufferedImage getGrass() {
        return grass;
    }

    public BufferedImage getBrick() {
        return brick;
    }

    public BufferedImage getWall() {
        return wall;
    }

    public BufferedImage getCoconut() {
        return coconut;
    }

    public BufferedImage getCoconut_water() {
        return coconut_water;
    }

    public BufferedImage getStrawberry() {
        return strawberry;
    }

    public BufferedImage getBanana() {
        return banana;
    }

    public BufferedImage getSoil() {
        return soil;
    }

    public BufferedImage getMound() {
        return mound;
    }

    public void load()
    {
        try {
            spriteSheet = ImageIO.read(getClass().getResource("/classic1.png"));
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
