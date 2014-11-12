package pl.edu.agh.two.abdms.gui.components.menu;

import pl.edu.agh.two.abdms.gui.components.configurations.ConfigurationsDialog;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataMenu extends JMenu {

    JMenuItem configurations;

    public DataMenu() {
        super("Data");
        configurations = new JMenuItem("Data sets");
        add(configurations);
        configurations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurationsDialog configurationsDialog = new ConfigurationsDialog();
                configurationsDialog.setVisible(true);
            }
        });
    }
}
