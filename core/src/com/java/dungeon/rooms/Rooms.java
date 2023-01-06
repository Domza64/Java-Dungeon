package com.java.dungeon.rooms;

public enum Rooms {
    ROOM_1("rooms/room_1.json"),
    ROOM_2("rooms/room_2.json"),
    ROOM_3("rooms/room_3.json"),
    ROOM_4("rooms/room_4.json"),
    ROOM_5("rooms/room_5.json"),
    ROOM_6("rooms/room_6.json");
    private final String path;

    Rooms(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
