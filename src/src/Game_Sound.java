import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.Random;

public class Game_Sound {
    Random random = new Random();

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
        if (gain > 6.0206f) gain = 6.0206f;

        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(gain);
        }
    }


    public void theme() {
        int randNum = random.nextInt(100);

        if (randNum <= 15) playSound("src/assets/sfx/FallGuys.wav", true, -10f);
        else if (randNum <= 30) playSound("src/assets/sfx/Megalovania.wav", true, -10f);
        else playSound("src/assets/sfx/Doom.wav", true, -10f);
        System.out.println(randNum);
    }

    public void win() {
        playSound("src/assets/sfx/win.wav", false, 0f);
    }

    public void death() {

        int randNum = random.nextInt(10);

        if (randNum <= 3) playSound("src/assets/sfx/death.wav", false, 6.0f);
        else playSound("src/assets/sfx/woah.wav", false, 10f);
    }

    public void restart() {
        // playSound("src/assets/sfx/whoosh.wav", false, -10.0f);
    }

    public void jump() {
        // playSound("src/assets/sfx/jump.wav", false, 0f);
    }

}
