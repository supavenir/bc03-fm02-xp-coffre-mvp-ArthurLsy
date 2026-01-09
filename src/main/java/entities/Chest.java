package entities;

import exceptions.CapacityChestException;
import exceptions.DuplicateItemException;
import exceptions.LockChestException;
import exceptions.NotFoundItemException;

import java.lang.reflect.Array;
import java.util.*;

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

    public int getLimit(){
        return this.limit;
    }
    public void setLimit(int limit){
        this.limit = limit;
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
        if (isLocked()) throw new LockChestException();
        if (this.limit < this.getWeight() + item.getWeight()) throw new CapacityChestException(this.limit);
        if (this.isDouble(item.getName())) throw new DuplicateItemException();
        this.items.add(item);

    }

    public void remove(String name) {
        this.items.removeIf(item -> item.getName().equals(name));
    }

    public void transfer(String name, Chest givenChest) throws Exception {
        if (isLocked()) throw new LockChestException();
        if (givenChest.getLimit() < this.getWeight() + getItem(name).getWeight()) throw new CapacityChestException(givenChest.getLimit());
        if (givenChest.isDouble(name)) throw new DuplicateItemException();

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


    public void sortByValue(String order) {
        this.items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item p1, Item p2) {
                if (order.equals("ascending")) {
                    return Integer.compare(p1.getValue(), p2.getValue());
                } else {
                    return Integer.compare(p2.getValue(), p1.getValue());
                }
            }
        });
    }

    public void sortByName(String order) {
        this.items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item p1, Item p2) {
                if (order.equals("ascending")) {
                    return p1.getName().compareTo(p2.getName());
                } else {
                    return p2.getName().compareTo(p1.getName());
                }
            }
        });
    }

    public void sortByWeight(String order) {
        this.items.sort(new Comparator<Item>() {
        @Override
        public int compare(Item p1, Item p2) {
            if (order.equals("ascending")) {
                return Integer.compare(p1.getWeight(), p2.getWeight());
            } else {
                return Integer.compare(p2.getWeight(), p1.getWeight());
            }
        }
    });
    }
}

