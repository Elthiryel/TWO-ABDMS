package pl.edu.agh.two.abdms.gui;

import pl.edu.agh.two.abdms.gui.components.MainWindow;
import pl.edu.agh.two.abdms.gui.controller.GraphController;

import javax.swing.*;

public class GuiLauncher {
    public void startGui() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }
}
