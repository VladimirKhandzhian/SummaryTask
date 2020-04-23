package ua.nure.khandzhian.SummaryTask4.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.DelegatingCallableStatement;

public class DBManager {

	private static final Logger LOGGER = Logger.getLogger(DBManager.class);
	private static DBManager instance;
	private DataSource dataSource;
	
	
	public static synchronized DBManager getInstance() throws Exception {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public DBManager() throws Exception {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/airport");
			LOGGER.trace("Data source ==> " + dataSource);
		} catch (NamingException ex) {
			LOGGER.error("Connection exception");
			throw new Exception("Connection exception");
		}
	}
	
	public Connection getConnection() throws Exception {
		Connection connnection = null;
		try {
			connnection = dataSource.getConnection();
		} catch (SQLException ex) {
			LOGGER.error("Connection exception");
			throw new Exception("Connection exception");
		}
		return connnection;
	}
	
	public void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				LOGGER.error("Can not close connection", ex);
			}
		}
	}
	
	public void close(DelegatingCallableStatement cstmt) {
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException ex) {
				LOGGER.error("Can not close DelegatingCallableStatement", ex);
			}
		}
	}

	public void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException ex) {
				LOGGER.error("Can not close Statement", ex);
			}
		}
	}

	public void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				LOGGER.error("Can not close ResultSet", ex);
			}
		}
	}
	
	public void close(Connection connection, Statement statement, ResultSet resultSet) {
		close(resultSet);
		close(statement);
		close(connection);
	}

	public void close(Connection connection, DelegatingCallableStatement cstmt) {
		close(cstmt);
		close(connection);
	}
	
	public void rollback(Connection connection) {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				LOGGER.error("Cannot rollback transaction", ex);
			}
		}
	}
}