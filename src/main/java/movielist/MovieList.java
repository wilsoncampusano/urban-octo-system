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

  public void rename(Movie aMovie, String newName) throws DuplicateMovieException{
    Movie potentialMovie = new Movie(newName);
    if(this.contains(potentialMovie))
      throw new DuplicateMovieException(potentialMovie.getName());

    aMovie.rename(newName);
  }

  public MovieList categorySublist(Category aCategory) {
    if(aCategory.equals(Category.ALL))
      return this;

    MovieList filteredList = new MovieList();
    for (Object m : movies) {
      if (((Movie)m).isOfCategory(aCategory)) {
        try {
          filteredList.add((Movie) m);
        } catch (DuplicateMovieException e) {
        }
      }
    }
    return filteredList;
  }

  @Override
  public String toString() {
    return "MovieList{" +
        "movies=" + movies +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MovieList movieList = (MovieList) o;

    return movies != null ? movies.equals(movieList.movies) : movieList.movies == null;

  }

  @Override
  public int hashCode() {
    return movies != null ? movies.hashCode() : 0;
  }
}