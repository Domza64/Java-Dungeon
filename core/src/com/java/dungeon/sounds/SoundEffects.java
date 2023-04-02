package com.java.dungeon.sounds;

public enum SoundEffects {
    GUITAR_EFFECT("sounds/effects/guitar_effect.mp3"),
    SUSPEND_EFFECT("sounds/effects/suspend_effect.mp3"),
    PAUSE_EFFECT("sounds/effects/pause.mp3"),
    POP_EFFECT("sounds/effects/pop.mp3"),
    WHIP_EFFECT("sounds/effects/whip.mp3"),
    SWORD_EFFECT("sounds/effects/sword.mp3"),
    COOL_DOOR_EFFECT("sounds/effects/cool_door.mp3"),
    POTION_EFFECT("sounds/effects/potion.mp3"),
    DOOR_EFFECT("sounds/effects/door.mp3");

    private final String label;

    SoundEffects(String label) {
        this.label = label;
    }

    public String getPath() {
        return label;
    }
}
