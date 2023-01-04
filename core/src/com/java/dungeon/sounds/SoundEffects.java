package com.java.dungeon.sounds;

public enum SoundEffects {
    GUITAR_EFFECT("sounds/effects/guitar_effect.mp3"),
    SUSPEND_EFFECT("sounds/effects/suspend_effect.mp3"),
    PAUSE_EFFECT("sounds/effects/pause.mp3"),
    POP_EFFECT("sounds/effects/pop.mp3"),

    COOL_DOOR_EFFECT("sounds/effects/cool_door.mp3"),
    DOOR_EFFECT("sounds/effects/door.mp3");

    private final String label;

    SoundEffects(String label) {
        this.label = label;
    }

    public String getPath() {
        return label;
    }
}
