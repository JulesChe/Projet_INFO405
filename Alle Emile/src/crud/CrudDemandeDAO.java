package crud;

import java.sql.*;
import java.util.HashMap;
import java.sql.ResultSet;

import model.Creneau;

public class CrudDemandeDAO {

    private Connection connection;

    public CrudDemandeDAO(Connection connection) {

        this.connection = connection;
    }

    public void demande(Creneau c) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("INSERT INTO creneau (debut, fin, id_asso, id_gardien) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, c.getDateDebut());
        preparedStatement.setString(2, c.getDateFin());
        preparedStatement.setInt(3, 1);
        preparedStatement.setInt(4, 1);
        preparedStatement.executeUpdate();
    }

    public void accepter(int i) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM demande WHERE id = ?");
        pstmt.setInt(1, i);
        ResultSet rs = pstmt.executeQuery();
        HashMap<String, Object> rowData = null;
        if (rs.next()) {
            // récupérer les colonnes de la ligne dans les variables Java
            String col1 = rs.getString("debut");
            String col2 = rs.getString("fin");
            int col3 = rs.getInt("id_asso");
            int col4 = rs.getInt("id_gardien");
            // stocker les valeurs dans un objet Java (HashMap ici)
            rowData.put("col1", col1);
            rowData.put("col2", col2);
            rowData.put("col3", col3);
            rowData.put("col4", col4);
        }
        rs.close();

        PreparedStatement pstmt2 = connection.prepareStatement("INSERT INTO table_destination (col1, col2, col3, col4) VALUES (?, ?, ?, ?)");
// insérer les valeurs stockées dans l'objet Java dans la table de destination
        pstmt2.setString(1, rowData.get("col1").toString());
        pstmt2.setString(2, rowData.get("col2").toString());
        pstmt2.setInt(3, (int) rowData.get("col3"));
        pstmt2.setInt(4, (int) rowData.get("col4"));
        pstmt2.executeUpdate();
        pstmt2.close();
        connection.close();
    }

    public void supprimer (int i) throws SQLException{
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("DELETE FROM demande WHERE id = 'i'");
        preparedStatement.executeUpdate();
    }
}
