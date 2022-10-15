package entitiesTest;

import org.junit.jupiter.api.Test;

import models.Collectable;
import models.CollectableImpl;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CollectableTest {
    
    List<Collectable> listCollectables = new ArrayList<>();
    
    @Test
    public void testCollectable() {
        for(int i = 0; i < 10; i++) {
            listCollectables.add(new CollectableImpl());
        }
        listCollectables.forEach(col -> System.out.println(col.getPoints()));
    }

}
