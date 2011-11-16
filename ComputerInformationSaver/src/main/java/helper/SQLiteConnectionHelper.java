package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * helper class for the database connection via JDBC
 * <p>
 * </p>
 * 
 * 
 * <br>
 * &nbsp;
 * 
 * @author ETHALON: tmy
 * 
 */

public class SQLiteConnectionHelper {

	/**
	 * 
	 * closes a given connection
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param connection
	 * @return Connection connection
	 */
	public static Connection closeConnection(final Connection connection) {
		try {
			connection.close();

		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 
	 * inits a given connection
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * 
	 * @param connection
	 * @return Connection connection
	 */
	public static Connection initConnection(Connection connection) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:ComputerDB");

		} catch (final ClassNotFoundException e) {
			e.printStackTrace();

		} catch (final SQLException e) {
			e.printStackTrace();
		}

		// TODO: implement me
		return connection;
	}
}
