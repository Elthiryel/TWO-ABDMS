package pl.edu.agh.two.abdms.gui.components.configurations;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.data.loader.xml.XMLLoader;
import pl.edu.agh.two.abdms.data.parsing.csv.CSVDataParser;
import pl.edu.agh.two.abdms.data.parsing.xls.XLSDataParser;
import pl.edu.agh.two.abdms.gui.controller.Configuration;
import pl.edu.agh.two.abdms.gui.controller.ConfigurationsController;
import pl.edu.agh.two.abdms.gui.statistics.StatisticsDialog;
import pl.edu.agh.two.applicationdata.ApplicationData;
import pl.edu.agh.two.applicationdata.ConfigurationData;

import com.google.common.io.Files;


public class ConfigurationsDialog extends JDialog {
    private final Container mainPanel;
    private final JButton newConfigurationButton;
    private final JButton refreshButton;
    private final JButton okButton;
    private final JButton showStatisticsButton;
    private final JTable dataTable = new JTable();
    private DataModel selectedDataModel;
    private Map<String, DataModel> loadedModels = new HashMap<>();

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
        refreshButton = new JButton("Refresh");
        showStatisticsButton = new JButton("Show Statistics");
        
        okButton = new JButton("OK");

        configurationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        configurationList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = configurationList.getSelectedIndex();
                if (index < 0)
                	return;
                Configuration cfg = configurationList.getModel().getElementAt(index);
                loadData(cfg);
            }
        });

        configurationList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JScrollPane scroll = new JScrollPane(configurationList);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, new JScrollPane( dataTable));
        split.setDividerSize(5);
        split.setDividerLocation(180);

        JPanel upPanel = new JPanel();
        upPanel.add(newConfigurationButton, BorderLayout.WEST);
        upPanel.add(refreshButton, BorderLayout.EAST);
        upPanel.add(okButton);
        upPanel.add(showStatisticsButton);
        add(upPanel, BorderLayout.SOUTH);
        //add(newConfigurationButton, BorderLayout.NORTH);
        //add(refreshButton, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);

        loadConfigurationsList();
        bindListeners();
    }

    private void loadData(Configuration cfg) {
    	if(!loadedModels.containsKey(cfg.getName())){
    		File file = cfg.getFile();
            DataModel tmpModel = parseFile(file);
            
            loadedModels.put(cfg.getName(), tmpModel);
            selectedDataModel = tmpModel;
    	}else{
    		selectedDataModel = loadedModels.get(cfg.getName());
    	}
        
        DataTableModel model = new DataTableModel(selectedDataModel);
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
    	switch(Files.getFileExtension(file.getName())){
    	case "csv":
    		CSVDataParser parser = new CSVDataParser();
    		return parser.parse(file.getPath(), ";");
    	case "xml":
    		XMLLoader loader = new XMLLoader();
    		return loader.load(file.getPath());
    	case "xls":
    	case "xlsx":
    		XLSDataParser xslParser = new XLSDataParser();
    		return xslParser.parse(file.getPath());
    	default:
    		throw new UnsupportedOperationException();
    	}        
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
        okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (configurationList.isSelectionEmpty()) {
					return;
				}
				ConfigurationData newConfiguration = 
						new ConfigurationData(selectedDataModel, configurationList.getSelectedValue().getName());
				ConfigurationsDialog.this.dispose();
				ApplicationData.setCurrentDataConfiguration(newConfiguration);
			}
		});
        final boolean isOnTop = this.isAlwaysOnTop();
		System.out.println(isOnTop);
		showStatisticsButton.addActionListener((e) -> {
			StatisticsDialog dlg = new StatisticsDialog(selectedDataModel);
			
			if (selectedDataModel == null)
				return;

			dlg.setMinimumSize(new Dimension(600, 300));
			dlg.setModalityType(ModalityType.APPLICATION_MODAL);
			dlg.pack();
			dlg.setLocationRelativeTo(null);
			dlg.setVisible(true);

		});
	}
}
