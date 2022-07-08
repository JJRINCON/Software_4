package views;

import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PartitionsPanel extends MyGridPanel{

    private static final String TITLE = "Lista de particiones";
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 20);
    private static final String TXT_ADD_PARTITION_BTN = "Agregar particion";
    private JPanel partitionsPanel;
    private ActionListener actionListener;

    public PartitionsPanel(ActionListener actionListener){
        this.actionListener = actionListener;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        initTitle();
        initPartitionsPanel();
        initAddPartitionBtn();
    }

    public void initTitle(){
        JLabel titleLb = new JLabel(TITLE);
        titleLb.setOpaque(true);
        titleLb.setBackground(Color.decode("#16A085"));
        titleLb.setForeground(Color.WHITE);
        titleLb.setFont(TITLE_FONT);
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLb, 0, 0, 12, 0.03);
    }

    private void initPartitionsPanel(){
        addComponent(new JLabel(""), 0, 1, 12, 0.01);
        partitionsPanel = new JPanel(new GridLayout(2,1,5,5));
        partitionsPanel.add(new PartitionPanel(actionListener, "Particion 1"));
        partitionsPanel.add(new PartitionPanel(actionListener, "Particion 2"));
        addComponent(partitionsPanel, 0, 2, 12, 0.8);
    }

    public void initAddPartitionBtn(){
        addComponent(new JLabel(""), 0, 3, 12, 0.01);
        JButton addProcessBtn = new JButton(TXT_ADD_PARTITION_BTN);
        addProcessBtn.setBackground(Color.decode("#2980B9"));
        addProcessBtn.setForeground(Color.WHITE);
        addProcessBtn.setFont(new Font("Arial", Font.BOLD, 18));
        addProcessBtn.setPreferredSize(new Dimension(500, 40));
        addProcessBtn.addActionListener(actionListener);
        addProcessBtn.setActionCommand(Events.ADD_PARTITION.toString());
        addComponent(addProcessBtn, 0, 4, 12, 0.01);
    }

}
