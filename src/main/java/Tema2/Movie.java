package Tema2;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String title;
    private String director;
    private int year;
    private List<String> actors;
    private String category;
    private List<String> keywords;

    public Movie(String title, String director, int year, List<String> actors, String category, List<String> keywords) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.actors = actors;
        this.category = category;
        this.keywords = keywords;
    }

    public Movie(String code) {
        decode(code);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String encode(){
        String res = "{";

        res += title + ";";
        res += director + ";";
        res += String.valueOf(year) + ";";
        res += "{";
        for (int i = 0; i < actors.size(); i++){
            res += actors.get(i) + ":";
        }
        res += "};";
        res += category + ";";
        res += "{";
        for (int i = 0; i < keywords.size(); i++){
            res += keywords.get(i) + ";";
        }
        res += "};";

        res += "}";
        return res;
    }

    private void decode(String code){
        String[] splitMovie = code.split(";");
        title = splitMovie[0].replace("{","");
        director = splitMovie[1];
        year = Integer.decode(splitMovie[2]);

        splitMovie[3].replace("{","");
        splitMovie[3].replace("}","");
        String[] splitMovieActors = splitMovie[3].split(":");
        actors = new ArrayList<>();
        for (String aux : splitMovieActors) {
            actors.add(aux);
        }

        category = splitMovie[4];

        splitMovie[5].replace("{","");
        splitMovie[5].replace("}","");
        String[] splitMovieKeywords = splitMovie[5].split(":");
        keywords = new ArrayList<>();
        for (String aux : splitMovieActors) {
            keywords.add(aux);
        }
    }

    @Override
    public String toString() {
        return title + "|" + director + "|" + year + "|" + category + "|";
    }
}
