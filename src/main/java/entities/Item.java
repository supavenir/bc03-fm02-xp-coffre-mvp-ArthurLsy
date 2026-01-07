package entities;

import java.util.UUID;

public class Item {
    private final UUID id = UUID.randomUUID();
    private final int value;
    private final int weight;
    private String name;

    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public String getName(){
        return this.name;
    }

    public int getValue(){
        return this.value;
    }

    public int getWeight(){
        return this.weight;
    }


}
