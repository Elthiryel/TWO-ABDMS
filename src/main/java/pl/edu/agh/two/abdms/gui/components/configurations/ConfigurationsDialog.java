package pl.edu.agh.two.abdms.gui.components.configurations;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.gui.controller.Configuration;
import pl.edu.agh.two.abdms.gui.controller.ConfigurationsController;


public class ConfigurationsDialog extends JDialog {
    private final Container mainPanel;
    private final JButton newConfigurationButton;
    private final JTable dataTable = new JTable();

    private final JList<Configuration> configurationList = new JList<>();
    private final ConfigurationsController configurationsController = 
            ConfigurationsController.getInstance();

    public ConfigurationsDialog() {
        mainPanel = this.getContentPane();
        mainPanel.setLayout(new BorderLayout());

        setTitle("Data sources");

        setSize(600, 500);
        setModal(true);
        newConfigurationButton = new JButton("Add new...");

        configurationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        configurationList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = e.getFirstIndex();
                Configuration cfg = configurationList.getModel().getElementAt(index);
                loadData(cfg);
            }
        });

        configurationList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JScrollPane scroll = new JScrollPane(configurationList);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, dataTable);
        split.setDividerSize(5);
        split.setDividerLocation(180);

        add(newConfigurationButton, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);

        loadConfigurationsList();
        bindListeners();
    }

    private void loadData(Configuration cfg) {
        File file = cfg.getFile();
        DataModel data = parseFile(file); 
        DataTableModel model = new DataTableModel(data);
        dataTable.setModel(model);
    }

    private DataModel parseFile(File file) {
        // String extension = Files.getFileExtension(file.getName());

        // TODO: Somehow load the data

        // Ideally, there should be a class that manages available file format 
        // parsers, and given a path it chooses appropriate parser, and consumes
        // the file, producing DataModel object.
        // Parsers should have uniform interface, and such parser manager should
        // automatically discover implementations in the classpath.
        // HERE is really not a good place to put this kind of logic.

        // return result of aforementioned magic.
        throw new UnsupportedOperationException();
    }

    private void loadConfigurationsList() {
        List<Configuration> configurations = configurationsController.getConfigurations();
        Configuration[] configurationsArray = new Configuration[configurations.size()];
        configurations.toArray(configurationsArray);

        configurationList.setListData(configurationsArray);
    }

    private void bindListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                loadConfigurationsList();
            }
        });
        newConfigurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewConfigurationDialog newConfigurationDialog = new NewConfigurationDialog();
                newConfigurationDialog.setVisible(true);
            }
        });
    }
}
