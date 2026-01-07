package entities;

import java.util.UUID;

public class Item {
    private final UUID id = UUID.randomUUID();
    private final int value;
    private String name;

    public Item(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return this.name;
    }

    public int getValue(){
        return this.value;
    }
}
