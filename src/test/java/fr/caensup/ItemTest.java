package fr.caensup;

import entities.Chest;
import entities.Item;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class ItemTest extends TestCase {


    public void testlisteItems() throws Exception {
        Chest c1 = new Chest(null);
        assertNull(c1.getItems());
        Item i1 = new Item("Potion");
        Chest c2 = new Chest(i1);
        List<Item> items = new ArrayList<Item>();
        items.add(i1);
        assertEquals(items,c2.getItems());
    }



}