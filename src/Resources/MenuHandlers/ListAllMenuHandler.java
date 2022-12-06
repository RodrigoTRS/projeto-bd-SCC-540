package Resources.MenuHandlers;

import Models.Animal;
import Resources.Controller;

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
            case 1: // Case Animal

                break;
        }
    }
}
