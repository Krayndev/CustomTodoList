
/*
    Krayndev; 06/23/2020
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    /*
        This application uses the Swing CardLayout class.
        Each card the application uses has been designed as a class, and instantiated
        in this class.
        The Frame is filled by the managerPanel, which is controlled by the CardLayout.
        The application changes between the cards corresponding with the user input.
        The TodoList class implements the List with the necessary state and behavior.
     */
    private JFrame frame;
    private JPanel managerPanel;
    private DisplayListCard displayListCard;
    private AddListCard addListCard;
    private EditListCard editListCard;
    private FindListCard findListCard;
    private RemoveListCard removeListCard;
    private CardLayout cardLayout;
    private TodoList todoList;

    // Sizing fields
    private String appWindowTitle;
    private int windowWidth, windowHeight;
    private Dimension appWindowDimension;

    // Constants used to track the cards by name
    private final String DISPLAY = "Display Todolist";
    private final String ADD = "Add to Todolist";
    private final String EDIT = "Edit Todolist";
    private final String FIND = "Find item on Todolist";
    private final String REMOVE = "Remove from Todolist";


    // Constructor sets the size of the frame, and calls methods that create the GUI
    public Main(String appWindowTitle, int windowWidth, int windowHeight) {
        this.appWindowTitle = appWindowTitle;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        init();
        createWindow();
    }


    // Initialize the various application resources
    private void init(){

        try {
            UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[4].getClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Init cards
        this.displayListCard = new DisplayListCard(this);
        this.addListCard = new AddListCard(this);
        this.editListCard = new EditListCard(this);
        this.findListCard = new FindListCard(this);
        this.removeListCard = new RemoveListCard(this);

        // Init manager panel
        this.appWindowDimension = new Dimension(this.windowWidth, this.windowHeight);
        managerPanel = new JPanel(new CardLayout());
        managerPanel.setPreferredSize(appWindowDimension);
        managerPanel.setMaximumSize(appWindowDimension);
        managerPanel.setMinimumSize(appWindowDimension);

        // Add cards to managerPanel
        managerPanel.add(displayListCard.getMasterPanel(), DISPLAY);
        managerPanel.add(addListCard.getAddPanel(), ADD);
        managerPanel.add(editListCard.getEditPanel(), EDIT);
        managerPanel.add(findListCard.getFindPanel(), FIND);
        managerPanel.add(removeListCard.getRemovePanel(), REMOVE);

        // Init card layout
        cardLayout = (CardLayout)(managerPanel.getLayout());

        // Init List
        todoList = new TodoList();
    }


    // Create app window
    private void createWindow(){
        frame = new JFrame(appWindowTitle);
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().add(managerPanel);
        frame.validate();
        frame.pack();

        // Show welcome message on default display screen
        this.displayListCard.getTextArea().append("\n\n\n                          Welcome to the Todo List App!\n");
        this.cardLayout.show(managerPanel, DISPLAY);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // User clicks add button
        if(e.getActionCommand() == DisplayListCard.DisplayActions.ADD.name()) {
            // Change to corresponding card
            cardLayout.show(managerPanel, ADD);
            // Grab the focus so users can automatically begin typing
            this.addListCard.getTaskEntryField().grabFocus();
        }

        if(e.getActionCommand() == DisplayListCard.DisplayActions.EDIT.name()) {
            if(this.todoList.getTodoList().isEmpty()){
                this.displayListCard.getTextArea().append("  There is nothing to edit in an empty list.\n");
            } else {
                cardLayout.show(managerPanel, EDIT);
                this.editListCard.getTaskToEditField().grabFocus();
            }
        }

        if(e.getActionCommand() == DisplayListCard.DisplayActions.FIND.name()) {
            if(this.todoList.getTodoList().isEmpty()){
                this.displayListCard.getTextArea().append("  There is nothing to find in an empty list.\n");
            } else {
                cardLayout.show(managerPanel, FIND);
                this.findListCard.getTaskEntryField().grabFocus();
            }
        }

        if(e.getActionCommand() == DisplayListCard.DisplayActions.REMOVE.name()) {
            if(this.todoList.getTodoList().isEmpty()){
                this.displayListCard.getTextArea().append("  There is nothing to remove from an empty list.\n");
            } else {
                cardLayout.show(managerPanel, REMOVE);
                this.removeListCard.getTaskEntryField().grabFocus();
            }

        }
    }


    // GETTERS ----------------

    public TodoList getTodoList() {
        return todoList;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public Dimension getAppWindowDimension() {
        return appWindowDimension;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getManagerPanel() {
        return managerPanel;
    }

    public String getDISPLAY() {
        return DISPLAY;
    }

    public DisplayListCard getDisplayListCard() {
        return displayListCard;
    }
}
