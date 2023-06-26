import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Game_Sound {

    private void playSound(String filePath, boolean loop, float volume) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            // Adjust volume level (in this example, reducing volume by 10 dB)
            setVolume(clip, volume);

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setVolume(Clip clip, float gain) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gain);
        }
    }


    public void theme() {
        //playSound("src/assets/sfx/theme.wav", true);
    }

    public void win() {
        playSound("src/assets/sfx/win.wav", false, 0f);
    }

    public void death() {
        playSound("src/assets/sfx/death.wav", false, 0f);
    }

    public void restart() {
        playSound("src/assets/sfx/whoosh.wav", false, -10.0f);
    }

    public void jump() {
        playSound("src/assets/sfx/jump.wav", false, 0f);
    }

}
