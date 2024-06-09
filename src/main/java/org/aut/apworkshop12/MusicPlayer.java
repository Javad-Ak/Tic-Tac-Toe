package org.aut.apworkshop12;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicPlayer {
    private static MediaPlayer music = null;

    private MusicPlayer() {
    }

    public static void play() {
        if (music == null) {
            String path = "./src/main/resources/org/aut/apworkshop12/music.mp3";
            Media media = new Media(new File(path).toURI().toString());
            music = new MediaPlayer(media);
            music.setOnEndOfMedia(() -> music.seek(Duration.ZERO));
        }
        music.play();
    }

    public static void pause() {
        if (music == null) return;
        music.pause();

    }
}
