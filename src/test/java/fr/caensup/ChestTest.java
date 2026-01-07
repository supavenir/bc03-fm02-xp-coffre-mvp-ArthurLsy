package fr.caensup;

import entities.Chest;
import entities.Item;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChestTest extends TestCase {

    @Test

    public void testListExistingChest() {
        Item i1 = new Item("Potion");
        Chest c1 = new Chest();
        c1.add(i1);
        assertEquals(List.of(i1), c1.getItems());
    }


    @Test
    public void testAddExistingObject() {
        Chest chest = new Chest();
        Item item = new Item("Potion");
        chest.add(item);

        List<Item> items = chest.getItems();
        assertFalse(items.isEmpty());
        assertTrue(items.contains(item));
    }


    @Test
    public void testRemoveExistingObject() {
        Chest chest = new Chest();
        Item item = new Item("Potion");
        chest.add(item);

        chest.remove("Potion");

        List<Item> items = chest.getItems();
        assertFalse(items.contains(item));
        assertTrue(items.isEmpty());
    }

    @Test
    public void testRemoveInexistingObject() {
        Chest chest = new Chest();
        Item item = new Item("Potion");
        Item item2 = new Item("Potion2");
        chest.add(item);
        chest.add(item2);

        chest.remove("Epee");

        List<Item> items = chest.getItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(item));
    }

    @Test
    public void testDoubleItemInChest() {
        Chest chest = new Chest();
        Item item = new Item("Potion");
        chest.add(item);
        chest.add(item);
        assertEquals(chest.getItemCount(), 1);
    }

    @Test
    public void testGetWeight() {
        Chest chest = new Chest();
        Item item = new Item("Potion");
        chest.add(item);
        assertEquals(chest.getWeight(), 1);
    }

    @Test
    public void testGetValueChest() {
        Chest chest = new Chest();
        Item item = new Item("Potion");
        chest.add(item);
        assertEquals(Chest.getValue(), 1);
    }

    @Test
    public void testLockedTest() {
        Chest chest = new Chest();
        assertEquals(Chest.isLocked(), false);
    }

    @Test
    public void testTransfer() {
        Chest chest2 = new Chest();
        Chest chest1 = new Chest();
        assertEquals(chest1, chest2);
        Item item = new Item("Potion");
        chest1.add(item);
        chest1.transfer(item, chest2);

        assertEquals(chest1.getItemCount(), 0);

        assertEquals(chest2.getItemCount(), 1);
    }



}