package com.java.dungeon.sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    private Music currentMusic;
    private Sound soundEffect;
    private Sounds lastPlayed;

    public void play(Sounds sound) {
        if (lastPlayed == sound) return;
        if (currentMusic != null && currentMusic.isPlaying()) currentMusic.dispose();
        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(sound.getPath()));
        currentMusic.setLooping(true);
        currentMusic.setVolume(sound.getVolume());
        currentMusic.play();
        lastPlayed = sound;
    }

    public void playEffect(SoundEffects effect) {
        soundEffect = Gdx.audio.newSound(Gdx.files.internal(effect.getPath()));
        soundEffect.play();
    }

    public void dispose() {
        if (currentMusic != null) currentMusic.dispose();
        if (soundEffect != null) soundEffect.dispose();
    }

    public void stopPlaying() {
        if (currentMusic.isPlaying() && currentMusic != null) currentMusic.dispose();
        if (soundEffect != null) soundEffect.dispose();
    }
}
