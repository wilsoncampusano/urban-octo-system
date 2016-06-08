package movielist;

import java.util.ArrayList;
import java.util.Collection;

public class MovieList {

  private Collection movies = new ArrayList();

  public int size() {
    return movies.size();
  }

  public void add(Movie movieToAdd) throws DuplicateMovieException{
    if(this.contains(movieToAdd))
      throw new DuplicateMovieException(movieToAdd.getName());

    movies.add(movieToAdd);
  }

  public boolean contains(Movie movieToCheckFor) {
    return movies.contains(movieToCheckFor);
  }

  public Collection getMovies() {
    return movies;
  }

  public Movie getMovies(int i) {
    return new ArrayList<Movie>(movies).get(i);
  }
}