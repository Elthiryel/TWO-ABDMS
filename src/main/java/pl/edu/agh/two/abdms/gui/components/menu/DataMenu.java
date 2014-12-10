package pl.edu.agh.two.abdms.gui.components.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import pl.edu.agh.two.abdms.gui.components.PropertyTextBoxController;
import pl.edu.agh.two.abdms.gui.components.configurations.ConfigurationsDialog;
import pl.edu.agh.two.applicationdata.ApplicationData;

public class DataMenu extends JMenu {

	private static final long serialVersionUID = -7469146557699151017L;

	JMenuItem configurations;

    public DataMenu(Map<String, PropertyTextBoxController> propertyTextBoxControllers) {
        super("Data");
        configurations = new JMenuItem("Data sets");
        add(configurations);
        configurations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigurationsDialog configurationsDialog = new ConfigurationsDialog();
                configurationsDialog.setVisible(true);
                configurationsDialog.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent arg0) {
						String configName = ApplicationData.getCurrentDataConfiguration().getConfigurationName();
						propertyTextBoxControllers.get(PropertyTextBoxController.DATA_CONFIG).setText(configName);
					}
                });
            }
        });
    }
}
