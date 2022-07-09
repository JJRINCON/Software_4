package views;

import models.MyProcess;
import models.Partition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PartitionReportsPanel extends JPanel {

    private static final String[] COLUMNS = {"Nombre", "Tiempo", "Tamaño", "Bloqueo"};

    public PartitionReportsPanel(ArrayList<MyProcess> readyProcess, ArrayList<MyProcess> dispatchedProcess,
                                 ArrayList<MyProcess> executingProcess, ArrayList<MyProcess> expiredProcess,
                                 ArrayList<MyProcess> toLockedProcess, ArrayList<MyProcess> lockedProcess,
                                 ArrayList<MyProcess> wakeUpProcess, ArrayList<MyProcess> terminatedProcess){
        setLayout(new BorderLayout());
        setBackground(Color.decode("#FDFEFE"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        initTitle();
        JTabbedPane reports = new JTabbedPane();
        reports.setBackground(Color.decode("#FDFEFE"));
        TablePanel readyTable = new TablePanel(Partition.processInfo(readyProcess), COLUMNS);
        reports.add("Listos", readyTable);

        TablePanel dispatchedTable = new TablePanel(Partition.processInfo(dispatchedProcess), COLUMNS);
        reports.add("Despachados", dispatchedTable);

        TablePanel executingTable = new TablePanel(Partition.processInfo(executingProcess), COLUMNS);
        reports.add("En ejecucion", executingTable);

        TablePanel expiredTable = new TablePanel(Partition.processInfo(expiredProcess), COLUMNS);
        reports.add("Tiempo expirado", expiredTable);

        TablePanel toLockedTable = new TablePanel(Partition.processInfo(toLockedProcess), COLUMNS);
        reports.add("A Bloqueados", toLockedTable);

        TablePanel lockedTable = new TablePanel(Partition.processInfo(lockedProcess), COLUMNS);
        reports.add("Bloqueados", lockedTable);

        TablePanel wakeUpTable = new TablePanel(Partition.processInfo(wakeUpProcess), COLUMNS);
        reports.add("Despertados", wakeUpTable);

        TablePanel terminatedTable = new TablePanel(Partition.processInfo(terminatedProcess), COLUMNS);
        reports.add("Terminados", terminatedTable);
        add(reports, BorderLayout.CENTER);
    }

    private void initTitle(){
        JLabel titleLb = new JLabel("REPORTES");
        titleLb.setForeground(Color.WHITE);
        titleLb.setFont(new Font("Arial", Font.BOLD, 16));
        titleLb.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLb, BorderLayout.NORTH);
    }
}
