package com.java.dungeon;

import com.java.dungeon.object.item.Item;

import java.util.ArrayList;

public class Inventory {
    private final ArrayList<Item> inventory;
    private final int size;

    public Inventory(int size) {
        this.inventory = new ArrayList<>();
        this.size = size;
    }

    public boolean addItem(Item item) {
        if (inventory.size() < size) {
            return inventory.add(item);
        }
        return false;
    }

    public boolean removeItem(Item item) {
        return inventory.remove(item);
    }

    public Item getItem(int slot) {
        if (slot >= inventory.size()) return null;
        return inventory.get(slot);
    }

    public ArrayList<Item> getInventory() {
        return new ArrayList<Item>(inventory);
    }
}
