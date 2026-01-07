package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chest {
   private UUID id;
   private int weight;
   private final List<Item> items =  new ArrayList<>();

    public Chest() {
    }

    public static int getValue() {
        return 1;
    }

    public static boolean isLocked() {
        return false;
    }
    

    public int getWeight() {
        return 1;
    }


    public List<Item> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }

    public UUID getId() {
        return id;
    }

    public void setItem(Item i1) {
        this.items.add(i1);
    }

    public void setItems(List<Item> items) {
    }

    public void add(Item item) {
        this.items.add(item);

    }

    public void remove(String potion) {
    }
}

