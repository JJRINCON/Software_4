package views;

import models.MyProcess;
import models.Partition;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public void updatePartitions(ArrayList<Partition> partitions){
        mainPanel.updatePartitions(partitions);
    }

    public void initReportsPanel(ArrayList<Partition> partitions,ArrayList<Partition> termined,ArrayList<MyProcess> finish){
        mainPanel.initReportsPanel(partitions, termined,finish);
    }

    public void newSimulation(){
        getContentPane().remove(mainPanel);
        mainPanel = new MainPanel(actionListener);
        add(mainPanel);
        getContentPane().revalidate();
    }
}
