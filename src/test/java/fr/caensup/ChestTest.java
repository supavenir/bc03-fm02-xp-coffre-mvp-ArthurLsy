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
}