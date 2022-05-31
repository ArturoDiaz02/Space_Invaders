package model.music;

import javax.sound.sampled.Clip;

public class Sound {

    private Clip clip;

    public Sound(Clip clip) {
        this.clip = clip;
    }

    public void play() {
        if (clip == null) {
            return;
        }
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void loop(){
        if (clip == null) {
            return;
        }
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
