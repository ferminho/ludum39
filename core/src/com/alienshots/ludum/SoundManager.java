package com.alienshots.ludum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;

public class SoundManager {
    public static SoundManager instance = new SoundManager();

    public static final String SFX_JUMP = "jmp01.wav";
    public static final String SFX_MOV = "mov01.wav";
    public static final String SFX_DIE = "die01.wav";
    public static final String SFX_DROP = "drop01.wav";

    @Getter
    @Setter
    private float pitch = 1.0f;
    private HashMap<String, Sound> sounds = new HashMap<>();

    public SoundManager() {
        Arrays.stream(new String[]{
                SFX_JUMP, SFX_MOV, SFX_DIE, SFX_DROP
                }).forEach((s) -> {
            sounds.put(s, Gdx.audio.newSound(Gdx.files.internal(s)));
        });
    }

    public void play(String soundName) {
        play(soundName, pitch);
    }
    public void play(String soundName, float pitch) {
        Sound sound = sounds.get(soundName);
        sound.stop();
        if (pitch > 0.0f)
            sound.play(1f, pitch, 0f);
    }
}
