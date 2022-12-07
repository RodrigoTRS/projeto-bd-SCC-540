package Resources.MenuHandlers;

import Models.Animal;
import Resources.Controller;

import java.sql.*;
import java.util.Scanner;

public class ListByTypeHandler {
    Controller controller;
    Statement stmt;
    ResultSet rset;
    ResultSet rsetSearch;
    String query;
    String sqlString;

    public void handle(Scanner stdin, Connection connection) {

        int operator;
        operator = Integer.parseInt(stdin.nextLine());


        switch (operator) {
            case 1: // Case Dog
                rset = getAllTypes(connection);

                break;
        }

    }

    public ResultSet getAllTypes(Connection connection) throws SQLException {
    
        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        query = "SELECT DISTINCT AN.ID_COLEIRA, AN.NOME, AN.CANIL, AN.IDADE FROM ANIMAL AN LEFT JOIN ADOCAO AD ON AN.ID_COLEIRA=AD.ANIMAL WHERE AN.ISCASTRADO = 'S' AND AN.ISVERMIFUGADO = 'S' AND AN.TIPO = 'CACHORRO' AND AN.ID_COLEIRA NOT IN (SELECT ANIMAL FROM ADOCAO);";
        System.out.println("\nExecuting query: " + query);
        rsetSearch = stmt.executeQuery(query);
        return rsetSearch;
    
    }


}