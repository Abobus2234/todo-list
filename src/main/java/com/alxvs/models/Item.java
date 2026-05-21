package com.alxvs.models;

public class Item {
    private int id;
    private boolean isMarked;
    private String description;

    public static Item createItem() {
        return new Item();
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    private Item() {
    }

    public Item(int id, String description, boolean isMarked) {
        this.id = id;
        this.isMarked = isMarked;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return (this.isMarked ? "* " : "  " ) + "[id] = " + this.id + "; [description] = " + this.description;
    }

}
