package com.java.dungeon.rooms;

import com.java.dungeon.JavaDungeonGame;
import com.java.dungeon.gameObjects.entity.Enemy;
import com.java.dungeon.screens.GameScreen;

public class CustomRoom6 extends BaseRoom {
    @Override
    public void onLoad(JavaDungeonGame game, GameScreen screen) {
        super.onLoad(game, screen);
        System.out.println("YOOOOOOOO - Custom Room loaded successfully!!!!!");
        Enemy boss = new Enemy();
        boss.width = 180;
        boss.height = 320;
        boss.setSpeed(1);
        game.entities.add(boss);
    }
}
