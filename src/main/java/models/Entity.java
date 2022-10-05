package models;

public interface Entity {
    Pair<Integer, Integer> getPosition();
    
    void setPosition(Pair<Integer, Integer> position);
}