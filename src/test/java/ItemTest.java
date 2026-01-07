import entities.Chest;
import entities.Item;
import junit.framework.TestCase;

public class ItemTest extends TestCase {


    public void testlisteItems() throws Exception {
        Chest c1 = new Chest(null);
        assertEquals(null,c1.getItems());
        Item i1 = new Item();
        Chest c2 = new Chest(i1);
        assertEquals(null,c2.getItems());
    }



}