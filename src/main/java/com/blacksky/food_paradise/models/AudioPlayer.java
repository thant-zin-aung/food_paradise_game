package com.blacksky.food_paradise.models;

import com.blacksky.food_paradise.Main;
import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AudioPlayer {

    Map<String,String> audioMap = new HashMap<>();
    AudioClip audioClip;
    String audioName;
    public AudioPlayer() {
        audioMap.put("backgroundSound",getURIAudioPath("background_music.mp3"));

        audioClip = new AudioClip(audioMap.get("backgroundSound"));

    }

    private String getURIAudioPath(String audioName) {
        return Objects.requireNonNull(Main.class.getResource("/audios/"+audioName)).toString();
    }

    public AudioClip getAudioClip() {
        return audioClip;
    }

    public void play(String audioName,double rate,double volume,boolean setInfinite) {
        this.audioName = audioName;
        audioClip = new AudioClip(audioMap.get(audioName));
        audioClip.setRate(rate);
        audioClip.setVolume(volume);
        audioClip.setCycleCount(setInfinite?-1:0);
        audioClip.play();
    }
    public void stop() {
        audioClip.stop();
    }
}
