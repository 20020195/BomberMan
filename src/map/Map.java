package map;

import common.*;
import entities.Enemy;

import java.io.*;
import java.util.ArrayList;

public class Map {
    private int level;
    private String string_level;

    public Map() {

    }

    public Map(int level) {
        this.level = level;
    }

    public void create_Map(int level) {
        string_level = "res/levels/level" + String.valueOf(level) + ".txt";
        int i = 0;
        try {
            FileReader fr = new FileReader(string_level);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            line = br.readLine();
            String[] first = line.split(" ");
            int rows = Integer.parseInt(first[1]);
            int columns = Integer.parseInt(first[2]);
            common_view.scene = new char[rows][columns];
            common_view.has_item = new int[rows][columns];
            while (rows > 0) {
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
                        } else if (common_view.scene[i][j] == '1') {
                            common_view.enemy1 = new Enemy(common_view.TILESIZE * 18, common_view.TILESIZE * 1);
                            common_view.enemy2 = new Enemy(common_view.TILESIZE * 1, common_view.TILESIZE * 12);
                            common_view.enemy3 = new Enemy(common_view.TILESIZE * j, common_view.TILESIZE * i);
                            common_view.scene[i][j] = ' ';
                        } else {
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
                rows --;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public ArrayList<String> get_level_passed() {
        ArrayList<String> levels_passed = new ArrayList<String>();
        try {
            FileReader fr = new FileReader("res/levels/passed.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            line = br.readLine();
            if (line == null) {
                return levels_passed;
            }
            String[] first = line.split(" ");
            for (int i = 0; i < first.length; i++) {
                levels_passed.add(String.valueOf(first[i]));
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return levels_passed;
    }

    public void load_level_passed() {
        try {
            File f = new File("res/levels/passed.txt");
            FileWriter fw = new FileWriter(f);
            for (int i = 1; i <= common_view.map.getLevel(); i++) {
                fw.write(i + 48);
                fw.write(" ");
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println("Loi ghi file: " + ex);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getString_level() {
        return string_level;
    }

    public void setString_level(String string_level) {
        this.string_level = string_level;
    }
}
