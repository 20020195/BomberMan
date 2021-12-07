package entities;

import common.common_view;
import sound.SoundEffect;

import java.awt.image.BufferedImage;

public class Bomb extends Entity {
        private BufferedImage img_bomb;
        private BufferedImage img_bomb_up;
        private BufferedImage img_bomb_down;
        private BufferedImage img_bomb_left;
        private BufferedImage img_bomb_right;

        private int frameBomb = 0;
        private int intervalBomb = 10;
        private int indexAnimBomb = 0;

        private boolean exploded;
        private int countToExplode;
        private int intervalToExplode = 3;

        private int frameExplosion = 0;
        private int intervalExplosion = 3;
        private int indexAnimExplosion = 0;

        private SoundEffect sound_explosion;

        public Bomb(int xUnit, int yUnit) {
                super(xUnit, yUnit);
                sound_explosion = new SoundEffect("res/audio/bomb_explosion.wav");
        }

        public void animBomb(Bomb bomb) {
                img_bomb = common_view.sprite.bombAnim[this.getIndexAnimBomb()];
                bomb.frameBomb++;
                if (bomb.frameBomb == bomb.intervalBomb) {
                        bomb.frameBomb = 0;
                        bomb.indexAnimBomb++;

                        if (bomb.indexAnimBomb > 2) {
                                bomb.indexAnimBomb = 0;
                                bomb.countToExplode++;
                        }

                        if (bomb.countToExplode >= bomb.intervalToExplode) {
                                bomb.exploded = true;
                        }
                }
        }

        public boolean nguoicobinokhong(int _x, int _y) {
                if (common_view.bomber.isNo_dead()) {
                        return false;
                }
                int size = common_view.TILESIZE * common_view.SCALE;
                int x_bomber = common_view.bomber.getX();
                int y_bomber = common_view.bomber.getY();
                if (x_bomber > (_x - 2) * size && x_bomber < (_x - 1) * size) {
                        if (y_bomber > (_y - 1) * size && y_bomber < (_y + 1) * size) {
                                return true;
                        }
                }
                if (x_bomber > (_x - 1) * size && x_bomber < (_x) * size) {
                        if (y_bomber > (_y - 2) * size && y_bomber < (_y + 2) * size) {
                                return true;
                        }
                }
                if (x_bomber == (_x - 1) * size || x_bomber == (_x + 1) * size) {
                        if (y_bomber > (_y - 1) * size && y_bomber < (_y + 1) * size) {
                                return true;
                        }
                }
                if (x_bomber >= (_x)  *  size && x_bomber < (_x + 1) * size) {
                        if (y_bomber > (_y - 2) * size && y_bomber < (_y + 2) * size) {
                                return true;
                        }
                }
                if (x_bomber > (_x + 1) * size && x_bomber < (_x + 2) * size) {
                        if (y_bomber > (_y - 1) * size && y_bomber < (_y + 1) * size) {
                                return true;
                        }
                }
                return false;
        }

