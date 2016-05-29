package movielist;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wilsoncampusano on 5/28/16.
 */
public class MovieList {

    private Collection movies = new ArrayList();

    public int size() {
        return movies.size();
    }

    public void add(Movie movieToAdd) {
        movies.add(movieToAdd);
    }

    public boolean contains(Movie movieToCheckFor) {
        return movies.contains(movieToCheckFor);
    }
}
