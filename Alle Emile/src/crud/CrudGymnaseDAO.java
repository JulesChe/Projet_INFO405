package crud;

import java.sql.*;

import model.Association;
import model.Gymnase;

public class CrudGymnaseDAO {

    private Connection connection;

    public CrudGymnaseDAO(Connection connection) {

        this.connection = connection;
    }

    public void create(Gymnase g) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO gymnase (nom,lieux) VALUES (?, ?)");
        preparedStatement.setString(1, g.getNom());
        preparedStatement.setString(2, g.getLieux());
        preparedStatement.executeUpdate();
    }

}