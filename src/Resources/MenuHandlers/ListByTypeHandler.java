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
    Connection connection;
    

    public ListByTypeHandler(Connection connection) {
        this.connection = connection;
    }

    public void handle(Scanner stdin) {

        int operator;
        operator = Integer.parseInt(stdin.nextLine());


        switch (operator) {
            case 1: // Case Dog
                try {
                    rset = getAllTypes();
                    while (rset.next()) {

                        String ID_COLEIRA = rset.getString("ID_COLEIRA");
                        String NOME = rset.getString("NOME");
                        String CANIL = rset.getString("CANIL");
                        String TIPO = rset.getString("TIPO");
                        int IDADE = rset.getInt("IDADE");
                        String RACA = rset.getString("RACA");
                        String ISCASTRADO = rset.getString("ISCASTRADO");
                        String ISVERMIFUGADO = rset.getString("ISVERMIFUGADO");

                        Animal animal = new Animal(ID_COLEIRA, NOME, CANIL, TIPO, IDADE, RACA, ISCASTRADO, ISVERMIFUGADO);

                        System.out.println(animal.toString());

                    }
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
        }

    }

    public ResultSet getAllTypes() throws SQLException {
    
        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        query = "SELECT DISTINCT AN.ID_COLEIRA, AN.NOME, AN.CANIL, AN.IDADE , AN.TIPO, AN.RACA , AN.ISCASTRADO, AN.ISVERMIFUGADO FROM ANIMAL AN LEFT JOIN ADOCAO AD ON AN.ID_COLEIRA=AD.ANIMAL WHERE AN.ISCASTRADO = 'S' AND AN.ISVERMIFUGADO = 'S' AND AN.TIPO = 'CACHORRO' AND AN.ID_COLEIRA NOT IN (SELECT ANIMAL FROM ADOCAO)";
        rsetSearch = stmt.executeQuery(query);
        return rsetSearch;
    
    }


}