package crud;

import model.Gymnase;
import model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void setId(Utilisateur u) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM `comptePersonnel` WHERE nom = (?) AND prenom = (?) LIMIT 1");
        preparedStatement.setString(1, u.getNom());
        preparedStatement.setString(2, u.getPrenom());
        ResultSet rs = preparedStatement.executeQuery();
        u.setId(rs.getInt("id"));
    }

    public void ajouterHabilitation(Gymnase g, Utilisateur u) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `GymnaseComptePersonnel`(`id_gardien`, `nom_gymnase`) VALUES (?,?)");
        preparedStatement.setInt(1, u.getId());
        preparedStatement.setString(2, g.getNom());
        preparedStatement.executeUpdate();
    }

    public void supprimerHabilitation(Gymnase g, Utilisateur u) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `GymnaseComptePersonnel` WHERE id_gardien = (?) AND nom_gymnase = (?)");
        preparedStatement.setInt(1, u.getId());
        preparedStatement.setString(2, g.getNom());
        preparedStatement.executeUpdate();
    }



}
