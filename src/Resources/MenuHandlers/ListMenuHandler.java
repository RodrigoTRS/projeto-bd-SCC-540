package Resources.MenuHandlers;

import Resources.Controller;

import java.util.Scanner;

public class ListMenuHandler {
    Controller controller;

    public ListMenuHandler(Controller controller) {
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
