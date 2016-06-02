package movielist.gui;

import java.util.Vector;


public interface MovieListEditorView {
    void setMovies(Vector movies);
    String getNewName();
    void setEditor(MovieListEditor anEditor);
    void setNewName(String newName);
}
