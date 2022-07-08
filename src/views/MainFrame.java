package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static final String TITLE = "Software 4";
    private MainPanel mainPanel;
    private ActionListener actionListener;

    public MainFrame(ActionListener actionListener){
        this.actionListener = actionListener;
        setUndecorated(true);
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        initMainPanel();
    }

    public void initMainPanel(){
        mainPanel = new MainPanel(actionListener);
        add(mainPanel);
    }
}
