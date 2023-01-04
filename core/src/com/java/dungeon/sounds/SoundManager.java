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

    /**
    *  Decreases volume to x percent of current volume. Example current volume is 0.8f and decreaseVolume is called with 50, volume will be set to 0.4f
    * */
    public void decreaseVolume(int percent) {
        if (currentMusic != null && currentMusic.isPlaying()) {
            float volume = (float) (currentMusic.getVolume() * (percent * 0.01));
            currentMusic.setVolume(volume);
        }
    }

    /**
     *  Resets volume to original Sound volume
     * */
    public void resetVolume() {
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.setVolume(lastPlayed.getVolume());
        }
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
