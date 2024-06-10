package org.aut.apworkshop12.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.ToggleButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class MusicPlayer {
    private static final ArrayList<ToggleButton> toggleButtons = new ArrayList<>();
    private static MediaPlayer music = null;
    private static BooleanProperty isPlaying;
    
    private MusicPlayer(){
    }

    public static void addToggle(ToggleButton toggleButton) {
        toggleButtons.add(toggleButton);
        
        if (music == null) {
            String path = "./src/main/resources/org/aut/apworkshop12/music.mp3";
            Media media = new Media(new File(path).toURI().toString());
            music = new MediaPlayer(media);
            music.setOnEndOfMedia(() -> music.seek(Duration.ZERO));
        }

        isPlaying = new SimpleBooleanProperty(false);
        toggleButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            for (ToggleButton button : toggleButtons) {
                button.setSelected(newValue);
            }
            if (newValue)
                play();
            else 
                pause();
        });
    }

    private static void play() {
        music.play();
        isPlaying.set(true);
    }

    private static void pause() {
        music.pause();
        isPlaying.set(false);
    }
}
