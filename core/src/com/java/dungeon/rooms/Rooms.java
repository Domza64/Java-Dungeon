package com.java.dungeon.rooms;

public enum Rooms {
    ROOM_1("rooms/room_1.json"),
    ROOM_2("rooms/room_2.json"),
    ROOM_3("rooms/room_3.json");

    private final String path;

    Rooms(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
