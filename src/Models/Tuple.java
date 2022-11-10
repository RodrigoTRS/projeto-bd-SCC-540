package Models;

import Resources.Controller;

import java.sql.Connection;
import java.util.Scanner;

interface Tuple {
    public void insert(Controller controller);

    public void insertOnDb(Connection connection);

    public void getInput(Scanner stdin);

    public String toString();
}
