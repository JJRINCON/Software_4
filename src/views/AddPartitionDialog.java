package views;

import exceptions.EmptyPartitionNameException;
import exceptions.EmptyPartitionSizeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddPartitionDialog extends JDialog {

    private AddPartitionPanel addPartitionPanel;

    public AddPartitionDialog(ActionListener actionListener, boolean isEditing){
        setSize(400, 300);
        setModal(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setUndecorated(true);
        addPartitionPanel = new AddPartitionPanel(actionListener, isEditing);
        add(addPartitionPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public String getPartitionName() throws EmptyPartitionNameException {
        return addPartitionPanel.getPartitionName();
    }

    public int getPartitionSize() throws EmptyPartitionSizeException, NumberFormatException {
        return addPartitionPanel.getPartitionSize();
    }
}
