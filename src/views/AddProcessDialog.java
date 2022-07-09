package views;


import exceptions.EmptyProcessNameException;
import exceptions.EmptyProcessSizeException;
import exceptions.EmptyProcessTimeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddProcessDialog extends JDialog {

    private AddProcessPanel addProcessPanel;

    public AddProcessDialog(ActionListener listener, boolean isEditing, String partitionName) {
        setInfo();
        addProcessPanel = new AddProcessPanel(listener, isEditing, partitionName);
        add(addProcessPanel);
    }

    private void setInfo(){
        setSize(400, 400);
        setModal(true);
        setLayout(new BorderLayout());
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
    }

    public String getProcessName() throws EmptyProcessNameException {
        return addProcessPanel.getProcessName();
    }

    public int getProcessTime() throws EmptyProcessTimeException, NumberFormatException {
        return addProcessPanel.getProcessTime();
    }

    public int getProcessSize() throws EmptyProcessSizeException, NumberFormatException {
        return addProcessPanel.getProcessSize();
    }

    public boolean getIsBlocked(){
        return addProcessPanel.getIsBlocked();
    }

    public void setInitialInfo(String name, String time, String size,boolean isLocked){
        addProcessPanel.setInitialInfo(name, time, size, isLocked);
    }
}
