package Resources.MenuHandlers;

import Models.Student;
import Resources.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.Scanner;

public class ListAllMenuHandler {
    Controller controller;

    public ListAllMenuHandler(Controller controller) {
        this.controller = controller;
    }

    public void handle(Scanner stdin) {

        int operator;
        operator = Integer.parseInt(stdin.nextLine());

        switch (operator) {
            case 1: // Case Student

                try {
                    Statement statement = controller.db.connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENTS ORDER BY ID");
                    while (resultSet.next()) {
                        Student student = new Student(
                            resultSet.getInt("ID"),
                            resultSet.getString("NAME"),
                            resultSet.getString("EMAIL")
                        );
                        System.out.println(student.toString());
                    }

                    statement.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
