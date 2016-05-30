package movielist.gui;

import java.util.Vector;


public interface MovieListEditorView {
    public void setMovies(Vector movies);
    String getNewName();
    void setEditor(MovieListEditor anEditor);
}
