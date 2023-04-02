package com.java.dungeon.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.entity.player.Player;
import com.java.dungeon.rooms.Rooms;
import com.java.dungeon.screens.GameScreen;
import com.java.dungeon.sounds.SoundEffects;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.exit;

public class ExitObject extends GameObject {
    private final Rooms leadsTo;
    private boolean isUnlocked;
    public SoundEffects effect;


    public ExitObject() {
        super(new Texture(Gdx.files.internal("textures/objects/exit_texture.png")));
        this.leadsTo = null;
    }

    public Rooms getLeadsTo() {
        return leadsTo;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void unlock() {
        isUnlocked = true;
    }

    @Override
    public boolean onCollisionWith(JavaDungeonGame game, GameScreen gameScreen) {
        if (getLeadsTo() != null) {
            if (isUnlocked()) {
                game.soundManager.playEffect(effect);
                gameScreen.loadRoom(getLeadsTo());
            }
        }
        return true;
    }
}
