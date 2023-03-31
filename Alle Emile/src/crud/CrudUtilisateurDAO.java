package crud;

import model.Gymnase;
import model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtilisateurDAO {

    private Connection connection;

    public CrudUtilisateurDAO(Connection connection) {

        this.connection = connection;
    }

    public void create(Utilisateur u) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comptePersonnel (nom,prenom,mdp,grade) VALUES (?, ?,?,?)");
        preparedStatement.setString(1, u.getNom());
        preparedStatement.setString(2, u.getPrenom());
        preparedStatement.setString(3, u.getMdp());
        preparedStatement.setInt(4, u.getNiveau());
        preparedStatement.executeUpdate();
    }
}
