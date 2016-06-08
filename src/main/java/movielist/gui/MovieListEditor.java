package movielist.gui;

import movielist.DuplicateMovieException;
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

  public void add(){
    String newName = view.getNewName();
    Movie newMovie = new Movie(newName);
    try {
      movies.add(newMovie);
      updateMovieList();
    } catch (DuplicateMovieException e) {
      view.duplicateException(newName);
    }
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
      try{
        movies.rename(selectedMovie, view.getNewName());
        updateMovieList();
      }catch (DuplicateMovieException e){
      }
    }
  }
}