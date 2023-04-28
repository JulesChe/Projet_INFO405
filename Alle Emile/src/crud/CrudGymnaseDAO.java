package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Association;
import model.Creneau;
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

    public ArrayList<Gymnase> getGymnase() throws SQLException {
        ArrayList<Gymnase> resultList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `gymnase`");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Gymnase gym = new Gymnase(rs.getString("nom"),rs.getString("lieux"));
            resultList.add(gym);
        }

        rs.close();
        preparedStatement.close();

        return resultList;
    }

}