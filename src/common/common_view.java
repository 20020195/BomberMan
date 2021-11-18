package common;

import java.awt.image.BufferedImage;

public class common_view {
    public static final int TILESIZE = 16;
    public static final int ROWS = 16;
    public static final int COLUMNS = 22;

    public static final int SCALE = 3;
    public static final int WIDTH = (TILESIZE * SCALE) * COLUMNS;
    public static final int HEIGHT = (TILESIZE * SCALE) * ROWS;

    public static BufferedImage view = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);;

    public static boolean right = false, left = false, up = false, down = false;
}
