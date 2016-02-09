import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by vajrayogini on 2/8/16.
 */
public class ToDo { //main method
    public static void main(String[] args) {
        ArrayList<ToDoItem> items = new ArrayList<>(); //alt enter to import. arraylist (overall data structure) of todo items
        Scanner scanner = new Scanner(System.in);

        while (true) {  //continuously loops over list of options
            System.out.println("1. Create to-do item");
            System.out.println(("2. Toggle to-do item"));
            System.out.println(("3. List to-do items")); //3 options

            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.printf("Enter your to-do item:");
                String text = scanner.nextLine();

                ToDoItem item = new ToDoItem(text, false); //created object, passing text into it, setting as false
                items.add(item); //creating array

            } else if (option.equals("2")) {
                System.out.println("Enter the number of the item you want to toggle:");
                int itemNum = Integer.valueOf(scanner.nextLine()); //convert string to int
                ToDoItem item = items.get(itemNum - 1);    //because not starting at zero. Captured into ToDo variable
                item.isDone = !item.isDone; //to toggle so if true becomes false and vv
            } else if (option.equals("3")) {
                int i = 1; //cause humans start counting at 1, to number list of options
                for (ToDoItem item : items) {   //todo item in items loops over todo items
                    String checkBox = "[]";
                    if (item.isDone) ;
                    {            //don't have to say == true
                        String checkbox = "[x]";
                        System.out.printf("%s %d. %s\n", checkbox, i, item.text);//(checkbox + i + ". " + item.text);
                        i++; //increments it (+1)
                    }
                }
            } else {
                System.out.println("Invalid option");

            }

        }
    }
}
