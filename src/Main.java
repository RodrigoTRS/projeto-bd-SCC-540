import Resources.Controller;
import Resources.DBConnector;
import Resources.MenuController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.start();
        controller.shutdown();
    }
}