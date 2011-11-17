package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * helper class for the sqlite database via JDBC
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

public class SQLiteJDBCHelper {

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

		return connection;
	}

	/**
	 * 
	 * TODO tmy (16.11.2011): Insert javadoc for method helper.SQLiteJDBCHelper.initStatement.
	 * <p>
	 * </p>
	 * 
	 * @author ETHALON: tmy
	 * @param statement
	 * 
	 * @param connection
	 * @return Statement statement
	 */
	public static Statement initStatement(Statement statement, final Connection connection) {
		try {
			statement = connection.createStatement();

		} catch (final SQLException e) {
			e.printStackTrace();
		}

		return statement;

	}
}
