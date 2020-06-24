
/*
    Krayndev; 06/23/2020
 */

import javax.swing.*;
import java.awt.*;

public class DisplayListCard {

    /*
        This class represents the default viewing screen of the application.
        On this screen the user will find the output of the application, as well as four
        buttons the user can click to interact with the application.
        The application output is displayed in a Text Area in a Scroll Pane, so a very
        long list is supportable.
     */
    private JPanel masterPanel, buttonPanel;
    private JButton addButton, editButton, findButton, removeButton;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    // Pass main for member access
    private Main main;

    // Sizing fields
    private Dimension buttonPanelDimensions;

    // Collection of button action names
    public enum DisplayActions {
        ADD,
        EDIT,
        FIND,
        REMOVE
    }


    // Constructor sets size of button panel and calls method to build GUI
    public DisplayListCard(Main main) {
        this.main = main;
        this.buttonPanelDimensions = new Dimension(main.getWindowWidth(), 80);
        buildMasterPanel();
    }


    private void buildMasterPanel(){

        // Init panel
        masterPanel = new JPanel();
        masterPanel.setPreferredSize(this.main.getAppWindowDimension());
        masterPanel.setMaximumSize(this.main.getAppWindowDimension());
        masterPanel.setMinimumSize(this.main.getAppWindowDimension());
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        // Build other components
        buildTextArea();
        buildButtons();

        // Add to masterPanel
        masterPanel.add(scrollPane);
        masterPanel.add(buttonPanel);
    }


    private void buildTextArea(){

        // Init text area
        textArea = new JTextArea();
        textArea.setColumns(5);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Serif", Font.BOLD,20));
        textArea.setToolTipText("The Todo List");
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);

        // Init scroll pane with textArea
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy
                (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy
                (ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }


    private void buildButtons(){

        // Init sub panel
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(buttonPanelDimensions);
        buttonPanel.setMaximumSize(buttonPanelDimensions);
        buttonPanel.setMinimumSize(buttonPanelDimensions);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(null);

        // Init add button
        addButton = new JButton();
        addButton.setText("Add");
        addButton.setBounds(110, 15, 75, 50);
        addButton.setToolTipText("Add item to List");
        addButton.setActionCommand(DisplayActions.ADD.name());
        addButton.addActionListener(main);

        // Init edit button
        editButton = new JButton();
        editButton.setText("Edit");
        editButton.setBounds(210, 15, 75, 50);
        editButton.setToolTipText("Edit item on List");
        editButton.setActionCommand(DisplayActions.EDIT.name());
        editButton.addActionListener(main);

        // Init find button
        findButton = new JButton();
        findButton.setText("Find");
        findButton.setBounds(310, 15, 75, 50);
        findButton.setToolTipText("Find item on List");
        findButton.setActionCommand(DisplayActions.FIND.name());
        findButton.addActionListener(main);

        // Init delete button
        removeButton = new JButton();
        removeButton.setText("Remove");
        removeButton.setBounds(410, 15, 75, 50);
        removeButton.setToolTipText("Remove item from List");
        removeButton.setActionCommand(DisplayActions.REMOVE.name());
        removeButton.addActionListener(main);

        // Add buttons to panel
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(findButton);
        buttonPanel.add(removeButton);
    }


    // Displays the contents of the list or empty
    public void updateListText(){
        if(main.getTodoList().getTodoList().isEmpty()){
            this.textArea.setText(null);
            this.textArea.append("\n\n\n                                              List : Empty\n");
        } else {
            this.textArea.setText(null);
            for(int i = 0; i < main.getTodoList().getTodoList().size(); i++){
                this.textArea.append("  " + (i + 1) + ". " + main.getTodoList().getTodoList().get(i) + "\n");
            }
        }
    }


    // GETTERS ------------------

    public JPanel getMasterPanel() {
        return masterPanel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
