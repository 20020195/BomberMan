
import entities.Bomb;
import entities.Bomber;
import graphics.Sprite;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import common.*;

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

            create_Map(common_view.level);
            common_view.sprite.load();

/*            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("src/asd.wav")));
            clip.start();*/
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
                    }
                    i += 1;
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public void update() {
        common_view.bomber.update(common_view.TILESIZE, common_view.SCALE, common_view.scene);
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

    public void draw() {
        Graphics2D g2 = (Graphics2D) common_view.view.getGraphics();

        int size = common_view.TILESIZE * common_view.SCALE;
        if (common_view.is_playing) {
            for (int i = 0; i < common_view.ROWS; i++) {
                for (int j = 0; j < common_view.COLUMNS; j++) {
                    if (common_view.scene[i][j] == '#') {
                        g2.drawImage(common_view.sprite.coconut, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'z' || common_view.scene[i][j] == 'Z') {
                        g2.drawImage(common_view.sprite.coconut_water, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'x' || common_view.scene[i][j] == 'X') {
                        g2.drawImage(common_view.sprite.banana, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'c' || common_view.scene[i][j] == 'C') {
                        g2.drawImage(common_view.sprite.mound, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'v' || common_view.scene[i][j] == 'V') {
                        g2.drawImage(common_view.sprite.strawberry, j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == '1') {
                        g2.drawImage(common_view.sprite.item1[common_view.items.get(0).indexAnimItem], j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == '9') {
                        g2.drawImage(common_view.sprite.bombAnim[common_view.bombs.get(0).indexAnimBomb], j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'f') {
                        g2.drawImage(common_view.sprite.fontExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'd') {
                        g2.drawImage(common_view.sprite.rightExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 's') {
                        g2.drawImage(common_view.sprite.downExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'a') {
                        g2.drawImage(common_view.sprite.leftExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                    } else if (common_view.scene[i][j] == 'w') {
                        g2.drawImage(common_view.sprite.upExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                    }
                    else {
                        g2.drawImage(common_view.sprite.soil, j * size, i * size, size, size, null);
                    }
                }
            }
            g2.drawImage(common_view.sprite.player, common_view.bomber.getX(), common_view.bomber.getY(), size, size, null);
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
                        bomb.setX(_x + 1);
                        bomb.setY(_y + 1);
                    } else if (common_view.bomber.getX() % size > (size / 2)) {
                        common_view.scene[_y][_x + 1] = '9';
                        bomb.setX(_x + 1);
                        bomb.setY(_y);
                    } else if (common_view.bomber.getY() % size > (size / 2)) {
                        common_view.scene[_y + 1][_x] = '9';
                        bomb.setX(_x);
                        bomb.setY(_y + 1);
                    } else {
                        common_view.scene[_y][_x] = '9';
                        bomb.setX(_x);
                        bomb.setY(_y);
                    }

                    common_view.bombs.add(bomb);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                common_view.is_playing = false;
                common_view.pause = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                common_view.bomber.right = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                common_view.bomber.left = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                common_view.bomber.up = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                common_view.bomber.down = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            common_view.bomber.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            common_view.bomber.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            common_view.bomber.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            common_view.bomber.down = false;
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
                create_Map(common_view.level);
                common_view.bomber = null;
                common_view.bomber = new Bomber(common_view.TILESIZE, common_view.TILESIZE);
            } else if (e.getX() > 170 && e.getX() < 240
                    && e.getY() > 32 && e.getY() < 110) {
                common_view.menu = false;
                common_view.choose_map = false;
                common_view.how_to_play = false;
                common_view.is_playing = true;
                common_view.level = "res/levels/level2.txt";
                create_Map(common_view.level);
                common_view.bomber = null;
                common_view.bomber = new Bomber(common_view.TILESIZE, common_view.TILESIZE);
            }
        } else if (common_view.pause) {
            if (e.getX() > 156 && e.getX() < 242
                    && e.getY() > 85 && e.getY() < 108) {
                create_Map(common_view.level);
                common_view.bomber = null;
                common_view.bomber = new Bomber(common_view.TILESIZE, common_view.TILESIZE);
                common_view.pause = false;
                common_view.is_playing = true;
            } else if (e.getX() > 90 && e.getX() < 152
                    && e.getY() > 404 && e.getY() < 426) {
                common_view.pause = false;
                common_view.menu = true;
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
            } else if (e.getX() > 381 && e.getX() < 525
                    && e.getY() > 352 && e.getY() < 396) {
                common_view.game_over = false;
                common_view.is_playing = true;
                create_Map(common_view.level);
                common_view.bomber = null;
                common_view.bomber = new Bomber(common_view.TILESIZE, common_view.TILESIZE);
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
