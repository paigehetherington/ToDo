import org.h2.tools.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by vajrayogini on 2/8/16.
 */
public class ToDo { //main method
    public static void insertToDo(Connection conn, String text) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO todos VALUES (NULL, ?, false)");
        stmt.setString(1, text);
        stmt.execute();
    }

    public static ArrayList<ToDoItem> selectTodos(Connection conn) throws SQLException {
        ArrayList<ToDoItem> items = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * FROM todos"); //execute query returns results
        while (results.next()) {
            int id  = results.getInt("id");
            String text = results.getString("text");
            Boolean isDone = results.getBoolean("is_done");
            items.add(new ToDoItem(id, text, isDone));
        }
        return items;

    }

    public static void toggleToDo(Connection conn, int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE todos SET is_done  = NOT is_done WHERE id = ?"); //NOT is sql version of !
        stmt.setInt(1, id);
        stmt.execute();
    }

    public static void main(String[] args) throws SQLException {
//        ArrayList<ToDoItem> items = new ArrayList<>(); //alt enter to import. arraylist (overall data structure) of todo items
        Scanner scanner = new Scanner(System.in);

        //database stuff
        //Server.createWebServer().start();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS todos (id IDENTITY, text VARCHAR, is_done BOOLEAN)");

        while (true) {  //continuously loops over list of options
            System.out.println("1. Create to-do item");
            System.out.println(("2. Toggle to-do item"));
            System.out.println(("3. List to-do items")); //3 options

            String option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.printf("Enter your to-do item:");
                String text = scanner.nextLine();

//                ToDoItem item = new ToDoItem(text, false); //created object, passing text into it, setting as false
//                items.add(item); //creating array

                insertToDo(conn, text);

            } else if (option.equals("2")) {
                System.out.println("Enter the number of the item you want to toggle:");
                int itemNum = Integer.valueOf(scanner.nextLine()); //convert string to int
//                ToDoItem item = items.get(itemNum - 1);    //because not starting at zero. Captured into ToDo variable
//                item.isDone = !item.isDone; //to toggle so if true becomes false and vv
                toggleToDo(conn, itemNum);

            } else if (option.equals("3")) {
                ArrayList<ToDoItem> items = selectTodos(conn);
//                int i = 1; //cause humans start counting at 1, to number list of options
                for (ToDoItem item : items) {   //todo item in items loops over todo items
                    String checkBox = "[]";
                    if (item.isDone) ;
                    {            //don't have to say == true
                        String checkbox = "[x]";
                        System.out.printf("%s %d. %s\n", checkbox, item.id, item.text);//(checkbox + i + ". " + item.text);
//                        i++; //increments it (+1)
                    }
                }
            } else {
                System.out.println("Invalid option");

            }

        }

    }
}
