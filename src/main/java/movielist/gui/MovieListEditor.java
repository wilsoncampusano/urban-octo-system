package movielist.gui;

import movielist.Movie;
import movielist.MovieList;

import java.util.Vector;

public class MovieListEditor {
  private MovieList movies;
  private MovieListEditorView view;
  private Movie selectedMovie;

  public MovieListEditor(MovieList movieList, MovieListEditorView aView) {
    this.movies = movieList;
    this.view = aView;
    updateMovieList();
    this.view.setEditor(this);
  }

  public void add() {
    Movie newMovie = new Movie(view.getNewName());
    movies.add(newMovie);
    updateMovieList();
  }

  private void updateMovieList() {
    view.setMovies(new Vector(movies.getMovies()));
  }

  public void select(int i) {
    if (i == -1){
      selectedMovie = null;
    }else {
      selectedMovie = movies.getMovies(i);
      view.setNewName(selectedMovie.getName());
    }
  }

  public void update() {
    if (selectedMovie != null) {
      selectedMovie.rename(view.getNewName());
    //  view.setMovies(new Vector(movies.getMovies()));
    }
  }
}