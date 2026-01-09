package fr.caensup;

import entities.Chest;
import entities.Item;
import exceptions.BadWeightItemException;
import exceptions.ItemException;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class ItemTest extends TestCase {

    public void testGetValueItem() throws ItemException {
        Item item = new Item("potion", 10, 5);
        Item item2 = new Item("potion", 10, 5, true);
        assertEquals(10, item.getValue());
        assertEquals(20, item2.getValue());
    }


    public void testGetWeightItem() throws ItemException {
        Item item = new Item("potion", 10, 5);
        assertEquals(5, item.getWeight());
    }

    public void testWrongWeight() throws ItemException {
        assertThrows(BadWeightItemException.class, () -> {
            new Item("potion", 10, -5);
        });
    }


    public void testItemRarity() throws ItemException {
        Item item = new Item("Potion", 10, 5, true);
        assertEquals(20, item.getValue());
    }


}