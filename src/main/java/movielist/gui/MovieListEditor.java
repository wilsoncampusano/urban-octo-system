package movielist.gui;

import movielist.DuplicateMovieException;
import movielist.Movie;
import movielist.MovieList;
import movielist.UnratedException;

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
    String newName = view.getNameField();
    //TODO: fix add with a rating. test-drive this.
    //int rating = view.getRatingField();
    //Movie newMovie = new Movie(newName, rating -1);
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
      view.setNameField(selectedMovie.getName());
      try {
        view.setRatingField(selectedMovie.getRating()+1);
        view.setCategoryField(selectedMovie.getCategory());
      } catch (UnratedException e) {
        view.setRatingField(0);
      }
    }
  }

  public void update() {
    if (selectedMovie != null) {
      String newName = view.getNameField();
      if(selectedMovie.getName().equals(newName)){
        updateMovie();
      }else{

        try{
          movies.rename(selectedMovie, newName);
          updateMovie();
        }catch (DuplicateMovieException e){
          view.duplicateException(newName);
        }

      }
    }
  }

  private void updateMovie() {
    selectedMovie.setRating(view.getRatingField() - 1);
    updateMovieList();
  }
}