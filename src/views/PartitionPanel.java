package views;

import models.MyProcess;
import models.Queue;
import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PartitionPanel extends JPanel {

    private static final String TXT_ADD_PROCESS_BTN = "Agregar proceso";
    private static final String TXT_EDIT_PARTITION_BTN = "Editar particion";
    private static final String TXT_DELETE_PARTITION_BTN = "Eliminar particion";
    public static final Color RED_COLOR = Color.decode("#DE1D2C");
    private static final Color GREEN_COLOR = Color.decode("#27AE60");
    public static final Color BLUE_COLOR = Color.decode("#2980B9");
    private ActionListener actionListener;

    public PartitionPanel(ActionListener actionListener, String partitionName, int partitionSize,
                          ArrayList<MyProcess> processQueue){
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        this.actionListener = actionListener;
        setLayout(new BorderLayout());
        initPartitionInfo(partitionName, partitionSize);
        initProcessesPanel(processQueue, partitionName);
        initButtonsPanel(partitionName);
    }

    private void initPartitionInfo(String partitionName, int partitionSize){
        JPanel partitionInfoPanel = new JPanel(new GridLayout(1,2));
        partitionInfoPanel.setBackground(Color.decode("#34495E"));
        JLabel partitionNameLb = new JLabel("Nombre: " + partitionName);
        partitionNameLb.setForeground(Color.WHITE);
        partitionNameLb.setHorizontalAlignment(SwingConstants.CENTER);
        partitionNameLb.setFont(new Font("Arial", Font.BOLD, 18));
        partitionInfoPanel.add(partitionNameLb);
        JLabel partitionSizeLb = new JLabel("Tamaño: " + partitionSize);
        partitionSizeLb.setForeground(Color.WHITE);
        partitionSizeLb.setHorizontalAlignment(SwingConstants.CENTER);
        partitionSizeLb.setFont(new Font("Arial", Font.BOLD, 18));
        partitionInfoPanel.add(partitionSizeLb);
        add(partitionInfoPanel, BorderLayout.NORTH);
    }

    private void initProcessesPanel(ArrayList<MyProcess> processQueue, String partitionName){
        ProcessesPanel processesPanel = new ProcessesPanel(actionListener, processQueue, partitionName);
        add(processesPanel, BorderLayout.CENTER);
    }

    private void initButtonsPanel(String partitionName){
        JPanel buttonsPanel = new JPanel(new GridLayout(1,3,15,15));
        JButton addProcessBtn = createBtn(TXT_ADD_PROCESS_BTN, GREEN_COLOR, actionListener,
                                            Events.ADD_PROCESS.toString(), partitionName);
        buttonsPanel.add(addProcessBtn);
        JButton editPartitionBtn = createBtn(TXT_EDIT_PARTITION_BTN, BLUE_COLOR, actionListener,
                Events.EDIT_PARTITION.toString(), partitionName);
        buttonsPanel.add(editPartitionBtn);
        JButton deletePartitionBtn = createBtn(TXT_DELETE_PARTITION_BTN, RED_COLOR, actionListener,
                Events.DELETE_PARTITION.toString(), partitionName);
        buttonsPanel.add(deletePartitionBtn);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private JButton createBtn(String txt, Color color, ActionListener listener, String command, String name){
        JButton btn = new JButton(txt);
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFont(new Font("Arial", Font.BOLD, 15));
        btn.addActionListener(listener);
        btn.setActionCommand(command);
        btn.setName(name);
        return btn;
    }
}
