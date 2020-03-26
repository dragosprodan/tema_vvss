package Tema2;

public class MainTema2 {
    public static void main(String[] args) {
        //va trebui sa schimbi cu locatia din proiectul tau cand il rulezi
        MovieRepo r = new MovieRepo("/Users/macbook/tema_vvss/src/main/resources/tema2/movie.txt");
        MovieService s = new MovieService(r);
        MovieUI ui = new MovieUI(s);
        ui.run();
    }
}
