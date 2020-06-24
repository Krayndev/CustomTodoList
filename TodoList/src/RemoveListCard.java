
/*
    Krayndev; 06/23/2020
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveListCard implements ActionListener {

    /*
        This class represents the screen where users can remove a task from the list.
        The user is presented with a prompt-label to remind them of which button
        they clicked on, and a text field to enter their task.
        Once the user has typed their task into the text field, they press the
        'enter' key to remove the task from the list.
     */
    private JPanel removePanel;
    private JLabel promptLabel;
    private JTextField taskEntryField;

    // Pass main for member access
    private Main main;


    // Constructor calls method that builds GUI
    public RemoveListCard(Main main) {
        this.main = main;

        buildPanel();
    }


    private void buildPanel(){

        // Init panel
        removePanel = new JPanel();
        removePanel.setPreferredSize(this.main.getAppWindowDimension());
        removePanel.setMaximumSize(this.main.getAppWindowDimension());
        removePanel.setMinimumSize(this.main.getAppWindowDimension());
        removePanel.setLayout(null);

        // Init label
        promptLabel = new JLabel(" Remove A Task");
        promptLabel.setBounds(200, 20, 205, 50);
        promptLabel.setFont(new Font("Serif", Font.BOLD, 28));
        promptLabel.setToolTipText("Type the task in the box below then press the 'enter' key");
        promptLabel.setBackground(Color.BLACK);
        promptLabel.setForeground(Color.WHITE);
        promptLabel.setOpaque(true);

        // Init field
        taskEntryField = new JTextField();
        taskEntryField.setPreferredSize(new Dimension(500, 40));
        taskEntryField.setMaximumSize(new Dimension(500, 40));
        taskEntryField.setMinimumSize(new Dimension(500, 40));
        taskEntryField.setBounds(50, 250, 500, 40);
        taskEntryField.setFont(new Font("Serif", Font.PLAIN, 16));
        taskEntryField.addActionListener(this);

        // Add to panel
        removePanel.add(promptLabel);
        removePanel.add(taskEntryField);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        /*
            This method checks that text has been entered, and that the
            task is on the list, if both are true, it removes the task from the list.
            The text field is cleared and the application returns to the
            default screen when the user presses the 'enter' key.
         */
        if(!taskEntryField.getText().isEmpty()){
            int positionOnList = main.getTodoList().findItem(taskEntryField.getText());
            if(positionOnList != -1){
                main.getTodoList().removeItem(taskEntryField.getText());
                main.getDisplayListCard().updateListText();
            } else {
                main.getDisplayListCard().getTextArea().append("  Task not found.\n");
            }
        } else{
            main.getDisplayListCard().getTextArea().append("  No task entered in remove.\n");
        }
        this.taskEntryField.setText(null);
        main.getCardLayout().show(main.getManagerPanel(), main.getDISPLAY());
    }


    // GETTERS -----------------

    public JPanel getRemovePanel() {
        return removePanel;
    }

    public JTextField getTaskEntryField() {
        return taskEntryField;
    }
}
