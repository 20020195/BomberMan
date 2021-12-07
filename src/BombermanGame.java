import common.*;
import sound.*;
import entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class BombermanGame extends JPanel implements Runnable, KeyListener, MouseListener {
    boolean isRunning;
    Thread thread;

    public BombermanGame() {
        setPreferredSize(new Dimension(common_view.WIDTH, common_view.HEIGHT));
        addKeyListener(this);
        addMouseListener(this);
    }

    public static void main(String[] args) throws IOException {
        common_view.w.setResizable(false);
        common_view.w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        common_view.w.add(new BombermanGame());
        common_view.w.pack();  //pack này nó sẽ chạy addNotify rồi đến run rồi start.
        common_view.w.setLocationRelativeTo(null);
        common_view.w.setVisible(true);  //thực hiện hàm draw().
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }
    }

    public void start() {
        try {
            common_view.view = new BufferedImage(common_view.WIDTH, common_view.HEIGHT, BufferedImage.TYPE_INT_ARGB);
            common_view.view_menu = new BufferedImage(common_view.WIDTH, common_view.HEIGHT, BufferedImage.TYPE_INT_ARGB);
            common_view.sprite.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create_Map(String path) {
        common_view.scene = new char[16][22];
        int i = 0;
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                if (line == null) {
                    break;
                }
                line = br.readLine();
                if (line != null) {
                    for (int j = 0; j < line.length(); j++) {
                        common_view.scene[i][j] = line.charAt(j);
                        common_view.has_item[i][j] = 0;
                        if (common_view.scene[i][j] == '#') {
                            common_view.has_item[i][j] = 2;
                        }
                        if (common_view.scene[i][j] != ' ' && common_view.scene[i][j] != '#') {
                            int random = (int) (Math.random() * 100 + 1);
                            if (random % 3 == 0) {
                                common_view.has_item[i][j] = 1;
                            } else {
                                common_view.has_item[i][j] = 0;
                            }
                        }
                    }
                    i += 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void update() {
        if (common_view.is_playing) {
            common_view.bomber.update(common_view.TILESIZE, common_view.SCALE, common_view.scene);
            for (int i = 0; i < common_view.enemies.size(); i++) {
                common_view.enemies.get(i).update1(common_view.TILESIZE, common_view.SCALE, common_view.scene);
            }
            if (common_view.bombs.size() > 0) {
                for (int i = 0; i < common_view.bombs.size(); i++) {
                    common_view.bombs.get(i).update();
                }
            }
            if (common_view.items.size() > 0) {
                for (int i = 0; i < common_view.items.size(); i++) {
                    common_view.items.get(i).update();
                }
            }
        }
    }

    public void draw() {
        Graphics2D g2 = (Graphics2D) common_view.view.getGraphics();

        int size = common_view.TILESIZE * common_view.SCALE;
        if (common_view.is_playing) {
            for (int i = 0; i < common_view.ROWS; i++) {
                for (int j = 0; j < common_view.COLUMNS; j++) {
                    if (common_view.scene[i][j] == '#') {
                        g2.drawImage(common_view.sprite.coconut, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'z') {
                        g2.drawImage(common_view.sprite.coconut_water, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'x') {
                        g2.drawImage(common_view.sprite.banana, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'c') {
                        g2.drawImage(common_view.sprite.mound, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'v') {
                        g2.drawImage(common_view.sprite.strawberry, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == '1') {
                        g2.drawImage(common_view.sprite.item1[common_view.items.get(0).getIndexAnimItem()], j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == '2') {
                        g2.drawImage(common_view.sprite.item2[common_view.items.get(0).getIndexAnimItem()], j * size, i * size, size, size, null);
                    } else {
                        g2.drawImage(common_view.sprite.soil, j * size, i * size, size, size, null);
                    }
                }
            }
            for (int i = 0; i < common_view.bombs.size(); i++) {
                if (!common_view.bombs.get(i).isExploded()) {
                    g2.drawImage(common_view.bombs.get(i).getImg_bomb(), common_view.bombs.get(i).getX(), common_view.bombs.get(i).getY(), size, size, null);
                } else {
                    g2.drawImage(common_view.bombs.get(i).getImg_bomb(), common_view.bombs.get(i).getX(), common_view.bombs.get(i).getY(), size, size, null);
                    if (common_view.scene[common_view.bombs.get(i).getY() / size - 1][common_view.bombs.get(i).getX() / size] != '#') {
                        g2.drawImage(common_view.bombs.get(i).getImg_bomb_up(), common_view.bombs.get(i).getX(), common_view.bombs.get(i).getY() - size, size, size, null);
                    }
                    if (common_view.scene[common_view.bombs.get(i).getY() / size + 1][common_view.bombs.get(i).getX() / size] != '#') {
                        g2.drawImage(common_view.bombs.get(i).getImg_bomb_down(), common_view.bombs.get(i).getX(), common_view.bombs.get(i).getY() + size, size, size, null);
                    }
                    if (common_view.scene[common_view.bombs.get(i).getY() / size][common_view.bombs.get(i).getX() / size - 1] != '#') {
                        g2.drawImage(common_view.bombs.get(i).getImg_bomb_left(), common_view.bombs.get(i).getX() - size, common_view.bombs.get(i).getY(), size, size, null);
                    }
                    if (common_view.scene[common_view.bombs.get(i).getY() / size][common_view.bombs.get(i).getX() / size + 1] != '#') {
                        g2.drawImage(common_view.bombs.get(i).getImg_bomb_right(), common_view.bombs.get(i).getX() + size, common_view.bombs.get(i).getY(), size, size, null);
                    }
                }
            }
            for (int i = 0; i < common_view.enemies.size(); i++) {
                g2.drawImage(common_view.enemies.get(i).getImg_enemy(), common_view.enemies.get(i).getX(), common_view.enemies.get(i).getY(), size, size, null);
            }
            g2.drawImage(common_view.bomber.getImg_player(), common_view.bomber.getX(), common_view.bomber.getY(), size, size, null);

        } else if (common_view.how_to_play) {
            g2.drawImage(common_view.view_how_to_play,0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        } else if (common_view.choose_map) {
            g2.drawImage(common_view.view_choose_map,0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        } else if (common_view.pause) {
            g2.drawImage(common_view.view_pause,0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        } else if (common_view.game_over) {
            g2.drawImage(common_view.view_game_over,0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        } else {
            g2.drawImage(common_view.view_menu,0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        }

        Graphics g = getGraphics();
        g.drawImage(common_view.view, 0, 0, common_view.WIDTH, common_view.HEIGHT, null);
        g.dispose();
    }

    @Override
    public void run() {
        try {
            common_view.sound_game = new SoundEffect("res/audio/theme.wav");
            common_view.sound_game_over = new SoundEffect("res/audio/sound_game_over.wav");
            common_view.sound_game.play_sound();
            requestFocus();
            start();
            while (isRunning) {
                update();
                draw();
                Thread.sleep(1000 / 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        common_view.bomber = null;
        common_view.enemies.clear();
        common_view.enemy1 = null;
        common_view.enemy2 = null;
        common_view.enemy3 = null;
        common_view.bombs.clear();
        common_view.items.clear();
    }

    public void create_data_game() {
        create_Map(common_view.level);
        common_view.bomber = new Bomber(common_view.TILESIZE, common_view.TILESIZE);
        common_view.enemy1 = new Enemy(common_view.TILESIZE * 2, common_view.TILESIZE * 5);
        common_view.enemy2 = new Enemy(common_view.TILESIZE * 4, common_view.TILESIZE * 5);
        common_view.enemy3 = new Enemy(common_view.TILESIZE * 6, common_view.TILESIZE * 5);
        common_view.enemies.add(common_view.enemy1);
        common_view.enemies.add(common_view.enemy2);
        common_view.enemies.add(common_view.enemy3);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (common_view.is_playing) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (common_view.bombs.size() < 2) {
                    int size = common_view.TILESIZE * common_view.SCALE;
                    Bomb bomb = new Bomb(0, 0);
                    int _x = (common_view.bomber.getX() / size);
                    int _y = (common_view.bomber.getY() / size);

                    if (common_view.bomber.getX() % size > (size / 2)
                            && common_view.bomber.getY() % size > (size / 2)) {
                        common_view.scene[_y + 1][_x + 1] = '9';
                        bomb.setX((_x + 1) * size);
                        bomb.setY((_y + 1) * size);
                    } else if (common_view.bomber.getX() % size > (size / 2)) {
                        common_view.scene[_y][_x + 1] = '9';
                        bomb.setX((_x + 1) * size);
                        bomb.setY(_y * size);
                    } else if (common_view.bomber.getY() % size > (size / 2)) {
                        common_view.scene[_y + 1][_x] = '9';
                        bomb.setX(_x * size);
                        bomb.setY((_y + 1) * size);
                    } else {
                        common_view.scene[_y][_x] = '9';
                        bomb.setX(_x * size);
                        bomb.setY(_y * size);
                    }

                    common_view.bombs.add(bomb);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                common_view.is_playing = false;
                common_view.pause = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                common_view.bomber.setRight(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                common_view.bomber.setLeft(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                common_view.bomber.setUp(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                common_view.bomber.setDown(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_M) {
                if (common_view.off_volume == false) {
                    common_view.off_volume = true;
                    if (common_view.sound_game.is_play_music) {
                        common_view.sound_game.stop_sound();
                    }
                } else {
                    common_view.off_volume = false;
                    common_view.sound_game.play_sound();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            common_view.bomber.setRight(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            common_view.bomber.setLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            common_view.bomber.setUp(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            common_view.bomber.setDown(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
            // If này là đang ở Menu
        if (common_view.menu) {
            // click how_to_play
            if (e.getX() > 413 && e.getX() < 680
                    && e.getY() > 360 && e.getY() < 400) {
                common_view.menu = false;
                common_view.how_to_play = true;
            } else if (e.getX() > 485 && e.getX() < 615     // click start
                    && e.getY() > 245 && e.getY() < 285) {
                common_view.menu = false;
                common_view.choose_map = true;
            }
        } else if (common_view.how_to_play) {   // if này là đang ở trong how_to_play
            if (e.getX() < 102 && e.getY() < 58) {
                common_view.how_to_play = false;
                common_view.menu = true;    // click back của how_to_play
            }
        } else if (common_view.choose_map) {   // if này đang ở trong chọn map
            if (e.getX() > 40 && e.getX() < 110
                    && e.getY() > 32 && e.getY() < 110) {
                common_view.choose_map = false;
                common_view.is_playing = true;
                common_view.level = "res/levels/level1.txt";
                clear();
                create_data_game();
            } else if (e.getX() > 170 && e.getX() < 240
                    && e.getY() > 32 && e.getY() < 110) {
                common_view.menu = false;
                common_view.choose_map = false;
                common_view.how_to_play = false;
                common_view.is_playing = true;
                common_view.level = "res/levels/level2.txt";
                clear();
                create_data_game();
            }
        } else if (common_view.pause) {
            if (e.getX() > 156 && e.getX() < 242
                    && e.getY() > 85 && e.getY() < 108) {
                clear();
                create_data_game();
                common_view.pause = false;
                common_view.is_playing = true;
            } else if (e.getX() > 90 && e.getX() < 152
                    && e.getY() > 404 && e.getY() < 426) {
                common_view.pause = false;
                common_view.menu = true;
                clear();
            } else if (e.getX() > 403 && e.getX() < 457
                    && e.getY() > 305 && e.getY() < 332) {
                common_view.pause = false;
                common_view.is_playing = true;
            }
        } else if (common_view.game_over) {
            if (e.getX() > 103 && e.getX() < 234
                    && e.getY() > 379 && e.getY() < 423) {
                common_view.game_over = false;
                common_view.menu = true;
                common_view.sound_game_over.stop_sound();
                common_view.sound_game.is_play_music = false;
                common_view.sound_game.play_sound();
                clear();
            } else if (e.getX() > 381 && e.getX() < 525
                    && e.getY() > 352 && e.getY() < 396) {
                common_view.game_over = false;
                common_view.is_playing = true;
                clear();
                create_data_game();
                common_view.sound_game_over.stop_sound();
                common_view.sound_game.is_play_music = false;
                common_view.sound_game.play_sound();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
