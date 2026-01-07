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
        Item item = new Item("potion", 10);
        assertEquals(item.getValue(), 10);
    }

}