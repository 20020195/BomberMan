
import entities.Bomber;
import graphics.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.*;

import common.*;

public class BombermanGame extends JPanel implements Runnable, KeyListener {
    boolean isRunning;
    Thread thread;
    public static BufferedImage view;
    Sprite sprite = new Sprite();
    char[][] scene = new char[16][22];

    Bomber bomber = new Bomber(common_view.TILESIZE, common_view.TILESIZE);

    public BombermanGame() {
        setPreferredSize(new Dimension(common_view.WIDTH, common_view.HEIGHT));
        addKeyListener(this);
    }

    public static void main(String[] args) throws IOException {

        JFrame w = new JFrame("Bomberman");
        w.setResizable(false);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.add(new BombermanGame());
        w.pack();  //pack này nó sẽ chạy addNotify rồi đến run rồi start.
        w.setLocationRelativeTo(null);
        w.setVisible(true);  //thực hiện hàm draw().
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
            create_Map("adasd");
            sprite.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create_Map(String path) {

        scene = new char[16][22];
        int i = 0;
        try {
            FileReader fr = new FileReader("res/levels/level2.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                if (line == null) {
                    break;
                }
                line = br.readLine();
                if (line != null) {
                    for (int j = 0; j<line.length(); j++) {
                        scene[i][j] = line.charAt(j);
                    }
                    i+=1;
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public void update() {
        bomber.update(common_view.TILESIZE, common_view.SCALE, scene);
    }

    public void draw() {
        Graphics2D g2 = (Graphics2D) common_view.view.getGraphics();
        g2.setColor(new Color(56, 135, 0));
        g2.fillRect(0, 0, common_view.WIDTH, common_view.HEIGHT);

        int size = common_view.TILESIZE * common_view.SCALE;
        for (int i = 0; i < common_view.ROWS; i++) {
            for (int j = 0; j < common_view.COLUMNS; j++) {
                if (scene[i][j] == 35) {
                    g2.drawImage(sprite.getCoconut(), j * size, i * size, size, size, null);
                } else if (scene[i][j] == 42) {
                    g2.drawImage(sprite.getCoconut_water(), j * size, i * size, size, size, null);
                } else if (scene[i][j] == 98) {
                    g2.drawImage(sprite.getBanana(), j * size, i * size, size, size, null);
                } else if (scene[i][j] == 99) {
                    g2.drawImage(sprite.getMound(), j * size, i * size, size, size, null);
                } else if (scene[i][j] == 100) {
                    g2.drawImage(sprite.getStrawberry(), j * size, i * size, size, size, null);
                } else {
                    g2.drawImage(sprite.getSoil(), j * size, i * size, size, size, null);
                }
            }
        }
        g2.drawImage(sprite.getPlayer(), bomber.getX(), bomber.getY(), size, size, null);
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
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            common_view.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            common_view.left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            common_view.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            common_view.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            common_view.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            common_view.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            common_view.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            common_view.down = false;
        }
    }
}
