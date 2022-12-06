package Resources.MenuHandlers;

import Resources.Controller;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteMenuHandler {
    Controller controller;

    public DeleteMenuHandler(Controller controller) {
        this.controller = controller;
    }

    public void handle(Scanner stdin) {
        int operator;
        operator = Integer.parseInt(stdin.nextLine());

        switch (operator) {
            case 1: // Case Student
                System.out.println("Student ID: ");
                int deletionID = Integer.parseInt(stdin.nextLine());
                String sql = "DELETE FROM STUDENTS WHERE ID=" + "\'" + deletionID + "\'";
                try {
                    Statement statement = controller.db.connection.createStatement();
                    int rows = statement.executeUpdate(sql);
                    if (rows > 0) {
                        System.out.println("A student has been deleted!");
                    } else {
                        System.out.println("There isn't a student with this ID!");
                    }
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
