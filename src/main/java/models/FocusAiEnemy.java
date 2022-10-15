package models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class FocusAiEnemy implements AiEnemy {

    @Override
    public Pair<Integer, Integer> move(Map<Pair<Integer,Integer>, Optional<Entity>> board, Pair<Integer, Integer> playerPosition, Pair<Integer, Integer> position) {
        List<MOVEMENT> movements = Arrays.asList(MOVEMENT.UP, MOVEMENT.DOWN, MOVEMENT.LEFT, MOVEMENT.RIGHT);
        Map<Pair<Integer, Integer>, Integer> open = getDirections(movements, position);
        Pair<Integer, Integer> q = new Pair<>(-1, -1);
        int g;
        int fMin = 100000;
        
        for(var e : open.entrySet()) {
            g = ((e.getKey().getX() - playerPosition.getX()) + (e.getKey().getY() - playerPosition.getY()));
            e.setValue(g);
        }
        
        for(var e : open.entrySet()) {
            if(e.getValue() < fMin) {
                fMin = e.getValue();
                q = e.getKey();
            }
        }
        
        return q;
    }
    
    private Map<Pair<Integer, Integer>, Integer> getDirections(List<MOVEMENT> movements, Pair<Integer, Integer> position) {
        Map<Pair<Integer, Integer>, Integer> directions = new HashMap<>();
        for(MOVEMENT i : movements) {
            directions.put(new Pair<>(position.getX()+i.x, position.getY()+i.y), 0);
        }
        return directions;
    }

}
