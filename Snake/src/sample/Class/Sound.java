package sample.Class;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.nio.file.Paths;

public class Sound {

    private static AudioClip audioClip = null;

    public static void play(){
        String path = "D:\\GitHubRepositories\\Java\\Snake\\src\\sample\\resources\\Soundtrack\\domkosnake44.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        audioClip = new AudioClip(media.getSource());
        audioClip.setVolume(0.20);
        audioClip.setRate(1);
        audioClip.setCycleCount(AudioClip.INDEFINITE);
        audioClip.play();
    }

    public static boolean isPlaying(){
        if(audioClip != null) {
            return audioClip.isPlaying();
        }
        return false;
    }


}