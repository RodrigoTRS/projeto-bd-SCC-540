package Resources.MenuHandlers;

import Models.Animal;
import Resources.Controller;

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
            case 1: // Case Animal
                Animal animal = new Animal();
                animal.insert(controller);
                break;
        }
    }
}
