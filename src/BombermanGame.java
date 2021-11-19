
import entities.Bomb;
import entities.Bomber;
import graphics.Sprite;

import javax.imageio.ImageIO;
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

    public BombermanGame() {
        setPreferredSize(new Dimension(common_view.WIDTH, common_view.HEIGHT));
        addKeyListener(this);
    }

    public static void main(String[] args) throws IOException {
        boolean menu = false;


        while (menu) {
            common_view.w.setSize(common_view.WIDTH, common_view.HEIGHT);
            common_view.w.setLocationRelativeTo(null);
            common_view.w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            String path = "C:/Users/tranh/Desktop/classic1.png";
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            JLabel label = new JLabel(new ImageIcon(image));
            common_view.w.add(label);
            common_view.w.setVisible(true);
        }
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
            create_Map("adasd");
            common_view.sprite.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create_Map(String path) {

        common_view.scene = new char[16][22];
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
                        common_view.scene[i][j] = line.charAt(j);
                    }
                    i+=1;
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
    }

    public void draw() {
        Graphics2D g2 = (Graphics2D) common_view.view.getGraphics();
        g2.setColor(new Color(56, 135, 0));
        g2.fillRect(0, 0, common_view.WIDTH, common_view.HEIGHT);

        int size = common_view.TILESIZE * common_view.SCALE;
        for (int i = 0; i < common_view.ROWS; i++) {
            for (int j = 0; j < common_view.COLUMNS; j++) {
                if (common_view.scene[i][j] == '#') {
                    g2.drawImage(common_view.sprite.coconut, j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == '*') {
                    g2.drawImage(common_view.sprite.coconut_water, j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == 'b') {
                    g2.drawImage(common_view.sprite.banana, j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == 'c') {
                    g2.drawImage(common_view.sprite.mound, j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == 'd') {
                    g2.drawImage(common_view.sprite.strawberry, j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == '9') {
                    g2.drawImage(common_view.sprite.bombAnim[common_view.bombs.get(0).indexAnimBomb], j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == 'F') {
                    g2.drawImage(common_view.sprite.fontExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == 'D') {
                    g2.drawImage(common_view.sprite.rightExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == 'S') {
                    g2.drawImage(common_view.sprite.downExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == 'A') {
                    g2.drawImage(common_view.sprite.leftExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                } else if (common_view.scene[i][j] == 'W') {
                    g2.drawImage(common_view.sprite.upExplosion[common_view.bombs.get(0).indexAnimExplosion], j * size, i * size, size, size, null);
                }
                else {
                    g2.drawImage(common_view.sprite.soil, j * size, i * size, size, size, null);
                }
            }
        }
        g2.drawImage(common_view.sprite.player, common_view.bomber.getX(), common_view.bomber.getY(), size, size, null);
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (common_view.bombs.size() < 2) {
                Bomb bomb = new Bomb(0, 0);
                int _x = (common_view.bomber.getX()/(common_view.TILESIZE * common_view.SCALE));
                int _y = (common_view.bomber.getY()/(common_view.TILESIZE * common_view.SCALE));

                if (common_view.bomber.getX() % (common_view.TILESIZE * common_view.SCALE) > (common_view.TILESIZE * common_view.SCALE / 2)
                    && common_view.bomber.getY() % (common_view.TILESIZE * common_view.SCALE) > (common_view.TILESIZE * common_view.SCALE / 2)) {
                    common_view.scene[_y + 1][_x + 1] = '9';
                    bomb.setX(_x + 1);
                    bomb.setY(_y + 1);
                } else if (common_view.bomber.getX() % (common_view.TILESIZE * common_view.SCALE) > (common_view.TILESIZE * common_view.SCALE / 2)) {
                    common_view.scene[_y][_x + 1] = '9';
                    bomb.setX(_x + 1);
                    bomb.setY(_y);
                } else if (common_view.bomber.getY() % (common_view.TILESIZE * common_view.SCALE) > (common_view.TILESIZE * common_view.SCALE / 2)) {
                    common_view.scene[_y + 1][_x] = '9';
                    bomb.setX(_x);
                    bomb.setY(_y + 1);
                } else {
                    common_view.scene[_y][_x] = '9';
                    bomb.setX(_x);
                    bomb.setY(_y);
                }

/*                bomb.setX(_x);
                bomb.setY(_y);
                common_view.scene[_y][_x] = '9';*/
                System.out.println("(" + bomb.getX() + ", " + bomb.getY() + ")");

                common_view.bombs.add(bomb);
            }
        }
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
