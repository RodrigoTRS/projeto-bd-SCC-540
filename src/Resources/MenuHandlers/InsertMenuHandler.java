package Resources.MenuHandlers;

import Models.Student;
import Resources.Controller;
import Resources.DBConnector;

import java.sql.Connection;
import java.util.Scanner;

public class InsertMenuHandler {

    Controller controller;
    public InsertMenuHandler(Controller controller) {
        this.controller = controller;
    }

    public void handle(Scanner stdin) {

        int operator;
        operator = Integer.parseInt(stdin.nextLine());

        switch (operator) {
            case 1: // Case Student
                Student student = new Student();
                student.insert(controller);
                break;
        }
    }
}
