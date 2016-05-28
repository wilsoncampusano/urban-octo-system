package movielist;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wilsoncampusano on 5/28/16.
 */
public class MovieList {

    private Collection movies = new ArrayList();
    private int numberOfMovies = 0;

    public int size() {
        return numberOfMovies;
    }

    public void add(Movie movie) {
        movies.add(movie);
        numberOfMovies++;
    }

}
