
/*
    Krayndev; 06/23/2020
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListCard implements ActionListener {

    /*
        This class represents the screen where users can add a task to the list.
        The user is presented with a prompt-label to remind them of which button
        they clicked on, and a text field to enter their task.
        Once the user has typed their task into the text field, they press the
        'enter' key to add the new task to the list.
     */
    private JPanel addPanel;
    private JLabel promptLabel;
    private JTextField taskEntryField;

    // Pass main for member access
    private Main main;


    // Constructor calls a method to build the GUI
    public AddListCard(Main main) {
        this.main = main;

        buildPanel();
    }


    private void buildPanel(){

        // Init panel
        addPanel = new JPanel();
        addPanel.setPreferredSize(this.main.getAppWindowDimension());
        addPanel.setMaximumSize(this.main.getAppWindowDimension());
        addPanel.setMinimumSize(this.main.getAppWindowDimension());
        addPanel.setLayout(null);

        // Init label
        promptLabel = new JLabel("   Add A Task");
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
        addPanel.add(promptLabel);
        addPanel.add(taskEntryField);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        /*
            This method checks that text has been entered, and if it has,
            it stores the new task in the list.
            The text field is cleared and the application returns to the
            default screen when the user presses the 'enter' key.
         */
        if(!taskEntryField.getText().isEmpty()) {
            main.getTodoList().addItem(taskEntryField.getText());
            main.getDisplayListCard().updateListText();
        } else {
            main.getDisplayListCard().getTextArea().append("  No task entered in add.\n");
        }
        this.taskEntryField.setText(null);
        main.getCardLayout().show(main.getManagerPanel(), main.getDISPLAY());
    }


    // GETTERS -----------------

    public JPanel getAddPanel() {
        return addPanel;
    }

    public JTextField getTaskEntryField() {
        return taskEntryField;
    }
}
