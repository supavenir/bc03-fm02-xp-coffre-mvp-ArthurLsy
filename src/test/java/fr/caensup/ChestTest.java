package fr.caensup;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChestTest extends TestCase {

    @Test
    public void testAddExistingObject() {
        Chest chest = new Chest();
        Item item = new Item("Potion");
        chest.add(item);

        List<Item> items = chest.list();
        assertFalse(items.isEmpty(), "Chest is empty");
        assertTrue(items.contains(item), "Item was added");
    }


    @Test
    public void testRemoveExistingObject() {
        Chest chest = new Chest();
        Item item = new Item("Potion");
        chest.add(item);

        chest.remove("Potion");

        List<Item> items = chest.list();
        assertFalse(items.contains(item), "Item is already here");
        assertTrue(items.isEmpty(), "Chest is empty");
    }

    @Test
    public void testRemoveInexistingObject() {
        Chest chest = new Chest();
        Item item = new Item("Potion");
        chest.add(item);

        chest.remove("Epee");

        List<Item> items = chest.list();
        assertEquals(1, items.size());
        assertTrue(items.contains(item));
    }
}