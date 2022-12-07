package Resources;

import Resources.MenuHandlers.InsertMenuHandler;
import Resources.MenuHandlers.ListMenuHandler;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Controller {
    public DBConnector db; // Database connect
    public Scanner stdin; // Input receiver
    public MenuController menu; // Menu controller
    public String state; // System state

    public Controller() {
        this.state = "init";
        this.db = new DBConnector();
        db.connect();
        this.menu = new MenuController();
        this.stdin = new Scanner(System.in);
    }

    public void start() {

        while (this.state != "exit") {
            this.state = "standby";
            this.menu.mainMenu();

            String operator = stdin.nextLine();

            switch (operator) {

                case "1": // Case Insert
                    this.state = "insert";
                    this.menu.insertMenu();
                    InsertMenuHandler insertMenuHandler = new InsertMenuHandler(this);
                    insertMenuHandler.handle(stdin);
                    break;

                case "2": // Case List All
                    this.state = "list all";
                    this.menu.listAllMenu();
                    ListMenuHandler listMenuHandler = new ListMenuHandler(this);
                    listMenuHandler.handle(stdin);
                    break;

                case "S" : // Case Exit
                    this.state = "exit";
                    break;
            }
        }
    }

    public void shutdown() {
        System.out.println("Shutting down...");
        this.db.disconnect();
    }
}
