package pds.r3.charfouni.BDDConnections;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Connection to the source database that contains operational data
 */
public class Source {
	
	//Specifying the parameters connection to the source DB
	
	private static final String DB_DRIVER = "com.mysql.jdbc.driver";
	private static final String DB_CONNECTION = "mysql://localhost:3306/src_appointments";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private Connection dbConnection = null;
	private PreparedStatement preparedStatement = null;
	public void setPreparedStatement(PreparedStatement p) {
		this.preparedStatement = p;}
	public void setConnection(Connection c) {
		this.dbConnection = c;
	}

	
	public boolean executeThisUpdateQuery(String query) throws Exceptions {
		try {
			dbConnection = getConnection();
			dbConnection.setAutoCommit(false);
			preparedStatement = dbConnection.prepareStatement(query);
			preparedStatement.executeUpdate();
			dbConnection.commit();
		} catch (SQLException e) {
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
			throw new Exceptions(e);

		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	/**
	 The select query
	 */
	public ResultSet executeThisSelectQuery(String query)
	
		throws Exceptions {
		
		List<List<String>> result = new ArrayList<List<String>>(); 
		
		ResultSet resultset;														

		try {
			dbConnection = getConnection();
			dbConnection.setAutoCommit(false);
			resultset = dbConnection.createStatement().executeQuery(query);
			ResultSetMetaData metadata = resultset.getMetaData();
			int cols = metadata.getColumnCount();
			return resultset;

		} catch (SQLException e) {

			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new Exceptions(e);

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Connection getConnection() throws SQLException {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e);

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e);
			throw new SQLException(e);

		}

	}
}
