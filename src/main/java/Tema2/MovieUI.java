package Tema2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MovieUI {
    private MovieService service;

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public MovieUI(MovieService service) {
        this.service = service;
    }

    private void execute(String command) {
        if (command.equals("1")){
            List<String> movieData = getMovieData();
            try{
                service.addMovie(movieData.get(0),movieData.get(1),Integer.decode(movieData.get(2)),getMovieActors(),movieData.get(3),getMovieKeywords());
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        } else if (command.equals("2")){
            System.out.println(service.showMovies());
        } else if (command.equals("3")){
            System.exit(0);
        } else {
            System.out.println("Eroare la citire!");
        }
    }

    private List<String> getMovieData(){
        List<String> aux = new ArrayList<>();
        System.out.println("Introdu titlul: ");
        try {
            aux.add(reader.readLine());
        } catch (Exception err) {  }
        System.out.println("Introdu directorul: ");
        try {
            aux.add(reader.readLine());
        } catch (Exception err) {  }
        System.out.println("Introdu anul: ");
        try {
            aux.add(reader.readLine());
        } catch (Exception err) {  }
        System.out.println("Introdu categoria: ");
        try {
            aux.add(reader.readLine());
        } catch (Exception err) {  }
        return aux;
    }

    private List<String> getMovieActors(){
        List<String> aux = new ArrayList<>();
        while (true) {
            System.out.println("Pentru a iesi scrie !exit");
            System.out.println("Introdu actorul: ");
            try {
                String command = reader.readLine();
                if (command.equals("!exit")){
                    break;
                } else {
                    aux.add(command);
                }
            } catch (Exception err) {  }
        }
        return aux;
    }

    private List<String> getMovieKeywords(){
        List<String> aux = new ArrayList<>();
        while (true) {
            System.out.println("Pentru a iesi scrie !exit");
            System.out.println("Introdu cuvinte cheie: ");
            try {
                String command = reader.readLine();
                if (command.equals("!exit")){
                    break;
                } else {
                    aux.add(command);
                }
            } catch (Exception err) {  }
        }
        return aux;
    }

    private void meniu(){
        System.out.println("1. Adauga film\n2. Afiseaza toate filmele\n3. Exit\n");
    }

    public void run(){
        String command;
        while (true) {
            try{
                meniu();
                command = reader.readLine();
                execute(command);
            } catch (Exception err) {}
        }
    }
}
