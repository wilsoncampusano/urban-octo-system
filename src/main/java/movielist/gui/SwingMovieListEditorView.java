package movielist.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class SwingMovieListEditorView extends JFrame implements MovieListEditorView{
    public static final String WINDOW_TITLE = "Movie list";
    private JList movieList = null;
    private MovieListEditor myEditor;
    private final JTextField movieField = new JTextField(16);

    public SwingMovieListEditorView() throws HeadlessException {
        super();
    }

    public void setEditor(MovieListEditor anEditor) {
        this.myEditor = anEditor;
    }

    public void setNewName(String newName) {
        movieField.setText(newName);
    }

    public void setMovies(Vector movies) {
        movieList.setListData(movies);
    }

    public String getNewName() {
        return movieField.getText();
    }

    public void init(){
        setTitle();
        setLayout();
        initList();
        initField();
        initAddButton();
        pack();
    }

    private void initAddButton() {
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myEditor.add();
            }
        });
        getContentPane().add(addButton );
    }

    private void initField() {
        getContentPane().add(movieField);
    }

    private void initList() {
        movieList = new JList(new Vector());
        movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        getContentPane().setLayout(new FlowLayout());
    }

    private void setTitle() {
        setTitle(WINDOW_TITLE);
    }


    public static void start(){
        SwingMovieListEditorView window = new SwingMovieListEditorView();
        window.init();
        window.show();
    }
}
