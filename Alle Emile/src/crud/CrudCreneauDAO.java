package crud;

import java.sql.*;


import model.Creneau;

public class CrudCreneauDAO {

    private Connection connection;

    public CrudCreneauDAO(Connection connection) {

        this.connection = connection;
    }

    public void create(Creneau c) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("INSERT INTO creneau (debut, fin, id_asso, id_gardien) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, c.getDateDebut());
        preparedStatement.setString(2, c.getDateFin());
        preparedStatement.setInt(3, 1);
        preparedStatement.setInt(4, 1);
        preparedStatement.executeUpdate();
    }
}