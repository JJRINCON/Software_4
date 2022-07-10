package views;

import models.Partition;
import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PartitionsPanel extends MyGridPanel{

    private static final String TITLE = "Lista de particiones";
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 20);
    private static final String TXT_ADD_PARTITION_BTN = "Agregar particion";
    public static final Color WHITE_COLOR = Color.decode("#FDFEFE");
    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private JPanel partitionsPanel;
    private JScrollPane scrollPane;
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
        partitionsPanel.add(new JLabel(" "));
        partitionsPanel.add(new JLabel(" "));
        addComponent(partitionsPanel, 0, 2, 12, 0.8);
    }

    public void updatePartitions(ArrayList<Partition> partitions){
        removeAll();
        initTitle();
        verifyRowsNumber(partitions);
        scrollPane = new JScrollPane(partitionsPanel);
        addComponent(scrollPane, 0,1, 12, 0.8);
        initAddPartitionBtn();
        updateUI();
    }

    private void verifyRowsNumber(ArrayList<Partition> partitions){
        if(partitions.isEmpty()) {
            partitionsPanel = new JPanel(new GridLayout(2,1, 5,5));
            partitionsPanel.setBackground(WHITE_COLOR);
            partitionsPanel.add(new JLabel(" "));
            partitionsPanel.add(new JLabel(" "));
        }else if(partitions.size() < 2){
            partitionsPanel = new JPanel(new GridLayout(2,1, 5,5));
            partitionsPanel.setBackground(WHITE_COLOR);
            partitionsPanel.add(new PartitionPanel(actionListener, partitions.get(0).getName(),
                    partitions.get(0).getSize(), partitions.get(0).getProcessesQueue()));
            partitionsPanel.add(new JLabel(" "));
        }else{
            partitionsPanel = new JPanel(new GridLayout(partitions.size(), 1, 5, 5));
            partitionsPanel.setBackground(WHITE_COLOR);
            addPartitions(partitions);
        }
    }

    private void addPartitions(ArrayList<Partition> partitions){
        for(Partition partition : partitions){
            PartitionPanel partitionPanel = new PartitionPanel(actionListener, partition.getName(), partition.getSize(),
                                                                partition.getProcessesQueue());
            partitionPanel.setPreferredSize(new Dimension((int) (WIDTH * 0.4), (int) (HEIGHT * 0.4)));
            partitionsPanel.add(partitionPanel);
        }
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
