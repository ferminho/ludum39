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
    public static final String SFX_DIE = "die03.wav";
    public static final String SFX_DROP = "drop01.wav";
    public static final String SFX_SAW = "saw01.wav";
    public static final String SFX_GENERATOR_UP = "generator01.wav";
    public static final String SFX_POWER_UP = "poweringup01.wav";
    public static final String SFX_SWITCH_OFF = "switchoff01.wav";
    public static final String SFX_THROW_BATTERY = "throw01.wav";
    public static final String SFX_CRATE = "box01.wav";
    public static final String SFX_SAW_TURN = "sawturn01.wav";
    public static final String SFX_CRATE_TURN = "boxturn01.wav";

    @Getter
    @Setter
    private float pitch = 1.0f;
    private HashMap<String, Sound> sounds = new HashMap<>();

    public SoundManager() {
        Arrays.stream(new String[]{
                SFX_JUMP, SFX_MOV, SFX_DIE, SFX_DROP, SFX_SAW, SFX_GENERATOR_UP,
                SFX_POWER_UP, SFX_SWITCH_OFF, SFX_THROW_BATTERY, SFX_CRATE,
                SFX_SAW_TURN, SFX_CRATE_TURN
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
