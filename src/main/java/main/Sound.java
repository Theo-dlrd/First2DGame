package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    private Clip clip;
    private URL[] soundURL;

    public Sound(){
        soundURL = new URL[30];

        soundURL[0] = getClass().getResource("/Sounds/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/Sounds/coin.wav");
        soundURL[2] = getClass().getResource("/Sounds/powerup.wav");
        soundURL[3] = getClass().getResource("/Sounds/unlock.wav");
        soundURL[4] = getClass().getResource("/Sounds/fanfare.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
