package movielist.gui;

import movielist.Category;

import java.util.Vector;


public interface MovieListEditorView {
  void setMovies(Vector movies);

  String getNameField();

  void setEditor(MovieListEditor anEditor);

  void setNameField(String newName);

  void duplicateException(String string);

  void setRatingField(int newRating);

  int getRatingField();

  void setCategoryField(Category aCategory);

  Category getCategoryField();
}