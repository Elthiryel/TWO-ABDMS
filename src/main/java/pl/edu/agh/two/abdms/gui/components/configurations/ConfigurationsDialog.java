package pl.edu.agh.two.abdms.gui.components.configurations;

import pl.edu.agh.two.abdms.gui.controller.Configuration;
import pl.edu.agh.two.abdms.gui.controller.ConfigurationsController;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class ConfigurationsDialog extends JDialog {
    private final Container mainPanel;
    JButton newConfigurationButton;
    private JList<Configuration> configurationList = new JList<Configuration>();
    private ConfigurationsController configurationsController = ConfigurationsController.getInstance();

    public ConfigurationsDialog() {
        mainPanel = this.getContentPane();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        setSize(500, 500);
        newConfigurationButton = new JButton("Add new...");
        setModal(true);
        add(newConfigurationButton);
        add(configurationList);
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
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                loadConfigurationsList();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
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
