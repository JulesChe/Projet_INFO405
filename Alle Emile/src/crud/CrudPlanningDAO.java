package crud;

import java.sql.*;
import java.util.ArrayList;

import model.Creneau;
import model.Planning;

public class CrudPlanningDAO {
    private Connection connection;

    public CrudPlanningDAO(Connection connection) {

        this.connection = connection;
    }

    public ArrayList<Creneau> getCreneauxFromTable(Connection connection, String tableName) throws SQLException {
        ArrayList<Creneau> resultList = new ArrayList<>();

        String selectSQL = "SELECT demande.id, demande.debut, demande.fin, compteAsso.nom AS nom_asso FROM demande JOIN compteAsso ON demande.id_asso = compteAsso.id";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Creneau creneau = new Creneau(rs.getString("debut"),rs.getString("fin"),rs.getString("nom_asso"));
            resultList.add(creneau);
        }

        rs.close();
        preparedStatement.close();

        return resultList;
    }

    public ArrayList<Creneau> getSemaine(Connection connection, String lundi) throws SQLException {
        // Récupère tous les créneaux d'une semaine en fonction du lundi donné
        ArrayList<Creneau> resultList = new ArrayList<>();
        String debutLundi = lundi+ " 00:00";
        Creneau c = new Creneau(debutLundi,debutLundi);
        String finLundi = c.ajouter7Jours(lundi)+" 00:00";

        String selectSQL = "SELECT creneau.id, creneau.debut, creneau.fin, compteAsso.nom AS nom_asso FROM creneau JOIN compteAsso ON creneau.id_asso = compteAsso.id WHERE creneau.fin >= ? AND creneau.fin <= ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, debutLundi);
        preparedStatement.setString(2, finLundi);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Creneau creneau = new Creneau(rs.getString("debut"),rs.getString("fin"),rs.getString("nom_asso"));
            resultList.add(creneau);
        }

        rs.close();
        preparedStatement.close();

        return resultList;
    }

    public ArrayList<Creneau> getSemaineGymnase(Connection connection, String lundi, String gymnaseNom) throws SQLException {
        // Récupère tous les créneaux d'une semaine en fonction du lundi donné et de l'ID du gymnase
        ArrayList<Creneau> resultList = new ArrayList<>();
        String debutLundi = lundi + " 00:00";
        Creneau c = new Creneau(debutLundi, debutLundi);
        String finLundi = c.ajouter7Jours(lundi) + " 00:00";

        String selectSQL = "SELECT creneau.id, creneau.debut, creneau.fin, compteAsso.nom AS nom_asso FROM creneau JOIN compteAsso ON creneau.id_asso = compteAsso.id WHERE creneau.fin >= ? AND creneau.fin <= ? AND creneau.nom_gymnase = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
        preparedStatement.setString(1, debutLundi);
        preparedStatement.setString(2, finLundi);
        preparedStatement.setString(3, gymnaseNom);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Creneau creneau = new Creneau(rs.getString("debut"), rs.getString("fin"), rs.getString("nom_asso"));
            resultList.add(creneau);
        }

        rs.close();
        preparedStatement.close();

        return resultList;
    }




}
