package views;

import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel extends MyGridPanel{


    public static final String EXIT_BTN_TXT = "Salir";
    private static final Color WHITE_COLOR = Color.decode("#FDFEFE");
    public static final Color RED_COLOR = Color.decode("#34495E");
    private PartitionsPanel partitionsPanel;
    private ActionListener actionListener;
    private MyGridPanel startSimulationPanel;

    public MainPanel(ActionListener actionListener){
        this.actionListener = actionListener;
        setBackground(WHITE_COLOR);
        initExitBtn();
        initPartitionsPanel();
        initStartSimulationPanel();
    }

    public void initPartitionsPanel(){
        partitionsPanel = new PartitionsPanel(actionListener);
        addComponent(partitionsPanel, 0, 1, 3, 1);
    }

    private void initExitBtn(){
        MyGridPanel exitBtnPanel = new MyGridPanel();
        exitBtnPanel.setBackground(RED_COLOR);
        JButton exitBtn = createBtn(EXIT_BTN_TXT, Color.decode("#DE1D2C"), actionListener, Events.EXIT.toString());
        exitBtnPanel.addComponentWithInsets(exitBtn, 11, 1, 1, 0.1, new Insets(5,0,5,0));
        addComponent(exitBtnPanel, 0,0, 12, 0.005);
    }

    private void initStartSimulationPanel(){
        startSimulationPanel = new MyGridPanel();
        JButton startSimulationBtn = createBtn("Iniciar Simulacion", Color.decode("#2980B9"),
                actionListener, Events.INIT_SIMLATION.toString());
        startSimulationPanel.setBackground(Color.decode("#FDFEFE"));
        startSimulationPanel.addComponent(new JLabel(" "), 0, 3, 12, 0.3);
        startSimulationPanel.addComponent(startSimulationBtn, 4, 4, 5, 0.05);
        startSimulationPanel.addComponent(new JLabel(" "), 0, 5, 12, 0.4);
        addComponent(startSimulationPanel, 3, 1, 9, 1);
    }

    private JButton createBtn(String txt, Color color, ActionListener listener, String command){
        JButton btn = new JButton(txt);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.addActionListener(listener);
        btn.setActionCommand(command);
        return btn;
    }
}
