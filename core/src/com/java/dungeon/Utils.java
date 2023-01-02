package com.java.dungeon;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.java.dungeon.rooms.JsonBaseRoom;

public class Utils {
    public static JsonBaseRoom loadRoomFromJson(FileHandle file) {
        return new Json().fromJson(JsonBaseRoom.class, file);
    }

    //    public static void objectToJsonString() {
//        Json json = new Json();
//        JsonBaseRoom room = new JsonBaseRoom();
//        room.number = 1;
//        room.exits = new Array<>();
//        room.music = Sounds.LEVEL_THEME;
//        room.exits.add(new ExitObject(Rooms.ROOM_2));
//        room.background = Backgrounds.ROOM_1;
//        json.setOutputType(JsonWriter.OutputType.json);
//        System.out.println(json.prettyPrint(room));
//    }
}