        public void animExplosion(Bomb bomb, int i) {
                img_bomb = common_view.sprite.fontExplosion[this.getIndexAnimExplosion()];
                img_bomb_up = common_view.sprite.upExplosion[this.getIndexAnimExplosion()];
                img_bomb_down = common_view.sprite.downExplosion[this.getIndexAnimExplosion()];
                img_bomb_left = common_view.sprite.leftExplosion[this.getIndexAnimExplosion()];
                img_bomb_right = common_view.sprite.rightExplosion[this.getIndexAnimExplosion()];
                int _x = bomb.getX() / (common_view.TILESIZE * common_view.SCALE);
                int _y = bomb.getY() / (common_view.TILESIZE * common_view.SCALE);
                bomb.frameExplosion++;
                if (bomb.frameExplosion == intervalExplosion) {
                        bomb.frameExplosion = 0;
                        bomb.indexAnimExplosion++;
                        if (bomb.indexAnimExplosion == 3) {
                                bomb.indexAnimExplosion = 0;
                                common_view.scene[_y][_x] = ' ';
                                if (common_view.has_item[_y + 1][_x] == 1) {
                                        int random = (int) (Math.random() * 100 + 1);
                                        Item item = new Item(common_view.TILESIZE * _x, common_view.TILESIZE * _y);
                                        common_view.items.add(item);
                                        if (random % 2 == 0) {
                                                common_view.scene[_y + 1][_x] = '1';
                                        } else {
                                                common_view.scene[_y + 1][_x] = '2';
                                        }
                                } else if (common_view.has_item[_y + 1][_x] == 0) {
                                        common_view.scene[_y + 1][_x] = ' ';
                                }
                                if (common_view.has_item[_y - 1][_x] == 1) {
                                        Item item = new Item(common_view.TILESIZE * _x, common_view.TILESIZE * _y);
                                        common_view.items.add(item);
                                        int random = (int) (Math.random() * 100 + 1);
                                        if (random % 2 == 0) {
                                                common_view.scene[_y - 1][_x] = '1';
                                        } else {
                                                common_view.scene[_y - 1][_x] = '2';
                                        }
                                } else if (common_view.has_item[_y - 1][_x] == 0) {
                                        common_view.scene[_y - 1][_x] = ' ';
                                }
                                if (common_view.has_item[_y][_x + 1] == 1) {
                                        Item item = new Item(common_view.TILESIZE * _x, common_view.TILESIZE * _y);
                                        common_view.items.add(item);
                                        int random = (int) (Math.random() * 100 + 1);
                                        if (random % 2 == 0) {
                                                common_view.scene[_y][_x + 1] = '1';
                                        } else {
                                                common_view.scene[_y][_x + 1] = '2';
                                        }
                                } else if (common_view.has_item[_y][_x + 1] == 0) {
                                        common_view.scene[_y][_x + 1] = ' ';
                                }
                                if (common_view.has_item[_y][_x - 1] == 1) {
                                        Item item = new Item(common_view.TILESIZE * _x, common_view.TILESIZE * _y);
                                        common_view.items.add(item);
                                        int random = (int) (Math.random() * 100 + 1);
                                        if (random % 2 == 0) {
                                                common_view.scene[_y][_x - 1] = '1';
                                        } else {
                                                common_view.scene[_y][_x - 1] = '2';
                                        }
                                } else if (common_view.has_item[_y][_x - 1] == 0) {
                                        common_view.scene[_y][_x - 1] = ' ';
                                }

                                if (nguoicobinokhong(_x, _y)) {
                                        common_view.is_playing = false;
                                        common_view.choose_map = false;
                                        common_view.how_to_play = false;
                                        common_view.menu = false;
                                        common_view.game_over = true;
                                        common_view.sound_game.stop_sound();
                                        common_view.sound_game_over.is_play_music = false;
                                        common_view.sound_game_over.play_sound();
                                }
                                common_view.bombs.remove(i);
                        }
                }
        }

        public void update() {
                if (common_view.bombs.size() != 0) {
                        for (int i = 0; i < common_view.bombs.size(); i++) {
                                animBomb(common_view.bombs.get(i));
                                if (common_view.bombs.get(i).exploded) {
                                        common_view.bombs.get(i).sound_explosion.play_sound();
                                        animExplosion(common_view.bombs.get(i), i);
                                }
                        }
                }
        }

        public BufferedImage getImg_bomb() {
                return img_bomb;
        }

        public BufferedImage getImg_bomb_up() {
                return img_bomb_up;
        }

        public BufferedImage getImg_bomb_down() {
                return img_bomb_down;
        }

        public BufferedImage getImg_bomb_left() {
                return img_bomb_left;
        }

        public BufferedImage getImg_bomb_right() {
                return img_bomb_right;
        }

        public int getIndexAnimBomb() {
                return indexAnimBomb;
        }

        public boolean isExploded() {
                return exploded;
        }

        public int getIndexAnimExplosion() {
                return indexAnimExplosion;
        }
}
