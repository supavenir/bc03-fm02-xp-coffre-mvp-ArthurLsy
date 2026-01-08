package entities;

import java.util.UUID;

public class Item {
    private final UUID id = UUID.randomUUID();
    private final int value;
    private final int weight;
    private final String name;
    private boolean rare;

    public Item(String name, int value, int weight, boolean rare) {
        this.name = name;
        this.rare = rare;
        this.value =  value;
        this.weight = weight;
    }

    public Item(String name, int value, int weight) {
        this(name, value, weight, false);
    }

    public Item(String name) {
        this(name, 1, 1);
    }

    public String getName(){
        return this.name;
    }

    public int getValue(){
        return this.rare ? this.value*2 : this.value;
    }

    public int getWeight(){
        return this.weight;
    }


}
