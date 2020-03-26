package Tema2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepo {
    private List<Movie> data = new ArrayList<>();
    private String FILENAME;

    public MovieRepo(String filename){
        FILENAME = filename;
        readFile(FILENAME);

    }

    public void add(String title, String director, int year, List<String> actors, String category, List<String> keywords){
        data.add(new Movie(title, director, year, actors, category, keywords));
        appendFile(FILENAME, data.get(data.size()-1).encode());
    }

    public List<Movie> getData(){
        return new ArrayList<>(data);
    }

    private void readFile(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                data.add(new Movie(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendFile(String filename, String textToAppend) {
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(textToAppend);
            printWriter.close();
        } catch (Exception err) {  }
    }

}
