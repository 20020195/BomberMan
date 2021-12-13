package event;

import common.common_view;
import enemys.*;
import entities.Bomber;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HandleMouse implements MouseListener {
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
        common_view.map.create_Map(common_view.map.getLevel());
        common_view.bomber = new Bomber(common_view.TILESIZE, common_view.TILESIZE);
        common_view.enemy1 = new Venom(common_view.TILESIZE * 20, common_view.TILESIZE * 1);
        common_view.enemy2 = new Bat(common_view.TILESIZE * 1, common_view.TILESIZE * 14);
        common_view.enemy3 = new Ghost(common_view.TILESIZE * 10, common_view.TILESIZE * 7);
        common_view.Boss = new Boss(common_view.TILESIZE * 20, common_view.TILESIZE * 14);
        common_view.bosses.add(common_view.Boss);
        common_view.enemies.add(common_view.enemy1);
        common_view.enemies.add(common_view.enemy2);
        common_view.enemies.add(common_view.enemy3);
    }

    public boolean check_position(int x, int y, int A, int B, int C, int D) {
        return x > A && x < B
                && y > C && y < D;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // If này là đang ở Menu
        if (common_view.menu) {
            // click how_to_play
            if (check_position(e.getX(), e.getY(), 413, 680, 360, 400)) {
                common_view.menu = false;
                common_view.how_to_play = true;
            } else if (check_position(e.getX(), e.getY(), 485, 615, 245, 285)) {
                common_view.menu = false;
                common_view.choose_map = true;
            }
        } else if (common_view.how_to_play) {   // if này là đang ở trong how_to_play
            if (check_position(e.getX(), e.getY(), 0, 102, 0, 58)) {
                common_view.how_to_play = false;
                common_view.menu = true;    // click back của how_to_play
            }
        } else if (common_view.choose_map) {   // if này đang ở trong chọn map
            if (check_position(e.getX(), e.getY(), 40, 110, 32, 110)) {
                common_view.map.setLevel(1);
                clear();
                create_data_game();
                common_view.choose_map = false;
                common_view.is_playing = true;
            } else if (check_position(e.getX(), e.getY(), 170, 240, 32, 110)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("1") > 0) {
                    common_view.map.setLevel(2);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            } else if (check_position(e.getX(), e.getY(), 300, 370, 32, 110)) {
                if (!common_view.passed_levels.isEmpty()
                        && common_view.passed_levels.get(common_view.passed_levels.size() - 1).compareTo("2") > 0) {
                    common_view.map.setLevel(3);
                    clear();
                    create_data_game();
                    common_view.choose_map = false;
                    common_view.is_playing = true;
                }
            }
        } else if (common_view.pause) {
            if (check_position(e.getX(), e.getY(), 156, 242, 85, 108)) {
                clear();
                create_data_game();
                common_view.pause = false;
                common_view.is_playing = true;
            } else if (check_position(e.getX(), e.getY(), 90, 152, 404, 426)) {
                common_view.pause = false;
                common_view.menu = true;
                clear();
            } else if (check_position(e.getX(), e.getY(), 403, 457, 305, 332)) {
                common_view.pause = false;
                common_view.is_playing = true;
            }
        } else if (common_view.game_over) {
            if (check_position(e.getX(), e.getY(), 103, 234, 379, 423)) {
                common_view.game_over = false;
                common_view.menu = true;
                common_view.sound_game_over.stop_sound();
                common_view.sound_game.is_play_music = false;
                common_view.sound_game.play_sound();
                clear();
            } else if (check_position(e.getX(), e.getY(), 381, 525, 352, 396)) {
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
