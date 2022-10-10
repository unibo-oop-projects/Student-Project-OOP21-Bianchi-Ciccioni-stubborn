package models;

public enum COLLECTABLETYPE {

    DIAMOND(100),
    COIN(50),
    CHALICE(150),
    CROWN(200),
    BAGOFCOINS(200);
    
    private final int points;
    
    COLLECTABLETYPE(int points){
        this.points = points;
    }
}
