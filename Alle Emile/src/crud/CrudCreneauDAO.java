package crud;

import java.sql.*;


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
}