package gui.files;

import gui.ControlPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dell on 08.05.2017.
 */
public class FilePanel extends JPanel {
    JLabel name;
    JButton remove;
    public FilePanel(ControlPanel panel)
    {
        super();
        this.name = new JLabel();
        this.remove = new JButton("Убрать");
        remove.addActionListener(e ->
        panel.reset());

        this.add(name);
        this.add(remove);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2,true));
    }
    public void enablePanel(String file)
    {

        this.name.setText(file.substring(file.lastIndexOf('\\')+1));
        this.setVisible(true);
    }
    public void disablePanel()
    {
        this.setVisible(false);
    }
}
