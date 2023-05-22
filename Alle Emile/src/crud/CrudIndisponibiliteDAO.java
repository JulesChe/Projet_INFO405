package crud;

import model.Gardien;
import model.Gymnase;
import model.Indisponibilite;
import model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;

public class CrudIndisponibiliteDAO {

    private Connection connection;

    public CrudIndisponibiliteDAO(Connection connection) {
        this.connection = connection;
    }

    public void demanderIndispo(Indisponibilite indispo) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `indisponibilite`(`motif`, `debut`, `fin`, `id_gardien`) VALUES (?,?,?,?,0)");
        preparedStatement.setString(1, indispo.getMotif());
        preparedStatement.setString(2, String.valueOf(indispo.getDebut()));
        preparedStatement.setString(3, String.valueOf(indispo.getFin()));
        preparedStatement.setInt(4, indispo.getGardien());
        preparedStatement.executeUpdate();
    }

    public void setId(Indisponibilite indispo) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM `indisponibilite` WHERE debut = (?) AND fin = (?) AND id_gardien = (?) LIMIT 1");
        preparedStatement.setString(1, String.valueOf(indispo.getDebut()));
        preparedStatement.setString(2, String.valueOf(indispo.getFin()));
        preparedStatement.setInt(3, indispo.getGardien());
        ResultSet rs = preparedStatement.executeQuery();
        indispo.setId(rs.getInt("id"));
    }

    public void modifierIndispo(Indisponibilite ancienIndispo, Indisponibilite nouvIndispo) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `indisponibilite` SET `motif`=(?),`debut`=(?),`fin`=(?) WHERE debut = (?) AND fin = (?) AND id_gardien = (?)");
        preparedStatement.setString(1, ancienIndispo.getMotif());
        preparedStatement.setString(2, String.valueOf(ancienIndispo.getDebut()));
        preparedStatement.setString(3, String.valueOf(ancienIndispo.getFin()));
        preparedStatement.setString(4, String.valueOf(nouvIndispo.getDebut()));
        preparedStatement.setString(5, String.valueOf(nouvIndispo.getFin()));
        preparedStatement.setInt(6, nouvIndispo.getGardien());
        preparedStatement.executeUpdate();
    }

    public ArrayList<Indisponibilite> getIndispos() throws SQLException {
        ArrayList<Indisponibilite> resultList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `indisponibilite`");
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Indisponibilite indispo = new Indisponibilite(rs.getString("motif"),rs.getDate("debut"),rs.getDate("fin"),rs.getInt("id_gardien"));
            indispo.setId(rs.getInt("id"));
            resultList.add(indispo);
        }

        rs.close();
        preparedStatement.close();

        return resultList;
    }

    public void ValiderIndispo(Indisponibilite indispo) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `indisponibilite` SET `valide`= 1 WHERE debut = (?) AND fin = (?) AND id_gardien = (?)");
        preparedStatement.setString(1, indispo.getMotif());
        preparedStatement.setString(2, String.valueOf(indispo.getDebut()));
        preparedStatement.setString(3, String.valueOf(indispo.getFin()));
        preparedStatement.executeUpdate();
    }

    public void RefuserIndispo(Indisponibilite indispo) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `indisponibilite` WHERE debut = (?) AND fin = (?) AND id_gardien = (?)");
        preparedStatement.setString(1, indispo.getMotif());
        preparedStatement.setString(2, String.valueOf(indispo.getDebut()));
        preparedStatement.setString(3, String.valueOf(indispo.getFin()));
        preparedStatement.executeUpdate();
    }

    public ArrayList<Indisponibilite> GetIndiposGardien(Gardien g) throws SQLException {
        ArrayList<Indisponibilite> resultList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `indisponibilite` WHERE `id_gardien` = (?)");
        preparedStatement.setInt(1, g.getID());
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Indisponibilite indispo = new Indisponibilite(rs.getString("motif"),rs.getDate("debut"),rs.getDate("fin"),rs.getInt("id_gardien"));
            indispo.setId(rs.getInt("id"));
            resultList.add(indispo);
        }

        rs.close();
        preparedStatement.close();

        return resultList;
    }
    }


