package sound;

import common.*;

import java.io.File;
import javax.sound.sampled.*;


public class SoundEffect {
    public File file;
    public Clip clip;
    public AudioInputStream audioInputStream;
    public boolean is_play_music;

    public SoundEffect(String path) {
        this.is_play_music = false;
        this.file = new File(path);
        this.audioInputStream = null;
    }

    public void play_sound() {
        if (!is_play_music && !common_view.off_volume) {
            try {
                this.audioInputStream = AudioSystem.getAudioInputStream(this.file);
                this.clip = AudioSystem.getClip();
                this.clip.open(this.audioInputStream);
                is_play_music = true;
                this.clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop_sound() {
        if (is_play_music) {
            this.clip.close();
            is_play_music = false;
        }
    }

}
