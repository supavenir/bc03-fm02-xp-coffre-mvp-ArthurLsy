package fr.caensup;

import entities.Chest;
import entities.Item;
import exceptions.ChestException;
import exceptions.*;
import junit.framework.TestCase;

import java.lang.Exception;
import java.util.List;
import java.util.concurrent.locks.Lock;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChestTest extends TestCase {

    public void testListExistingChest() throws Exception {
        Item i1 = new Item("Potion", 10, 5);
        Chest c1 = new Chest();
        c1.add(i1);
        assertEquals(List.of(i1), c1.getItems());
    }


    public void testAddExistingObject() throws Exception {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        chest.add(item);

        List<Item> items = chest.getItems();
        assertFalse(items.isEmpty());
        assertTrue(items.contains(item));
    }


    public void testRemoveExistingObject() throws Exception {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        chest.add(item);

        chest.remove("Potion");

        List<Item> items = chest.getItems();
        assertFalse(items.contains(item));
        assertTrue(items.isEmpty());
    }

    public void testRemoveInexistingObject() throws Exception {
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

    public void testDoubleItemInChest() throws Exception {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        chest.add(item);
        assertThrows(DuplicateItemException.class, () -> {
            chest.add(item);
        });
        assertEquals(1, chest.getItemCount());
    }

    public void testGetWeight() throws Exception {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        assertEquals(0, chest.getWeight());
        chest.add(item);
        assertEquals(item.getWeight(), chest.getWeight());
    }

    public void testGetTotalValue() throws Exception {
        Chest chest = new Chest();
        Item i1 = new Item("Potion", 10, 2);
        Item i2 = new Item("Baguette", 15, 2);
        chest.add(i1);
        chest.add(i2);
        assertEquals(25, chest.getValue());
    }

    public void testGetValueChest() throws Exception {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        Item item1 = new Item("Potions", 20, 5);
        chest.add(item);
        chest.add(item1);
        assertEquals(30, chest.getValue());
    }
    public void testGetValueChestWithRareItem() throws Exception {
        Chest chest = new Chest();
        Item item = new Item("Potion", 10, 5);
        Item item1 = new Item("Potions", 20, 5, true);
        chest.add(item);
        chest.add(item1);

        assertEquals(chest.getValue(), 50);
    }

    public void testLockedTest() throws Exception {
        Chest chest = new Chest();
        chest.close();
        assertTrue(chest.isLocked());
        Item item = new Item("Potion", 1, 1);
        assertThrows(LockChestException.class, () -> {
            chest.add(item);
        });
        assertTrue(chest.isLocked());
        assertTrue(chest.isEmpty());
        chest.open();
        assertFalse(chest.isLocked());
        chest.add(item);
        assertFalse(chest.isEmpty());

    }

    public void testTransferToClosedChest() throws Exception {
        Chest chest = new Chest();
        Chest chest2 = new Chest();
        Item item = new Item("Potion", 10, 5);
        chest.add(item);
        chest.close();
        assertThrows(LockChestException.class, () -> {
            chest.transfer("PotionA", chest2);
        });
        chest.open();
    }
    public void testTransferToDuplicateItemInChest() throws Exception {
        Chest chest1 = new Chest();
        Chest chest2 = new Chest();
        Item item = new Item("Potion", 10, 5);
        chest1.add(item);
        chest2.add(item);
        assertThrows(DuplicateItemException.class, () -> {
            chest1.transfer("Potion", chest2);
        });
    }

    public void testTransfer() throws Exception {
        Chest chest1 = new Chest();
        Chest chest2 = new Chest();
        Item item = new Item("Potion", 10, 5);
        chest1.add(item);
        chest1.transfer("Potion", chest2);
        assertEquals(0, chest1.getItemCount());
        assertEquals(item, chest2.getItem("Potion"));
    }

    public void testSizeLimit() throws Exception {
        Chest chest = new Chest();
        chest.setMaximumCapacity(50);
        Item i1 = new Item("Potion", 10, 100);
        assertThrows(CapacityChestException.class, () -> {
            chest.add(i1);
        });
    }

    public void testSortByValueAsc() throws Exception {
        Chest chest = new Chest();
        Item itemA = new Item("Potion", 10, 5);
        Item itemB = new Item("Baguette", 2, 5);
        Item itemC = new Item("Tapis", 40, 5);
        chest.add(itemA);
        chest.add(itemB);
        chest.add(itemC);
        chest.sortByValue("ascending");

        assertTrue(chest.getItems().get(0).getValue() < chest.getItems().get(1).getValue());
        assertTrue(chest.getItems().get(1).getValue() < chest.getItems().get(2).getValue());
        assertTrue(chest.getItems().get(0).getValue() < chest.getItems().get(2).getValue());


    }

    public void testSortByValueDesc() throws Exception {
        Chest chest = new Chest();
        Item itemA = new Item("Potion", 10, 5);
        Item itemB = new Item("Baguette", 2, 5);
        Item itemC = new Item("Tapis", 40, 5);
        chest.add(itemA);
        chest.add(itemB);
        chest.add(itemC);
        chest.sortByValue("descending");

        assertTrue(chest.getItems().get(0).getValue() > chest.getItems().get(1).getValue());
        assertTrue(chest.getItems().get(1).getValue() > chest.getItems().get(2).getValue());
        assertTrue(chest.getItems().get(0).getValue() > chest.getItems().get(2).getValue());

    }

    public void testSortByWeightAsc() throws Exception {
        Chest chest = new Chest();
        Item itemA = new Item("Potion", 1, 10);
        Item itemB = new Item("Baguette", 1, 2);
        Item itemC = new Item("Tapis", 1, 40);
        chest.add(itemA);
        chest.add(itemB);
        chest.add(itemC);
        chest.sortByWeight("ascending");

        assertEquals(2, chest.getItems().get(0).getWeight());
        assertEquals(10, chest.getItems().get(1).getWeight());
        assertEquals(40, chest.getItems().get(2).getWeight());

    }

    public void testSortByWeightDesc() throws Exception {
        Chest chest = new Chest();
        Item itemA = new Item("Potion", 1, 10);
        Item itemB = new Item("Baguette", 1, 2);
        Item itemC = new Item("Tapis", 1, 40);
        chest.add(itemA);
        chest.add(itemB);
        chest.add(itemC);
        chest.sortByWeight("descending");

        assertEquals(2, chest.getItems().get(2).getWeight());
        assertEquals(10, chest.getItems().get(1).getWeight());
        assertEquals(40, chest.getItems().get(0).getWeight());

    }


    public void testSortByNameAsc() throws Exception {
        Chest  chest = new Chest();
        Item item1 = new Item("Zebra");
        Item item2 = new Item("Apple");
        Item item3 = new Item("Mango");
        chest.add(item1);
        chest.add(item2);
        chest.add(item3);
        chest.sortByName("ascending");

        assertEquals("Apple", chest.getItems().get(0).getName());
        assertEquals("Mango", chest.getItems().get(1).getName());
        assertEquals("Zebra", chest.getItems().get(2).getName());
    }

    public void testSortByNameDesc() throws Exception {
        Chest  chest = new Chest();
        Item item1 = new Item("Zebra");
        Item item2 = new Item("Apple");
        Item item3 = new Item("Mango");
        chest.add(item1);
        chest.add(item2);
        chest.add(item3);
        chest.sortByName("descending");

        assertEquals("Zebra", chest.getItems().get(0).getName());
        assertEquals("Mango", chest.getItems().get(1).getName());
        assertEquals("Apple", chest.getItems().get(2).getName());

    }

}