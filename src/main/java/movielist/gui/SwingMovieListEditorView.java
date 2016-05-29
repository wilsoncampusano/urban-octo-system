package movielist.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class SwingMovieListEditorView extends JFrame implements MovieListEditorView{
    private JList movieList = null;

    public SwingMovieListEditorView() throws HeadlessException {
        super();
    }

    public void setMovies(Vector movies) {
        movieList.setListData(movies);
    }

    public void init(){
        setTitle("Movie list");
        getContentPane().setLayout(new FlowLayout());
        movieList = new JList(new Vector());
        JScrollPane scroller = new JScrollPane(movieList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroller);
        pack();
    }


    public static void start(){
        SwingMovieListEditorView window = new SwingMovieListEditorView();
        window.init();
        window.show();
    }
}
