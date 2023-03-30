package BD;

import java.sql.*;

import modele.Gymnase;

public class CrudGymnase {

	private Connection connection;
	
	public CrudGymnase(Connection connection) {
		
		this.connection = connection;
	}
	
	public void create(Gymnase gymnase) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `gymnase`(`nom`, `lieux`) VALUES (?,?)");
        preparedStatement.setString(1, gymnase.getNom());
        preparedStatement.setString(2, gymnase.getLieux());
        preparedStatement.executeUpdate();
    }
	
}
