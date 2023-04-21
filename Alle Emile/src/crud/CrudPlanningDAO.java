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



}
