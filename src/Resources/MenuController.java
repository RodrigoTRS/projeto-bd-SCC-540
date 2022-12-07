package Resources;

public class MenuController {

    StringBuilder str;

    public MenuController() {
        this.str = new StringBuilder();
    }

    public void reset() {
        this.str.setLength(0);
    }

    public void divider() {
        this.str.append("---------------------------\n");
    }

    public void mainMenu() {
        this.reset();
        this.str.append("\n\n\n\n\n\n\n\n\n");
        this.divider();
        this.str.append("S - Sair\n");
        this.str.append("1 - Inserir\n");
        this.str.append("2 - Listar\n");
        this.divider();
        this.print();
    }

    public void insertMenu() {
        this.reset();
        this.divider();
        this.str.append("1 - Animal\n");
        this.divider();
        this.print();
    }

    public void listAllMenu() {
        this.reset();
        this.divider();
        this.str.append("1 - Animal\n");
        this.divider();
        this.print();
    }

    public void byTypeListAllMenu() {
        this.reset();
        this.divider();
        this.str.append("1 - Cachorro\n");
        this.divider();
        this.print();
    }


    public void print() {
        System.out.println(this.str.toString());
    }
}
