package views;

import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PartitionPanel extends JPanel {

    private static final String TXT_ADD_PROCESS_BTN = "Agregar proceso";
    private static final String TXT_EDIT_PARTITION_BTN = "Editar particion";
    private static final String TXT_DELETE_PARTITION_BTN = "Eliminar particion";
    public static final Color RED_COLOR = Color.decode("#DE1D2C");
    private static final Color GREEN_COLOR = Color.decode("#27AE60");
    public static final Color BLUE_COLOR = Color.decode("#2980B9");
    private ActionListener actionListener;

    public PartitionPanel(ActionListener actionListener, String partitionName){
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        this.actionListener = actionListener;
        setLayout(new BorderLayout());
        initPartitionName(partitionName);
        initProcessesPanel();
        initButtonsPanel(partitionName);
    }

    private void initPartitionName(String partitionName){
        JLabel partitionNameLb = new JLabel(partitionName);
        partitionNameLb.setOpaque(true);
        partitionNameLb.setBackground(Color.decode("#34495E"));
        partitionNameLb.setForeground(Color.WHITE);
        partitionNameLb.setHorizontalAlignment(SwingConstants.CENTER);
        partitionNameLb.setFont(new Font("Arial", Font.BOLD, 18));
        add(partitionNameLb, BorderLayout.NORTH);
    }

    private void initProcessesPanel(){
        JPanel processesPanel = new JPanel();
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
