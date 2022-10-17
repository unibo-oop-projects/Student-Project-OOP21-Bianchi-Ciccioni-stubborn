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
    
    // TODO riempire la lista this.scores con i dati del file .txt degli scores
    @Override
    public List<String> getAllScores() {
        return this.scores;
    }

    @Override
    public String getScore() {
        String score = readScoreFromFile();
        return score;
    }
    
    private String readScoreFromFile() { 
        // format name:score (ex: Marco:100)
        
        FileReader readFile = null;
        BufferedReader reader = null;
        
        try {
            readFile = new FileReader("score.txt");
            reader = new BufferedReader(readFile); // TODO change this method for read all line of the file .txt
            return reader.readLine(); // for now this method return the first line of the file
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
    
    private void writeScoreIntoFile(String score) {
        File scoreFile = new File("score.txt"); // create new directory scores
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

}
