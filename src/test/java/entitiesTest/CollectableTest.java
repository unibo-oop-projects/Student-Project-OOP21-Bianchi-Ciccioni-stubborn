package entitiesTest;

import org.junit.jupiter.api.Test;

import models.Collectable;
import models.CollectableImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollectableTest {
    
    static int MIN = 60;
    static int NUM_COLL = 10;
    List<Collectable> listCollectables = new ArrayList<>();
    
    @Test
    public void testCollectable() {
        for(int i = 0; i < NUM_COLL; i++) {
            listCollectables.add(new CollectableImpl());
        }
        assertEquals(NUM_COLL, listCollectables.size());
        listCollectables.removeIf(col -> col.getPoints() < MIN);
        Iterator<Collectable> iter = listCollectables.iterator();
        while(iter.hasNext()) {
            assertTrue(iter.next().getPoints() > MIN);
        }
    }

}
