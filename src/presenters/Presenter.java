package presenters;

import exceptions.EmptyPartitionNameException;
import exceptions.EmptyPartitionSizeException;
import exceptions.EmptyProcessNameException;
import exceptions.EmptyProcessTimeException;
import views.AddPartitionDialog;
import views.AddProcessDialog;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presenter implements ActionListener {

    private MainFrame mainFrame;
    private AddPartitionDialog addPartitionDialog;
    private AddProcessDialog addProcessDialog;

    public Presenter() {
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
                manageAcceptEditPartitionAction();
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
        System.out.println("Editar particion: " + partitionName);
    }

    private void manageDeletePartitionAction(ActionEvent e) {
        String partitionName = ((JButton) e.getSource()).getName();
        System.out.println("Eliminar particion: " + partitionName);
    }

    private void manageAcceptAddPartitionAction() {
        try {
            String name = addPartitionDialog.getPartitionName();
            int size = addPartitionDialog.getPartitionSize();
            System.out.println("Nueva particion:\n" + "Nombre: " + name + " Tamaño: " + size);
            addPartitionDialog.dispose();
        } catch (EmptyPartitionNameException | EmptyPartitionSizeException e) {
            JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(mainFrame, "El tamaño debe ser un numero",
                    "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manageCancelAddPartitionAction() {
        addPartitionDialog.dispose();
    }

    private void manageAcceptEditPartitionAction() {
    }

    private void manageCancelEditPartitionAction() {
    }

    private void manageAddProcessAction(ActionEvent e) {
        String partitionName = ((JButton) e.getSource()).getName();
        System.out.println("Agregar proceso a :" + partitionName);
        addProcessDialog = new AddProcessDialog(this, false, partitionName);
        addProcessDialog.setVisible(true);
    }

    private void manageAcceptAddProcessAction(ActionEvent e) {
        try {
            String name = addProcessDialog.getProcessName();
            int time = addProcessDialog.getProcessTime();
            boolean isBlocked = addProcessDialog.getIsBlocked();
            System.out.println("Info\n Nombre: " + name + " Tiempo: " + time + " ¿Se bloquea?: " + isBlocked);
            addProcessDialog.dispose();
        } catch (EmptyProcessNameException | EmptyProcessTimeException ex) {
            JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(mainFrame,"El tiempo debe ser un numero");
        }
    }

    private void manageCancelAddProcessAction() {
        addProcessDialog.dispose();
    }

    private void manageAcceptEditProcessAction(ActionEvent e) {
    }

    private void manageCancelEditProcessAction() {
    }
}
