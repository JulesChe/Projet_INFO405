package crud;

import model.Creneau;
import model.Gymnase;
import model.Utilisateur;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public ArrayList<Integer> getIdAllPerso() throws SQLException{
        // Création de la requête SQL
        String query = "SELECT id FROM comptePersonnel";

        // Création de l'objet Statement pour exécuter la requête
        Statement stmt = connection.createStatement();

        // Exécution de la requête
        ResultSet rs = stmt.executeQuery(query);

        // Parcours des résultats et ajout des identifiants à l'ArrayList
        ArrayList<Integer> ids = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            ids.add(id);
        }

        // Fermeture des ressources
        rs.close();
        stmt.close();
        connection.close();

        return ids;
    }

    public Map<Integer, ArrayList<Creneau>> getIndispo() throws SQLException, ParseException {
        Map<Integer, ArrayList<Creneau>> result = new HashMap<>();
        String query = "SELECT * FROM indisponibilite ORDER BY id_gardien";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int idGardien = resultSet.getInt("id_gardien");
            Creneau creneau = new Creneau();

            creneau.setId(resultSet.getInt("id"));
            creneau.setId_gardien(idGardien);




            //Mise au bon format des dates
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = sdf1.parse(String.valueOf(resultSet.getTimestamp("debut")));
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String newDateString = sdf2.format(date);

            creneau.setDateDebut(newDateString);


            //Mise au bon format des dates
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date2 = sdf3.parse(String.valueOf(resultSet.getTimestamp("fin")));
            SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String newDateString2 = sdf4.format(date2);

            creneau.setDateFin(newDateString2);


            result.computeIfAbsent(idGardien, k -> new ArrayList<>()).add(creneau);
        }

        resultSet.close();

        return result;
    }

}
