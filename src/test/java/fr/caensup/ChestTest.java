package fr.caensup;

import entities.Chest;
import entities.Item;
import exceptions.DuplicateItemException;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChestTest extends TestCase {

    @Test

    public void testListExistingChest() throws DuplicateItemException {
        Item i1 = new Item("Potion", 10, 5);
        Chest c1 = new Chest();
        c1.add(i1);
        assertEquals(List.of(i1), c1.getItems());
    }


    @Test
    public void testAddExistingObject() throws DuplicateItemException {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        chest.add(item);

        List<Item> items = chest.getItems();
        assertFalse(items.isEmpty());
        assertTrue(items.contains(item));
    }


    @Test
    public void testRemoveExistingObject() throws DuplicateItemException {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        chest.add(item);

        chest.remove("Potion");

        List<Item> items = chest.getItems();
        assertFalse(items.contains(item));
        assertTrue(items.isEmpty());
    }

    @Test
    public void testRemoveInexistingObject() throws DuplicateItemException {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        Item item2 = new Item("Potion2", 10, 5);
        chest.add(item);
        chest.add(item2);

        chest.remove("Epee");

        List<Item> items = chest.getItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(item));
    }

    @Test
    public void testDoubleItemInChest() throws DuplicateItemException {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        chest.add(item);
        chest.add(item);
        assertEquals(chest.getItemCount(), 1);
    }

    @Test
    public void testGetWeight() throws DuplicateItemException {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        // Test si coffre vide le poids doit être null
        assertNull(chest.getWeight());
        chest.add(item);
        assertEquals(item.getWeight(), chest.getWeight());
    }

    public void testGetTotalValue() throws DuplicateItemException {
        Chest chest = new Chest();
        Item i1 = new Item("Potion", 2, 10); // 2 = poids , 10 = valeur
        Item i2 = new Item("Potion", 2, 15);
        chest.add(i1);
        chest.add(i2);
        assertEquals(25, chest.getValue());
        assertEquals(chest.getWeight(), 5);
    }

    @Test
    public void testGetValueChest() throws DuplicateItemException {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        Item item1 = new Item("Potions", 20, 5);
        chest.add(item);
        chest.add(item1);
        assertEquals(30, chest.getValue());
    }

    @Test
    public void testLockedTest() {
        Chest chest = new Chest();
        assertEquals(chest.isLocked(), true);
        Item item = new Item("Potion");
        chest.add(item);
        assertEquals(true, chest.isLocked());
        assertEquals(null, chest.getWeight()); // a adapter en fonction de getWeight soit 0 soit null

    }

    @Test
    public void testTransfer() throws DuplicateItemException {
        Chest chest2 = new Chest();
        Chest chest1 = new Chest();
        assertEquals(chest1, chest2);
        Item item = new Item("Potion", 10, 5);
        chest1.add(item);
        chest1.transfer("Potion", chest2);

        assertEquals(0, chest1.getItemCount());

        assertEquals(1, chest2.getItemCount());
        chest1.transfer("Potion", chest2);
        // ici on manque le cas ou "Potion" existe deja dans chest2, dans ce cas le transfere est annuler
        // verifier que item 'potion' n'est pas dans chest2, ensuite vérifier les sizes des coffres :
        assertEquals(false, chest2.isDoubled("Potion"));
        assertEquals(0, chest1.getItemCount());
        assertEquals(1, chest2.getItemCount());
    }



}