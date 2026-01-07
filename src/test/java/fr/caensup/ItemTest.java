package fr.caensup;

import entities.Chest;
import entities.Item;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class ItemTest extends TestCase {

    @Test
    public void testGetValueItem(){
        Item item = new Item("potion", 10, 5);
        assertEquals(item.getValue(), 10);
    }

    @Test
    public void testGetWeightItem(){
        Item item = new Item("potion", 10, 5);
        assertEquals(item.getWeight(), 5);
    }

    @Test
    public void testItemRarity() {
        Item item = new Item("Potion", 10, 5, true);
        assertEquals(item.getValue(), 20);
    }

}