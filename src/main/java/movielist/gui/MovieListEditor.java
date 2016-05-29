package movielist.gui;

import movielist.MovieList;

import java.util.Vector;

public class MovieListEditor {
    public MovieListEditor(MovieList movieList, MovieListEditorView view) {
        view.setMovies(new Vector(movieList.getMovies()));
    }
}
