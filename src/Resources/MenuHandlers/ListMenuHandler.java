package Resources.MenuHandlers;

import Models.Animal;
import Resources.Controller;

import java.sql.*;
import java.util.Scanner;

public class ListMenuHandler {
    Controller controller;
    Statement stmt;
    ResultSet rset;
    String query;
    String sqlString;

    public ListMenuHandler(Controller controller) {
        this.controller = controller;
    }

    public void handle(Scanner stdin) {

        int operator;
        operator = Integer.parseInt(stdin.nextLine());

        switch (operator) {
            case 1: // Case Animal
                controller.menu.byTypeListAllMenu();
                ListByTypeHandler listByTypeHandler = new ListByTypeHandler(this.controller.db.connection);
                listByTypeHandler.handle(stdin);
                break;
        }
    }
}
