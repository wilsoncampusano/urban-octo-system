package movielist.gui;

import movielist.Movie;
import movielist.MovieList;

import java.util.Vector;

public class MovieListEditor {
    private MovieList movies;
    private MovieListEditorView view;

    public MovieListEditor(MovieList movieList, MovieListEditorView aView) {
        this.movies = movieList;
        this.view = aView;
        this.view.setMovies(new Vector(this.movies.getMovies()));
        this.view.setEditor(this);
    }

    public void add() {
        Movie newMovie = new Movie(view.getNewName());
        movies.add(newMovie);
        view.setMovies(new Vector(movies.getMovies()));
    }

    public void select(int i) {
        view.setNewName(movies.getMovies(i).getName());
    }
}
