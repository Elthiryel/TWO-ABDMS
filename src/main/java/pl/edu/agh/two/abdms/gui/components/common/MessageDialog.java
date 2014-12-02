package pl.edu.agh.two.abdms.gui.components.common;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

public class MessageDialog extends JDialog {

    private JLabel messageLabel;

    private MessageDialog(String messageText, Color color, String title) {
        setSize(500, 100);
        setModal(true);
        setTitle(title);

        Container mainPanel = this.getContentPane();
        mainPanel.setLayout(new BorderLayout());

        messageLabel = new JLabel(messageText);
        messageLabel.setForeground(color);
        mainPanel.add(messageLabel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static MessageDialog error(String text) {
        return new MessageDialog(text, Color.red, "Error");
    }

    public static MessageDialog warning(String text) {
        return new MessageDialog(text, Color.black, "Warning");
    }
}
