package models;

import java.util.Map;
import java.util.Optional;

public interface AiEnemy {
    
    public Pair<Integer, Integer> move(Map<Pair<Integer,Integer>,Optional<Entity>> board, Pair<Integer, Integer> playerPosition, Pair<Integer, Integer> position);
    
}
