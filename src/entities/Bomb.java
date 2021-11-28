package entities;

import common.common_view;

public class Bomb extends Entity {
        public int frameBomb = 0;
        public int intervalBomb = 10;
        public int indexAnimBomb = 0;

        public boolean exploded;
        public int countToExplode;
        public int intervalToExplode = 3;

        public int frameExplosion = 0;
        public int intervalExplosion = 3;
        public int indexAnimExplosion = 0;

        public Bomb(int xUnit, int yUnit) {
                super(xUnit, yUnit);
        }

        public void animBomb(Bomb bomb) {
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

        public void nguoicobinokhong(int _x, int _y) {
                int size = common_view.TILESIZE * common_view.SCALE;
                int x_bomber = common_view.bomber.getX();
                int y_bomber = common_view.bomber.getY();
                if (x_bomber > (_x - 2) * size && x_bomber < (_x - 1) * size)
                        if (y_bomber > (_y - 1) * size && y_bomber < (_y + 1) * size) {
                                common_view.is_playing = false;
                                common_view.choose_map = false;
                                common_view.how_to_play = false;
                                common_view.menu = false;
                                common_view.game_over = true;
                        }
                if (x_bomber > (_x - 1) * size && x_bomber < (_x) * size) {
                        if (y_bomber > (_y - 2) * size && y_bomber < (_y + 2) * size) {
                                common_view.is_playing = false;
                                common_view.choose_map = false;
                                common_view.how_to_play = false;
                                common_view.menu = false;
                                common_view.game_over = true;
                        }
                }
                if (x_bomber == (_x - 1) * size || x_bomber == (_x + 1) * size) {
                        if (y_bomber > (_y - 1) * size && y_bomber < (_y + 1) * size) {
                                common_view.is_playing = false;
                                common_view.choose_map = false;
                                common_view.how_to_play = false;
                                common_view.menu = false;
                                common_view.game_over = true;
                        }
                }
                if (x_bomber >= (_x)  *  size && x_bomber < (_x + 1) * size) {
                        if (y_bomber > (_y - 2) * size && y_bomber < (_y + 2) * size) {
                                common_view.is_playing = false;
                                common_view.choose_map = false;
                                common_view.how_to_play = false;
                                common_view.menu = false;
                                common_view.game_over = true;
                        }
                }
                if (x_bomber > (_x + 1) * size && x_bomber < (_x + 2) * size) {
                        if (y_bomber > (_y - 1) * size && y_bomber < (_y + 1) * size) {
                                common_view.is_playing = false;
                                common_view.choose_map = false;
                                common_view.how_to_play = false;
                                common_view.menu = false;
                                common_view.game_over = true;
                        }
                }
        }

        public void update() {
                if (common_view.bombs.size() != 0) {
                        for (int i = 0; i < common_view.bombs.size(); i++) {
                                int _x = common_view.bombs.get(i).getX();
                                int _y = common_view.bombs.get(i).getY();

                                animBomb(common_view.bombs.get(i));

                                if (common_view.bombs.get(i).exploded) {
                                        System.out.println(common_view.scene[_y + 1][_x]);
                                        System.out.println(common_view.scene[_y - 1][_x]);
                                        System.out.println(common_view.scene[_y][_x + 1]);
                                        System.out.println(common_view.scene[_y][_x - 1]);

                                        common_view.scene[_y][_x] = 'f';
                                        if (common_view.scene[_y + 1][_x] != '#') {
                                                common_view.scene[_y + 1][_x] = 's';
                                        }
                                        if (common_view.scene[_y][_x - 1] != '#') {
                                                common_view.scene[_y][_x - 1] = 'a';
                                        }
                                        if (common_view.scene[_y][_x + 1] != '#') {
                                                common_view.scene[_y][_x + 1] = 'd';
                                        }
                                        if (common_view.scene[_y - 1][_x] != '#') {
                                                common_view.scene[_y - 1][_x] = 'w';
                                        }
                                        common_view.bombs.get(i).frameExplosion++;
                                        if (common_view.bombs.get(i).frameExplosion == intervalExplosion) {
                                                common_view.bombs.get(i).frameExplosion = 0;
                                                common_view.bombs.get(i).indexAnimExplosion++;
                                                if (common_view.bombs.get(i).indexAnimExplosion == 3) {
                                                        common_view.bombs.get(i).indexAnimExplosion = 0;

                                                        common_view.scene[_y][_x] = ' ';
                                                        if (common_view.has_item[_y + 1][_x] == 1) {
                                                                Item item = new Item(0, 0);
                                                                common_view.items.add(item);
                                                                common_view.scene[_y + 1][_x] = '1';
                                                        } else if (common_view.has_item[_y + 1][_x] == 0) {
                                                                common_view.scene[_y + 1][_x] = ' ';
                                                        }
                                                        if (common_view.has_item[_y - 1][_x] == 1) {
                                                                Item item = new Item(0, 0);
                                                                common_view.items.add(item);
                                                                common_view.scene[_y - 1][_x] = '1';
                                                        } else if (common_view.has_item[_y - 1][_x] == 0) {
                                                                common_view.scene[_y - 1][_x] = ' ';
                                                        }
                                                        if (common_view.has_item[_y][_x + 1] == 1) {
                                                                Item item = new Item(0, 0);
                                                                common_view.items.add(item);
                                                                common_view.scene[_y][_x + 1] = '1';
                                                        } else if (common_view.has_item[_y][_x + 1] == 0) {
                                                                common_view.scene[_y][_x + 1] = ' ';
                                                        }
                                                        if (common_view.has_item[_y][_x - 1] == 1) {
                                                                Item item = new Item(0, 0);
                                                                common_view.items.add(item);
                                                                common_view.scene[_y][_x - 1] = '1';
                                                        } else if (common_view.has_item[_y][_x - 1] == 0) {
                                                                common_view.scene[_y][_x - 1] = ' ';
                                                        }

                                                        nguoicobinokhong(_x, _y);

                                                        common_view.bombs.remove(i);
                                                }
                                        }
                                }
                        }
                }
        }
}
