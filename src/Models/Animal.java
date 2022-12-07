package Models;

import Resources.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Animal implements Tuple
{
    String idColeira;
    String Nome;
    String Canil;
    String Tipo;
    int Idade;
    String Raca;
    String isCastrado;
    String isVermifugado;



    public Animal() {}
    public Animal(String idColeira, String nome, String canil, String tipo, int idade, String raca, String isCastrado, String isVermifugado) {
        this.idColeira = idColeira;
        this.Nome = nome;
        this.Canil = canil;
        this.Tipo = tipo;
        this.Idade = idade;
        this.Raca = raca;
        this.isCastrado = isCastrado;
        this.isVermifugado = isVermifugado;
    }
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

    public void setCanilInput(Scanner stdin) {
        String canil = stdin.nextLine();
        if (canil.length() > 50) {
            System.out.println("O nome do canil deve ser menor do que 50 caracteres;");
            setCanilInput(stdin);
        }
        canil = canil.replace(";", "_");
        canil = canil.replace("\'", "_");
        canil = canil.replace("--", "_");
        canil = canil.replace("/*", "_");
        canil = canil.replace("*/", "_");
        canil = canil.replace("xp_", "_");
        this.setCanil(canil);
    }

    public void setTipo(String tipo) {this.Tipo = tipo;}

    public void setTipoInput(Scanner stdin) {
        String tipo = stdin.nextLine();
        if (!Objects.equals(tipo, "CACHORRO") && !Objects.equals(tipo, "GATO")) {
            System.out.println("O tipo deve ser CACHORRO ou GATO;");
            setTipoInput(stdin);
        }
        tipo = tipo.replace(";", "_");
        tipo = tipo.replace("\'", "_");
        tipo = tipo.replace("--", "_");
        tipo = tipo.replace("/*", "_");
        tipo = tipo.replace("*/", "_");
        tipo = tipo.replace("xp_", "_");
        this.setTipo(tipo);
    }

    public void setIdade(int idade) {
        this.Idade = idade;
    }

    public void setIdadeInput(Scanner stdin) {
        String stringIdade = stdin.nextLine();
        int idade = Integer.parseInt(stringIdade);
        if ((idade < 0) || (idade >= 40)) {
            System.out.println("A idade deve ser positiva e inferior a 40 anos;");
            setIdadeInput(stdin);
        }
        this.setIdade(idade);
    }

    public void setRaca(String raca) {
        this.Raca = raca;
    }

    public void setRacaInput(Scanner stdin) {
        String raca = stdin.nextLine();
        if (raca.length() > 30) {
            System.out.println("O nome da raça deve ser menor do que 30 caracteres;");
            setRacaInput(stdin);
        }
        raca = raca.replace(";", "_");
        raca = raca.replace("\'", "_");
        raca = raca.replace("--", "_");
        raca = raca.replace("/*", "_");
        raca = raca.replace("*/", "_");
        raca = raca.replace("xp_", "_");
        this.setRaca(raca);
    }

    public void setIsCastrado(String isCastrado) {
        this.isCastrado = isCastrado;
    }

    public void setIsCastradoInput(Scanner stdin) {
        String isCastrado = stdin.nextLine();
        if (!Objects.equals(isCastrado, "S") && !Objects.equals(isCastrado, "N")) {
            System.out.println("Essa informações deve ser igual a S ou N;");
            setIsCastradoInput(stdin);
        }
        isCastrado = isCastrado.replace(";", "_");
        isCastrado = isCastrado.replace("\'", "_");
        this.setIsCastrado(isCastrado);
    }

    public void setIsVermifugado(String isVermifugado) {
        this.isVermifugado = isVermifugado;
    }

    public void setIsVermifugadoInput(Scanner stdin) {
        String isVermifugado = stdin.nextLine();
        if (!Objects.equals(isVermifugado, "S") && !Objects.equals(isVermifugado, "N")) {
            System.out.println("Essa informações deve ser igual a S ou N;");
            setIsVermifugadoInput(stdin);
        }
        isVermifugado = isVermifugado.replace(";", "_");
        isVermifugado = isVermifugado.replace("\'", "_");
        this.setIsVermifugado(isVermifugado);
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
                System.out.println("Um animal foi inserido!");
            }
            statement.close();
        } catch (SQLException e) {
            StringBuilder error = new StringBuilder();
            error.append("\nCodigo de erro: ");
            error.append(e.getErrorCode());
            error.append("\nMensagem de erro: ");
            error.append(e.getMessage());
            System.out.println(error);
            // throw new RuntimeException(e);
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
        System.out.println("Raça do animal:");
        setRacaInput(stdin);
        System.out.println("Animal castrado? (S ou N):");
        setIsCastradoInput(stdin);
        System.out.println("Animal vermifugado? (S ou N):");
        setIsVermifugadoInput(stdin);
    }


    public String toString() {
        String str =
            "\nID: " + this.idColeira +
            "\n  NOME: " + this.Nome +
            "\n  CANIL: " + this.Canil +
            "\n  TIPO: " + this.Tipo +
            "\n  IDADE: " + this.Idade +
            "\n  RACA: " + this.Raca +
            "\n  CASTRADO? " + this.isCastrado +
            "\n  VERMIFUGADO? " + this.isVermifugado;
        return str;
    }
}

