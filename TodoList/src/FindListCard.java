
/*
    Krayndev; 06/23/2020
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindListCard implements ActionListener {

    // OBJECTS
    private JPanel findPanel;
    private JLabel promptLabel;
    private JTextField taskEntryField;
    private Main main;

    // CONSTRUCTOR
    public FindListCard(Main main) {
        this.main = main;

        buildPanel();
    }

    private void buildPanel() {

        // Init panel
        findPanel = new JPanel();
        findPanel.setPreferredSize(this.main.getAppWindowDimension());
        findPanel.setMaximumSize(this.main.getAppWindowDimension());
        findPanel.setMinimumSize(this.main.getAppWindowDimension());
        findPanel.setLayout(null);

        // Init label
        promptLabel = new JLabel("   Find A Task");
        promptLabel.setBounds(200, 20, 200, 50);
        promptLabel.setFont(new Font("Serif", Font.BOLD, 28));
        promptLabel.setToolTipText("Type the task in the box below then press the 'enter' key");
        promptLabel.setBackground(Color.BLACK);
        promptLabel.setForeground(Color.WHITE);
        promptLabel.setOpaque(true);

        // Init Text field
        taskEntryField = new JTextField();
        taskEntryField.setPreferredSize(new Dimension(500, 40));
        taskEntryField.setMaximumSize(new Dimension(500, 40));
        taskEntryField.setMinimumSize(new Dimension(500, 40));
        taskEntryField.setBounds(50, 250, 500, 40);
        taskEntryField.setFont(new Font("Serif", Font.PLAIN, 16));
        taskEntryField.addActionListener(this);

        // Add to panel
        findPanel.add(promptLabel);
        findPanel.add(taskEntryField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
            This method checks that text has been entered, and if it has,
            it checks against the list and displays whether the task was found.
            The text fields are cleared and the application returns to the
            default screen when the user presses the 'enter' key.
         */
        String newTask = taskEntryField.getText();
        if(!taskEntryField.getText().isEmpty()) {
            int positionOnList = main.getTodoList().findItem(newTask);
            if(positionOnList != -1){
                String result = "  " + newTask + " was found at position " + (positionOnList + 1)  + " on the list.\n";
                main.getDisplayListCard().getTextArea().append(result);
            } else {
                String result = "  " + newTask + " was not found on the list.\n";
                main.getDisplayListCard().getTextArea().append(result);
            }
        } else {
            main.getDisplayListCard().getTextArea().append("  No task entered in find.\n");
        }
        this.taskEntryField.setText(null);
        main.getCardLayout().show(main.getManagerPanel(), main.getDISPLAY());
    }

    public JPanel getFindPanel() {
        return findPanel;
    }

    public JTextField getTaskEntryField() {
        return taskEntryField;
    }
}
