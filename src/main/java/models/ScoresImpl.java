package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoresImpl implements Scores {

    private List<String> scores = new ArrayList<>();

    @Override
    public void setScore(String score) {
        writeScoreIntoFile(score);
        this.scores.add(score);
    }
    
    @Override
    public List<String> getAllScores() {
        return this.scores;
    }
    
    
    @Override
    public String getScore() {
        String score = readScoreFromFile();
        return score;
    }
    
    
    private void writeScoreIntoFile(String score) {
        File scoreFile = new File("score.txt");
        if(!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter writeFile = null;
        BufferedWriter writer = null;
        try {
            writeFile = new FileWriter(scoreFile);
            writer = new BufferedWriter(writeFile);
            writer.write(score);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) {
                    writer.close(); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    private String readScoreFromFile() { 
        // format name:score (ex: Marco:100)
        
        FileReader readFile = null;
        BufferedReader reader = null;
        
        try {
            readFile = new FileReader("score.txt");
            reader = new BufferedReader(readFile); 
            return reader.readLine();
        } catch (Exception e) {
            return "0";
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    

}
