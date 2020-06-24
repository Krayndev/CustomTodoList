
/*
    Krayndev; 06/23/2020
 */

import java.util.ArrayList;

public class TodoList {

    // Create an Arraylist that represents a todolist
    private ArrayList<String> todoList = new ArrayList<String>();

    // Method for adding items to the list
    public void addItem(String item){
        todoList.add(item);
    }

    // Method for printing the contents of the list
    public void printTodoList() {
        for(int i = 0; i < todoList.size(); i++){
            // TODO: The below line needs to print to the scrollPanel on display card
            System.out.println((i + 1) + ". " + todoList.get(i));
        }
    }

    // Method for calling modifying method on an item on the list
    public void modifyItem(String currentItem, String newItem){
        int position = findItem(currentItem);
        if(position >= 0){
            modifyItem(position, newItem);
        }
    }

    // Private method that modifies items on the list
    private void modifyItem(int position, String newItem){
        todoList.set(position, newItem);
    }

    // Method for calling removal method on an item on the list
    public void removeItem(String item){
        int position = findItem(item);
        if(position >= 0){
            removeItem(position);
        }
    }

    // Private method that removes items from the list
    private void removeItem(int position){
        String theItem = todoList.get(position);
        todoList.remove(position);
    }

    //  method that returns the index of an item on the list
    public int findItem(String searchItem){
        return todoList.indexOf(searchItem);
    }

    // Method that confirms an item is on the list
    public boolean onFile(String searchItem){
        int position = findItem(searchItem);
        if(position >= 0){
            return true;
        }
        return false;
    }

    // Getter method for list
    public ArrayList<String> getTodoList() {
        return todoList;
    }
}
