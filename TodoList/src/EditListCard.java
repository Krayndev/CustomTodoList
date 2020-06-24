
/*
    Krayndev; 06/23/2020
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditListCard implements ActionListener {

    /*
        This class represents the screen where users can edit a task on the list.
        The user is presented with a prompt-label to remind them of which button
        they clicked on, and two text fields to enter their tasks.
        Once the user has typed their tasks into the text fields, they press the
        'enter' key to edit the task on the list.
     */
    private JPanel editPanel;
    private JLabel promptLabel;
    private JTextField taskToEditField;
    private JTextField newTaskEntryField;

    // Pass main for member access
    private Main main;

    // Constructor calls method that builds GUI
    public EditListCard(Main main) {
        this.main = main;

        buildPanel();
    }


    private void buildPanel() {

        // Init panel
        editPanel = new JPanel();
        editPanel.setPreferredSize(this.main.getAppWindowDimension());
        editPanel.setMaximumSize(this.main.getAppWindowDimension());
        editPanel.setMinimumSize(this.main.getAppWindowDimension());
        editPanel.setLayout(null);

        // Init label
        promptLabel = new JLabel("   Edit A Task");
        promptLabel.setBounds(200, 20, 200, 50);
        promptLabel.setFont(new Font("Serif", Font.BOLD, 28));
        promptLabel.setToolTipText("Type the tasks in the boxes below then press the 'enter' key.");
        promptLabel.setBackground(Color.BLACK);
        promptLabel.setForeground(Color.WHITE);
        promptLabel.setOpaque(true);

        // Init Text field
        taskToEditField = new JTextField();
        taskToEditField.setPreferredSize(new Dimension(500, 40));
        taskToEditField.setMaximumSize(new Dimension(500, 40));
        taskToEditField.setMinimumSize(new Dimension(500, 40));
        taskToEditField.setBounds(50, 200, 500, 40);
        taskToEditField.setFont(new Font("Serif", Font.PLAIN, 16));
        taskToEditField.setToolTipText("Type the name of the task to edit here.");
        taskToEditField.addActionListener(this);

        // Init Text field
        newTaskEntryField = new JTextField();
        newTaskEntryField.setPreferredSize(new Dimension(500, 40));
        newTaskEntryField.setMaximumSize(new Dimension(500, 40));
        newTaskEntryField.setMinimumSize(new Dimension(500, 40));
        newTaskEntryField.setBounds(50, 350, 500, 40);
        newTaskEntryField.setFont(new Font("Serif", Font.PLAIN, 16));
        newTaskEntryField.setToolTipText("Type the new edited task here.");
        newTaskEntryField.addActionListener(this);

        // Add to panel
        editPanel.add(promptLabel);
        editPanel.add(taskToEditField);
        editPanel.add(newTaskEntryField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
            This method checks that text has been entered, and if it has,
            it stores the new task in the list where the old task was.
            The text fields are cleared and the application returns to the
            default screen when the user presses the 'enter' key.
         */
        String oldTask = taskToEditField.getText();
        String newTask = newTaskEntryField.getText();
        if(taskToEditField.getText().isEmpty() || newTaskEntryField.getText().isEmpty()) {
            main.getDisplayListCard().getTextArea().append("  Task fields cannot be left empty when editing.\n");
        } else {
            int positionOnList = main.getTodoList().findItem(oldTask);
            if (positionOnList != -1) {
                main.getTodoList().getTodoList().set(positionOnList, newTask);
            } else {
                String result = "  " + oldTask + " was not found on the list.\n";
                main.getDisplayListCard().getTextArea().append(result);
            }
            main.getDisplayListCard().updateListText();
        }
        this.taskToEditField.setText(null);
        this.newTaskEntryField.setText(null);
        main.getCardLayout().show(main.getManagerPanel(), main.getDISPLAY());
    }


    // GETTERS --------------------

    public JPanel getEditPanel() {
        return editPanel;
    }

    public JTextField getTaskToEditField() {
        return taskToEditField;
    }
}
