package Tema2;

import java.util.List;

public class MovieService {
    private MovieRepo repo;

    public MovieService(MovieRepo repo) {
        this.repo = repo;
    }

    public void addMovie(String title, String director, int year, List<String> actors, String category, List<String> keywords) throws Exception{
        if (year < 1878 || year > 2020) {
            throw new Exception("Eroare! Anul nu este unul valid!");
        } else if(title.length() < 1 || title.length() > 99) {
            throw new Exception("Eroare! Titlul nu este unul valid!");
        } else {
            repo.add(title, director, year, actors, category, keywords);
        }
    }

    public String showMovies(){
        String data = "";
        for (Movie aux : repo.getData()) {
            data += aux.toString() + "\n";
        }
        return data;
    }
}
