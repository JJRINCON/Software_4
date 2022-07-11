package views;

import models.MyProcess;
import models.Partition;
import presenters.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReportsPanel extends JPanel {

    private static final String NEW_SIMULATION_BTN_TXT = "Nueva simulacion";
    private static final String[] TERMINED_COLUMNS = {"Nombre", "Tiempo"};
    private static final Color BLUE_COLOR = Color.decode("#2980B9");

    public ReportsPanel(ActionListener listener, ArrayList<Partition> partitions,ArrayList<Partition> termined,ArrayList<MyProcess> finish){
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FDFEFE"));
        initTitle();
        addReports(partitions, termined, finish);
        initNewSimulationBtn(listener);
    }

    public void addReports(ArrayList<Partition> partitions, ArrayList<Partition> termined, ArrayList<MyProcess> finish){
        JTabbedPane reports = new JTabbedPane();
        reports.setFont(new Font("Arial", Font.BOLD, 18));
        for(Partition partition : partitions){
            PartitionReportsPanel partitionReportsPanel = new PartitionReportsPanel(partition.getReadyProccess(),
                    partition.getProcessDespachados(), partition.getExecuting(),
                    partition.getProcessExpired(), partition.getProcessToLocked(),
                    partition.getProcessLocked(), partition.getProcessWakeUp(),
                    partition.getProcessTerminated(), partition.getOverSize());
            reports.add(partitionReportsPanel, partition.getName());
        }
        TablePanel terminedTablePanel = new TablePanel(Partition.partitionTermined(termined), TERMINED_COLUMNS);
        reports.add("Tiempo de terminacion", terminedTablePanel);
        TablePanel terminedProcessTablePanel = new TablePanel(Partition.processInfo(finish), TERMINED_COLUMNS);
        reports.add("Procesos Terminados", terminedProcessTablePanel);
        add(reports, BorderLayout.CENTER);
    }

    private void initNewSimulationBtn(ActionListener listener){
        JButton newSimulationBtn = new JButton(NEW_SIMULATION_BTN_TXT);
        newSimulationBtn.setFont(new Font("Arial", Font.BOLD, 20));
        newSimulationBtn.setForeground(Color.WHITE);
        newSimulationBtn.setBackground(BLUE_COLOR);
        newSimulationBtn.addActionListener(listener);
        newSimulationBtn.setActionCommand(Events.NEW_SIMULATION.toString());
        add(newSimulationBtn, BorderLayout.SOUTH);
    }

    private void initTitle(){
        JLabel titleLb = new JLabel("REPORTES");
        titleLb.setFont(new Font("Arial", Font.BOLD, 16));
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLb, BorderLayout.NORTH);
    }
}
