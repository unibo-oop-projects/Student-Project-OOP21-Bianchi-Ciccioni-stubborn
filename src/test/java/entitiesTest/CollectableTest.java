package entitiesTest;

import org.junit.jupiter.api.Test;

import models.Collectable;
import models.CollectableImpl;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CollectableTest {
    
    static int NUM_COLL = 10;
    List<Collectable> listCollectables = new ArrayList<>();
    
    @Test
    public void testCollectable() {
        for(int i = 0; i < NUM_COLL; i++) {
            listCollectables.add(new CollectableImpl());
        }
        listCollectables.forEach(col -> System.out.println(col.getPoints()));
    }

}
