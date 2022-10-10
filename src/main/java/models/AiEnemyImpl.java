package models;

import java.util.Random;

public class AiEnemyImpl implements AiEnemy {

    @Override
    public Pair<Integer, Integer> move(Pair<Integer, Integer> position) {
        Random r = new Random();
        int randomSelect = r.nextInt(4);
        
        switch(randomSelect) {
        case 0:
          // code block
          break;
        case 1:
          // code block
          break;
        case 2:
            // code block
            break;
        case 3:
            // code block
            break;
      }
        
        position = new Pair<>(position.getX()+direction.getX(), position.getY()+direction.getY()); 
        return position;
        
        /*
         * 
         * */
    }

}
