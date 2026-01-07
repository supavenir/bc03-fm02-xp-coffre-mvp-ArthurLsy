package entities;

import java.util.UUID;

public class Item {
    private final UUID id = UUID.randomUUID();
    private final int value;
    private final int weight;
    private String name;

    public Item(String name, int value, int weight, boolean rare) {
        this.name = name;
        this.value = rare ? value * 2 : value;
        this.weight = weight;
    }

    public Item(String name, int value, int weight) {
        this(name, value, weight, false);
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
