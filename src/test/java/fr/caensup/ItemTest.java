package fr.caensup;

import entities.Chest;
import entities.Item;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class ItemTest extends TestCase {

    public void testGetValueItem(){
        Item item = new Item("potion", 10, 5);
        assertEquals(10, item.getValue());
    }


    public void testGetWeightItem(){
        Item item = new Item("potion", 10, 5);
        assertEquals(5, item.getWeight());
    }


    public void testItemRarity() {
        Item item = new Item("Potion", 10, 5, true);
        assertEquals(20, item.getValue());
    }

}