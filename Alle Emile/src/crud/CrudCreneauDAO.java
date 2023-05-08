package crud;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import model.Creneau;

public class CrudCreneauDAO {

    private Connection connection;

    public CrudCreneauDAO(Connection connection) {

        this.connection = connection;
    }

    public void create(Creneau c, int idAsso, String gymnase) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("INSERT INTO creneau (debut, fin, id_asso, id_gardien,nom_gymnase) VALUES (?, ?, ?, ?,?)");
        preparedStatement.setString(1, c.getDateDebut());
        preparedStatement.setString(2, c.getDateFin());
        preparedStatement.setInt(3, idAsso);
        preparedStatement.setInt(4, 1);
        preparedStatement.setString(5, gymnase);
        preparedStatement.executeUpdate();
    }

    public void supprimer (Creneau c) throws SQLException{

        String sql = "DELETE FROM creneau WHERE debut = ? AND fin = ?";
        PreparedStatement pstmt3 = connection.prepareStatement(sql);
        pstmt3.setString(1, c.getDateDebut());
        pstmt3.setString(2, c.getDateFin());
        pstmt3.executeUpdate();
    }

    public void modifier (Creneau c,String dateDebut, String dateFin) throws SQLException{

        String sql = "UPDATE `creneau` SET `debut`= ?,`fin`= ? WHERE `debut` = ? AND `fin` = ?";
        PreparedStatement pstmt3 = connection.prepareStatement(sql);
        pstmt3.setString(1, dateDebut);
        pstmt3.setString(2, dateFin);
        pstmt3.setString(3, c.getDateDebut());
        pstmt3.setString(4, c.getDateFin());
        pstmt3.executeUpdate();
    }


    public Map<Integer, List<Creneau>> getAllGardiensCreneaux() throws SQLException, ParseException {
        Map<Integer, List<Creneau>> result = new HashMap<>();
        String query = "SELECT * FROM creneau ORDER BY id_gardien";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int idGardien = resultSet.getInt("id_gardien");
            Creneau creneau = new Creneau();

            creneau.setId(resultSet.getInt("id"));
            creneau.setId_gardien(idGardien);




            //Mise au bon format des dates
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf1.parse(String.valueOf(resultSet.getTimestamp("debut")));
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