package Models;

import java.sql.Connection;

interface Tuple {
    public void insert(Connection connection);
    public void insertOnDb(Connection connection);

    public void getInput();
}
