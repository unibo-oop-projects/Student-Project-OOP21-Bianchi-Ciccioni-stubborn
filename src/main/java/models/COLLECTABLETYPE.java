package models;

import java.util.Random;

public enum COLLECTABLETYPE {

    DIAMOND(100),
    COIN(50),
    CHALICE(150),
    CROWN(200),
    BAGOFCOINS(200);
    
    private final int points;
    
    public int getPoints()  {
        return this.points;
    }
    
    public static COLLECTABLETYPE getRandomType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
    
    COLLECTABLETYPE(int points){
        this.points = points;
    }
}
