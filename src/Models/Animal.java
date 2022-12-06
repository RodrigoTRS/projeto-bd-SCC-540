package Models;

import Resources.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Animal implements Tuple
{


    int idColeira;
    String Nome;
    String Canil;
    String Tipo;
    int Idade;
    String Raca;
    String isCastrado;
    String isVermifugado;



    public Animal() {}

    public void setNome(String nome) {
        this.Nome = nome;
    }
    public void setNomeInput(Scanner stdin) {
        String nome = stdin.nextLine();
        if (nome.length() > 50) {
            System.out.println("O nome deve ser menor do que 50 caracteres;");
            setNomeInput(stdin);
        }
        nome = nome.replace(";", "_");
        nome = nome.replace("\'", "_");
        nome = nome.replace("--", "_");
        nome = nome.replace("/*", "_");
        nome = nome.replace("*/", "_");
        nome = nome.replace("xp_", "_");
        this.setNome(nome);
    }

    public void setCanil(String canil) {
        this.Canil = canil;
    }

    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }

    public void setIdade(int idade) {
        this.Idade = idade;
    }

    public void setRaca(String raca) {
        this.Raca = raca;
    }

    public void setIsCastrado(String isCastrado) {
        this.isCastrado = isCastrado;
    }

    public void setIsVermifugado(String isVermifugado) {
        this.isVermifugado = isVermifugado;
    }

    @Override
    public void insert(Controller controller) {
        this.getInput(controller.stdin);
        this.insertOnDb(controller.db.connection);
    }

    @Override
    public void insertOnDb(Connection connection) {
        String sql =
                "INSERT INTO ANIMAL (NOME, CANIL, TIPO, IDADE, RACA, ISCASTRADO, ISVERMIFUGADO) " +
                "VALUES " + '(' + "\'" +
                this.Nome +
                "\', \'" +
                this.Canil +
                "\', \'" +
                this.Tipo +
                "\', \'" +
                this.Idade +
                "\', \'" +
                this.Raca +
                "\', \'" +
                this.isCastrado +
                "\', \'" +
                this.isVermifugado +
                "\'" + ')';

        try {
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0) {
                System.out.println("An animal has been inserted!");
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getInput(Scanner stdin) {
        System.out.println("Nome do animal:");
        setNomeInput(stdin);
        System.out.println("Canil do animal:");
        setCanilInput(stdin);
        System.out.println("Tipo do animal:");
        setTipoInput(stdin);
        System.out.println("Idade do animal:");
        setIdadeInput(stdin);
    }

    public String getStringInput()

    public String toString() {
        String str =
            "\nID: " + this.id +
            "\nNAME: " + this.name +
            "\nEMAIL: " + this.email;
        return str;
    }
}

