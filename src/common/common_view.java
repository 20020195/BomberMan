package common;

import entities.Bomb;
import entities.Bomber;
import entities.Enemy;
import entities.Item;
import graphics.Sprite;
import sound.SoundEffect;

import javax.swing.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

public class common_view {
    public static JFrame w = new JFrame("Bomberman");

    public static final int TILESIZE = 16;
    public static final int ROWS = 16;
    public static final int COLUMNS = 22;

    public static final int SCALE = 2;
    public static final int WIDTH = (TILESIZE * SCALE) * COLUMNS;
    public static final int HEIGHT = (TILESIZE * SCALE) * ROWS;

    public static BufferedImage view;
    public static BufferedImage view_menu;
    public static BufferedImage view_how_to_play;
    public static BufferedImage view_choose_map;
    public static BufferedImage view_pause;
    public static BufferedImage view_game_over;

    public static char[][] scene = new char[16][22];
    public static int[][] has_item = new int[16][22];

    public static SoundEffect sound_game;
    public static SoundEffect sound_game_over;

    public static boolean menu = true;
    public static boolean is_playing = false;
    public static boolean how_to_play = false;
    public static boolean choose_map = false;
    public static boolean pause = false;
    public static boolean game_over = false;
    public static boolean off_volume = false;

    public static String level;

    public static Bomber bomber;
    public static Enemy enemy1;
    public static Enemy enemy2;
    public static Enemy enemy3;

    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    public static ArrayList<Item> items = new ArrayList<Item>();

    public static Sprite sprite = new Sprite();

}
