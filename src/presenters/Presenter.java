package presenters;

import exceptions.*;
import models.Manager;
import models.MyProcess;
import models.Partition;
import views.AddPartitionDialog;
import views.AddProcessDialog;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Presenter implements ActionListener {

    private Manager manager;
    private MainFrame mainFrame;
    private AddPartitionDialog addPartitionDialog;
    private AddPartitionDialog editPartitionDialog;
    private AddProcessDialog addProcessDialog;
    private AddProcessDialog editProcessDialog;

    public Presenter() {
        manager = new Manager();
        mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Events.valueOf(e.getActionCommand())) {
            case ADD_PARTITION:
                manageAddPartitionAction(e);
                break;
            case EDIT_PARTITION:
                manageEditPartitionAction(e);
                break;
            case DELETE_PARTITION:
                manageDeletePartitionAction(e);
                break;
            case ACCEPT_ADD_PARTITION:
                manageAcceptAddPartitionAction();
                break;
            case CANCEL_ADD_PARTITION:
                manageCancelAddPartitionAction();
                break;
            case ACCEPT_EDIT_PARTITION:
                manageAcceptEditPartitionAction(e);
                break;
            case CANCEL_EDIT_PARTITION:
                manageCancelEditPartitionAction();
                break;
            case ADD_PROCESS:
                manageAddProcessAction(e);
                break;
            case ACCEPT_ADD_PROCESS:
                manageAcceptAddProcessAction(e);
                break;
            case CANCEL_ADD_PROCESS:
                manageCancelAddProcessAction();
                break;
            case ACCEPT_EDIT_PROCESS:
                manageAcceptEditProcessAction(e);
                break;
            case CANCEL_EDIT_PROCESS:
                manageCancelEditProcessAction();
                break;
            case EDIT_PROCESS:
                manageEditProcessAction(e);
                break;
            case DELETE_PROCESS:
                manageDeleteProcessAction(e);
                break;
            case INIT_SIMULATION:
                manageInitSimulationAction();
                break;
            case NEW_SIMULATION:
                manageNewSimulationAction();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    private void manageAddPartitionAction(ActionEvent e) {
        addPartitionDialog = new AddPartitionDialog(this, false);
        addPartitionDialog.setVisible(true);
    }

    private void manageEditPartitionAction(ActionEvent e) {
        String partitionName = ((JButton) e.getSource()).getName();
        Partition partition = manager.searchPartition(partitionName);
        editPartitionDialog = new AddPartitionDialog(this,true);
        editPartitionDialog.setInitialInfo(partition.getName(), partition.getSize());
        editPartitionDialog.setVisible(true);
    }

    private void manageDeletePartitionAction(ActionEvent e) {
        String partitionName = ((JButton) e.getSource()).getName();
        if(manager.deletePartition(partitionName)){
            mainFrame.updatePartitions(manager.getPartitions());
            JOptionPane.showMessageDialog(mainFrame, "Particion eliminada con exito", "Eliminar",
                                            JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(mainFrame, "No se ha podido eliminar la particion",
                    "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageAcceptAddPartitionAction() {
        try {
            String name = addPartitionDialog.getPartitionName();
            manager.verifyPartitionName(name);
            int size = addPartitionDialog.getPartitionSize();
            manager.addPartition(name, size);
            mainFrame.updatePartitions(manager.getPartitions());
            addPartitionDialog.dispose();
        } catch (EmptyPartitionNameException | EmptyPartitionSizeException | RepeatedNameException |
                 InvalidSizeException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageCancelAddPartitionAction() {
        addPartitionDialog.dispose();
    }

    private void manageAcceptEditPartitionAction(ActionEvent e) {
        try {
            String actualName = ((JButton) e.getSource()).getName();
            if(!actualName.equals(editPartitionDialog.getPartitionName())){
                manager.verifyPartitionName(editPartitionDialog.getPartitionName());
            }
            manager.editPartition(actualName, editPartitionDialog.getPartitionName(),
                                        editPartitionDialog.getPartitionSize());
            editPartitionDialog.dispose();
            mainFrame.updatePartitions(manager.getPartitions());
        } catch (EmptyPartitionNameException | EmptyPartitionSizeException | RepeatedNameException |
                 InvalidSizeException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageCancelEditPartitionAction() {
        editPartitionDialog.dispose();
    }

    private void manageAddProcessAction(ActionEvent e) {
        String partitionName = ((JButton) e.getSource()).getName();
        addProcessDialog = new AddProcessDialog(this, false, partitionName);
        addProcessDialog.setVisible(true);
    }

    private void manageAcceptAddProcessAction(ActionEvent e) {
        try {
            String partitionName = ((JButton) e.getSource()).getName();
            String processName = addProcessDialog.getProcessName();
            manager.verifyProcessName(processName);
            int time = addProcessDialog.getProcessTime();
            int size = addProcessDialog.getProcessSize();
            boolean isBlocked = addProcessDialog.getIsBlocked();
            manager.addProcess(partitionName, processName, time, size, isBlocked);
            mainFrame.updatePartitions(manager.getPartitions());
            addProcessDialog.dispose();
        } catch (EmptyProcessNameException | EmptyProcessTimeException | EmptyProcessSizeException |
                 RepeatedNameException | InvalidTimeException | InvalidSizeException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
        }
    }

    private void manageEditProcessAction(ActionEvent e) {
        String[] info = ((JButton) e.getSource()).getName().split(",");
        System.out.println(Arrays.toString(info));
        editProcessDialog = new AddProcessDialog(this, true, info[0]);
        MyProcess process = manager.searchProcess(info[0], info[1]);
        editProcessDialog.setInitialInfo(process.getName(), String.valueOf((int)process.getTime()),
                String.valueOf(process.getSize()), process.isLocked());
        editProcessDialog.setVisible(true);
    }

    private void manageCancelEditProcessAction() {
        editProcessDialog.dispose();
    }

    private void manageCancelAddProcessAction() {
        addProcessDialog.dispose();
    }

    private void manageAcceptEditProcessAction(ActionEvent e) {
        try {
            String[] info = ((JButton) e.getSource()).getName().split(",");
            System.out.println("info de proceso a editar: " + Arrays.toString(info));
            if(!info[1].equals(editProcessDialog.getProcessName())){
                manager.verifyProcessName(editProcessDialog.getProcessName());
            }
            manager.editProcess(info[0], info[1], editProcessDialog.getProcessName(), editProcessDialog.getProcessTime(),
                    editProcessDialog.getProcessSize(), editProcessDialog.getIsBlocked());
            mainFrame.updatePartitions(manager.getPartitions());
            editProcessDialog.dispose();
        } catch (EmptyProcessNameException | RepeatedNameException | EmptyProcessTimeException |
                 EmptyProcessSizeException | InvalidSizeException | InvalidTimeException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
        }
    }

    private void showError(String message){
        JOptionPane.showMessageDialog(mainFrame, message, "ERROR!!", JOptionPane.ERROR_MESSAGE);
    }

    private void manageDeleteProcessAction(ActionEvent e) {
        String[] info = ((JButton) e.getSource()).getName().split(",");
        manager.deleteProcess(info[0], info[1]);
        mainFrame.updatePartitions(manager.getPartitions());
    }

    private void manageInitSimulationAction() {
    	
        if(!manager.getPartitions().isEmpty()){
            manager.initSimulation();
            mainFrame.initReportsPanel(manager.getPartitions());
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe haber almenos una particion para poder iniciar la simulacion",
                                            "ALERTA", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void manageNewSimulationAction() {
        manager = new Manager();
        mainFrame.newSimulation();
    }
}
