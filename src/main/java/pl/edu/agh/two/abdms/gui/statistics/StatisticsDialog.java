package pl.edu.agh.two.abdms.gui.statistics;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import pl.edu.agh.two.abdms.data.loader.DataModel;
import pl.edu.agh.two.abdms.statistics.StatisticsPreparator;

public class StatisticsDialog extends JDialog {

    private final DataModel dataModel;
    private JLabel loadingLabel;
    private BoxLayout layout;

    
    public StatisticsDialog(DataModel dataModel) {
    	this.setTitle("Statistics");
        this.dataModel = dataModel;
        initializeGui();
    }
    
    private void initializeGui() {
        this.layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.loadingLabel = new JLabel("Loading...");
        this.setLayout(layout);
        this.add(loadingLabel);
        
        loadData();
    }
    
    private void loadData() {
        new Thread() {
            public void run() {
                final StatisticsPreparator statisticsPreparator = new StatisticsPreparator(dataModel); // this may take long
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        createTable(statisticsPreparator);
                    }
                });
            };
        }.start();
    }
    
    private void createTable(StatisticsPreparator stats) {
        Set<String> numCols = stats.getMedians().keySet();
        Set<String> textCols = stats.getUniqueValueCounts().keySet();
        
        Object[] numHeaders = { "name", "avg", "median", "skewness" };
        Object[] textHeaders = { "name", "unique vals" };
        
        Object[][] numVals = new Object[numCols.size()][4];
        int i = 0;
        for (String col: numCols) {
            numVals[i][0] = col;
            numVals[i][1] = stats.getAverage(col);
            numVals[i][2] = stats.getMedian(col);
            numVals[i][3] = stats.getSkewness(col);
            ++ i;
        }
        
        JTable table = new JTable(numVals, numHeaders);
        
        Object[][] textVals = new Object[textCols.size()][4];
        i = 0;
        for (String col: textCols) {
            textVals[i][0] = col;
            textVals[i][1] = stats.getUniqueValueCount(col);
            ++ i;
        }
        
        JTable textTable = new JTable(textVals, textHeaders);
        
        loadingLabel.setVisible(false);
        this.add(new JLabel("Numeric columns"));
        this.add(new JScrollPane(table));
        this.add(Box.createVerticalStrut(10));
        this.add(new JLabel("Text columns"));
        this.add(new JScrollPane(textTable));
        
        table.setBorder(BorderFactory.createLineBorder(Color.black));
        textTable.setBorder(BorderFactory.createLineBorder(Color.black));
    }
       

}
