package entities;

import java.util.UUID;

public class Item {
    private final UUID id = UUID.randomUUID();
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
