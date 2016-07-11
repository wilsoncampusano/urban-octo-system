package movielist.gui;

import java.util.Vector;


public interface MovieListEditorView {
  void setMovies(Vector movies);

  String getNameField();

  void setEditor(MovieListEditor anEditor);

  void setNameField(String newName);

  void duplicateException(String string);

  void setRatingField(int newRating);
}