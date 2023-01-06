package com.java.dungeon.sounds;

public enum Sounds {
    MAIN_THEME("sounds/music/main_theme.mp3", 0.8f),
    BOSS_THEME("sounds/music/boss_theme.mp3", 0.7f),
    LEVEL_THEME("sounds/music/level_theme.mp3", 0.4f);

    private final String label;
    private final float volume;

    Sounds(String label, float volume) {
        this.label = label;
        this.volume = volume;
    }

    public String getPath() {
        return label;
    }

    public float getVolume() {
        return volume;
    }
}
