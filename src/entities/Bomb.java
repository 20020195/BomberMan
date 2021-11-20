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

        public void update() {
                if (common_view.bombs.size() != 0) {
                        for (int i = 0; i < common_view.bombs.size(); i++) {
                                System.out.println("(" + common_view.bombs.get(i).getX() + ", " + common_view.bombs.get(i).getY() + ")");
                                common_view.bombs.get(i).frameBomb++;
                                if (common_view.bombs.get(i).frameBomb == common_view.bombs.get(i).intervalBomb) {
                                        common_view.bombs.get(i).frameBomb = 0;
                                        common_view.bombs.get(i).indexAnimBomb++;

                                        if (common_view.bombs.get(i).indexAnimBomb > 2) {
                                                common_view.bombs.get(i).indexAnimBomb = 0;
                                                common_view.bombs.get(i).countToExplode++;
                                        }

                                        if (common_view.bombs.get(i).countToExplode >= common_view.bombs.get(i).intervalToExplode) {
                                                common_view.bombs.get(i).exploded = true;
                                        }
                                }

                                if (common_view.bombs.get(i).exploded) {
                                        common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX()] = 'F';
                                        if (common_view.scene[common_view.bombs.get(i).getY() + 1][common_view.bombs.get(i).getX()] != 35) {
                                                common_view.scene[common_view.bombs.get(i).getY() + 1][common_view.bombs.get(i).getX()] = 'S';
                                        }
                                        if (common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX() - 1] != 35) {
                                                common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX() - 1] = 'A';
                                        }
                                        if (common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX() + 1] != 35) {
                                                common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX() + 1] = 'D';
                                        }
                                        if (common_view.scene[common_view.bombs.get(i).getY() - 1][common_view.bombs.get(i).getX()] != 35) {
                                                common_view.scene[common_view.bombs.get(i).getY() - 1][common_view.bombs.get(i).getX()] = 'W';
                                        }
                                        common_view.bombs.get(i).frameExplosion++;
                                        if (common_view.bombs.get(i).frameExplosion == intervalExplosion) {
                                                common_view.bombs.get(i).frameExplosion = 0;
                                                common_view.bombs.get(i).indexAnimExplosion++;
                                                if (common_view.bombs.get(i).indexAnimExplosion == 3) {
                                                        common_view.bombs.get(i).indexAnimExplosion = 0;

                                                        common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX()] = ' ';
                                                        if (common_view.scene[common_view.bombs.get(i).getY() + 1][common_view.bombs.get(i).getX()] != 35) {
                                                                common_view.scene[common_view.bombs.get(i).getY() + 1][common_view.bombs.get(i).getX()] = ' ';
                                                        }
                                                        if (common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX() - 1] != 35) {
                                                                common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX() - 1] = ' ';
                                                        }
                                                        if (common_view.scene[common_view.bombs.get(i).getY() - 1][common_view.bombs.get(i).getX()] != 35) {
                                                                common_view.scene[common_view.bombs.get(i).getY() - 1][common_view.bombs.get(i).getX()] = ' ';
                                                        }
                                                        if (common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX() + 1] != 35) {
                                                                common_view.scene[common_view.bombs.get(i).getY()][common_view.bombs.get(i).getX() + 1] = ' ';
                                                        }
                                                        common_view.bombs.remove(i);
                                                }
                                        }
                                }
                        }
                }
        }
}
