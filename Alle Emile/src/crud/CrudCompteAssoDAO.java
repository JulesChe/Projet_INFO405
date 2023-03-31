package crud;

import java.sql.*;


import model.Association;

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



}