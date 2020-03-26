package Tema2;

import java.util.List;

public class MovieService {
    private MovieRepo repo;

    public MovieService(MovieRepo repo) {
        this.repo = repo;
    }

    public void addMovie(String title, String director, int year, List<String> actors, String category, List<String> keywords){
        repo.add(title, director, year, actors, category, keywords);
    }

    public String showMovies(){
        String data = "";
        for (Movie aux : repo.getData()) {
            data += aux.toString() + "\n";
        }
        return data;
    }
}
