package movielist.gui;

import movielist.Category;
import movielist.MovieList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class SwingMovieListEditorView extends JFrame implements MovieListEditorView {
  public static final String WINDOW_TITLE = "Movie list";
  private JList movieList = null;
  private MovieListEditor myEditor;
  private final JTextField movieField = new JTextField(15);
  private JComboBox ratingField = null;
  private JComboBox categoryField = null;

  GridLayout actionButtons = new GridLayout(1,2);
  Panel actionButtonPanel = new Panel(actionButtons);

  GridLayout combos = new GridLayout(1,2);
  Panel combosPanel = new Panel(combos);

  GridLayout mainLayout =  new GridLayout(4, 1);

  public SwingMovieListEditorView() throws HeadlessException {
    super();
  }

  public void setEditor(MovieListEditor anEditor) {
    this.myEditor = anEditor;
  }

  public void setNameField(String newName) {
    movieField.setText(newName);
  }

  public void duplicateException(String string) {
    JOptionPane.showMessageDialog(this, "that would result in a duplicate movie", "duplicate movie", JOptionPane.ERROR_MESSAGE);
  }

  public void setRatingField(int selectedIndex) {
    ratingField.setSelectedIndex(selectedIndex);
  }

  public int getRatingField() {
    return ratingField.getSelectedIndex();
  }

  public void setCategoryField(Category aCategory) {
    categoryField.setSelectedItem(aCategory);
  }

  public Category getCategoryField() {
    return (Category)categoryField.getSelectedItem();
  }

  public void setMovies(Vector movies) {
    movieList.setListData(movies);
  }

  public String getNameField() {
    return movieField.getText();
  }

  public void init() {
    setTitle();
    setLayout();
    initList();
    initField();
    initRatingCombo();
    initCategoryField();
    initAddButton();
    initUpdateButton();
    buildLayout();
    pack();
  }

  private void buildLayout() {
    getContentPane().add(combosPanel);
    getContentPane().add(actionButtonPanel);
  }

  private void initCategoryField() {
    categoryField = new JComboBox(Category.categories());
    categoryField.setSelectedItem(Category.UNCATEGORIZED);
    combosPanel.add(categoryField);
  }

  private void initRatingCombo() {
    ratingField = new JComboBox(CustomMovieListRenderer.ratingIcons);
    combosPanel.add(ratingField);
  }

  private void initUpdateButton() {
    JButton updateButton = new JButton("Update");
    updateButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        myEditor.update();
      }
    });
    actionButtonPanel.add(updateButton);
  }

  private void initAddButton() {
    JButton addButton = new JButton("Add");
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        myEditor.add();
      }
    });
    actionButtonPanel.add(addButton);
  }

  private void initField() {
    getContentPane().add(movieField);
  }

  private void initList() {
    movieList = new JList(new Vector());
    movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    movieList.setCellRenderer(new CustomMovieListRenderer());
    movieList.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        myEditor.select(movieList.getSelectedIndex());
      }
    });
    JScrollPane scroller = new JScrollPane(movieList,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    getContentPane().add(scroller);
  }

  private void setLayout() {
    getContentPane().setLayout(mainLayout);
  }

  private void setTitle() {
    setTitle(WINDOW_TITLE);
  }


  public static SwingMovieListEditorView start() {
    SwingMovieListEditorView window = new SwingMovieListEditorView();
    window.init();
    window.show();
    return window;
  }

  public static void main(String[] args) {
    SwingMovieListEditorView window = SwingMovieListEditorView.start();
    MovieList list = new MovieList();
    MovieListEditor editor = new MovieListEditor(list, window);
  }
}