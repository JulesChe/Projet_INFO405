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
        ArrayList<Creneau> resultList = new ArrayList<>();
        String debutLundi = lundi+ " 00:00";
        Creneau c = new Creneau(debutLundi,debutLundi);
        String finLundi = c.ajouter7Jours(lundi)+" 00:00";

        String selectSQL = "SELECT demande.id, demande.debut, demande.fin, compteAsso.nom AS nom_asso FROM demande JOIN compteAsso ON demande.id_asso = compteAsso.id WHERE demande.fin >= ? AND demande.fin <= ?";

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



}
