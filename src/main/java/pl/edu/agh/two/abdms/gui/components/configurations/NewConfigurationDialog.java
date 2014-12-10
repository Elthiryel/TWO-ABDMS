package pl.edu.agh.two.abdms.gui.components.configurations;

import pl.edu.agh.two.abdms.gui.controller.Configuration;
import pl.edu.agh.two.abdms.gui.controller.ConfigurationsController;
import pl.edu.agh.two.abdms.gui.exceptions.ValidationException;

import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewConfigurationDialog extends JDialog {
    private ConfigurationsController configurationsController = ConfigurationsController.getInstance();

    private JButton uploadFileButton;
    private JTextField configurationName;
    private JLabel uploadLabel = new JLabel();
    private JLabel configurationNameLabel = new JLabel("Configuration name:");
    private JLabel errorLabel = new JLabel();
    private JButton confirmButton = new JButton("Continue");
    private File selectedFile;

    private Container mainPanel;

    public NewConfigurationDialog() {
    	this.setTitle("New configuration");
        mainPanel = this.getContentPane();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        uploadFileButton = new JButton("Select file");
        configurationName = new JFormattedTextField();
        errorLabel.setForeground(Color.red);
        bindEvents();
        render();
        setModal(true);
        setSize(300, 200);
    }

    private void render() {
        mainPanel.add(uploadLabel);
        mainPanel.add(uploadFileButton);
        mainPanel.add(configurationNameLabel);
        mainPanel.add(configurationName);
        mainPanel.add(confirmButton);
        mainPanel.add(errorLabel);
    }


    private void bindEvents() {
        uploadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int status = fileChooser.showDialog(null, "Select file");
                if (status == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    uploadLabel.setText("Selected: " + selectedFile.getName());
                } else {
                    uploadLabel.setText("Failed to load this file.");
                }
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuration configuration = new Configuration(selectedFile, configurationName.getText());
                try {
                    configurationsController.addConfiguration(configuration);
                    NewConfigurationDialog.this.dispose();
                } catch (ValidationException exception) {
                    errorLabel.setText(exception.getMessage());
                }
            }
        });
    }
}
