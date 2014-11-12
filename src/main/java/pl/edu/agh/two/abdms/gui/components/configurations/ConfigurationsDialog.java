package pl.edu.agh.two.abdms.gui.components.configurations;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.edu.agh.two.abdms.gui.controller.Configuration;
import pl.edu.agh.two.abdms.gui.controller.ConfigurationsController;


public class ConfigurationsDialog extends JDialog {
    private final Container mainPanel;
    private final JButton newConfigurationButton;

    private final JList<Configuration> configurationList = new JList<>();
    private final ConfigurationsController configurationsController = 
            ConfigurationsController.getInstance();

    public ConfigurationsDialog() {
        mainPanel = this.getContentPane();
        mainPanel.setLayout(new BorderLayout());
        
        setSize(500, 500);
        setModal(true);
        newConfigurationButton = new JButton("Add new...");
        
        configurationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        configurationList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = e.getFirstIndex();
                Configuration cfg = configurationList.getModel().getElementAt(index);
            }
        });
        
        add(newConfigurationButton, BorderLayout.PAGE_START);
        add(configurationList, BorderLayout.LINE_START);
        loadConfigurationsList();
        bindListeners();

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
