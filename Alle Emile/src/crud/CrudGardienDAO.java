package crud;

import java.sql.*;
import java.util.ArrayList;


import model.Association;
import model.Creneau;

public class CrudGardienDAO {

    private Connection connection;

    public CrudGardienDAO(Connection connection) {

        this.connection = connection;
    }

    public int selectId(String nom,String prenom) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT `id` FROM `comptePersonnel` WHERE nom = ? AND prenom = ?");
        preparedStatement.setString(1, nom);
        preparedStatement.setString(2, prenom);

        ResultSet rs = preparedStatement.executeQuery();
        int res = -1;
        if(rs.next()) {
            res = rs.getInt("id");
        }

        return res;
    }

    public ArrayList<Creneau> getIndispo(int id) throws SQLException {
        ArrayList<Creneau> resultList = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `indisponibilite` WHERE id_gardien = ?");
        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Creneau creneau = new Creneau(rs.getString("debut"),rs.getString("fin"),rs.getString("motif"));
            resultList.add(creneau);
        }

        rs.close();
        preparedStatement.close();

        return resultList;
    }



}