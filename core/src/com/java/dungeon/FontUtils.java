package com.java.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontUtils {
    public enum Fonts {
        BITMGOTHIC("fonts/bitmgothic/Bitmgothic.ttf"),
        MINECRAFT("fonts/minecraft/Minecraft.ttf");

        public final String filePath;

        Fonts(String filePath) {
            this.filePath = filePath;
        }
    }
    public static BitmapFont getFont(Fonts font, int size, Color color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(font.filePath));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        BitmapFont newFont = generator.generateFont(parameter);
        generator.dispose();
        return newFont;
    }
}
