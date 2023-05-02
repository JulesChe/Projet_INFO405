package crud;

import java.sql.*;
import java.util.ArrayList;


import model.Association;
import model.Creneau;

public class CrudCompteAssoDAO {

    private Connection connection;

    public CrudCompteAssoDAO(Connection connection) {

        this.connection = connection;
    }

    public void create(Association association) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO compteAsso (nom,mdp) VALUES (?, ?)");
        preparedStatement.setString(1, association.getNom());
        preparedStatement.setString(2, association.getMdp());
        preparedStatement.executeUpdate();
    }

    public int selectId(String nom) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT `id` FROM `compteAsso` WHERE nom = ?");
        preparedStatement.setString(1, nom);

        ResultSet rs = preparedStatement.executeQuery();
        int res = -1;
        if(rs.next()) {
            res = rs.getInt("id");
        }

        return res;
    }


    public ArrayList<Association> getAssoFromTable(Connection connection) throws SQLException {
        ArrayList<Association> resultList = new ArrayList<>();

        String selectSQL = "SELECT `nom` FROM `compteAsso` WHERE 1";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Association association = new Association(rs.getString("nom"));
            resultList.add(association);
        }

        rs.close();
        preparedStatement.close();

        return resultList;
    }

}