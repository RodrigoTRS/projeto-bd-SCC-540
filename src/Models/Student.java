package Models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student implements Tuple
{
    String name;
    String email;

    public Student() {}

    public Student(String name, String email) {
        this.setName(name);
        this.setEmail(email);
    }

    public void setName(String name) {
        if (name.length() < 45) {
            this.name = name;
        } else {
            System.out.println("Name must be shorter then 45 chars!");
        }
    }

    public void setEmail(String email) {
        if (email.length() < 45) {
            this.email = email;
        } else {
            System.out.println("Email must be shorter then 45 chars!");
        }
    }



    @Override
    public void insert(Connection connection) {
        this.getInput();
        this.insertOnDb(connection);
    }

    @Override
    public void insertOnDb(Connection connection) {
        String sql =
                "INSERT INTO STUDENTS (NAME, EMAIL) " +
                "VALUES " + '(' + "\'" +
                this.name +
                "\', \'" +
                this.email +
                "\'" + ')';

        try {
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0) {
                System.out.println("A student has been inserted!");
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getInput() {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Models.Student's name:");
        setName(stdin.nextLine());
        System.out.println("Models.Student's email:");
        setEmail(stdin.nextLine());
        stdin.close();
    }
}
