package entities;

import java.util.UUID;

public class Item {
    private final UUID id = UUID.randomUUID();
    private String name;
    private int weight;
    private int value;

    public Item(String name) {
        this.name = name;
    }
    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;

    }

    public String getName(){
        return this.name;
    }

    public int getValue() {
        return 1;
    }

    public int getWeight() {
        return 1;
    }
}
