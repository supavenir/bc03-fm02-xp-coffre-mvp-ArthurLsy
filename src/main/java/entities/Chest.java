package entities;

import exceptions.CapacityChestException;
import exceptions.DuplicateItemException;
import exceptions.LockChestException;
import exceptions.NotFoundItemException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chest {
   private UUID id;
   private int weight;
   private final List<Item> items =  new ArrayList<>();
   private boolean lock = false;
   private int limit = 100;


    public Chest() {
    }

    public int getValue() {
        int totalValue = 0;

        for (Item item : this.items) {
            totalValue += item.getValue();
        }

        return totalValue;
    }

    public int getWeight() {
        int totalWeight = 0;
        if (items.isEmpty()) { return totalWeight; }

        for (Item item : this.items) {
            totalWeight += item.getWeight();
        }

        return totalWeight;
    }

    public boolean isEmpty(){
        return this.items.isEmpty();
    }

    public boolean isLocked() {
        return this.lock;
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

    public void add(Item item) throws Exception {
        this.security(item);
        this.items.add(item);

    }

    public void remove(String name) {
        this.items.removeIf(item -> item.getName().equals(name));
    }

    public void transfer(String name, Chest givenChest) throws Exception {
        this.security(getItem(name));

        Item toTransfer = this.getItem(name);
        if(toTransfer != null){
            givenChest.add(toTransfer);
            this.remove(name);
        } else  {
            throw new NotFoundItemException();
        }

    }

    public boolean isDouble(String name) {
        return this.items.stream().anyMatch(item -> item.getName().equals(name));
    }

    public void close() {
        this.lock = true;
    }
    public void open() {
        this.lock = false;
    }

    public void setMaximumCapacity(int i) {
        this.limit = i;
    }

    public Item getItem(String name) {
        return this.items.stream().filter(i -> i.getName().equals(name)).findFirst().orElse(null);
    }

    public void security(Item item) throws Exception {
        if (isLocked()) throw new LockChestException();
        if (this.limit < this.getWeight() + item.getWeight()) throw new CapacityChestException(this.limit);
        if (this.isDouble(item.getName())) throw new DuplicateItemException();

    }
}

