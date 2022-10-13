package models;

public interface AiEnemy {
    
    public Pair<Integer, Integer> move(Pair<Integer, Integer> playerPosition, Pair<Integer, Integer> position);
    
}
