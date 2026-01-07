package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chest {
    private UUID id;
    private int weight;
    private ArrayList<Item> items;

    public Chest(Item i1) {
        this.setItem(i1);
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItem(Item i1) {
        this.items.add(i1);
    }

    public void setItems(List<Item> items) {
    }

    public void add(Item item) {
    }

    public void remove(String potion) {
    }
}

