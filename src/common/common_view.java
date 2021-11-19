package common;

import entities.Bomb;
import entities.Bomber;
import graphics.Sprite;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Queue;

public class common_view {
    public static JFrame w = new JFrame("Bomberman");

    public static JButton button = new JButton("Ok");

    public static final int TILESIZE = 16;
    public static final int ROWS = 16;
    public static final int COLUMNS = 22;

    public static final int SCALE = 3;
    public static final int WIDTH = (TILESIZE * SCALE) * COLUMNS;
    public static final int HEIGHT = (TILESIZE * SCALE) * ROWS;

    public static BufferedImage view = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    public static char[][] scene = new char[16][22];

    public static boolean right = false, left = false, up = false, down = false;

    public static Bomber bomber = new Bomber(common_view.TILESIZE, common_view.TILESIZE);
    public static Sprite sprite = new Sprite();

    public static ArrayList<Bomb> bombs = new ArrayList<Bomb>();
}
