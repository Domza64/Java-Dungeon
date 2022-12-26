package com.java.dungeon.sounds;

public enum SoundEffects {
    GUITAR_EFFECT("sounds/effects/guitar_effect.mp3"),
    SUSPEND_EFFECT("sounds/effects/suspend_effect.mp3");

    private final String label;

    SoundEffects(String label) {
        this.label = label;
    }

    public String getPath() {
        return label;
    }
}
