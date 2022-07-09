package views;

import models.MyProcess;
import models.Node;
import models.Queue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProcessesPanel extends MyGridPanel {

    private static final String TITLE = "Lista de Procesos";
    private JPanel processes;
    private JScrollPane scrollPane;
    private ActionListener listener;

    public ProcessesPanel(ActionListener listener, Queue<MyProcess> processQueue, String partitionName) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setBackground(Color.decode("#FDFEFE"));
        initComponents(listener, processQueue, partitionName);
    }

    private void initComponents(ActionListener listener, Queue<MyProcess> processQueue, String partitionName){
        scrollPane = new JScrollPane();
        this.listener = listener;
        addTitle();
        initProcessesPanel(processQueue, partitionName);
    }

    public void addTitle(){
        JLabel titleLb = new JLabel(TITLE);
        titleLb.setOpaque(true);
        titleLb.setBackground(Color.decode("#16A085"));
        titleLb.setForeground(Color.WHITE);
        titleLb.setFont(new Font("Arial", Font.BOLD, 16));
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        addComponent(titleLb, 0, 0, 12, 0.03);
    }

    private void initProcessesPanel(Queue<MyProcess> processQueue, String partitionName){
        verifyRowsNumber(processQueue, partitionName);
        scrollPane = new JScrollPane(processes);
        addComponent(scrollPane, 0, 1, 12, 0.8);
    }

    private void verifyRowsNumber(Queue<MyProcess> processQueue, String partitionName){
        if(processQueue.size() < 5){
            processes = new JPanel(new GridLayout(5, 1, 5, 5));
            processes.setBackground(Color.decode("#FDFEFE"));
            addProcesses(processQueue, partitionName);
            for (int i = 0; i < (5 - processQueue.size()); i++) {
                processes.add(new JLabel(" "));
            }
        }else{
            processes = new JPanel(new GridLayout(processQueue.size(), 1, 5,5));
            processes.setBackground(Color.decode("#FDFEFE"));
            addProcesses(processQueue, partitionName);
        }
    }

    private void addProcesses(Queue<MyProcess> processQueue, String partitionName){
        Node<MyProcess> temp = processQueue.peek();
        while (temp != null){
            ProcessPanel processPanel = new ProcessPanel(temp.getData(), partitionName, listener);
            processes.add(processPanel);
            temp = temp.getNext();
        }
    }
}
