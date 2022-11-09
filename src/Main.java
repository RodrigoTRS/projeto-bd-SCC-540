import Models.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        DBConnector db = new DBConnector();
        db.connect();

        String state = "init";
        int operator;

        StringBuilder mainMenu = new StringBuilder();

        mainMenu.append("--------------------\n");
        mainMenu.append("1 - Insert\n");
        mainMenu.append("2 - Exit\n");
        mainMenu.append("--------------------");


        while (state != "exit") {
            state = "standby";
            System.out.println(mainMenu.toString());
            operator = Integer.parseInt(stdin.nextLine());
            switch (operator) {
                case 1:
                    state = "insert";
                    System.out.println("Data inserted!");
                    break;
                case 2:
                    state = "exit";
                    System.out.println("Shutting down...");
                    db.disconnect();
                    break;
            }
        }
    }
}